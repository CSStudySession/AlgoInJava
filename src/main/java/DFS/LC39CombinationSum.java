package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class LC39CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(ret, new ArrayList<>(), candidates, 0, target);
        return ret;
    }

    private void dfs(List<List<Integer>> ret, List<Integer> temp, int[] candidates,
                     int level, int target) {
        if (target == 0) {
            ret.add(new ArrayList<>(temp));
            return;
        }
        // i=level,不是i=0!!!
        for (int i = level; i < candidates.length; i++) {
            if (target - candidates[i] >= 0) { // 剪枝
                temp.add(candidates[i]);
                // i传到下一层 不是传level本身！i不加一因为可以重复取某个数本身
                dfs(ret, temp, candidates, i, target-candidates[i]);
                //回溯时消除影响
                temp.remove(temp.size()-1);
            }
        }
    }

}
