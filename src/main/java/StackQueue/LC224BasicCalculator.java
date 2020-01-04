package StackQueue;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ),
 * the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * Example 1:
 * Input: "1 + 1"
 * Output: 2
 *
 * Example 2:
 * Input: " 2-1 + 2 "
 * Output: 3
 *
 * Example 3:
 * Input: "(1+(4+5+2)-3)+ (6+8)"
 * Output: 23
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class LC224BasicCalculator {

    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int curNum = 0;
        char[] strArr = s.toCharArray();

        for (int i = 0; i < strArr.length; i++) {
            char c = strArr[i];
            if (c == ' ') continue;

            if (c == '(' || c == '+' || c == '-') {
                operators.push(c);
                continue;
            }

            if (c == ')') {
                operators.pop();
            }

            else if (Character.isDigit(c)) {
                while (i < strArr.length && Character.isDigit(strArr[i])) {
                    curNum = curNum * 10 + strArr[i] - '0';
                    i++;
                }
                i--;
                operands.push(curNum);
                curNum = 0;
            }

            if (operators.isEmpty() || operators.peek() == '(') continue;

            int num1 = operands.pop();
            int num2 = operands.pop();

            if (operators.peek() == '+') {
                operands.push(num2 + num1);
            } else {
                operands.push(num2 - num1);
            }
            operators.pop();
        }

        return operands.pop();
    }

}

