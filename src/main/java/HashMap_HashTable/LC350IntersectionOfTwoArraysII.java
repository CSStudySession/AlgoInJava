package HashMap_HashTable;

import java.util.*;

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

    // version 1: map计数
    public int[] intersectUsingMap(int[] nums1, int[] nums2) {
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

    // version 2: binary search 
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        // swap to make nums1's len smaller than nums2's
        if (nums1.length > nums2.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        List<Integer> res = new ArrayList<>();
        int prevCnt = 0;
        for (int i = 0; i < nums1.length; i++) {
            // 如果是第一个元素 或者 当前元素跟之前的不一样 都要重新二分查找
            if (i == 0 || nums1[i - 1] != nums1[i]) {
                prevCnt = getCnt(nums2, nums1[i]);
                if (prevCnt == 0) continue; // 找不到 直接跳过
                prevCnt--;
                res.add(nums1[i]);
            } else {
                // 当前元素跟之前的一样
                if (prevCnt != 0) { // 如果之前的元素在另外的数组里还有
                    prevCnt--;
                    res.add(nums1[i]);
                }
            }
        }

        int[] ans = new int[res.size()];
        int idx = 0;
        for (int num : res) {
            ans[idx++] = num;
        }

        return ans;
    }

    // 在nums中 找target出现的次数
    private int getCnt(int[] nums, int target) {
        int left = binarySearch(nums, target);
        if (nums[left] != target) return 0;
        int right = binarySearch(nums, target + 1);
        // 两种情况 1.right停在target  2.right停在第一个比target大的元素上
        return nums[right] == target ? right - left + 1 : right - left;
    }

    // 在nums中找第一个大于或等于target的元素 注意如果不存在 则返回的是target应该插入的位置
    private int binarySearch(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // 不停Push右边界 (lo+1,hi) 二分模板
            if (nums[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public static void main(String[] args) {
        LC350IntersectionOfTwoArraysII inst = new LC350IntersectionOfTwoArraysII();
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};

        int[] res = inst.intersect(nums1, nums2);
        System.out.println(".");

    }


}
