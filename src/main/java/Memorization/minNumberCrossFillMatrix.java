package Memorization;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * given a n * m matrix with initialized all 0, return the min number of fill it with all 1's
 * that in a cross-fill way.
 * a cross-fill means when setting point (i,j), (i-1, j) (i+1, j) (i, j-1) (i, j+1) will be filled
 * to 1 if previously it was 0, to 0 otherwise.
 * e.g.:
 * 0 0 0   set(1,1)   0 1 0    set(1,1)      0 0 0
 * 0 0 0  --------->  1 1 1  ------------->  0 0 0
 * 0 0 0              0 1 0                  0 0 0
 *
 * 思路:
 * 记忆化搜索 每次把当前board编码成一个一维"01"字符串 然后枚举所有可能的起点进行set操作
 * 注意点: 1.可能出现环路 即搜到正在扩展的状态的祖先节点 2.回溯时消除影响
 */

public class minNumberCrossFillMatrix {
    public int minSteps(int[][] A) {
        int n = A.length;
        int m = A[0].length;

        char[] board = new char[n*m];
        for (int k = 0; k < n*m; k++) {
            board[k] = '0';
        }

        int result = dfs(board, n, m, new HashMap<>(), new HashSet<>(), 0);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int dfs(char[] board, int n, int m, Map<String, Integer> memo,
                    Set<String> trace, int cnt) {
        String cur = new String(board);
        if (memo.containsKey(cur)) return memo.get(cur);

        if (cnt == n*m) {
            return 0;
        }

        // 防止出现环路 如果走到探索中的状态 直接返回
        if (trace.contains(cur)) {
            return Integer.MAX_VALUE;
        }
        trace.add(cur);

        int minStep = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int idx = i*m + j;
                if (board[idx] == '0') {
                    board[idx] = '1';
                    cnt++;
                    int zeroToOne = setMatrix(board, i, j, n, m);
                    cnt += zeroToOne;
                    int curRes = dfs(board, n, m, memo, trace, cnt);
                    curRes = curRes == Integer.MAX_VALUE ? Integer.MAX_VALUE : curRes + 1;
                    minStep = Math.min(minStep, curRes);
                    // 回溯时消除影响
                    board[idx] = '0';
                    setMatrix(board, i, j, n, m);
                }
            }
        }

        memo.put(cur, minStep);
        return minStep;
    }

    private int setMatrix(char[] board, int i, int j, int n, int m) {
        int[] di = {0, 0, 1, -1};
        int[] dj = {1, -1, 0, 0};

        int zeroCnt = 0;
        for (int k = 0; k < 4; k++) {
            int ni = i + di[k];
            int nj = j + dj[k];
            if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                int nIdx = ni*m + nj;
                char prev = board[nIdx];
                if (prev == '0')  zeroCnt++;
                board[nIdx] = prev == '0' ? '1' : '0';
            }
        }
        return zeroCnt;
    }
}
