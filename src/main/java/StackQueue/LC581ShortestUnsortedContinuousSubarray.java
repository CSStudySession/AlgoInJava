package StackQueue;

import java.util.Stack;

/**
 * Given an integer array, you need to find one continuous subarray that
 * if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 *
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 *
 * 思路: 单调栈
 * 对于数组: 2 6 4 1 10 9 15
 * 找非排序数组的起始点 分两边 第一遍从左到右遍历找起点 然后从右到左遍历找终点
 * 找起点时 栈是单调不减的 一旦遇到比栈顶元素小的 就出栈 并维护一个最小的index
 * 找终点时 栈是单调不增的 一旦遇到比栈顶元素大的 就出栈 并维护一个最大的index
 */
public class LC581ShortestUnsortedContinuousSubarray {

    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int left = nums.length;
        int right = 0;

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left = Math.min(left, stack.pop());
            }
            stack.push(i);
        }

        stack.clear();

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                right = Math.max(right, stack.pop());
            }
            stack.push(i);
        }

        return right - left > 0 ? right - left + 1 : 0;
    }
}
