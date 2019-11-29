package DP.Sequence;

import java.util.Arrays;

/**
 * Given a positive integer n,
 * find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class LC279PerfectSquares {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // 先打表
        for (int i = 0; i * i <= n; i++) {
            dp[i*i] = 1;
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j * j <= i; j++) {
                //  合法的状态只可能是: i-1, i-4, i-9, i-16...
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }

        return dp[n];
    }
}
