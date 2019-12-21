package DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer array, your task is to find all the different possible
 * increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2.
 *
 * Example:
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 *
 * Note:
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also be
 * considered as a special case of increasing sequence.
 */
public class LC491IncreasingSubsequences {

    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        dfs(nums, set, new ArrayList<>(), 0);
        return new ArrayList<>(set);
    }

    private void dfs(int[] nums,
                     Set<List<Integer>> set,
                     List<Integer> tmp,
                     int startIdx) {
        if (tmp.size() > 1) {
            // 利用set机制去重 注意set里面放list是可以根据元素来去重的
            set.add(new ArrayList<>(tmp));
        }

        for (int i = startIdx; i < nums.length; i++) {
            // 可以加入的情况 1.目前tmp里没有元素 2.准备加入的nums[i]不小于tmp最后一个元素(tmp是有序的)
            if (tmp.size() == 0 || tmp.get(tmp.size() - 1) <= nums[i]) {
                tmp.add(nums[i]);
                dfs(nums, set, tmp, i + 1);
                // 回溯时消除影响
                tmp.remove(tmp.size() - 1);
            }
        }
    }

}
