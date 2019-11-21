package DP.Sequence;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class LC53MaximumSubarray {

    /*
    solution 1:
    复杂度：
    时间O(n) 空间O(n)
    */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] res = new int[nums.length];
        res[0] = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (res[i-1] > 0) {
                res[i] = res[i-1] + nums[i];
            } else {
                res[i] = nums[i];
            }
            maxSum = Math.max(maxSum, res[i]);
        }

        return maxSum;
    }

    /*
    solution 2:
    复杂度：
    时间O(n) 空间O(1)
    整体-局部最优法
    */
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int globalMax = nums[0];
        int localMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            localMax = Math.max(localMax+nums[i], nums[i]);
            globalMax = Math.max(globalMax, localMax);
        }
        return globalMax;
    }
}
