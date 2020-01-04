package StackQueue;

import java.util.Stack;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input,
 * tells you how many days you would have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
 * your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000].
 * Each temperature will be an integer in the range [30, 100].
 *
 * 思路：单调栈
 * 维护一个栈 里面存的下标 下标对应的元素单调递减 看到的当前元素 如果比栈顶的元素对应的值大
 * 则当前元素就是栈顶元素对应的next greater element
 */
public class LC739DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                ret[idx] = i - idx; // 下一次等i-idx天
            }
            // 压栈
            stack.push(i);
        }

        return ret;
    }
}
