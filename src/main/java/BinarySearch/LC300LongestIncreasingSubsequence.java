package BinarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Note:
 * There may be more than one LIS combination,
 * it is only necessary for you to return the length.
 *
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LC300LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        List<Integer> dp = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int left = 0, right = dp.size();
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (dp.get(mid) < nums[i]) left = mid + 1;
                else right = mid;
            }
            if (right >= dp.size()) dp.add(nums[i]);
            else dp.set(right, nums[i]);
        }

        return dp.size();
    }
}
