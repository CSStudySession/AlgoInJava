package BinarySearch;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 *
 * Example 2
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */
public class LC4MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1 == null && nums2 == null) || (nums1.length == 0 && nums2.length == 0)) return 0.0;

        // nums1始终指向较短的数组
        if (nums2.length < nums1.length) {
            int[] tmp = nums2;
            nums2 = nums1;
            nums1 = tmp;
        }

        int len1 = nums1.length;
        int len2 = nums2.length;
        int k = (len1 + len2 + 1) / 2; // 6->3, 5->3
        int left = 0, right = len1;
        int ptr1 = 0, ptr2 = 0; // 每个数组左侧元素的数量

        // 二分分割位置 最后left==right的时候是合法的分割点
        while (left <= right) {
            // 短数组取出的元素的个数
            ptr1 = left + (right - left) / 2;
            // 长数组取出的元素个数
            ptr2 = k - ptr1;
            /*
               a b
              xx|xx
                  c d
              ooooo|ooooo
              一个合法的切割 需要满足下述关系:
              a <= d && c <= b (when a,b,c,d都存在)
             */
            // 先看a和d的比较 注意ptr1 - 1是否存在
            if (ptr1 > 0 && nums1[ptr1 - 1] > nums2[ptr2]) {
                // nums1左边取多了 nums1需要调整右边界
                right = ptr1 - 1;
            } else if (ptr1 != len1 && nums2[ptr2 - 1] > nums1[ptr1]) {
                // nums2左边取多了 nums1需要调整左边界
                left = ptr1 + 1;
            } else {
                // 找到合法的分割点了
                double smaller = 0.0;
                if (ptr1 == 0) {
                    smaller = nums2[ptr2 - 1];
                } else if (ptr2 == 0) {
                    smaller = nums1[ptr1 - 1];
                } else {
                    smaller = Math.max(nums1[ptr1 - 1], nums2[ptr2 - 1]);
                }
                // 奇数个元素 直接返回结果
                if ((len1 + len2) % 2 != 0) return smaller;

                // 偶数个元素 还需要选择一个元素求平均值
                double larger = 0.0;
                if (ptr1 == len1) {
                    larger = nums2[ptr2];
                } else if (ptr2 == len2) {
                    larger = nums1[ptr1];
                } else {
                    // 靠近分割线的数
                    larger = Math.min(nums1[ptr1], nums2[ptr2]);
                }
                return (smaller + larger) / 2;
            }
        }
        return 0.0;
    }

}
