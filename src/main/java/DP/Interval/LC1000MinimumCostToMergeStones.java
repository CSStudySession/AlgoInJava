package DP.Interval;

/**
 * There are N piles of stones arranged in a row.
 * The i-th pile has stones[i] stones.
 * A move consists of merging exactly K consecutive piles into one pile,
 * and the cost of this move is equal to the total number of stones in these K piles.
 * Find the minimum cost to merge all piles of stones into one pile.
 * If it is impossible, return -1.
 *
 * Example 1:
 * Input: stones = [3,2,4,1], K = 2
 * Output: 20
 * Explanation:
 * We start with [3, 2, 4, 1].
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5].
 * We merge [5, 5] for a cost of 10, and we are left with [10].
 * The total cost was 20, and this is the minimum possible.
 *
 * Example 2:
 * Input: stones = [3,2,4,1], K = 3
 * Output: -1
 * Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
 *
 * Example 3:
 * Input: stones = [3,5,1,2,6], K = 3
 * Output: 25
 * Explanation:
 * We start with [3, 5, 1, 2, 6].
 * We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
 * We merge [3, 8, 6] for a cost of 17, and we are left with [17].
 * The total cost was 25, and this is the minimum possible.
 *
 * Note:
 * 1 <= stones.length <= 30
 * 2 <= K <= 30
 * 1 <= stones[i] <= 100
 */
public class LC1000MinimumCostToMergeStones {


    int[][][] dp;
    int max = Integer.MAX_VALUE;
    int K;

    // DP：dp[i][j][k] 表示下标i到j合并成K堆的最小花费 -> 答案是dp[0][n -1][1]
    public int mergeStonesTopDown(int[] stones, int K) {
        this.K = K;
        int len = stones.length;
        if ((len - 1) % (K - 1) != 0) {
            return -1;
        }

        dp = new int[len + 1][len + 1][K + 1];
        int[] prefixSum = new int[len + 1];

        // 一般区间dp都会计算前缀和来加速计算
        int i;
        for (i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i - 1];
        }

        getResult(prefixSum, 1, len, 1);
        return dp[1][len][1];
    }

    private int getResult(int[] prefixSum, int left, int right, int piles) {
        if (dp[left][right][piles] != 0) {
            return dp[left][right][piles];
        }

        int res = max;
        int t;
        if (left == right) {
            res = (piles == 1) ? 0 : max;
        } else {
            if (piles == 1) {
                int mergeK = getResult(prefixSum, left, right, K);
                if (mergeK != max) {
                    res = mergeK + prefixSum[right] - prefixSum[left - 1];
                }
            } else {
                for (t = left; t < right; t++) {
                    int l = getResult(prefixSum, left, t, piles - 1);
                    int r = getResult(prefixSum, t + 1, right, 1);
                    if (l != max && r != max) {
                        res = Math.min(res, l + r);
                    }
                }
            }
        }

        dp[left][right][piles] = res;
        return res;
    }

    /*
     * 思路：
     * 区间dp套路模板题
     * DP：dp[i][j][k] 表示下标i到j合并成K堆的最小花费。 答案是dp[0][n -1][1];
     * TC/SC Worse case: 时间复杂度n^2*(k)*(n/k) = n ^3, 空间复杂度n * n * k.
     */
    public int mergeStonesBottomUp(int[] stones, int K) {

        int n = stones.length;
        if ((n - 1) % (K - 1) != 0) {
            return -1;
        }

        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + stones[i - 1];
        }

        int[][][] dp = new int[n][n][K + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k <= K; ++k) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            dp[i][i][1] = 0;
        }

        for (int len = 2; len <= n; ++len) {
            for (int i = 0; i + len <= n; ++i) {
                int j = i + len - 1;
                for (int k = 2; k <= K; ++k) {
                    // 小优化，中间大部分都是没有解的 m每次加K-1
                    for (int m = i; m < j; m += K - 1) {
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][m][1] + dp[m + 1][j][k - 1]);
                    }
                }
                dp[i][j][1] = dp[i][j][K] + sum[j + 1] - sum[i];
            }
        }

        return dp[0][n - 1][1];
    }

}
