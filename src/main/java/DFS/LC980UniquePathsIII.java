package DFS;

/**
 * On a 2-dimensional grid, there are 4 types of squares:
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square
 * to the ending square, that walk over every non-obstacle square exactly once.
 *
 * Example 1:
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 *
 * Example 2:
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 *
 * Example 3:
 * Input: [[0,1],[2,0]]
 * Output: 0
 * Explanation:
 * There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 *
 * Note:
 * 1 <= grid.length * grid[0].length <= 20
 */
public class LC980UniquePathsIII {

    public int uniquePathsIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int zeroCnt = 0;
        int si = 0;
        int sj = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) zeroCnt++;
                if (grid[i][j] == 1) {
                    si = i;
                    sj = j;
                }
            }
        }

        int[] di = {0, 0, 1, -1};
        int[] dj = {1, -1, 0, 0};
        int[] ret = {0};

        dfs(grid, si, sj, 0, zeroCnt, di, dj, ret);
        return ret[0];
    }

    private void dfs(int[][] grid, int i, int j, int curZeroCnt,
                     int zeroCnt, int[] di, int[] dj, int[] ret) {
        if (grid[i][j] == 2) {
            if (curZeroCnt == zeroCnt) {
                ret[0]++;
            }
            return;
        }

        // 这里需要特判！因为搜索入口的值是1不是0！！
        if (grid[i][j] == 0) curZeroCnt+=1;
        int prev = grid[i][j];
        // 这里可以改用Set来判断走过的路径
        grid[i][j] = -1;
        for (int k = 0; k < 4; k++) {
            int ni = i + di[k];
            int nj = j + dj[k];
            if (ni < 0 || ni >= grid.length ||
                    nj < 0 || nj >= grid[0].length ||
                    grid[ni][nj] == -1) continue;
            dfs(grid, ni, nj, curZeroCnt, zeroCnt, di, dj, ret);
        }
        grid[i][j] = prev;
    }

    public static void main(String[] args) {
        LC980UniquePathsIII slu = new LC980UniquePathsIII();
        int[][] grid = {{1,0,0,0}, {0,0,0,0}, {0,0,2,-1}};
        int ret = slu.uniquePathsIII(grid);
        System.out.println(".");
    }
}









