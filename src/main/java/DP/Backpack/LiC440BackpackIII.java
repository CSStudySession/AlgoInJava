package DP.Backpack;

/**
 * Given n kinds of items, and each kind of item has an infinite number available.
 * The i-th item has size A[i] and value V[i].
 * Also given a backpack with size m. What is the maximum value you can put into the backpack?
 *
 * Example 1:
 * Input: A = [2, 3, 5, 7], V = [1, 5, 2, 4], m = 10
 * Output: 15
 * Explanation: Put three item 1 (A[1] = 3, V[1] = 5) into backpack.
 *
 * Example 2:
 * Input: A = [1, 2, 3], V = [1, 2, 3], m = 5
 * Output: 5
 * Explanation: Strategy is not unique. For example, put five item 0 (A[0] = 1, V[0] = 1) into backpack.
 * A[0] and A[2] into backpack, getting the maximum value V[0] + V[2] = 10 7], backpack size=12
 * Output: 12
 * Notice
 * You cannot divide item into small pieces. Total size of items you put into backpack can not exceed m.
 */
public class LiC440BackpackIII {

    // version 1: 最基础的背包
    public int backPackIII(int[] A, int[] V, int m) {
        if (A == null || A.length == 0 || V == null || V.length == 0 || m <= 0) {
            return 0;
        }

        int n = A.length;
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 0; // 0 items to fill 0 weight, value = 0

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 加一层循环 枚举所有用尽A[i-1]的可能性 因为重复元素可以无限次使用
                for (int k = 0; k * A[i - 1] <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * A[i - 1]] + k * V[i - 1]);
                }
            }
        }

        return dp[n][m];
    }

    // version 2: 小优化 去掉循环k的过程 优化到 这里就可以用滚动数组优化了
    public int backPackIIINoLoopK(int[] A, int[] V, int m) {
        if (A == null || A.length == 0 || V == null || V.length == 0 || m <= 0) {
            return 0;
        }

        int n = A.length;
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 0; // 0 items to fill 0 weight, value = 0

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= A[i - 1]) {
                    // 注意这里是dp[i][j - A[i - 1]] 不是i-1
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        return dp[n][m];
    }


}
