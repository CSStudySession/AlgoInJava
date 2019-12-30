package DP.Sequence;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction
 * (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 *
 * Example 2:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * 思路：
 * 状态 profit[i]：在前i天能获得的最大收益
 * 如果在第i天交易: profit[i] = prices[i] - minPrice, where minPrice is the lowest price seen.
 * 如果不在第i天交易: profit[i] = profit[i-1]
 */
public class LC121BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int[] profit = new int[prices.length];
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(prices[i], minPrice);
            profit[i] = Math.max(profit[i - 1], prices[i] - minPrice);
        }
        return profit[prices.length - 1];
    }

}
