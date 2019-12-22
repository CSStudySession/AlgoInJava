package TwoPointers;

/**
 * Given strings S and T, find the minimum (contiguous) substring W of S,
 * so that T is a subsequence of W.
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such minimum-length windows, return the one with the left-most starting index.
 *
 * Example 1:
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 * Note:
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 *
 * 思路：
 * 遍历S，更新T的index
 * 当找到T中最后一个字符后，往回找到最优candidate，然后从最优的左pointer + 1 开始下一次搜索
 * 对找到的window的size进行比较更新
 */
public class LC727MinimumWindowSubsequence {

    public String minWindow(String S, String T) {
        int minL = 0;
        int minLen =  Integer.MAX_VALUE;
        char[] sArr = S.toCharArray();
        char[] tArr = T.toCharArray();

        for (int sIdx = 0, tIdx = 0; sIdx < S.length(); sIdx++) {

            if (sArr[sIdx] == tArr[tIdx]) {
                tIdx++;
            }

            // 找到一组可行解
            if (tIdx == tArr.length) {
                // reverse to find best candidate
                int left = sIdx;
                while (left > minL) {
                    if (sArr[left] == tArr[tIdx - 1]) {
                        tIdx--;
                    }
                    if (tIdx == 0) break; // found the best candidate
                    left--;
                }

                if (sIdx - left + 1 < minLen) {
                    minLen = sIdx - left + 1;
                    minL = left;
                }

                tIdx = 0;
                sIdx = left;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : S.substring(minL, minL + minLen);
    }
}
