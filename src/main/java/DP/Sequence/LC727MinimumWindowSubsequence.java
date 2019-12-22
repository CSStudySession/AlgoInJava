package DP.Sequence;

/**
 * Given strings S and T, find the minimum (contiguous) substring W of S,
 * so that T is a subsequence of W.
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such minimum-length windows, return the one with the left-most starting index.
 *
 * Example 1:
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 * Note:
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 */
public class LC727MinimumWindowSubsequence {

    public String minWindow(String S, String T) {
        int m = T.length(), n = S.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j <= n; j++) {     //初始化两字符串匹配的起点
            dp[0][j] = j + 1;
        }

        for (int i = 1; i <= m; i++) {    	//T字符串
            for (int j = 1; j <= n; j++) {		//S字符串
                if (T.charAt(i - 1) == S.charAt(j - 1)) {	//两字符匹配
                    dp[i][j] = dp[i - 1][j - 1];		//dp[i][j]的起点则等于dp[i-1][j-1]的起点
                } else {
                    dp[i][j] = dp[i][j - 1];		//dp[i][j]的起点等于dp[i][j-1]的起点
                }
            }
        }

        int start = 0, len = n + 1;
        for (int j = 1; j <= n; j++) {
            if (dp[m][j] != 0) {			//dp[m][j]!=0表示当前T串的m个字符已经匹配成为S串的前j个长度字符串的子序列
                if (j - dp[m][j] + 1 < len) {		//选择字符串长度最小的
                    start = dp[m][j] - 1;
                    len = j - dp[m][j] + 1;
                }
            }
        }
        return len == n + 1 ? "" : S.substring(start, start + len);
    }
}
