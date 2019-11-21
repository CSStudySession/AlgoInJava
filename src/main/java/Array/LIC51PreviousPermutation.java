package Array;

import java.util.List;

/**
 * Given a list of integers, which denote a permutation.
 *
 * Find the previous permutation in ascending order.
 *
 * Example
 * Example 1:
 * Input:[1]
 * Output:[1]
 *
 * Example 2:
 * Input:[1,3,2,3]
 * Output:[1,2,3,3]
 *
 * Example 3:
 * Input:[1,2,3,4]
 * Output:[4,3,2,1]
 *
 * next permutation的逆过程
 * 从右到左找第一个上升沿 再从右到左找第一个比上升沿小的数字 将其互换
 * 然后翻转上升沿下标右边的整个数组
 * e.g: 1 2 3 7 5 8 -> 1 2 3 (7) (5) 8 -> 1 2 3 (5) [7 8] -> 1 2 3 5 [8 7]
 *
 */
public class LIC51PreviousPermutation {

    public List<Integer> previousPermuation(List<Integer> nums) {
        if (nums == null || nums.size() == 1) return nums;

        int idx = nums.size() - 1;
        while (idx > 0) {
            if (nums.get(idx-1) > nums.get(idx)) break;
            idx--;
        }

        if (idx > 0) {
            int ptr = nums.size() - 1;
            while (ptr >= idx-1) {
                if (nums.get(ptr) < nums.get(idx-1)) break;
                ptr--;
            }
            swap(nums, idx-1, ptr);
        }

        int right = nums.size()-1;
        while (idx < right) {
            swap(nums, idx, right);
            idx++;
            right--;
        }

        return nums;
    }

    private void swap(List<Integer> nums, int i, int j) {
        int tmp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, tmp);
    }

}
