package StackQueue;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string
 *
 *  The expression string may contain open ( and closing parentheses ),
 * the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * The expression string contains only non-negative integers, +, -, *, / operators ,
 * open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-2147483648, 2147483647].
 *
 * Some examples:
 * "1 + 1" = 2
 * " 6-4 / 2 " = 4
 * "2*(5+5*2)/3+(6/2+8)" = 21
 * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 * Note: Do not use the eval built-in library function.
 */
public class LC772BasicCalculatorIII {

    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> nums = new Stack<>();   //数字栈
        Stack<Character> ops = new Stack<>();   //操作符栈
        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr == ' ') {
                continue;
            }

            // 字符串转化数字
            if (Character.isDigit(curr)) {
                num = curr - '0';
                // 数字长度可能大于1bit e.g.: 247
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                nums.push(num);			//数字直接存入栈中
                num = 0;
            } else if (curr == '(') {		//左括号直接存入
                ops.push(curr);
            } else if (curr == ')') {		// 遇到右括号就开始取操作符计算 直到看到左括号
                while (ops.peek() != '(') {		// 对栈顶两数字进行运算
                    nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                }
                // 括号之间的都运算完了 pop掉左括号
                ops.pop();
            } else if (curr == '+' || curr == '-' || curr == '*' || curr == '/') {   // 遇到操作符
                // 取数计算的条件:当前运算符的优先级不高于栈顶运算符的优先级
                while (!ops.isEmpty() && precedence(curr, ops.peek())) {
                    nums.push(operation(ops.pop(), nums.pop(),nums.pop()));
                }
                // ops栈优先级递增
                ops.push(curr);
            }
        }

        // calculate remaining items
        while (!ops.isEmpty()) {
            nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }

        return nums.pop();
    }

    // 把计算过程抽象出来
    private int operation(char operator, int rightHandOperand, int leftHandOperand) {

        switch (operator) {
            case '+': return leftHandOperand + rightHandOperand;   //加法
            case '-': return leftHandOperand - rightHandOperand;   //减法
            case '*': return leftHandOperand * rightHandOperand;   //乘法
            case '/': return leftHandOperand / rightHandOperand;   //除法
        }

        return 0;
    }

    // 比较op1和op2的优先级 如果op2优先级不低于op1 返回true otherwise返回false
    private boolean precedence(char op1, char op2) {
        // 左括号情况特殊 只要栈顶是左括号 就只能等遇到右括号再计算
        if (op2 == '(') {
            return false;
        }

        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }

        return true;
    }

}