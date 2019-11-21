package TwoPointers;

/**
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: "cbbd"
 * Output: "bb"
 *
 */
public class LC5LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int start = 0;
        int len = 0;
        int longest = 0;
        for (int i = 0; i < s.length(); i++) {
            len = findLongestPalindromeFrom(s, i, i);
            if (len > longest) {
                longest = len;
                start = i - len / 2;
            }

            len = findLongestPalindromeFrom(s, i, i + 1);
            if (len > longest) {
                longest = len;
                start = i - len / 2 + 1;
            }
        }

        return s.substring(start, start + longest);
    }

    private int findLongestPalindromeFrom(String s, int left, int right) {
        int len = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            len += left == right ? 1 : 2;
            left--;
            right++;
        }

        return len;
    }
}
