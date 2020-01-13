package DP.Backpack;

/**
 * There are n items and a backpack with size m.
 * Given array A representing the size of each item and array V representing the value of each item.
 * What's the maximum value can you put into the backpack?
 * A[i], V[i], n, m are all integers.
 * You can not split an item.
 * The sum size of the items you want to put into backpack can not exceed m.
 * Each item can only be picked up once
 * Have you met this question in a real interview?
 *
 * Example 1:
 * Input: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
 * Output: 9
 * Explanation: Put A[1] and A[3] into backpack, getting the maximum value V[1] + V[3] = 9
 *
 * Example 2:
 * Input: m = 10, A = [2, 3, 8], V = [2, 5, 8]
 * Output: 10
 * Explanation: Put A[0] and A[2] into backpack, getting the maximum value V[0] + V[2] = 10
 * Challenge
 * O(nm) memory is acceptable, can you do it in O(m) memory?
 */
public class LiC125BackpackII {

    // version 1: 常规背包
    public int backPackII(int m, int[] A, int[] V) {
        int[][] dp = new int[A.length + 1][m + 1];
        dp[0][0] = 0;

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= m; j++) {
                if (j - A[i-1] >= 0) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - A[i-1]] + V[i-1]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[A.length][m];
    }

    // version 2: 滚动数组优化
    public int backPackIIRollingArray(int m, int[] A, int[] V) {
        int[][] dp = new int[2][m + 1];
        dp[0][0] = 0;

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= m; j++) {
                // 更新之前清空一下之前的记录
                dp[i%2][j] = 0;
                if (j - A[i-1] >= 0) {
                    dp[i%2][j] = Math.max(dp[(i-1)%2][j], dp[(i-1)%2][j - A[i-1]] + V[i-1]);
                } else {
                    dp[i%2][j] = dp[(i-1)%2][j];
                }
            }
        }

        return dp[A.length % 2][m];
    }


}
