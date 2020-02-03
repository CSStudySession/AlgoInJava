package BinarySearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 *
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 *
 * Note:
 * Each element in the result must be unique.
 * The result can be in any order.
 *
 * 思路：先选择一个数组sort 比如数组2 (选择长的数组进行排序)
 * 然后对数组1里面的每个元素 从数组2中进行binary search 找到就放入结果set里面
 */
public class LC349IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 ||
                nums2.length == 0) return new int[] {};
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (Integer num : nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }
        int i = 0;
        int[] result = new int[set.size()];
        for (Integer num : set) {
            result[i++] = num;
        }
        return result;
    }

    public boolean binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[low] == target ? true : false;
    }
}
