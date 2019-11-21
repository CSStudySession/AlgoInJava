package Airbnb;


import java.util.*;

/**
 * Implement a queue with a number of arrays, in which each array has fixed size.
 */
public class ArrayQueue<T> {
    int size, head, i, hi;
    List<List<T>> lists;

    public ArrayQueue(int size) {
        this.size = size <= 0 ? 5 : size;
        this.head = 0;
        this.i = size-1;
        this.hi = 0;
        lists = new ArrayList<>();
        lists.add(new ArrayList<>());
    }

    public T peek() {
        if (hi >= size || hi >= lists.get(head).size()) {
            head++;
            hi = 0;
        }
        if (head >= lists.size()) {
            return null;
        } else {
            return lists.get(head).get(hi);
        }
    }

    public T poll() {
        if (hi >= size || hi >= lists.get(head).size()) {
            lists.get(head).clear();
            head++;
            hi = 0;
        }

        if (head >= lists.size()) {
            return null;
        } else {
            return lists.get(head).get(hi++);
        }
    }

    public void offer(T object) {
        if (i >= size) {
            lists.add(new ArrayList<>());
            i = 0;
        }
        lists.get(lists.size()-1).add(object);
        ++i;
    }

    public static void main(String[] agrs) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(3);

        for (int i = 0; i < 10; i++) {
            arrayQueue.offer(i);
        }

        while(arrayQueue.peek() != null) {
            System.out.println(arrayQueue.poll());
        }

    }
}
