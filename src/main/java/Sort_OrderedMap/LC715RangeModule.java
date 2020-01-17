package Sort_OrderedMap;

import java.util.TreeMap;

/**
 * A Range Module is a module that tracks ranges of numbers.
 * Your task is to design and implement the following interfaces in an efficient manner.
 *
 * addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval.
 * Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right)
 * that are not already tracked.
 *
 * queryRange(int left, int right) Returns true if and only if every real number in the interval [left, right)
 * is currently being tracked.
 *
 * removeRange(int left, int right) Stops tracking every real number currently being tracked in the interval [left, right).
 *
 * Example 1:
 * addRange(10, 20): null
 * removeRange(14, 16): null
 * queryRange(10, 14): true (Every number in [10, 14) is being tracked)
 * queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
 * queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite the remove operation)
 *
 * Note:
 * A half open interval [left, right) denotes all real numbers left <= x < right.
 * 0 < left < right < 10^9 in all calls to addRange, queryRange, removeRange.
 * The total number of calls to addRange in a single test case is at most 1000.
 * The total number of calls to queryRange in a single test case is at most 5000.
 * The total number of calls to removeRange in a single test case is at most 1000.
 */
public class LC715RangeModule {

    private TreeMap<Integer, Integer> tm;

    public LC715RangeModule() {
        tm = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Integer lowerBound = tm.floorKey(left);
        Integer upperBound = tm.floorKey(right);

        if (lowerBound != null && tm.get(lowerBound) >= left) {
            left = lowerBound;
        }

        if (upperBound != null) {
            right = Math.max(tm.get(upperBound), right);
            removeIntervals(left+1, upperBound);
        }

        tm.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Integer lb = tm.lowerKey(left);
        if (lb != null && tm.get(lb) >= right) return true;
        return false;
    }

    public void removeRange(int left, int right) {
        Integer lowerBound = tm.lowerKey(left);
        if (lowerBound != null && tm.get(lowerBound) > left) {
            if (tm.get(lowerBound) > right) {
                tm.put(right, tm.get(lowerBound));
                tm.put(lowerBound, left);
                return;
            } else {
                tm.put(lowerBound, left);
            }
        }
        Integer upperBound = tm.lowerKey(right);
        if (upperBound != null && tm.get(upperBound) > right) {
            tm.put(right, tm.get(upperBound));
        }
        removeIntervals(left, right-1);
    }

    private void removeIntervals(int left, int right) {
        if (tm.ceilingKey(left) == null) return;
        Integer nextLeft = tm.ceilingKey(left);
        while (nextLeft != null && nextLeft <= right) {
            tm.remove(nextLeft);
            nextLeft = tm.higherKey(nextLeft);
        }
    }

    public static void main(String[] args) {
        LC715RangeModule obj = new LC715RangeModule();
        obj.addRange(10,20);
        obj.removeRange(14, 16);
        boolean ret1, ret2, ret3;
        ret1 = obj.queryRange(10, 14);
        ret2 = obj.queryRange(13,15);
        ret3 = obj.queryRange(16,17);
        System.out.println(".");
    }
}
