package StackQueue;

import java.util.Stack;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that
 * the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 * Example 1:
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 * Example 2:
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 *
 * Example 3:
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 * Example 4:
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is one of  '(' , ')' and lowercase English letters.
 *
 * 思路:
 * use a stack and mark mismatched '(' and ')'. Here, we just negate the index to indicate ')'.
 * Note that we add 1 to make the index 1-based because we cannot know if 0 is '(' or ')'.
 */
public class LC1249MinimumRemoveToMakeValidParentheses {

    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> st = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.add(i + 1);
            }
            if (s.charAt(i) == ')') {
                // 正数为'(' 负数为')'
                if (!st.empty() && st.peek() > 0) {
                    st.pop();
                } else {
                    st.add(-(i + 1));
                }
            }
        }

        // stack里面存的是mismatch的括号下标
        for (int j = s.length() - 1; j >= 0; j--) {
            if (j == Math.abs(st.peek()) - 1) {
                st.pop();
            } else {
                sb.insert(0, s.charAt(j));
            }
        }

        return sb.toString();
    }
}












