package DFS;

import sun.awt.CGraphicsConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty 2D array grid of 0's and 1's,
 * an island is a group of 1's (representing land) connected 4-directionally
 * (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * Count the number of distinct islands.
 * An island is considered to be the same as another if and only if
 * one island can be translated (and not rotated or reflected) to equal the other.
 *
 * Example 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * Given the above grid map, return 1.
 *
 * Example 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 *
 * Notice that:
 * 11
 * 1
 * and
 *  1
 * 11
 * are considered different island shapes, because we do not consider reflection / rotation.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
public class LC694NumberOfDistinctIslands {

    public int numDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        int[] dj = {1,-1,0,0};
        int[] di = {0,0,1,-1};
        Set<String> set = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    StringBuilder sb = new StringBuilder();
                    int ri = i;
                    int rj = j;
                    dfs(i, j, grid, ri, rj, sb, vis, di, dj);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    private void dfs(int i, int j, int[][] grid,
                     int ri,  int rj,
                     StringBuilder sb,
                     boolean[][] vis, int[] di, int[] dj) {
        vis[i][j] = true;
        sb.append((i - ri) + "" + (j - rj) + " ");

        for (int k = 0; k < 4; k++) {
            int ni = i + di[k], nj = j + dj[k];
            if (ni < 0 || ni >= grid.length ||
                    nj < 0 || nj >= grid[0].length
                    || vis[ni][nj] || grid[ni][nj] == 0)
                continue;
            dfs(ni, nj, grid, ri, rj, sb, vis, di, dj);
        }
    }
}
