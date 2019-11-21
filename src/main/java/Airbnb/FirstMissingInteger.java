package Airbnb;


/**
 * Given an unsorted integer array, findWithDuplicates the smallest missing positive integer.
 *
 * Example 1:
 *
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: [7,8,9,11,12]
 * Output: 1
 * Note:
 *
 * Your algorithm should run in O(n) time and uses constant extra space.
 */
public class FirstMissingInteger {

    public int firstMissingPositive(int[] nums) {


        // O(N)
        for (int i = 0; i < nums.length; ++i) {
            // this is constant time b/c it only runs through constant number of elements
            while(nums[i] > 0 && nums[i] <= nums.length &&
                    nums[nums[i]-1] != nums[i]) {
                int tmp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = tmp;
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }


}
