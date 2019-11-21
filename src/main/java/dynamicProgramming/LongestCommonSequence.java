package dynamicProgramming;


/**
 * Given two strings, findWithDuplicates the longest common subsequence (LCS).
 *
 * Your code should return the length of LCS.
 *
 * Example
 * Example 1:
 * 	Input:  "ABCD" and "EDCA"
 * 	Output:  1
 *
 * 	Explanation:
 * 	LCS is 'A' or  'D' or 'C'
 *
 *
 * Example 2:
 * 	Input: "ABCD" and "EACB"
 * 	Output:  2
 *
 * 	Explanation:
 * 	LCS is "AC"
 *
 *
 *
 * 	Dp[i][j] 表示A序列前i个数，与B的前j个数的LCS长度。
 *  对A的每个位置i，枚举B的每个位置j。
 *  转移方程见代码。
 */
public class LongestCommonSequence {

    public int longestCommonSubsequence(String A, String B) {
        int n = A.length(), m = B.length();
        int[][] dp = new int[n+1][m+1];

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if (A.charAt(i-1) == B.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
            }
        }

        return dp[n][m];
    }
}
