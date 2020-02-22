package HashMap_HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 *
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 *
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 *
 * Follow up：
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that
 * you cannot load all elements into the memory at once?
 *
 * followup思路:
 * Q. What if the given array is already sorted? How would you optimize your algorithm?
 * If both arrays are sorted, I would use two pointers to iterate,
 * which somehow resembles the merge process in merge sort.
 *
 * Q. What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * Suppose lengths of two arrays are N and M, the time complexity of my solution is O(N+M)
 * and the space complexity if O(N) considering the hash. So it's better to use the smaller array
 * to construct the counter hash.
 * Or use binary search approach to do this problem.
 *
 * Q. What if elements of nums2 are stored on disk, and the memory is limited such that
 * you cannot load all elements into the memory at once?
 * Divide and conquer. Repeat the process frequently: Slice nums2 to fit into memory,
 * process (calculate intersections), and write partial results to memory.
 * Or perform MapReduce if data is too big.
 */
public class LC350IntersectionOfTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            int freq = map.getOrDefault(i, 0);
            map.put(i, freq + 1);
        }

        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if(map.get(i) != null && map.get(i) > 0) {
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }

        int[] ret = new int[list.size()];
        for(int i = 0; i < list.size();i++){
            ret[i] = list.get(i);
        }

        return ret;
    }

}
