package Memorization;

import java.util.Arrays;

/**
 * In a N x N grid representing a field of cherries, each cell is one of three possible integers.
 *
 * 0 means the cell is empty, so you can pass through;
 * 1 means the cell contains a cherry, that you can pick up and pass through;
 * -1 means the cell contains a thorn that blocks your way.
 *
 * Your task is to collect maximum number of cherries possible by following the rules below:
 *
 * Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down
 * through valid path cells (cells with value 0 or 1);
 * After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
 * When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
 * If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
 *
 * Example 1:
 * Input: grid =
 * [[0, 1, -1],
 *  [1, 0, -1],
 *  [1, 1,  1]]
 * Output: 5
 * Explanation:
 * The player started at (0, 0) and went down, down, right right to reach (2, 2).
 * 4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
 * Then, the player went left, up, up, left to return home, picking up one more cherry.
 * The total number of cherries picked up is 5, and this is the maximum possible.
 *
 * Note:
 * grid is an N by N 2D array, with 1 <= N <= 50.
 * Each grid[i][j] is an integer in the set {-1, 0, 1}.
 * It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
 *
 * 思路:
 * Key observation: (0,0) to (n-1, n-1) to (0, 0) is the same as (n-1,n-1) to (0,0) twice
 *
 * Two people starting from (m-1, n-1) and go to (0, 0).
 * They move one step (left or up) at a time simultaneously.
 * And pick up the cherry within the grid (if there is one).
 *
 * if they ended up at the same grid with a cherry. Only one of them can pick up it.
 * Solution: Recursion with memoization.
 *
 * iA, jA, iB to represent a state -> jB can be computed: jB = iA + jA – iA
 *
 * dp(iA, jA, iB) computes the max cherries if start from {(iA, jA), (iB, jB)} to (0, 0), which is a recursive function.
 *
 * Since two people move independently, there are 4 sub-problem:
 * (left, left), (left, up), (up, left), (left, up). Finally, we have:
 * dp(iA, jA, iB) = g[iA][jA] + g[iB][jB] + max(4 dp sub-optimal)
 *
 * Time complexity: O(n^3)
 * Space complexity: O(n^3)
 */
 public class LC741CherryPickup {

    public int cherryPickup(int[][] grid) {
        if (grid == null ||  grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int[][][] memo = new int[m][n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], Integer.MIN_VALUE);
            }
        }
        return Math.max(0, dp(grid, memo, m-1, n-1, m-1));
    }

    private int dp(int[][] grid, int[][][] memo,
                   int iA, int jA, int iB) {
        int jB = iA + jA - iB;
        // 因为起点在右下角 且只往左或上面走 所以右或者下的方向不会越界
        if (iA < 0 || jA < 0 || iB < 0 || jB < 0) return -1;
        // 阻塞点
        if (grid[iA][jA] < 0 || grid[iB][jB] < 0) return -1;
        // 两个人会同时走到(0,0)点 根据步数和相等这个条件
        if (iA == 0 && jA == 0) return grid[iA][jA];
        if (memo[iA][jA][iB] != Integer.MIN_VALUE) return memo[iA][jA][iB];

        int nextA = dp(grid, memo, iA - 1, jA, iB - 1);
        int nextB = dp(grid, memo, iA, jA - 1, iB);
        int nextC = dp(grid, memo, iA, jA - 1, iB - 1);
        int nextD = dp(grid, memo, iA - 1, jA, iB);
        int nextMax = Math.max(nextA, Math.max(nextB, Math.max(nextC, nextD)));
        if (nextMax >= 0) {
            memo[iA][jA][iB] = nextMax + grid[iA][jA];
            if (iA != iB) {
                memo[iA][jA][iB] += grid[iB][jB];
            }
        }
        return memo[iA][jA][iB];
    }
}
