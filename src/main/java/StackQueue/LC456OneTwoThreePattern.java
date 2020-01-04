package StackQueue;

import java.util.Stack;

/**
 * Given a sequence of n integers a1, a2, ..., an,
 * a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj.
 * Design an algorithm that takes a list of n numbers as input
 * and checks whether there is a 132 pattern in the list.
 * Note: n will be less than 15,000.
 *
 * Example 1:
 * Input: [1, 2, 3, 4]
 * Output: False
 * Explanation: There is no 132 pattern in the sequence.
 *
 * Example 2:
 * Input: [3, 1, 4, 2]
 * Output: True
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 *
 * Example 3:
 * Input: [-1, 3, 2, 0]
 * Output: True
 * Explanation: There are three 132 patterns in the sequence:
 * [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */
public class LC456OneTwoThreePattern {

    /*
    version 1 可以推广到找所有
    先固定一个数字，然后去遍历另外两个数字。
    我们先确定哪个数字呢，当然是最小的那个，我们维护一个变量 mn，初始化为整型最大值，然后在遍历数字的时候，
    每次用当前数字来更新 mn，然后我们判断，若 mn 为当前数字就跳过，因为需要找到数字j的位置，
    数字j是大于数字i的，mn 表示的就是数字i。这样数字i和数字j都确定了之后，就要来遍历数字k了，
    范围是从数组的最后一个位置到数字j之间，只要中间的任何一个数字满足题目要求的关系，就直接返回 true 即可
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int mn = Integer.MAX_VALUE;

        for (int j = 0; j < n; j++) {
            mn = Math.min(mn, nums[j]);
            if (mn == nums[j]) continue;
            for (int k = n - 1; k > j; k--) {
                // 这里找到一组可行解之后 可以放进一个数组中 实现找所有
                if (mn < nums[k] && nums[j] > nums[k]) return true;
            }
        }

        return false;
    }

    /*
    维护一个栈和一个变量 third，其中 third 就是第三个数字，也是 pattern 132 中的2，初始化为整型最小值，
    栈里面按顺序放所有大于 third 的数字，也是 pattern 132 中的3，那么我们在遍历的时候，
    如果当前数字小于 third，即 pattern 132 中的1找到了，我们直接返回 true 即可，因为已经找到了
    注意我们应该从后往前遍历数组 如果当前数字大于栈顶元素，那么我们将栈顶数字取出，赋值给 third，
    然后将该数字压入栈，这样保证了栈里的元素仍然都是大于 third 的，我们想要的顺序依旧存在，
    进一步来说，栈里存放的都是可以维持坐标 second > third 的 second 值，
    其中的任何一个值都是大于当前的 third 值，如果有更大的值进来，
    那就等于形成了一个更优的 second > third 的这样一个组合，并且这时弹出的 third 值比以前的 third 值更大，
    为什么要保证 third 值更大，因为这样才可以更容易的满足当前的值 first 比 third 值小这个条件
     */
    public boolean find132pattern2(int[] nums) {
        int third = Integer.MIN_VALUE;
        Stack<Integer> st = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < third) return true;
            while (!st.empty() && nums[i] > st.peek()) {
                third = st.pop();
            }
            st.push(nums[i]);
        }

        return false;
    }

}
