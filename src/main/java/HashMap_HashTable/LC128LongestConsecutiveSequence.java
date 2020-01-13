package HashMap_HashTable;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers,
 * find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 * Therefore its length is 4.
 */
public class LC128LongestConsecutiveSequence {

    /*
    用Set而不是Map的解法 需要遍历2遍数组 第一遍先把数都放进Set 第二遍求长度
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxLen = 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            // set为空时 不用再遍历了
            if (set.isEmpty()) break;
            int len = 1;

            int bigger = nums[i] + 1;
            while (set.contains(bigger)) {
                len++;
                set.remove(bigger++);
            }

            int smaller = nums[i] - 1;
            while (set.contains(smaller)) {
                len++;
                set.remove(smaller--);
            }

            if (len > maxLen) {
                maxLen = len;
            }
        }

        return maxLen;
    }



}
