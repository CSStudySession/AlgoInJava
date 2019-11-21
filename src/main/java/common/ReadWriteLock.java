package common;


import java.util.HashMap;
import java.util.Map;

/**
 * ReaderThread: if no threads are writing, and no threads have requested writeRequest, then it can proceed
 * WriteThread: if no threads are writing or reading, then it can proceed
 */
public class ReadWriteLock {
    // version 1, 2, 3 variable
    private int writers = 0;
    // this variable is meant to avoid starvation, up-prioritize writer thread.
    // Because if threads are constantly getting read access, writer thread can be blocked in a long time so starvation happens.
    // version 1, 2, 3 variable
    private int writeRequest = 0;
    // version 1 variable
    private int readers = 0;
    // version 2 variable
    private Thread writingThread = null;
    // version 2 variable
    private Map<Thread, Integer> readingThreads = new HashMap<>();



    /*
     * Why calling notifyAll()??
     * 1. Avoid DeadLock. For example, if a readThread is being unlocked, and it awakes another readerThread, nothing more happens, so deadLock
     *
     *
     * Advantage:
     * 1. notifyAll() can awake all threads so that each thread can get the desired access
     * 2. if multiple readerThreads are waiting for unlockWrite(), once unlockWrite() is called, all readerThreads can have read access
     */


    // Version 1, not reentrant

    /** Problems:
     * 1. DeadLock
     *  a. a reader thread
     *  b. a writer thread
     * 2. ReaderThread cannot be a writerThread
     */

    public synchronized void lockRead1() throws InterruptedException {
        while (writers > 0 || writeRequest > 0) {
            wait();
        }

        readers++;
    }

    public synchronized void unlockRead1() throws InterruptedException {
        readers--;
        notifyAll();
    }

    public synchronized void lockWrite1() throws InterruptedException {
        writeRequest++;
        while (readers > 0 || writers > 0) {
            wait();
        }
        writeRequest--;
        writers++;
    }

    public synchronized void unLockWrite1() throws InterruptedException {
        writers--;
        notifyAll();
    }


    //Version 2, reentrant reader to reader, writer to writer
    /**
     * Reentrant reader thread rules:
     * A thread is granted read reentrant if it can get read access (no writes or write request), or if it already has read access (regardless of write requests)
     */

    private boolean canGrantReaderToReaderAccess(Thread callingThread) {
        if (writers > 0) return false;
        if (readingThreads.get(callingThread) != null) return true;
        return writeRequest <= 0;
    }

    public synchronized void lockRead2() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReaderToReaderAccess(callingThread)) {
            wait();
        }
        readingThreads.put(callingThread, readingThreads.getOrDefault(callingThread, 0) + 1);
    }

    public synchronized void unLockRead2() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        int accessToken = readingThreads.get(callingThread);
        if (accessToken <= 1) readingThreads.remove(callingThread);
        else {readingThreads.put(callingThread, accessToken-1);}
        notifyAll();
    }

    /**
     * Reentrant writer thread rule:
     * A thread is granted write reentrant only if the thread has already write access.
     */

    private boolean canGrantWriterToWriterAccess(Thread callingThread) {
        if (readingThreads.size() > 0) return false;
        if(writingThread == null) return true;
        if (callingThread != writingThread) return false;
        return true;
    }

    public synchronized void lockWrite2() throws InterruptedException {
        writeRequest++;
        Thread callingThread = Thread.currentThread();
        while (!canGrantWriterToWriterAccess(callingThread)) {
            wait();
        }
        writeRequest--;
        writers++;
        writingThread = callingThread;
    }

    public synchronized void unlockWrite2() throws InterruptedException {
        writers--;
        if (writers == 0) writingThread = null;
        notifyAll();
    }


    // Version 3, reader to writer reentrant, writer to reader reentrant

    private boolean isWritter(Thread callingThread) {
        return callingThread == writingThread;
    }

    private boolean isReader(Thread callingThread) {
        return readingThreads.get(callingThread) != null;
    }

    private boolean isOnlyReader(Thread callingThread) {
        return readingThreads.size() == 1 && readingThreads.get(callingThread) != null;
    }

    /**
     * Rule:
     * 1. if callingThread is the only writing thread
     * 2. if callingThread is a reader
     * 3. if no write requests
     * @param callingThread
     * @return
     */
    private boolean canGrantReaderToWriterAccess(Thread callingThread) {
        if (isWritter(callingThread)) return true;
        if (writingThread != null) return false;
        if (isReader(callingThread)) return true;
        return writeRequest <= 0;
    }

    /**
     * Rule:
     * 1. if callingThread is the only reader
     * 2. if callingThread is the only writingThread
     * @param callingThread
     * @return
     */
    private boolean canGrantWriterToReaderAccess(Thread callingThread) {
        if (isOnlyReader(callingThread)) return true;
        if (readingThreads.size() > 0) return false;
        if (writingThread == null) return true;
        return isWritter(callingThread);
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequest++;
        Thread callingThread = Thread.currentThread();
        while (!canGrantWriterToReaderAccess(callingThread)) {
            wait();
        }
        writeRequest--;
        writers++;
        writingThread = callingThread;
    }

    public synchronized void unlockWrite() throws InterruptedException {
        writers--;
        if (writers == 0) {
            writingThread = null;
        }
        notifyAll();
    }

    public synchronized void lockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReaderToWriterAccess(callingThread)) {
            wait();
        }
        readingThreads.put(callingThread, readingThreads.getOrDefault(callingThread, 0)+1);
    }

    public synchronized void unlockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        Integer accessToken = readingThreads.get(callingThread);
        if (accessToken == null) {
            throw new IllegalMonitorStateException("Calling thread does not hold a read lock on this ReadWriteLock");
        }
        if (accessToken == 1) {readingThreads.remove(callingThread);}
        else {readingThreads.put(callingThread, accessToken-1);}
        notifyAll();
    }

}
