package DFS;

import java.util.*;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 * Count the number of distinct islands.
 * An island is considered to be the same as another if they have the same shape,
 * or have the same shape after rotation (90, 180, or 270 degrees only)
 * or reflection (left/right direction or up/down direction).
 *
 * Example 1:
 * 11000
 * 10000
 * 00001
 * 00011
 * Given the above grid map, return 1.
 *
 * Notice that:
 * 11
 * 1
 * and
 *  1
 * 11
 * are considered same island shapes.
 * Because if we make a 180 degrees clockwise rotation on the first island,
 * then two islands will have the same shapes.
 *
 * Example 2:
 * 11100
 * 10001
 * 01001
 * 01110
 * Given the above grid map, return 2.
 *
 * Here are the two distinct islands:
 * 111
 * 1
 * and
 * 1
 * 1
 *
 * Notice that:
 * 111
 * 1
 * and
 * 1
 * 111
 * are considered same island shapes. Because if we flip the first array in the up/down direction,
 * then they have the same shapes.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
public class LC711NumberOfDistinctIslandsII {

    public int numDistinctIslands2(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        Set<String> set = new HashSet<>();
        List<int[]> island = new ArrayList<>();
        int[] di = {0,0,1,-1};
        int[] dj = {1,-1,0,0};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, di, dj, i, j, island);
                    set.add(normalize(island));
                    island.clear();
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int[] di, int[] dj,
                     int i, int j, List<int[]> island) {
        grid[i][j] = 0;
        island.add(new int[] {i,j});

        for (int k = 0; k < 4; k++) {
            int ni = i + di[k];
            int nj = j + dj[k];
            if (ni < 0 || ni >= grid.length ||
            nj < 0 || nj >= grid[0].length ||
            grid[ni][nj] != 1) continue;

            dfs(grid, di, dj, ni, nj, island);
        }
    }

    private String normalize(List<int[]> island) {
        List<List<int[]>> islands = new ArrayList<>();
        // 八种变换方式
        for (int k = 0; k < 8; k++) {
            islands.add(new ArrayList<>());
        }

        for (int[] point : island) {
            int i = point[0];
            int j = point[1];

            islands.get(0).add(new int[] {i, j});
            islands.get(1).add(new int[] {i, -j});
            islands.get(2).add(new int[] {-i, j});
            islands.get(3).add(new int[] {-i, -j});
            islands.get(4).add(new int[] {j, i});
            islands.get(5).add(new int[] {j, -i});
            islands.get(6).add(new int[] {-j, i});
            islands.get(7).add(new int[] {-j, -i});
        }

        for (int k = 0; k < islands.size(); k++) {
            // 每个岛先排序 islands.get(k) -> one island
            Collections.sort(islands.get(k), (p1, p2) ->
                    p1[0] == p2[0] ? p1[1] - p2[1] : p1[0]- p2[0]);

            // 每个排过序的岛 按照第一个点对齐
            for (int p = islands.get(k).size() - 1; p >= 0; p--) {
                islands.get(k).get(p)[0] -= islands.get(k).get(0)[0];
                islands.get(k).get(p)[1] -= islands.get(k).get(0)[1];
            }
        }

        // 所有岛排序
        Collections.sort(islands, (island1, island2) -> {
            for (int t = 0; t < island1.size(); t++) {
                if (island1.get(t)[0] != island2.get(t)[0] ||
                        island1.get(t)[1] != island2.get(t)[1])
                    return island1.get(t)[0] != island2.get(t)[0] ?
                            island1.get(t)[0] - island2.get(t)[0] :
                            island1.get(t)[1] - island2.get(t)[1];
            }

            // 两个islands所有点都相同
            return 0;
        });

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < islands.get(0).size(); k++) {
            sb.append(islands.get(0).get(k)[0]);
            sb.append(",");
            sb.append(islands.get(0).get(k)[1]);
            sb.append(".");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LC711NumberOfDistinctIslandsII inst = new LC711NumberOfDistinctIslandsII();
        int[][] grid = {{1,1,0,0,0},
                        {1,0,0,0,0},
                        {0,0,0,0,1},
                        {0,0,0,1,1}};
        inst.numDistinctIslands2(grid);
        System.out.println(".");
    }

}
