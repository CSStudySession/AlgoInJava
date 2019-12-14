package Math;

/**
 * Given a non-negative integer, you could swap two digits at most once
 * to get the maximum valued number. Return the maximum valued number you could get.
 *
 * Example 1:
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 *
 * Example 2:
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 * Note:
 * The given number is in the range [0, 108]
 */
public class LC670MaximumSwap {

    /*
    贪心法: 从前往后看 每一位找后面比它大的最大位进行交换
    2 9 9 6: 2跟倒数第二位9交换 -> 9 9 2 6
     */
    public int maximumSwap(int num) {
        if (num <= 10) return num;

        char[] numChars = String.valueOf(num).toCharArray();
        // 记录每一位出现的最后位置
        int[] lstIdx = new int[10];

        for (int i = 0; i < numChars.length; i++) {
            lstIdx[numChars[i] - '0'] = i;
        }

        for (int i = 0; i < numChars.length; i++) {
            // 从大到小枚举可能换的数字
            for (int d = 9; d > numChars[i] - '0'; d--) {
                if (lstIdx[d] > i) { // 只能跟后面的换
                    swap(numChars, i, lstIdx[d]);
                    return Integer.valueOf(String.valueOf(numChars));
                }
            }
        }
        return num;
    }

    private void swap(char[] numChars, int i, int j) {
        char tmp = numChars[i];
        numChars[i] = numChars[j];
        numChars[j] = tmp;
    }

}
