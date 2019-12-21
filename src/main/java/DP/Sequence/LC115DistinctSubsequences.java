package DP.Sequence;

/**
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 * A subsequence of a string is a new string which is formed from the original string
 * by deleting some (can be none) of the characters without disturbing the relative positions
 * of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * Example 1:
 * Input: S = "rabbbit", T = "rabbit"
 * Output: 3
 * Explanation:
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 *
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 *
 * Example 2:
 * Input: S = "babgbag", T = "bag"
 * Output: 5
 * Explanation:
 * As shown below, there are 5 ways you can generate "bag" from S.
 * (The caret symbol ^ means the chosen letters)
 *
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 */
public class LC115DistinctSubsequences {

    // version 1: no rolling array
    public int numDistinct(String s, String t) {
        if (s.equals(t)) {
            return 1;
        }
        int sLen = s.length();
        int tLen = t.length();
        int[][] dp = new int[sLen + 1][tLen + 1];
        // 代表 s.substring(0 , i) 和 t.substring(0 ，j) 的最大distinct答案
        for (int i = 0; i <= sLen; i++) {
            dp[i][0] = 1;
            //t 为 “”  s有一种变成 “”的方案:全删了
        }

        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // 去前面s找有匹配的个数      只和当前的i 匹配
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                } else {
                    // 去前面s找有匹配的个数
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[sLen][tLen];
    }

    // version 2: rolling array
    public int numDistinctRollingArray(String s, String t) {
        if (s.equals(t)) {
            return 1;
        }
        int sLen = s.length();
        int tLen = t.length();
        int[][] dp = new int[2][tLen + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= sLen; i++) {
            dp[i%2][0] = 1;
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // 去前面s找有匹配的个数      只和当前的i 匹配
                    dp[i%2][j] = dp[(i - 1)%2][j] + dp[(i - 1)%2][j - 1];
                } else {
                    // 去前面s找有匹配的个数
                    dp[i%2][j] = dp[(i - 1)%2][j];
                }
            }
        }
        return dp[sLen%2][tLen];
    }
}
