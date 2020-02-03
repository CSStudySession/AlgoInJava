package Sort_OrderedMap;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * Example:
 * Input: nums = [3,5,2,1,6,4]
 * Output: One possible answer is [3,5,1,6,2,4]
 *
 * 思路:
 * 从左到右扫一遍，不满足条件的交换
 * 需要证明的是，当我们 交换了 nums[i] 和 nums[i - 1] 以后：
 * ... nums[i - 2], nums[i], nums[i - 1]
 * nums[i - 2] 不会和 nums[i] 形成逆序（不满足条件的大小关系）
 *
 * 那假如原来是 nums[i - 2] <= nums[i - 1]，那么 nums[i - 1] 和 nums[i] 交换的条件是，nums[i - 1] <= nums[i]。
 * 那我们就推导出此时 nums[i] >= nums[i - 2]，因此交换之后，不会让 nums[i] 和 nums[i - 2] 的大小关系出现变化。
 * 反过来如果 nums[i - 2] >= nums[i - 1] 的情况同理。
 */
public class LC280WiggleSort {

    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 满足条件的情况：奇数位的元素>=前一个偶数位的元素 && 偶数位元素<=前一个奇数位元素
            if((i % 2 == 1) && (nums[i] < nums[i-1]) ||
                    (i % 2 == 0) && (nums[i] > nums[i-1])) {
                swap(nums, i - 1, i);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
