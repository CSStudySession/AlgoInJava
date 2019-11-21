package Memorization;

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
        int[][] memo = new int[len][len];
        return memoDfs(s, 0, len-1, memo);
    }

    private int memoDfs(String s, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) return memo[i][j];
        if (i > j) return 0;
        if (i == j) return 1;
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = memoDfs(s, i+1, j-1, memo) + 2;
        } else {
            memo[i][j] = Math.max(memoDfs(s, i, j-1, memo), memoDfs(s, i+1, j, memo));
        }
        return memo[i][j];
    }
}
