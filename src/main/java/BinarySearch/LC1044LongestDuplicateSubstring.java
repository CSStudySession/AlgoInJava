package BinarySearch;


import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S, consider all duplicated substrings: (contiguous) substrings of S
 * that occur 2 or more times.  (The occurrences may overlap.)
 *
 * Return any duplicated substring that has the longest possible length.
 * (If S does not have a duplicated substring, the answer is "".)
 *
 * Example 1:
 * Input: "banana"
 * Output: "ana"
 *
 * Example 2:
 * Input: "abcd"
 * Output: ""
 *
 * Note:
 * 2 <= S.length <= 10^5
 * S consists of lowercase English letters.
 */
public class LC1044LongestDuplicateSubstring {

    private String ret = "";
    public String longestDupSubstring(String S) {
        int len = S.length();
        int left = 1;
        int right = len;
        int mod = Integer.MAX_VALUE / 33;

        // 二分区间 [left, right]
        while (left < right) {
            int tarLen = left + (right - left) / 2;
            int strIdx = search(S, tarLen, mod);
            if (strIdx != -1) {
                if (tarLen > ret.length()) {
                    ret = S.substring(strIdx, strIdx + tarLen);
                }
                left = tarLen + 1;
            } else {
                right = tarLen;
            }
        }
        return ret;
    }

    public int search(String S, int tarLen, int mod) {
        Map<Integer, Integer> dict = new HashMap<>();

        int power = 1;
        for (int k = 0; k < tarLen; k++) {
            power = (power * 33) % mod;
        }

        int curHash = 0;
        for (int i = 0; i < S.length(); i++) {
            curHash = (curHash * 33 + S.charAt(i) - 'a') % mod;
            if (i < tarLen - 1) continue;

            if (i >= tarLen) {
                curHash = curHash - ((S.charAt(i-tarLen) - 'a') * power) % mod;
                if (curHash < 0) curHash += mod;
            }

            if (dict.containsKey(curHash)) {
                int prevIdx = dict.get(curHash);
                if (S.substring(prevIdx, prevIdx+tarLen).equals(S.substring(i-tarLen+1, i+1))) {
                    return i - tarLen + 1;
                }
            } else {
                dict.put(curHash, i - tarLen + 1);
            }
        }

        return -1;
    }

    public static void main (String[] args) {
        LC1044LongestDuplicateSubstring inst =  new LC1044LongestDuplicateSubstring();
        String S = "banana";
        // int idx = inst.search(S,  3, Integer.MAX_VALUE / 33);
        String ret = inst.longestDupSubstring(S);
        System.out.println(".");
    }

}
