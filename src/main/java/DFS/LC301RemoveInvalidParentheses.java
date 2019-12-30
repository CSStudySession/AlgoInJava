package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 *
 * Example 2:
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 *
 * Example 3:
 * Input: ")("
 * Output: [""]
 */
public class LC301RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        // 统计最少需要移除的左右括号
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                if (left == 0) {
                    right++;
                } else {
                    left--;
                }
            }
        }
        List<String> result = new ArrayList<>();
        dfs(s, 0, left, right, result);
        return result;
    }

    private void dfs(String str, int start, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            if (isValid(str))  {
                result.add(str);
            }
            return;
        }

        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) != ')' && str.charAt(i) != '(') continue;
            // 去重 对于"((()" 或者 "(())))" 只在第一次去删除多余的左或者右括号
            if (i != start && str.charAt(i) ==  str.charAt(i-1)) continue;
            // 只删除左或者右括号 跳过字母等其他无效字符
            if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                String strNxt = str.substring(0, i) + str.substring(i+1);
                // 先删除多余的右括号 因为右括号多肯定不合法
                if (right > 0 && str.charAt(i) == ')') {
                    dfs(strNxt, i, left, right- 1, result);
                } else if (left > 0 && str.charAt(i) == '(') {
                    // 右括号处理完了 处理左括号
                    dfs(strNxt, i, left - 1, right, result);
                }
            }
        }

    }

    // 判断是否是个合法的左右括号字符串
    private boolean isValid(String str) {
        int cnt = 0;
        for (int k = 0; k < str.length(); k++) {
            if (str.charAt(k) == '(') cnt++;
            if (str.charAt(k) == ')') cnt--;
            if (cnt < 0) return false;
        }
        return cnt == 0;
    }
}
