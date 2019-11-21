package DP.Sequence;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Example:
 *
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Note:
 * You can assume that you can always reach the last index.
 *
 */
public class LC45JumpGameII {

    /*
    dp version 1
    jumps[i]:跳到index i所需要的最小次数
    jumps[i] 可以从[0, i-1]中的任何一个状态转移过来
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int[] jumps = new int[nums.length];
        jumps[0] = 0;

        int minStep = 0;
        for (int i = 1; i < nums.length; i++) {
            minStep = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (j != 0 && jumps[j] == 0) continue;
                if (j + nums[j] >= i) {
                    minStep = Math.min(minStep, jumps[j]+1);
                    jumps[i] = minStep;
                }
            }
        }
        return jumps[nums.length-1];
    }

    /*
    dp version 2：贪心
    cache[i]: 跳到下标i所用的最小步数
    在下标i处, 枚举所有可能的步数[ 1,nums[i] ] 更新能到达的cache[next position]
     */
    public int jump2(int[] nums) {
        int[] cache = new int[nums.length];

        cache[0] = 0;

        for (int i = 1; i < cache.length; i++) {
            cache[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                // 这里i+j要小于等于length-1 防止越界
                int next = Math.min(i+j, nums.length-1);

                cache[next] = Math.min(cache[next], 1 + cache[i]);
            }
        }

        return cache[nums.length-1];
    }
}
