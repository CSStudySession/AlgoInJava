package DP.Sequence;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class LC70ClimbingStairs {

    // version 1 一维数组
    public int climbStairs(int n) {
        if (n < 2) return n;
        int[] res = new int[n+1];
        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i <= n; i++) {
            res[i] = res[i-1] + res[i-2];
        }
        return res[n];
    }

    // version 2: 滚动数组
    public int climbStairs2(int n) {
        if (n < 2) return n;

        int state1 = 1;
        int state2 = 2;
        int state3 = 0;

        for (int i = 3; i <= n; i++) {
            state3 = state1 + state2;
            state1 = state2;
            state2 = state3;
        }
        return state2;
    }

}
