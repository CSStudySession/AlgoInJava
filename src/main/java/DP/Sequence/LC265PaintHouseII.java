package DP.Sequence;

/**
 * There are a row of n houses, each house can be painted with one of the k colors.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x k cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color 0;
 * costs[1][2] is the cost of painting house 1 with color 2, and so on...
 * Find the minimum cost to paint all houses.
 *
 * Note:
 * All costs are positive integers.
 *
 * Example:
 *
 * Input: [[1,5,3],[2,9,4]]
 * Output: 5
 * Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
 *              Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 * Follow up:
 * Could you solve it in O(nk) runtime?
 *
 * 注意
 * - 序列型dp[i]表示'前i-1个'的结果. 所以dp最好设定为 int[n + 1] size.
 * - 然而, 颜色在这里是状态, 所以保留在 j: [ 0~k)
 * - [[8]] 这样的edge case. 跑不进for loop, 所以特殊handle.
 *
 */
public class LC265PaintHouseII {

    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int min = 0;
        int minSec = 0;
        int minIdx = -1;

        for (int i = 0; i < costs.length; i++) {
            int curMin = Integer.MAX_VALUE;
            int curMinSec = Integer.MAX_VALUE;
            int curIdx = -1;
            for (int j = 0; j < costs[0].length; j++) {
                costs[i][j] = costs[i][j] + (minIdx == j ? minSec : min);
                if (costs[i][j] < curMin) {
                    curMinSec = curMin;
                    curMin = costs[i][j];
                    curIdx = j;
                } else if (costs[i][j] < curMinSec) {
                    curMinSec = costs[i][j];
                }
            }
            min = curMin;
            minSec = curMinSec;
            minIdx = curIdx;
        }
        return min;
    }

    public int minCostIIRollingArray(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        } else if (costs.length == 1 && costs[0].length == 1) {
            return costs[0][0];
        }
        int minCost = Integer.MAX_VALUE;
        int n = costs.length, k = costs[0].length;
        int[][] dp = new int[2][k]; // dp[0][j] = 0; for j = [0 ~ k)

        for (int i = 1; i <= n; i++) {
            int minIndex = -1;
            int minSecIndex = -1;
            for (int j = 0; j < k; j++) {
                if (minIndex == -1 || dp[(i - 1) % 2][j] < dp[(i - 1) % 2][minIndex]) {
                    minSecIndex = minIndex;
                    minIndex = j;
                } else if (minSecIndex == -1 || dp[(i - 1) % 2][j] < dp[(i - 1) % 2][minSecIndex]) {
                    minSecIndex = j;
                }
            }

            // DP Processing
            for (int j = 0; j < k; j++) {
                if (j == minIndex) dp[i % 2][j] = dp[(i - 1) % 2][minSecIndex] + costs[i - 1][j];
                else dp[i % 2][j] = dp[(i - 1) % 2][minIndex] + costs[i - 1][j];
                // 最后一个房子的时候 每涂一个颜色就比较一次最小花费
                if (i == n) minCost = Math.min(minCost, dp[i % 2][j]);
            }
        }
        return minCost;
    }

}
