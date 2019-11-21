package Dropbox;

public class DesignPhoneDirectory {

    boolean[] visited;
    int i;

    public DesignPhoneDirectory(int maxNumbers) {
        visited = new boolean[maxNumbers];
        i = 0;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int j = i;
        while (j < visited.length && visited[j]) {
            ++j;
        }

        if (j == visited.length) {
            j = 0;
            while (j != i && visited[j]) {
                ++j;
            }

            if (j == i) {
                return -1;
            }
        }
        visited[j] = true;
        return j;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !visited[number];
    }

    /** Recycle or release a number. */
    public void release(int number) {
        visited[number] = false;
    }

}


/**
 * Optimization
 */

class PhoneDirectory {


    int[] next;
    int pos;

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        next = new int[maxNumbers];
        for (int i = 0; i < maxNumbers; i++) {
            next[i] = (i + 1) % maxNumbers;
        }
        pos = 0;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (next[pos] == -1) return -1;
        int ret = pos;
        pos = next[pos];
        next[ret] = -1;
        return ret;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return next[number] != -1;
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (next[number] != -1) return;
        next[number] = pos;
        pos = number;
    }
}
