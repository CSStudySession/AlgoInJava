package BinarySearch;

/**
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
 * You need to return the number of important reverse pairs in the given array.
 *
 * Example1:
 * Input: [1,3,2,3,1]
 * Output: 2
 *
 * Example2:
 * Input: [2,4,3,5,1]
 * Output: 3
 * Note:
 * The length of the given array will not exceed 50,000.
 * All the numbers in the input array are in the range of 32-bit integer.
 *
 * 思路：
 * merge sort + CDQ分治
 */
public class LC493ReversePairs {

    public int reversePairs(int[] nums) {
        int n = nums.length;
        return divideAndConquer(nums, 0, n - 1);
    }

    // divide and conquer approach
    private int divideAndConquer(int[] nums, int lo, int hi) {
        if (lo >= hi) return 0;
        int result = 0;
        int mid = lo + (hi - lo) / 2;
        // 统计左边的逆序对
        result += divideAndConquer(nums, lo, mid);
        // 统计右边的逆序对
        result += divideAndConquer(nums, mid + 1, hi);
        // 统计跨过mid形成的逆序对
        result += merge(nums, lo, mid, hi);

        return result;
    }

    // 此时的数组 [lo...mid] [mid+1...hi] 分段有序
    private int merge(int[] nums, int lo, int mid, int hi) {
        int count = 0;
        int[] temp = new int[hi - lo + 1];
        int ptrA = lo;
        int ptrB = mid + 1;

        // 统计跨过mid的逆序对
        while (ptrA <= mid && ptrB <= hi) {
            if ((long)nums[ptrA] > 2 * (long)nums[ptrB]) {
                /*
                      pA      mid
                1,     3,     4

                1,     2
                pB     hi
                 */
                count += mid - ptrA + 1;
                ptrB++;
            } else {
                ptrA++;
            }
        }

        // 正常的merge sort中的merge阶段
        ptrA = lo;
        ptrB = mid + 1;
        int idx = 0;
        while (ptrA <= mid && ptrB <= hi) {
            if (nums[ptrA] > nums[ptrB]) {
                temp[idx++] = nums[ptrB++];
            } else {
                temp[idx++] = nums[ptrA++];
            }
        }
        while (ptrA <= mid) {
            temp[idx++] = nums[ptrA++];
        }
        while (ptrB <= hi) {
            temp[idx++] = nums[ptrB++];
        }

        idx = 0;
        for (int k = lo; k <= hi; k++) {
            nums[k] = temp[idx++];
        }

        return count;
    }

}
