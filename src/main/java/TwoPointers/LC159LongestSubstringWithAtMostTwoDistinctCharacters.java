package TwoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 * Example 1:
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 *
 * Example 2:
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 */
public class LC159LongestSubstringWithAtMostTwoDistinctCharacters {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.trim().length() == 0) return s.length();
        Map<Character, Integer> charToFreq = new HashMap<>();
        int result = 0;

        for (int i = 0, j = 0; i < s.length(); i++) {
            while (j < s.length()) {
                if (charToFreq.size() < 2 ||
                        (charToFreq.size() == 2 && charToFreq.containsKey(s.charAt(j)))) {
                    charToFreq.put(s.charAt(j), charToFreq.getOrDefault(s.charAt(j), 0)+1);
                    j++;
                } else {
                    break;
                }
            }
            result = Math.max(result, j - i);

            charToFreq.put(s.charAt(i), charToFreq.get(s.charAt(i))-1);
            if (charToFreq.get(s.charAt(i)) == 0) {
                charToFreq.remove(s.charAt(i));
            }
        }
        return result;
    }
}
