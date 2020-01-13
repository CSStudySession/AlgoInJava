package DP.Interval;

/**
 * Given n balloons, indexed from 0 to n-1.
 * Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons.
 * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * Example:
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 */
public class LC312BurstBalloons {

    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        // 加上两个padding
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for(int i = 0; i < n; i++){
            arr[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];


        return memoizedSearch(1, n, arr, dp);
    }

    private int memoizedSearch(int start, int end, int[] arr, int[][] dp){
        if(dp[start][end] != 0) return dp[start][end];

        int max = 0;
        for(int i = start; i <= end; i++) {
            // [start,i) and (i, end]在下面会计算到
            int cur = arr[start - 1] * arr[i] * arr[end + 1];
            int left = memoizedSearch(start, i - 1, arr, dp);
            int right = memoizedSearch(i + 1, end, arr, dp);

            max = Math.max(max, cur + left + right);
        }

        dp[start][end] = max;

        return max;
    }
}
