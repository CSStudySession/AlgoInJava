package HashMap_HashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * We define a harmounious array as an array where the difference
 * between its maximum value and its minimum value is exactly 1.
 * Now, given an integer array, you need to find the length of
 * its longest harmonious subsequence among all its possible subsequences.
 *
 * Example 1:
 * Input: [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 * Note: The length of the input array will not exceed 20,000.
 */
public class LC594LongestHarmoniousSubsequence {
    /*
    因为subsequence可以不连续 所以不关心元素的绝对顺序
    建立 数字 -> 出现次数 的映射 然后按顺序遍历每个原数组的数字 只要（数字+1）在数组里存在
    那么[数字, 数字+1] 就是一个合法的组合 从映射里取出个数相加即可
     */
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> numToCnt = new HashMap<>();
        for (int num : nums) {
            numToCnt.put(num, numToCnt.getOrDefault(num, 0) + 1);
        }

        int result = 0;
        for (int num : nums) {
            if (numToCnt.containsKey(num + 1)) {
                result = Math.max(result, numToCnt.get(num) + numToCnt.get(num + 1));
            }
        }
        return result;
    }
}
