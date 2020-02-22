package DP.Sequence;

import java.util.Arrays;

/**
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 *
 * Return the result as a list of indices representing the starting position of each interval (0-indexed).
 * If there are multiple answers, return the lexicographically smallest one.
 *
 * Example:
 * Input: [1,2,1,2,6,7,5,1], 2
 * Output: [0, 3, 5]
 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 *
 * Note:
 * nums.length will be between 1 and 20000.
 * nums[i] will be between 1 and 65535.
 * k will be between 1 and floor(nums.length / 3).
 */
public class LC689MaximumSumOf3NonOverlappingSubarrays {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        int dpSize = len - k + 1;
        int sum = 0;
        // dp[i]: 以i为起点 长度为k的子数组的和
        int[] dp = new int[dpSize];

        for (int i = 0; i < len; i++) {
            sum += nums[i];
            // 维护一个大小为k的窗口
            if (i >= k) {
                sum -= nums[i - k];
            }

            if (i >= k - 1) {
                dp[i - k + 1] = sum;
            }
        }

        /*
        left[i] / right[i]:
        从左往右 / 右往左扫描 以i为终点的(到i为止) 有最大子数组和的起点的index
        注意 这里left和right数组存的都是下标
         */
        int[] left = new int[dpSize];
        int[] right = new int[dpSize];

        int maxIdx = 0;
        for (int i = 0; i < dpSize; i++) {
            if (dp[i] > dp[maxIdx]) {
                maxIdx = i;
            }
            left[i] = maxIdx;
        }

        maxIdx = dpSize - 1;
        for (int i = dpSize - 1; i >= 0; i--) {
            if (dp[i] >= dp[maxIdx]) {
                maxIdx = i;
            }
            right[i] = maxIdx;
        }

        // res存下标
        int[] res = new int[3];
        Arrays.fill(res, -1);

        // 前面和后面至少留出k个位置给另外两个数组 i是中间数组的start index
        for (int i = k; i < dpSize - k; i++) {
            if (res[0] == -1 ||
                    dp[res[0]] + dp[res[1]] + dp[res[2]] <
                            dp[left[i - k]] + dp[i] + dp[right[i + k]]) {
                res[0] = left[i - k];
                res[1] = i;
                res[2] = right[i + k];
            }
        }

        return res;
    }

}