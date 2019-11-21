package dynamicProgramming;

/**
 * Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
 *
 * Example
 * Example 1:
 * 	Input:  [3,4,8,5], backpack size=10
 * 	Output:  9
 *
 * Example 2:
 * 	Input:  [2,3,5,7], backpack size=12
 * 	Output:  12
 *
 * Challenge
 * O(n x m) time and O(m) memory.
 *
 * O(n x m) memory is also acceptable if you do not know how to optimize memory.
 */
public class Backpack {

    public static int findWeight(int m, int[] A) {
        int n = A.length;
        boolean[][] dp = new boolean[n+1][m+1];

        dp[0][0] = true;

        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                dp[i][j] = dp[i-1][j];
                if (j >= A[i-1] && dp[i-1][j-A[i-1]]) {
                    dp[i][j] = true;
                }
            }
        }

        for (int i = m; i >= 0; --i) {
            if (dp[n][i]) return i;
        }

        return 0;
    }

    public static int findWeight1dArray(int m, int[] A) {
        int n = A.length;

        boolean[] dp = new boolean[m+1];
        dp[0] = true;
        for (int i = 1; i <= n; ++i) {
            boolean[] tmp = new boolean[m+1];
            for (int j = 0; j <= m; ++j) {
                tmp[j] = dp[j];
                if (j >= A[i-1] && dp[j-A[i-1]]) {
                    tmp[j] = true;
                }
            }
            dp = tmp;
        }

        for (int i = m; i >= 0; --i) {
            if (dp[i]) return i;
        }

        return 0;
    }

    public static int findWeightOptimize(int m, int[] A) {
        int f[] = new int[m + 1];

        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                f[j] = Math.max(f[j], f[j - A[i]] + A[i]);
            }
        }
        return f[m];
    }
}
