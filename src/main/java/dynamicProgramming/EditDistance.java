package dynamicProgramming;


/**
 * Given two words word1 and word2, findWithDuplicates the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 * Example
 * Given word1 = "mart" and word2 = "karma", return 3.
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i <= n; ++i) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= m; ++i) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                char c = word1.charAt(i-1), d = word2.charAt(j-1);
                if (c == d) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }

        return dp[n][m];
    }
}
