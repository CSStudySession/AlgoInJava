package Stack;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 *
 * Example 2:
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class LC32LongestValidParentheses {

    public int longestValidParentheses(String s) {
        if (s == null) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int maxLen = 0;
        // 记录第一个左括号的位置
        int startIndex = 0;

        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // 有左括号就无脑入栈
                stack.push(i);
            } else { // 遇到右括号
                if (stack.isEmpty()) {
                    startIndex = i + 1;
                } else {
                    /*
                    两种情况：
                    1.栈不空:
                    ( ( ( ( )
                            i

                    2.栈空:
                    (           ( ) ( ) ( ) ( ) )
                  startIndex                    i
                     */
                    stack.pop();
                    maxLen = stack.isEmpty() ? Math.max(maxLen, i - startIndex + 1) :
                            Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }

}
