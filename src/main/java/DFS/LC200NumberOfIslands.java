package DFS;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * Output: 1
 *
 * Example 2:
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * Output: 3
 */
public class LC200NumberOfIslands {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        int ret = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '2' || grid[i][j] == '0') continue;
                ret++;
                dfs(grid, i, j, dir);
            }
        }

        return ret;
    }

    private void dfs(char[][] grid, int i, int j, int[][] dir) {
        grid[i][j] = '2';

        for (int k = 0; k < dir.length; k++) {
            int ni = i + dir[k][0];
            int nj = j + dir[k][1];
            if (ni < 0 || ni >= grid.length || nj < 0 || nj >= grid[0].length
            || grid[ni][nj] == '0' || grid[ni][nj] == '2') continue;
            dfs(grid, ni, nj, dir);
        }
    }
}
