package Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a circular array (the next element of the last element is the first element of the array),
 * print the Next Greater Number for every element.
 * The Next Greater Number of a number x is the first greater number to its
 * traversing-order next in the array, which means you could search circularly
 * to find its next greater number. If it doesn't exist, output -1 for this number.
 *
 * Example 1:
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 *
 * Note: The length of given array won't exceed 10000.
 *
 * 思路：
 * 解决循环数组问题的方法之一：倍增
 * nums: 1, 2, 1
 * 倍增后: 1, 2, 1 || 1, 2, 1
 *                   i = i % nums.length where i in [0, 2*nums.length)
 */
public class LC503NextGreaterElementII {

    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length < 1) return nums;
        int[] ret = new int[nums.length];
        Arrays.fill(ret, -1);
        // stack里面存下标 而不是元素!!
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < 2 * nums.length; i++) {
            int num = nums[i % nums.length];
            // 注意下面是 && 的关系！
            while (!st.isEmpty() && nums[st.peek()] < num) {
                // 注意这里是st.pop() 只要栈顶下标对应的元素比num小 num就是它右面第一个比它大的
                ret[st.pop()] = num;
            }
            /*
            只有当i小于length的时候才压栈 后面的循环都只是为了
            给栈中的元素找右边第一个大的值
             */
            if (i < nums.length) st.push(i);
        }
        return ret;
    }

}

