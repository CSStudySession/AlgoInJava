package TwoPointers;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Example：
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class LC283MoveZeroes {
    // version 1: j 指针往后找非零元素 跟i指针指向的数字交换 然后i++, j++
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && nums[j] == 0) {
                j++;
            }
            if (j == nums.length) break;
            swap(nums, i, j);
            j++; // 注意这里需要j++ i在外层循环中自动++了
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // version 2: min number of write
    /*
    计算每个非0元素num[i]之前0的个数，如果0的个数是1，num[i]往前挪一个位置，如果是2，往前挪两个，以此类推。
    最后一个数挪完了就往剩下的位置里填0。
    因为是从前往后遍历，所以这样move不会覆盖数组里的非零元素。
    这是move最少的了，因为每个元素都直接挪到目的位置
     */
    public void moveZeroesMinWrites(int[] nums) {
        int zeroNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroNum++;
            } else {
                // 每碰到一个非0数num[i],就把它移到 i-zeroNum 的位置
                if (zeroNum != 0) {
                    nums[i - zeroNum] = nums[i];
                }
            }
        }
        // 回过头来填上0
        for (int i = nums.length - zeroNum; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[i] = 0;
            }
        }
    }

}
