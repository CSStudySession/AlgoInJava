package TwoPointers;

import java.util.*;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * Example 1:
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 *
 * Example 2:
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 *
 */
public class LC340LongestSubstringWithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int j = 0, cur = 0, res = 0;

        for (int i = 0; i < s.length(); i++) {
            while (j < s.length()) {
                if (!map.containsKey(s.charAt(j)) && cur == k) break;
                if (!map.containsKey(s.charAt(j))) cur++;
                map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
                res = Math.max(res, j - i + 1);
                j++;
            }

            map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
            if (map.get(s.charAt(i)) == 0) {
                map.remove(s.charAt(i));
                cur--;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        LC340LongestSubstringWithAtMostKDistinctCharacters inst = new LC340LongestSubstringWithAtMostKDistinctCharacters();
        String s = "aba";
        int k = 1;

        int ret = inst.lengthOfLongestSubstringKDistinct(s, k);
        System.out.println(".");
    }
}
