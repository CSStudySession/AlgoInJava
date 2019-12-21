package DP.Sequence;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 * The substrings with different start indexes or end indexes are counted as different
 * substrings even they consist of same characters.
 *
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 * Note:
 * The input string length won't exceed 1000.
 */
public class LC647PalindromicSubstrings {

    public int countSubstrings(String s) {
        int len = s.length();
        int result = 0;
        boolean[][] dp = new boolean[2][len]; // 滚动数组优化

        for (int i = len - 1; i >= 0; i--) {
            dp[i%2][i] = true;
            result++;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    dp[i%2][j] = (j == i + 1) ? true : dp[(i+1)%2][j-1];
                } else {
                    // 注意这里需要显示得把dp[i%2][j]置为false 否则下面计数会出错
                    dp[i%2][j] = false;
                }

                if (dp[i%2][j] == true) {
                    result++;
                }
            }
        }
        return result;
    }

}
