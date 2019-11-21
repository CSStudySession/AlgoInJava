package DFS;

import java.util.Arrays;

/**
 * Given an array of integers nums and a positive integer k,
 * find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 * Example 1:
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 * Note:
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 *
 *
 */
public class LC698PartitionToKEqualSumSubsets {

    // version 1:
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int tot = 0;
        int max = 0;
        for (int num : nums) {
            tot += num;
            max = Math.max(num, max);
        }
        if (tot % k != 0) return false;

        int subTot = tot / k;
        if (max > subTot) return false;
        Arrays.sort(nums);
        return dfs(nums, nums.length-1, k, new int[k], subTot);
    }

    // n个元素k个位置 每个元素可以选择去k中任何一个位置 元素选位置
    private boolean dfs(int[] nums, int level, int k, int[] ret, int subTot) {
        if (level == -1) {
            for (int sub : ret) {
                if (sub != subTot) return false;
            }
            return true;
        }

        for (int i = 0; i < k; i++) {
            ret[i] += nums[level];
            if ((ret[i] <= subTot) &&
                    dfs(nums, level-1, k, ret, subTot)) {
                return true;
            }
            //回溯时消除结果影响
            ret[i] -= nums[level];
        }
        return false;
    }

    // 位置选元素 每次先填满一个位置 按照组合dfs的思路去思考 既需要level 又需要visited数组去记录每个元素的使用情况
    private boolean dfs2(int[] nums, int[] visited, int level, int k, int curSum, int subTot) {
        // k个group都已经填完
        if (k==0) return true;
        // 当前group填已经填好 进入下一个group的填充 一切参数需要重置
        if (curSum == subTot) return dfs2(nums, visited, 0, k-1, 0, subTot);
        for (int i = level; i < nums.length; i++) {
            if (visited[i] == 1 || curSum + nums[i] > subTot) continue;
            visited[i] = 1;
            if (dfs2(nums, visited, i+1, k, curSum+nums[i], subTot)) return true;
            // 回溯时消除影响
            visited[i] = 0;
        }
        // 不在上面return true 证明找不到可行解
        return false;
    }

}
