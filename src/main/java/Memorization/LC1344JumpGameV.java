package Memorization;

/**
 * Given an array of integers arr and an integer d. In one step you can jump from index i to index:
 *
 * i + x where: i + x < arr.length and 0 < x <= d.
 * i - x where: i - x >= 0 and 0 < x <= d.
 *
 * In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k]
 * for all indices k between i and j (More formally min(i, j) < k < max(i, j)).
 *
 * You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
 * Notice that you can not jump outside of the array at any time.
 *
 * Example 1:
 * Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
 * Output: 4
 * Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
 * Note that if you start at index 6 you can only jump to index 7.
 * You cannot jump to index 5 because 13 > 9.
 * You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
 * Similarly You cannot jump from index 3 to index 2 or index 1.
 *
 * Example 2:
 * Input: arr = [3,3,3,3,3], d = 3
 * Output: 1
 * Explanation: You can start at any index. You always cannot jump to any index.
 *
 * Example 3:
 * Input: arr = [7,6,5,4,3,2,1], d = 1
 * Output: 7
 * Explanation: Start at index 0. You can visit all the indicies.
 *
 * Example 4:
 * Input: arr = [7,1,7,1,7,1], d = 2
 * Output: 2
 *
 * Example 5:
 * Input: arr = [66], d = 1
 * Output: 1
 *
 * Constraints:
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 10^5
 * 1 <= d <= arr.length
 *
 * 思路:
 * dp[i]表示以i为起点能跳到最多的顶点个数
 * sub-optimal function:
 * dp[i] = 1 if i has no vertices to reach
 * dp[i] = max(dp[j] + 1) for all j that i can directly jump to
 *
 * answer: max(dp[i])
 *
 * T(n) = O(nd) S(n) = O(n)
 */
public class LC1344JumpGameV {

    public int maxJumps(int[] arr, int d) {
        int[] dp = new int[arr.length];
        int ans = 1;
        for (int i = 0; i < arr.length; i++) {
            ans = Math.max(ans, dfs(arr, d, i, dp));
        }

        return ans;
    }

    private int dfs(int[] arr, int d, int cur, int[] dp) {
        if (dp[cur] != 0) return dp[cur];

        // 至少能跳到自己 所以res初始化为1
        int res = 1;
        // 当arr[j] >= arr[cur]时 循环就可以break
        for (int j = cur + 1; j <= Math.min(cur + d, arr.length - 1) &&
                arr[j] < arr[cur]; j++) {
            res = Math.max(res, 1 + dfs(arr, d, j, dp));
        }

        for (int j = cur - 1; j >= Math.max(cur - d, 0) &&
                arr[j] < arr[cur]; j--) {
            res = Math.max(res, 1 + dfs(arr, d, j, dp));
        }

        // 更新dp数组 把res赋值给dp[cur]
        dp[cur] = res;
        return dp[cur];
    }
}
