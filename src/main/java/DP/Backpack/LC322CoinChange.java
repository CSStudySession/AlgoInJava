package DP.Backpack;

import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */
public class LC322CoinChange {

    public int coinChange(int[] coins, int amount) {
        if(amount <= 0){
            return 0;
        }

        // amount可以取到
        int dp[] = new int[amount+1];
        // 求最小值 所以都初始化成Integer.MAX_VALUE
        Arrays.fill(dp, Integer.MAX_VALUE);
        // 用非零的钱凑出0元的方案为0
        dp[0] = 0;

        // dp[i] 可以从之前的 i-j where j in coins 转移过来
        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < coins.length; j++) {
                // i-coins[j]有意义 且 dp[i-coins[j]]能凑出来
                if (i - coins[j] >= 0 && dp[i-coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE? -1: dp[amount];
    }
}
