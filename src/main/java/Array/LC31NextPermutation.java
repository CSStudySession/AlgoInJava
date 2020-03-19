package Array;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place and use only constant extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 思路：找规律
 * 从右往左扫，找到第一个满足：nums[i-1] < nums[i]条件的，再找到从右到左第一个比nums[i-1]大的数，把它们swap，
 * 再把所有i-1之后的数字swap即可。
 *
 * 边界条件：
 * 1. i = nums.length - 1，这时候i-1之后只有一个值,
 * 2. 数组一直递减，这时候i变成0，没有nums[i-1] to swap，只需要swap从0到nums.length - 1的所有数
 * e.g: 1 2 3 (5) 8 (7) -> 1 2 3 (7) 8 (5) -> 1 2 3 (7) [5 8]
 * 注意数组下标不要越界！
 */
public class LC31NextPermutation {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int idx = nums.length - 1;

        while (idx > 0) {
            if (nums[idx-1] < nums[idx]) break;
            idx--;
        }

        if (idx > 0) {
            int ptr = nums.length - 1;
            while (ptr >= 0) {
                if (nums[ptr] > nums[idx - 1]) break;
                ptr--;
            }
            swap(nums, idx-1, ptr);
        }

        int right = nums.length - 1;
        while (idx < right) {
            swap(nums, idx, right);
            idx++;
            right--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
