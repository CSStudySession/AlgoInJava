package Heap;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right.
 * You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position. Return the max sliding window.
 *
 * Example:
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 *
 * Follow up:
 * Could you solve it in linear time?
 */
public class LC239SlidingWindowMaximum {

    /*
    复杂度
    时间:O(nlogK) 空间:O(k)

    思路：heap
    维护一个大小为k的treeSet,依次将原数组数据入堆,每次堆顶的元素即为窗口中的最大值.
    注意当k == nums.length的情况：当pq.size() == k时就要更新结果数组
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};

        TreeSet<Point> heap = new TreeSet<>((a, b) ->
                (b.val != a.val ? b.val - a.val : b.idx - a.idx));

        int[] ret = new int[nums.length-k+1];
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            heap.add(new Point(nums[i], i));
            if (heap.size() == k) {
                ret[idx++] = heap.first().val;
                heap.remove(new Point(nums[i-k+1], i-k+1));
            }
        }
        return ret;
    }

    public class Point {
        int val;
        int idx;
        public Point(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

}
