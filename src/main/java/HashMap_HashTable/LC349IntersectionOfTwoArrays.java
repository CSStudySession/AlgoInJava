package HashMap_HashTable;

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
 * 思路：Set去重
 * 一个set存一个数组的值(最好存短的数组 少占用空间) 另一个set存公共的重叠部分
 * 为什么用两个set? 因为输出不允许重复 如果允许重复 只用一个set即可
 */
public class LC349IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }

        int[] result = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {
            result[i++] = num;
        }

        return result;
    }

}
