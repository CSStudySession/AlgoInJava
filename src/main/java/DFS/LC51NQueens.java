package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 *
 * 思路: 每层枚举queen可能在的位置.每放一个queen就判断一下是否合法.
 */
public class LC51NQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ret = new ArrayList<>();
        if (n < 1) return ret;
        List<int[]> allPos = new ArrayList<>();
        getPos(0, n, new int[n], allPos);
        // 每一个数组pos代表一种可能的摆放方式
        for (int[] pos : allPos) {
            List<String> sol = new ArrayList<>();
            for (int i = 0; i < pos.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == pos[i]) sb.append("Q");
                    else sb.append(".");
                }
                sol.add(sb.toString());
            }
            ret.add(sol);
        }
        return ret;
    }

    private void getPos(int cur, int n, int[] pos, List<int[]> allPos) {
        // cur从0开始，所以当cur==n时，[0,n-1]所有的位置已经解决了
        if (cur == n) {
            allPos.add(Arrays.copyOf(pos, pos.length));
            return;
        }
        // 在第cur层,枚举所有可能的位置i
        for (int i = 0; i < n; i++) {
            pos[cur] = i;
            if (valid(pos, cur)) {
                // 如果当前位置合法,进入cur+1层
                getPos(cur + 1, n, pos, allPos);
            }
            // 回溯时要消除影响
            pos[cur] = 0;
        }
    }

    private boolean valid(int[] pos, int cur) {
        for (int i = 0; i < cur; i++) {
            if (pos[i] == pos[cur]) return false;
            // 斜率相同 -> deltaX == deltaY
            if (Math.abs(pos[cur]-pos[i]) == Math.abs(cur-i)) return false;
        }
        return true;
    }
}
