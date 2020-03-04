package Heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge K sorted interval lists into one sorted interval list.
 * You need to merge overlapping intervals too.
 *
 * Example1
 * Input: [
 *   [(1,3),(4,7),(6,8)],
 *   [(1,2),(9,10)]
 * ]
 * Output: [(1,3),(4,8),(9,10)]
 *
 * Example2
 * Input: [
 *   [(1,2),(5,6)],
 *   [(3,4),(7,8)]
 * ]
 * Output: [(1,2),(3,4),(5,6),(7,8)]
 *
 * 复杂度：
 * 假设总共有N个整数，一共K个数组
 * 时间复杂度 O(NlogK)
 * 空间复杂度 O(K)
 */
public class LiC577MergeKSortedIntervalLists {

    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        int k = intervals.size();
        PriorityQueue<Pair> queue = new PriorityQueue<Pair>(
                k,
                new Comparator<Pair>() {
                    public int compare(Pair e1, Pair e2) {
                        return intervals.get(e1.row).get(e1.col).start -
                                intervals.get(e2.row).get(e2.col).start;
                    }
                }
        );

        for (int i = 0; i < intervals.size(); i ++) {
            if (intervals.get(i).size() > 0) {
                queue.add(new Pair(i, 0));
            }
        }

        List<Interval> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            result.add(intervals.get(pair.row).get(pair.col));
            pair.col++;
            if (pair.col < intervals.get(pair.row).size()) {
                queue.add(pair);
            }
        }

        return merge(result);
    }

    private List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return intervals;
        }

        List<Interval> result = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval interval : intervals) {
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            } else {
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        // kickoff the last interval
        result.add(new Interval(start, end));

        return result;
    }

    class Pair {
        int row, col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
