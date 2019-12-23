package DP.Backpack;

/**
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 * You can assume below:
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 *
 * Example1
 * Input: amount = 10 and coins = [10]
 * Output: 1
 *
 * Example2
 * Input: amount = 8 and coins = [2, 3, 8]
 * Output: 3
 * Explanation:
 * there are three ways to make up the amount:
 * 8 = 8
 * 8 = 3 + 3 + 2
 * 8 = 2 + 2 + 2 + 2
 */
public class LiC740CoinChange2 {

    public int change(int amount, int[] coins) {
        // dp[i][j]: only use first i coins, how many ways to reach j amount
        int[][] dp = new int[coins.length + 1][amount + 1];
        // amount = 0, dp always equals to 1
        dp[0][0] = 1;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                // 不取第i个硬币
                dp[i][j] = dp[i-1][j];
                // 取第i个硬币
                if (j - coins[i-1] >= 0) {
                    /*
                    we use dp[i][j-coins[i-1]] instead of dp[i-1][j-coins[i-1]]
                    because coins can be used multiple times.
                    */
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[coins.length][amount];
    }

    public int changerRollingArray(int amount, int[] coins) {
        // dp[i][j]: only use first i coins, how many ways to reach j amount
        int[][] dp = new int[2][amount + 1];
        // amount = 0, dp always equals to 1
        dp[0][0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j == 0) {
                    dp[i%2][j] = 1;
                    continue;
                }
                // 这里需要清空一下 用了滚动数组
                dp[i%2][j] = 0;
                // 不取第i个硬币
                dp[i%2][j] = dp[(i-1)%2][j];
                // 取第i个硬币
                if (j - coins[i-1] >= 0) {
                    /*
                    we use dp[i][j-coins[i-1]] instead of dp[i-1][j-coins[i-1]]
                    because coins can be used multiple times.
                    */
                    dp[i%2][j] += dp[i%2][j - coins[i - 1]];
                }
            }
        }
        return dp[coins.length % 2][amount];
    }

}
