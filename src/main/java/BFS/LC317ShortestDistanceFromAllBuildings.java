package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 *
 * Example:
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 *
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * Output: 7
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
 *              the point (1,2) is an ideal empty land to build a house, as the total
 *              travel distance of 3+3+1=7 is minimal. So return 7.
 * Note:
 * There will be at least one building.
 * If it is not possible to build such house according to the above rules, return -1.
 *
 * 思路：
 * Traverse the matrix. For each building, use BFS to compute the shortest distance from each '0' to
 * this building. After we do this for all the buildings, we can get the sum of shortest distance
 * from every '0' to all reachable buildings. This value is stored in 'distance[][]'.
 * For example, if grid[2][2] == 0, distance[2][2] is the sum of shortest distance from this block to all reachable buildings.
 *
 * Time complexity: O(number of 1)O(number of 0) ~ O(m^2n^2)
 *
 * We also count how many building each '0' can be reached. It is stored in reach[][]. This can be done during the BFS.
 * We also need to count how many total buildings are there in the matrix, which is stored in 'buildingNum'.
 * Finally, we can traverse the distance[][] matrix to get the point having shortest distance to all buildings. O(m*n)
 * The total time complexity will be O(m^2*n^2)
 */
public class LC317ShortestDistanceFromAllBuildings {

    public int shortestDistance(int[][] grid) {
        if (grid == null || grid[0].length == 0) return 0;
        final int[] shift = new int[] {0, 1, 0, -1, 0};

        int row  = grid.length, col = grid[0].length;
        int[][] distance = new int[row][col];
        int[][] reach = new int[row][col];
        int buildingNum = 0;

        for (int i = 0; i < row; i++) {
            for (int j =0; j < col; j++) {
                if (grid[i][j] == 1) {
                    buildingNum++;
                    Queue<int[]> myQueue = new LinkedList<>();
                    myQueue.offer(new int[] {i,j});

                    boolean[][] isVisited = new boolean[row][col];
                    int level = 1;

                    while (!myQueue.isEmpty()) {
                        int qSize = myQueue.size();
                        for (int q = 0; q < qSize; q++) {
                            int[] curr = myQueue.poll();

                            for (int k = 0; k < 4; k++) {
                                int nextRow = curr[0] + shift[k];
                                int nextCol = curr[1] + shift[k + 1];

                                if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
                                        && grid[nextRow][nextCol] == 0 && !isVisited[nextRow][nextCol]) {
                                    // The shortest distance from [nextRow][nextCol] to this building
                                    // is 'level'.
                                    distance[nextRow][nextCol] += level;
                                    // update reachable number of building
                                    reach[nextRow][nextCol]++;

                                    isVisited[nextRow][nextCol] = true;
                                    myQueue.offer(new int[] {nextRow, nextCol});
                                }
                            }
                        }
                        level++;
                    }
                }
            }
        }

        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 当前点为0且能到达所有的buildings
                if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
                    shortest = Math.min(shortest, distance[i][j]);
                }
            }
        }
        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }
}
