package Stack;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 *
 * Example:
 * Input: [2,1,5,6,2,3]
 * Output: 10
 *
 * 思路：
 * 维护一个单调递增栈，逐个将元素 push 到栈里。push 进去之前先把 >= 自己的元素 pop 出来。
 * 每次从栈中 pop 出一个数的时候，就找到了往左数比它小的第一个数（当前栈顶）和往右数比它小的第一个数（即将入栈的数），
 * 从而可以计算出这两个数中间的部分宽度 * 被pop出的数，就是以这个被pop出来的数为最低的那个直方向两边展开的最大矩阵面积。
 * 因为要计算两个数中间的宽度，因此放在 stack 里的是每个数的下标。
 */
public class LC84LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0 || heights == null) {
            return 0;
        }

        int maxArea = 0;
        int curArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && (heights[i] <= heights[stack.peek()])) {
                int idx = stack.pop();
                curArea = stack.isEmpty() ? i*heights[idx] : (i - stack.peek() - 1) * heights[idx];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            curArea = stack.isEmpty() ? heights.length*heights[idx] : (heights.length-stack.peek()-1)*heights[idx];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

}
