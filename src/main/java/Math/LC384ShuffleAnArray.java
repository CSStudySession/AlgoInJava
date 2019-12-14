package Math;

import java.util.Random;

/**
 * Shuffle a set of numbers without duplicates.
 *
 * Example:
 * // Init an array with set 1, 2, and 3.
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 *
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 *
 * // Returns the random shuffling of array [1,2,3].
 * solution.shuffle();
 */
public class LC384ShuffleAnArray {

    private int[] nums;

    public LC384ShuffleAnArray(int[] nums) {
        this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] cur = nums.clone();
        Random random = new Random();
        for (int i = cur.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(cur, i, j);
        }

        /*
        // 与上面的for循环效果 一样
        int len = cur.length;
        while (len > 1) {
            int idx = random.nextInt(len);
            swap(cur, idx, len-1);
            len--;
        }
        */
        return cur;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
