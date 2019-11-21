package DP.Sequence;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 * You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input:
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 *
 * Example 2:
 * Input:
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 */
public class LC516LongestPalindromicSubsequence {

    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[][] dp = new int[len][len];

        for (int i = len-1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }

    // 滚动数组优化
    public int longestPalindromeSubseqRollingArray(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[][] dp = new int[2][len];

        for (int i = len-1; i >= 0; i--) {
            dp[i%2][i] = 1;
            for (int j = i+1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i%2][j] = (j==i+1) ? 2 : dp[(i+1)%2][j-1]+2;
                } else {
                    dp[i%2][j] = Math.max(dp[i%2][j-1], dp[(i+1)%2][j]);
                }
            }
        }
        return dp[0][len-1];
    }
}
