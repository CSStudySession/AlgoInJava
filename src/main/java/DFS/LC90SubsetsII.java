package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums,
 * return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class LC90SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) return results;

        if (nums.length == 0) {
            results.add(new ArrayList<>());
            return results;
        }

        Arrays.sort(nums);
        List<Integer> subset = new ArrayList<>();
        helper(nums, 0, subset, results);
        return results;
    }
    public void helper(int[] nums, int startIndex,
                       List<Integer> subset, List<List<Integer>> results){
        results.add(new ArrayList<>(subset));

        for(int i = startIndex; i < nums.length; i++){
            if (i != startIndex && nums[i] == nums[i-1]) {
                continue;
            }
            subset.add(nums[i]);
            helper(nums, i + 1, subset, results);
            subset.remove(subset.size()-1);
        }
    }
}
