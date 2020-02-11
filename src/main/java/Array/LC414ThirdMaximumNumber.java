package Array;

/**
 * Given a non-empty array of integers, return the third maximum number in this array.
 * If it does not exist, return the maximum number. The time complexity must be in O(n).
 *
 * Example 1:
 * Input: [3, 2, 1]
 * Output: 1
 * Explanation: The third maximum is 1.
 *
 * Example 2:
 * Input: [1, 2]
 * Output: 2
 * Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 *
 * Example 3:
 * Input: [2, 2, 3, 1]
 * Output: 1
 * Explanation: Note that the third maximum here means the third maximum distinct number.
 * Both numbers with value 2 are both considered as second maximum.
 */
public class LC414ThirdMaximumNumber {

    public int thirdMax(int[] nums) {
        // 0: small 1: mid 2: large
        long memory[] = {Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE};
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == memory[0] || nums[i] == memory[1] || nums[i] == memory[2]) continue;
            if(nums[i] > memory[2]) {
                memory[0] = memory[1];
                memory[1] = memory[2];
                memory[2] = nums[i];
            } else if (nums[i] > memory[1]){
                memory[0] = memory[1];
                memory[1] = nums[i];
            } else if (nums[i] > memory[0]) {
                memory[0] = nums[i];
            }
        }

        // 不存在第三大 返回最大的
        for(int i = 0; i < memory.length; i++) {
            if (memory[i] == Long.MIN_VALUE) return (int)memory[2];
        }

        //  存在第三大
        return (int)memory[0];
    }

}
