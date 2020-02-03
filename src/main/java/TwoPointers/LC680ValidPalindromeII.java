package TwoPointers;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 *
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 *
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 *
 * 思路：
 * 双指针算法。从两头走到中间，发现第一对不一样的字符之后，要么删左边的，要么删右边的。
 */
public class LC680ValidPalindromeII {
    // version 1: 双指针+要么删除左边要么删除右边
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left++;
            right--;
        }

        if (left >= right) {
            return true;
        }

        return isSubPalindrome(s, left + 1, right) || isSubPalindrome(s, left, right - 1);
    }

    private boolean isSubPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // version 2: 删除N个字符的通用模板
    public boolean validPalindrome2(String s) {
        return validate(s, 0, s.length() - 1, 0, 1);
    }

    public boolean validate(String s, int i, int j, int delCount, int N) {
        if (delCount > N) return false; // N - times of deletion allowed.

        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                delCount++;
                return validate(s, i, j - 1, delCount, N) || validate(s, i + 1, j, delCount, N);
            }
        }

        return true;
    }

}
