package StackQueue;

import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * 思路: 单调栈
 */
public class LC42TrappingRainWater {

    public int trap(int[] height) {
        if (height == null || height.length < 3) return 0;
        Stack<Integer> stack = new Stack<>(); //存Index
        int idx = 0;
        int ret = 0;

        while (idx < height.length) {
            if (stack.isEmpty() || height[idx] <= height[stack.peek()]) {
                stack.push(idx);
                idx++;
            } else { //可能会形成凹储水
                int prev = stack.pop();
                if (stack.isEmpty()) continue;
                ret += (Math.min(height[stack.peek()], height[idx]) - height[prev]) * (idx- stack.peek() -1);
            }
        }

        return ret;
    }

}
