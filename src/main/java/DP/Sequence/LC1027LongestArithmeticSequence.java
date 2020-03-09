package DP.Sequence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
 * and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
 *
 * Example 1:
 * Input: [3,6,9,12]
 * Output: 4
 * Explanation:
 * The whole array is an arithmetic sequence with steps of length = 3.
 *
 * Example 2:
 * Input: [9,4,7,2,10]
 * Output: 3
 * Explanation:
 * The longest arithmetic subsequence is [4,7,10].
 *
 * Example 3:
 * Input: [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation:
 * The longest arithmetic subsequence is [20,15,10,5].
 *
 * Note:
 * 2 <= A.length <= 2000
 * 0 <= A[i] <= 10000
 *
 * 思路: dp[i][j] 记录以A[i] A[j]结尾的最长等差数列的长度
 * 根据等差数列的性质 如果 a,b,c构成等差数列: a = 2*b - c 所以如果确定了A[i],A[j] 根据公式算出第一个元素first
 * 然后看first在不在数组下标i之前的数组里 存在的话更新dp[i][j]
 *
 * 维护两个数据结构 dp[i][j]和Map<item, index> map用来快速定位first在数组的下标
 */
public class LC1027LongestArithmeticSequence {

    public int longestArithSeqLength(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        Map<Integer, Integer> valToIdx = new HashMap<>();
        int res = 2;

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 2);

            for (int j = i + 1; j < n; j++) {
                int first = A[i] * 2 - A[j];
                if (first < 0 || !valToIdx.containsKey(first)) continue;

                dp[i][j] = dp[valToIdx.get(first)][i] + 1; // index(first), i , j 构成等差数列
                res = Math.max(res, dp[i][j]);
            }
            // 外侧循环每次结束时 更新看到的元素下标
            valToIdx.put(A[i], i);
        }

        return res;
    }
}
