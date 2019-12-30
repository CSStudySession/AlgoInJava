package DP.Sequence;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions at the same time
 * (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 *
 * Example 2:
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 *
 * Example 3:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * 思路：
 * 状态：dp[i][k] 到i为止(或者理解为i及其i以前) 满足状态k的最好收益
 * 这里的dp定义类似于前缀和的性质(到i为止包括i之前的所有操作) 注意k从大到小有依赖关系
 */
public class LC123BestTimeToBuyAndSellStockIII {

    // version 1: 常规dp
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        /*
        i和以前买一次的最好收益 dp[i][0]
        i和以前卖一次的最好收益 dp[i][1]
        i和以前买两次的最好收益 dp[i][2]
        i和以前卖两次的最好收益 dp[i][3]
         */
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0]; // 买一次股票的收益是负的 卖的时候会补回来
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;
        dp[0][3] = Integer.MIN_VALUE;


        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i]);

            // 注意这里一定要把取不到的时候赋值成Integer.MIN_VALUE！否则java会自动初始化成0
            dp[i][2] = (i > 1) ? Math.max(dp[i-1][2], dp[i-1][1] - prices[i]) : Integer.MIN_VALUE;
            dp[i][3] = (i > 2) ? Math.max(dp[i-1][3], dp[i-1][2] + prices[i]) : Integer.MIN_VALUE;
        }

        return Math.max(0, Math.max(dp[prices.length-1][1], dp[prices.length-1][3]));
    }

    // version 2: 滚动数组优化
    public int maxProfitRollingArray(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        /*
        i和以前买一次的最好收益 dp[i][0]
        i和以前卖一次的最好收益 dp[i][1]
        i和以前买两次的最好收益 dp[i][2]
        i和以前卖两次的最好收益 dp[i][3]
         */
        // i只跟i-1有关 所以只需要两行
        int[][] dp = new int[2][4];
        dp[0][0] = -prices[0];
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;
        dp[0][3] = Integer.MIN_VALUE;


        for (int i = 1; i < prices.length; i++) {
            dp[i%2][0] = Math.max(dp[(i-1)%2][0], -prices[i]);
            dp[i%2][1] = Math.max(dp[(i-1)%2][1], dp[(i-1)%2][0] + prices[i]);

            dp[i%2][2] = (i > 1) ? Math.max(dp[(i-1)%2][2], dp[(i-1)%2][1] - prices[i]) : Integer.MIN_VALUE;
            dp[i%2][3] = (i > 2) ? Math.max(dp[(i-1)%2][3], dp[(i-1)%2][2] + prices[i]) : Integer.MIN_VALUE;
        }

        return Math.max(0, Math.max(dp[(prices.length-1)%2][1], dp[(prices.length-1)%2][3]));
    }

    public static void main(String[] args) {
        LC123BestTimeToBuyAndSellStockIII inst = new LC123BestTimeToBuyAndSellStockIII();
        int[] prices = {1,2,3,4,5};
        inst.maxProfit(prices);
        System.out.println(".");
    }


}
