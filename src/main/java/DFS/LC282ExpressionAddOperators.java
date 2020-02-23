package DFS;

import java.util.ArrayList;
import java.util.List;
/**
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -, or *
 * between the digits so they evaluate to the target value.
 *
 * Example 1:
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"]
 *
 * Example 2:
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 *
 * Example 3:
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 *
 * Example 4:
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 *
 * Example 5:
 * Input: num = "3456237490", target = 9191
 * Output: []
 */
public class LC282ExpressionAddOperators {

    public List<String> addOperators(String num, int target) {
        List<String> ret = new ArrayList<>();
        if (num == null || num.length() == 0) return ret;
        dfs(num, target, "", 0, 0, 0, ret);
        return ret;
    }

    private void dfs(String num, int target,
                     String trace, long lstRet, long preNum,
                     int level, List<String> ret) {
        if (level == num.length()) {
            if (lstRet == target) {
                ret.add(trace);
            }
            return;
        }

        for (int i = level; i < num.length(); i++) {
            // 消除有leading zeros的选择 只有当这一层开始的时候才去取0 层中间出现的0都跳过
            if (i != level && num.charAt(level) == '0') break;
            long curNum = Long.valueOf(num.substring(level, i + 1)); // substring方法左闭右开 右端点是i+1
            if (level == 0) {
                dfs(num, target, String.valueOf(curNum), curNum, curNum,i + 1, ret);
            } else {
                dfs(num, target, trace+ "+" +curNum, lstRet+curNum, curNum, i + 1, ret);
                dfs(num, target, trace+ "-" +curNum, lstRet-curNum, -curNum, i + 1, ret);
                dfs(num, target, trace+ "*" +curNum, lstRet-preNum+preNum*curNum, preNum * curNum, i + 1, ret);
                /*
                if (curNum != 0) {
                    dfs(num, target, trace+ "/" + curNum, lstRet-preNum+preNum/curNum,
                            preNum/curNum, i+1, ret);
                }
                */
            }
        }
    }

    public static void main(String[] args) {
        LC282ExpressionAddOperators slu = new LC282ExpressionAddOperators();
        String num = "232";
        int target = 8;
        List<String> ret = slu.addOperators(num, target);
        System.out.println(".");
    }
}
