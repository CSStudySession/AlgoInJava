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

    // version 1: 二分分割位置
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


    // version 2: 每次扔掉k/2个数
    public double findMedianSortedArraysV2(int A[], int B[]) {
        int n = A.length + B.length;

        if (n % 2 == 0) {
            return (
                    findKth(A, 0, B, 0, n / 2) +
                            findKth(A, 0, B, 0, n / 2 + 1)
            ) / 2.0;
        }

        return findKth(A, 0, B, 0, n / 2 + 1);
    }

    // find kth number of two sorted array
    public static int findKth(int[] A, int startOfA,
                              int[] B, int startOfB,
                              int k) {
        // 下面所有的下标都要减一：第k个数下标为k-1
        // 某个数组的数都扔完了 直接返回对面数组的第start + k - 1个位置的数即可
        if (startOfA >= A.length) {
            return B[startOfB + k - 1];
        }

        if (startOfB >= B.length) {
            return A[startOfA + k - 1];
        }

        // 只剩一个数待检查 直接返回A,B数组里面小的那个即可
        // 这里不用查下标越界的原因是 上面两个if已经确保下标都合法了
        if (k == 1) {
            return Math.min(A[startOfA], B[startOfB]);
        }

        /*
        下面的两个比较 比较的是A和B的start + k/2 - 1的位置哪个数字小 谁小就把谁的前k/2个数字扔掉
        因为这k/2个数肯定不会包含结果 

        从start开始的第k/2个数的下标为 start + k/2 - 1
        如果start + k/2 - 1 为什么返回正最大？-> 把扔掉数字的机会给对面的数组
        比如: A有2个数 B有101个数 中位数是两数组合并后第52个数 每次应该从A或者B里面挑
        52/2 = 26个数字扔掉 但是A没有这么多数可以扔 那就扔B的26个数就可以 为什么可以这么做？
        因为求的是第52个数字 B扔掉26个 也不会扔掉可能的结果 所以当一个数组数字不够需要扔的数时
        选择扔对面的数组即可 会不会出现都不够的情况？不会 因为k是合法的 在都不够扔之前肯定就找到
        第k个数了
         */
        int halfKthOfA = startOfA + k / 2 - 1 < A.length
                ? A[startOfA + k / 2 - 1]
                : Integer.MAX_VALUE;
        int halfKthOfB = startOfB + k / 2 - 1 < B.length
                ? B[startOfB + k / 2 - 1]
                : Integer.MAX_VALUE;

        // 扔掉A从start开始的k/2个数 下一次A的起点变成A + start / 2 扔掉了k/2个 还剩k - k/2个数待检查
        if (halfKthOfA < halfKthOfB) {
            return findKth(A, startOfA + k / 2, B, startOfB, k - k / 2);
        } else {
            return findKth(A, startOfA, B, startOfB + k / 2, k - k / 2);
        }
    }

}
