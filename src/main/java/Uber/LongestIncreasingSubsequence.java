package Uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Given an unsorted array of integers, findWithDuplicates the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {


    /**
     * N^2
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int max = Integer.MIN_VALUE;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (list.isEmpty()) {
                list.add(num);
            } else {
                boolean appended = false;
                for (int i = 0; i < list.size(); ++i) {
                    if (num <= list.get(i)) {
                        list.set(i, num);
                        appended = true;
                        break;
                    }
                }
                if (!appended) {
                    list.add(num);
                }
            }
        }

        return list.size();
    }


    /**
     * O(NlogN)
     * @param nums
     * @return
     */
    public int lengthOfLISNlogN(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public int LIS(int[] nums) {
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > list.get(list.size()-1)) {
                list.add(nums[i]);
            } else {
                for (int j = list.size() - 1; j >= 0; --j) {
                    if (nums[i] <= list.get(j) && !list.contains(nums[i])) {
                        list.set(j, nums[i]);
                        break;
                    }
                }
            }
        }

        return list.size();
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        long current = System.currentTimeMillis();
        System.out.println(longestIncreasingSubsequence.lengthOfLISNlogN(nums));
        long post = System.currentTimeMillis();
        System.out.println((float)(post - current));
        current = System.currentTimeMillis();
        System.out.println(longestIncreasingSubsequence.LIS(nums));
        post = System.currentTimeMillis();
        System.out.println((float)(post - current));
    }
}
