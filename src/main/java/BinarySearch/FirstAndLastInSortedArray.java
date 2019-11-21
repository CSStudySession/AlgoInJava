package BinarySearch;


/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class FirstAndLastInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        int s = 0, e = nums.length - 1;

        while (s <= e) {
            while (s + 1 < e && nums[s] == nums[s+1]) {
                s++;
            }

            while (e - 1 > s && nums[e] == nums[e-1]) {
                e--;
            }


            int m = (s + e) / 2;
            //System.out.println(s + ", " + e + ", " + m);
            if (nums[m] == target) {
                int k = m-1;
                while (k >= 0 && nums[k] == nums[m]) {
                    k--;
                }

                int l = m+1;
                while (l < nums.length && nums[l] == nums[m]) {
                    l++;
                }

                return new int[]{k+1, l-1};
            } else if (nums[m] > target) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }

        return new int[]{-1,-1};
    }
}
