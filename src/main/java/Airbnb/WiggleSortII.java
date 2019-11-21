package Airbnb;


import java.util.Random;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * Example 1:
 *
 * Input: nums = [1, 5, 1, 1, 6, 4]
 * Output: One possible answer is [1, 4, 1, 5, 1, 6].
 * Example 2:
 *
 * Input: nums = [1, 3, 2, 2, 3, 1]
 * Output: One possible answer is [2, 3, 1, 3, 1, 2].
 * Note:
 * You may assume all input has valid answer.
 *
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class WiggleSortII {

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    private int partition(int[] nums, int left, int right, int pivot_index) {
        int pivot = nums[pivot_index];

        swap(nums, pivot_index, right);
        // this one calculate how many elements are less than then nums[pivot_index]
        int store_index = left;

        for (int i = left; i <= right; ++i) {
            if (nums[i] < pivot) {
                swap(nums, store_index, i);
                store_index++;
            }
        }

        swap(nums, store_index, right);

        return store_index;
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        Random random = new Random();
        int pivot_index = left + random.nextInt(right - left);

        pivot_index = partition(nums, left, right, pivot_index);

        if (k == pivot_index) {
            return nums[k];
        } else if(k < pivot_index){
            return quickSelect(nums, left, pivot_index - 1, k);
        } else {
            return quickSelect(nums, pivot_index + 1, right, k);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length-1, nums.length - k);
    }

    private int getVirtualIndex(int index, int n) {
        return (2*index + 1) %  (n | 1);
    }

    public void wiggleSort(int[] nums) {
        int median = findKthLargest(nums, nums.length / 2);

        for (int num : nums) {
            System.out.println(num);
        }

        int i = 0, left = 0, n = nums.length, right = n - 1;
        while (i <= right) {
            if (nums[getVirtualIndex(i,n)] > median) {
                swap(nums, getVirtualIndex(left++,n), getVirtualIndex(i++,n));
            }
            else if (nums[getVirtualIndex(i,n)] < median) {
                swap(nums, getVirtualIndex(right--,n), getVirtualIndex(i,n));
            }
            else {
                i++;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {3,4,2,5,1};
        WiggleSortII wiggleSortII = new WiggleSortII();
        wiggleSortII.wiggleSort(nums);
    }
}
