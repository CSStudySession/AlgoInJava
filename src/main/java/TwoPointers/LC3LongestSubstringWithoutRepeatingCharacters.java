package TwoPointers;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LC3LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.trim().length() == 0) return 1;

        Set<Character> charSet = new HashSet<>();
        int result = 0;

        for (int i = 0, j = 0; i < s.length(); i++) {
            while (j < s.length()) {
                if (!charSet.contains(s.charAt(j))) {
                    charSet.add(s.charAt(j));
                    j++;
                } else {
                    break;
                }
            }
            result = Math.max(result, j - i);
            if (j == s.length()) break;

            charSet.remove(s.charAt(i));
        }

        return result;
    }

}
