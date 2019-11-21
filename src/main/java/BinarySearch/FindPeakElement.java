package BinarySearch;


/**
 * There is an integer array which has the following features:
 *
 * The numbers in adjacent positions are different.
 * We define a position P is a peak if:
 *
 * A[P] > A[P-1] && A[P] > A[P+1]
 * Find a peak element in this array. Return the index of the peak.
 *
 * Example
 * Example 1:
 * 	Input:  [1, 2, 1, 3, 4, 5, 7, 6]
 * 	Output:  1 or 6
 *
 * 	Explanation:
 * 	return the index of peek.
 *
 *
 * Example 2:
 * 	Input: [1,2,3,4,1]
 * 	Output:  3
 *
 * Challenge
 * Time complexity O(logN)
 */


public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length-1);
    }

    public int search(int[] nums, int i, int j) {
        if (i == j) {
            return i;
        }

        int mid = (i + j) / 2;
        if (nums[mid] > nums[mid+1]) {
            return search(nums, i, mid);
        } else {
            return search(nums, mid+1, j);
        }
    }


    /**
     *  A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
     */

    public int findPeak(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        if (nums.length == 1) return 0;

        if (nums.length == 2) return nums[0] > nums[1] ? 0 : 1;

        int i = 0, j = nums.length-1;
        int ret = -1;
        while (i + 1 < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] < nums[mid+1] && nums[mid-1] < nums[mid]) {
                i = mid;
            } else if (nums[mid] > nums[mid+1] && nums[mid-1] > nums[mid]) {
                j = mid;
            } else if (nums[mid] > nums[mid+1] && nums[mid-1] < nums[mid]) {
                ret = mid;
                break;
            } else {
                i = mid;
            }
        }

        return ret;
    }
}
