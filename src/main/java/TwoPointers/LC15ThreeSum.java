package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 思路:双指针
 *
 * followup: 元素可以重复使用 但是只能是三个元素相加
 * 传到two sum那一层时 idx可以用上一层用过的元素 并且left和right可以取同一个元素:while(left<=right)
 *
 * followup: 输出index而不是元素本身
 * ref: https://www.1point3acres.com/bbs/thread-273676-1-1.html
 */
public class LC15ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length <= 2) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // a + b + c == 0 -> -a = b + c
            if (i != 0 && nums[i] == nums[i-1]) continue; // 去重
            if (nums[i] > 0) break; // 最小数大于零 后面的数肯定都大于零
            List<List<Integer>> curRes = twoSum(nums, i + 1, -nums[i]);
            for (int j = 0; j < curRes.size(); j++) {
                curRes.get(j).add(nums[i]);
            }
            res.addAll(curRes);
        }

        return res;
    }
    // twoSum as subroutine
    private List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 2) return res;
        int left = start;
        int right = nums.length - 1;

        while (left < right) {
            if (nums[left] + nums[right] == target) {
                List<Integer> curList = new ArrayList<>();
                curList.add(nums[left]);
                curList.add(nums[right]);
                res.add(curList);
                left++;
                right--;
                //去重！left和right都可能与之前看到的元素重复
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }

        return res;
    }

}
