package DP.Sequence;

/**
 * Given two words word1 and word2,
 * find the minimum number of operations required to convert word1 to word2.
 * You have the following 3 operations permitted on a word:
 * Insert a character
 * Delete a character
 * Replace a character
 *
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 *
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
public class LC72EditDistance {

    // version 1: 二维dp
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) dp[i][0] = i;
        for (int j = 1; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                }
            }
        }

        return dp[n][m];
    }


    /*
    version 2:
    滚动数组 注意为什么对j==1要进行特判->第一列的初始化
    很明显的可以注意到
    dp[i][0] = i 这一行的信息损失了
    因为原来[2][0]=2 现在dp[2][0] = dp[0][0] = 0
    所以在滚动数组的时候要把这个情况考虑进去
    把j == 1的情况做了特殊处理
    */
    public int minDistance2(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[2][m+1];
        dp[0][0] = 0;
        dp[1][0] = 1;
        for (int j = 1; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i % 2][j] = j == 1 ? i - 1 : dp[(i-1) % 2][j-1];
                } else {
                    dp[i % 2][j] = j == 1 ? Math.min(i - 1, Math.min(i - 1, dp[(i-1) % 2][j])) + 1 :
                            Math.min(dp[(i-1) % 2][j-1], Math.min(dp[i % 2][j-1], dp[(i-1) % 2][j])) + 1;
                }
            }
        }

        return dp[n % 2][m];
    }



}
