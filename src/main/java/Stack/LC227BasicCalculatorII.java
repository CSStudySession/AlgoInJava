package Stack;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 * The integer division should truncate toward zero.
 *
 * Example 1:
 * Input: "3+2*2"
 * Output: 7
 *
 * Example 2:
 * Input: " 3/2 "
 * Output: 1
 *
 * Example 3:
 * Input: " 3+5 / 2 "
 * Output: 5
 *
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class LC227BasicCalculatorII {

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("invalid input");
        }

        char[] strArr = s.toCharArray();
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int curNum = 0;

        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] == ' ') continue;

            if (Character.isDigit(strArr[i])) {
                while (i < strArr.length && Character.isDigit(strArr[i])) {
                    curNum = curNum * 10 + strArr[i] - '0';
                    i++;
                }
                i--;
                operands.push(curNum);
                curNum = 0;
            } else {
                if (strArr[i] == '*' || strArr[i] == '/') {
                    while (!operators.isEmpty() && (operators.peek() == '*' || operators.peek() == '/')) {
                        calc(operands, operators);
                    }

                } else {
                    while (!operators.isEmpty()) {
                        calc(operands, operators);
                    }
                }
                operators.push(strArr[i]);
            }
        }

        while (!operators.isEmpty()) {
            calc(operands, operators);
        }

        return operands.pop();
    }

    private void calc(Stack<Integer> operands, Stack<Character> operators) {
        int opndB = operands.pop();
        int opndA = operands.pop();
        char operator = operators.pop();
        int result = 0;

        switch(operator) {
            case '+':
                result = opndA + opndB;
                break;
            case '-':
                result = opndA - opndB;
                break;
            case '*':
                result = opndA * opndB;
                break;
            case '/':
                result = opndA / opndB;
                break;
        }

        operands.push(result);
    }


    public static void main(String[] args) {
        LC227BasicCalculatorII inst = new LC227BasicCalculatorII();
        String s ="3+2*2";
        int ret = inst.calculate(s);
        System.out.println(".");
    }
}












