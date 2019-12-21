package DFS;

/**
 * Return the result of evaluating a given boolean expression, represented as a string.
 * An expression can either be:
 * "t", evaluating to True;
 * "f", evaluating to False;
 * "!(expr)", evaluating to the logical NOT of the inner expression expr;
 * "&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
 * "|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...
 *
 * Example 1:
 * Input: expression = "!(f)"
 * Output: true
 *
 * Example 2:
 * Input: expression = "|(f,t)"
 * Output: true
 *
 * Example 3:
 * Input: expression = "&(t,f)"
 * Output: false
 *
 * Example 4:
 * Input: expression = "|(&(t,f,t),!(t))"
 * Output: false
 *
 * Constraints:
 * 1 <= expression.length <= 20000
 * expression[i] consists of characters in {'(', ')', '&', '|', '!', 't', 'f', ','}.
 * expression is a valid expression representing a boolean, as given in the description.
 *
 * Time complexity: O(n)
 * Space complexity: O(n)
 */

public class LC1106ParsingABooleanExpression {

    public boolean parseBoolExpr(String expression) {
        char[] exprArr = expression.toCharArray();
        int[] idx = {0};
        return parse(exprArr, idx);
    }

    // 注意跳过左右括号
    private boolean parse(char[] exprArr, int[] idx) {
        char ch = exprArr[idx[0]];
        idx[0]++;
        if (ch == 't') return true;
        if (ch == 'f') return false;
        if (ch == '!') {
            idx[0]++;
            boolean ret = !parse(exprArr, idx);
            idx[0]++;
            return ret;
        }
        boolean isAnd = (ch == '&');
        boolean ret = isAnd;
        idx[0]++;
        while (true) {
            if (isAnd) ret &= parse(exprArr, idx);
            else ret |= parse(exprArr, idx);
            if (exprArr[idx[0]++] == ')') {
                break;
            }
        }
        return ret;
    }
}
