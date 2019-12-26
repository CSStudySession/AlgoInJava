package DP.Sequence;

/**
 * In combinatorial mathematics, a derangement is a permutation of the elements of a set,
 * such that no element appears in its original position.
 *
 * There's originally an array consisting of n integers from 1 to n in ascending order,
 * you need to find the number of derangement it can generate.
 * Also, since the answer may be very large, you should return the output mod 109 + 7.
 *
 * Example 1:
 * Input: 3
 * Output: 2
 * Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].
 *
 * Note:
 * n is in the range of [1, 106].
 *
 * 思路：
 * 设定状态: f[i] 表示含i个元素的排列能生成的错乱的数量
 * 状态转移方程: f[i] = (i - 1) * (f[i-1] + f[i-2])
 * 边界: f[1] = 0, f[2] = 1
 *
 * 对于 f[n] 的计算, 假定把 n 放到了第 k 个位置:
 * 这时如果把 k 放到了第 n 个位置, 那么剩下的 n-2 个元素的错乱即为 f[n-2]
 * 如果把 k 放到了其他位置, 也就是说 k 不能放到 n, 与 n-1 个元素的错乱中 "k不能放到k" 是等价的, 也就是说, 这时是f[n-1]
 * k一共有 n-1 个选择, 故 f[n] = (i - 1) * (f[n-1] + f[n-2])
 */
public class LC634FindTheDerangementOfAnArray {

    public int findDerangement(int n) {
        if (n <= 1) {
            return 0;
        }

        long[] dp = new long[n + 1];
        int mod = 1000000007;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]) % mod;
        }

        return (int)dp[n];
    }

}
