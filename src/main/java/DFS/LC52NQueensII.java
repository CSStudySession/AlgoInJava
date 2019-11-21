package DFS;

/**
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * 思路: 求所有,问是否有解,是否存在某种解,最后都是dfs所有可能性
 */
public class LC52NQueensII {

    private static int tot = 0;

    public int totalNQueens(int n) {
        if (n < 1) return 0;
        getPos(0, n, new int[n]);
        return tot;
    }

    private void getPos(int cur, int n, int[] pos) {
        if (cur == n) {
            tot++;
            return;
        }
        for (int i = 0; i < n; i++) {
            pos[cur] = i;
            if (valid(pos, cur)) {
                getPos(cur + 1, n, pos);
            }
            pos[cur] = 0;
        }
    }

    private boolean valid(int[] pos, int cur) {
        for (int i = 0; i < cur; i++) {
            if (pos[i] == pos[cur]) return false;
            // 对角线上的点 y=x -> deltaY == deltaX
            // 注意不要用 delta(Y/X) == 1 整数除法会向下取整导致误判！-> 慎用除法！
            if (Math.abs(pos[cur]-pos[i]) == Math.abs(cur-i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LC52NQueensII slu = new LC52NQueensII();
        System.out.println(slu.totalNQueens(1));
    }
}
