package Airbnb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * Implement an iterator to flatten a 2d vector.
 *
 * Example:
 *
 * Input: 2d vector =
 * [
 *   [1,2],
 *   [3],
 *   [4,5,6]
 * ]
 * Output: [1,2,3,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,2,3,4,5,6].
 * Follow up:
 * As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
public class Vector2D implements Iterator<Integer> {

    private Iterator<List<Integer>> rowIter;
    private Iterator<Integer> colIter;

    public Vector2D(List<List<Integer>> vec2d) {
        rowIter = vec2d.iterator();
        colIter = Collections.emptyIterator();
    }

    @Override
    public Integer next() {
        return colIter.next();
    }

    @Override
    public boolean hasNext() {
        while ((colIter == null || !colIter.hasNext() && rowIter.hasNext())) {
            colIter = rowIter.next().iterator();
        }
        return colIter.hasNext();
    }

    @Override
    public void remove() {
        while(colIter == null && rowIter.hasNext()) {
            colIter = rowIter.next().iterator();
        }
        if (colIter != null) {
            colIter.remove();
        }
    }

    public static void main(String[] args) {

        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        lists.add(new ArrayList<>(list));
        list.add(3);
        lists.add(new ArrayList<>(list));
        list.add(4);
        list.add(5);
        list.add(6);
        lists.add(new ArrayList<>(list));

        Vector2D vector2D = new Vector2D(lists);
        while(vector2D.hasNext()) {
            System.out.println(vector2D.next());
            vector2D.remove();
        }

    }
}
