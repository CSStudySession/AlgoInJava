package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class LC46Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null || nums.length == 0) return ret;
        int[] visited = new int[nums.length];
        dfs(ret, new ArrayList<>(), visited, nums);
        return ret;
    }
    private void dfs(List<List<Integer>> ret, List<Integer> temp,
                     int[] visited, int[] nums) {
        if (temp.size() == nums.length) {
            ret.add(new ArrayList<>(temp));
            return;
        }
        //与组合不同 排列的话可以从头开始选 所以i每次从0开始
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            temp.add(nums[i]);
            visited[i] = 1;
            dfs(ret, temp, visited, nums);
            //回溯时temp和visited数组都要消除影响！
            temp.remove(temp.size()-1);
            visited[i] = 0;
        }
    }

}
