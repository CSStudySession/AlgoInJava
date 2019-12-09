package DFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ),
 * the plus + or minus sign -, non-negative integers and empty spaces .
 * The expression string contains only non-negative integers, +, -, *, / operators,
 * open ( and closing parentheses ) and empty spaces .
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-2147483648, 2147483647].
 *
 * Some examples:
 * "1 + 1" = 2
 * " 6-4 / 2 " = 4
 * "2*(5+5*2)/3+(6/2+8)" = 21
 * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 */
public class LC772BasicCalculatorIII {

    //
    public int calculate(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') {
                queue.offer(c);
            }
        }
        queue.offer(' ');
        return dfs(queue);
    }

    private int dfs (Queue<Character> queue) {
        int num = 0;
        int prev = 0;
        int sum = 0;
        char prevOp = '+';

        while (!queue.isEmpty()) {
            char c = queue.poll();

            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                num = dfs(queue);
            } else {
                switch(prevOp) {
                    case '+': {
                        sum += prev;
                        prev = num;
                        break;
                    }
                    case '-':{
                        sum += prev;
                        prev = -num;
                        break;
                    }
                    case '*': {
                        prev *= num;
                        break;
                    }
                    case '/': {
                        prev /= num;
                        break;
                    }
                }
                if (c == ')') break;
                prevOp = c;
                num = 0;
            }
        }
        return sum + prev;
    }
}
