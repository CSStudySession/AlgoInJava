package DP.Sequence;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * 思路：
 * 设定状态: dp[i] 表示字符串前i位有多少种解码方案
 * 状态转移方程:
 * 初始化 dp 数组为 0
 * 若字符串中 s[i] 表示的阿拉伯数字在 1~9 范围内, dp[i] += dp[i-1]
 * 若s[i-1]和s[i]连起来表示的数字在 10~26 范围内, dp[i] += dp[i-2] (若i==1, 则f[i] += 1)
 * 边界: dp[0] = 1
 *
 * 特判:
 * 如果字符串以 '0' 开头, 则直接返回0.
 * 如果运算中发现 dp[i] == 0, 则说明此处无法解码, 同样直接返回0.
 */
public class LC91DecodeWays {

    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;

        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.valueOf(s.substring(i - 1, i));
            int twoDigits = Integer.valueOf(s.substring(i - 2, i));

            // 出现非法字符
            if (twoDigits == 0) return 0;

            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] = dp[i-1];
            }

            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i-2];
            }
        }

        return dp[n];
    }
}
