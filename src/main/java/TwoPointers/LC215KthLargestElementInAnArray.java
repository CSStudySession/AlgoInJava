package TwoPointers;

/**
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 *
 * Example 2:
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 */
public class LC215KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return -1;
        return quickSelect(nums, 0, nums.length-1, k);
    }

    private int quickSelect(int[] nums, int lo, int hi, int k) {
        // 只剩下一个元素 直接返回元素本身
        if (lo >= hi) return nums[lo];
        int index = partition(nums, lo, hi);
        if (index == k - 1) {
            return nums[index];
        } else if (index > k - 1) {
            return quickSelect(nums, lo, index-1, k);
        } else {
            // 注意 舍掉左半边 是index+1!
            return quickSelect(nums, index+1, hi, k);
        }
    }

    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        int i = lo + 1;
        int j = hi;

        while (i <= j) {
            while (i <= j && nums[i] > pivot) {
                i++;
            }

            while (i <= j && nums[j] < pivot) {
                j--;
            }

            if (i >= j) break;

            exchange(nums, i, j);
            i++;
            j--;
        }
        // 最后需要把pivot和j指针上的元素互换
        exchange(nums, lo, j);
        return j;
    }

    private void exchange(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        LC215KthLargestElementInAnArray inst = new LC215KthLargestElementInAnArray();
        int[] nums = {99,99};
        int k = 1;
        inst.findKthLargest(nums, k);

        System.out.println(".");
    }

}
