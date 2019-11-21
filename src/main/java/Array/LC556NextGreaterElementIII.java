package Array;

/**
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer
 * which has exactly the same digits existing in the integer n and is greater in value than n.
 * If no such positive 32-bit integer exists, you need to return -1.
 *
 * Example 1:
 * Input: 12
 * Output: 21
 *
 * Example 2:
 * Input: 21
 * Output: -1
 */
public class LC556NextGreaterElementIII {

    public int nextGreaterElement(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        int len = chars.length, pos = len - 1;

        // 从右往左找到第一个降序
        while (pos > 0 && chars[pos] <= chars[pos - 1]) {
            pos--;
        }
        // 找不到 直接返回-1
        if (pos == 0) {
            return -1;
        }

        for (int i = len - 1; i > pos - 1; i--) {
            if (chars[i] > chars[pos - 1]) {
                swap(chars, i, pos - 1);
                break;
            }
        }

        reverse(chars, pos, len - 1);
        long res = Long.valueOf(new String(chars));
        if(res > Integer.MAX_VALUE) {
            return -1;
        }
        return (int)res;
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            swap(chars, i++, j--);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

}
