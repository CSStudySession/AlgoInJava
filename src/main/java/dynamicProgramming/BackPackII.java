package dynamicProgramming;


/**
 * Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?
 */
public class BackPackII {

    public int backPackII(int m, int[] A, int[] V) {
        int n = A.length;
        int[] dp = new int[m+1];

        for (int i = 1; i <= n; ++i) {
            int[] tmp = new int[m+1];
            for (int j = 0; j <= m; ++j) {
                if (j >= A[i-1] && dp[j-A[i-1]] + V[i-1] > dp[j]) {
                    tmp[j] = dp[j-A[i-1]] + V[i-1];
                } else {
                    tmp[j] = dp[j];
                }
            }
            dp = tmp;
        }


        return dp[m];
    }
}
