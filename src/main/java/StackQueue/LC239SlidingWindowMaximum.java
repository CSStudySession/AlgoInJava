package StackQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k numbers in the window.
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

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];

        for (int i = 0; i < k - 1; i++) {
            inQueue(nums, deque, i);
        }

        int pos = 0;
        for (int i = k - 1; i < nums.length; i++) {
            inQueue(nums, deque, i);
            // 注意这里是入队元素 nums[xxx]
            res[pos++] = nums[deque.peekFirst()];
            outQueue(deque, i - k + 1);
        }

        return res;
    }

    private void outQueue(Deque<Integer> deque, int index) {
        while (!deque.isEmpty() && deque.peekFirst() <= index) {
            deque.pollFirst();
        }
    }

    private void inQueue(int[] nums, Deque<Integer> deque, int index) {
        while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[index]) {
            deque.pollLast();
        }
        deque.offer(index);
    }

}
