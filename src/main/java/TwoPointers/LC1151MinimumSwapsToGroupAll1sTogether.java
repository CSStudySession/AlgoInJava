package TwoPointers;

/**
 * Given a binary array data, return the minimum number of swaps required to
 * group all 1’s present in the array together in any place in the array.
 *
 * Example 1:
 * Input: [1,0,1,0,1]
 * Output: 1
 * Explanation:
 * There are 3 ways to group all 1's together:
 * [1,1,1,0,0] using 1 swap.
 * [0,1,1,1,0] using 2 swaps.
 * [0,0,1,1,1] using 1 swap.
 * The minimum is 1.
 *
 * Example 2:
 * Input: [0,0,0,1,0]
 * Output: 0
 * Explanation:
 * Since there is only one 1 in the array, no swaps needed.
 *
 * Example 3:
 * Input: [1,0,1,0,1,0,0,1,1,0,1]
 * Output: 3
 * Explanation:
 * One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
 *
 * Note:
 * 1 <= data.length <= 10^5
 * 0 <= data[i] <= 1
 */
public class LC1151MinimumSwapsToGroupAll1sTogether {

    public int minSwaps(int[] data) {
        if(data.length < 3) return 0;

        int n = 0;
        for (int num: data) {
            if (num == 1) n++; // 统计1的个数作为滑窗大小
        }

        if (n < 2) return 0;

        int curr = 0;
        int result = Integer.MAX_VALUE;
        // 维护滑窗[i,j]长度为n 所以i+n <= len
        for (int i = 0, j = 0; i + n <= data.length; i++) {
            // j不会后退 当到头或者j-i==n的时候停下 让i往前走
            while (j < data.length && j - i < n) {
                // 找窗口里的0 每一个0对应换一次窗口外的1
                if (data[j] == 0) {
                    curr++;
                }
                j++; // 不能忘了移动j
            }
            // 打擂台 更新全局结果
            result = Math.min(result, curr);
            if (j == data.length) break; // j到头 最后一个n大小的窗口已遍历完 直接退出
            // 如果i指向0 就还回去
            if (data[i] == 0) curr--;
        }

        return result;
    }

}
