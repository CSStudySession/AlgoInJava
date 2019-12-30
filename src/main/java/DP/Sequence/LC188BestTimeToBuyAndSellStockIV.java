package DP.Sequence;

import java.util.Arrays;

/**
 * Say you have an array for which the i-th element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *
 * Example 2:
 * Input: [3,2,6,5,0,3], k = 2
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 *              Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.第
 *
 * 思路：
 * 状态 dp[i][j][k]: 到第i位为止 用了k次transaction 最后一次是买(0)或卖(1)的最大收益
 * 答案: max{dp[len-1][k][1]}
 */
public class LC188BestTimeToBuyAndSellStockIV {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k < 1) return 0;

        int len = prices.length;
        // k可能很大 k等于len/2就够用了
        if (k > len / 2) {
            k = len / 2;
        }
        int[][][] dp = new int[len][k+1][2];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= k; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        dp[0][1][0] = -prices[0];

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                if (k == 1) {
                    dp[i][j][0] = Math.max(dp[i-1][j][0], -prices[i]);
                } else {
                    dp[i][j][0] = Math.max(dp[i-1][j][0],
                            ((dp[i-1][j-1][1] != Integer.MIN_VALUE) ? dp[i-1][j-1][1] - prices[i] : Integer.MIN_VALUE));
                }

                dp[i][j][1] = Math.max(dp[i-1][j][1],
                        ((dp[i-1][j][0] != Integer.MIN_VALUE) ? dp[i-1][j][0] + prices[i] : Integer.MIN_VALUE));
            }
        }

        int result = 0;
        for (int j = 1; j <= k; j++) {
            result = Math.max(result, dp[len-1][j][1]);
        }

        return result;
    }

    public static void main(String[] args) {
        LC188BestTimeToBuyAndSellStockIV inst = new LC188BestTimeToBuyAndSellStockIV();
        int[] prices = {2, 1, 2, 0, 1};
        int k = 2;
        inst.maxProfit(k, prices);
        System.out.println(".");
    }

}
