package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by
 * rolling up, down, left or right, but it won't stop rolling until hitting a wall.
 * When the ball stops, it could choose the next direction.
 *
 * Given the ball's start position, the destination and the maze, find the shortest distance
 * for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled
 * by the ball from the start position (excluded) to the destination (included).
 * If the ball cannot stop at the destination, return -1.
 *
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space.
 * You may assume that the borders of the maze are all walls.
 * The start and destination coordinates are represented by row and column indexes.
 *
 * Example 1:
 * Input 1: a maze represented by a 2D array
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 * Output: 12
 * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 *              The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 *
 * Example 2:
 * Input 1: a maze represented by a 2D array
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 * Output: -1
 * Explanation: There is no way for the ball to stop at the destination.
 *
 * Note:
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space,
 * and they will not be at the same position initially.
 *
 * The given maze does not contain border (like the red rectangle in the example pictures),
 * but you could assume the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 *
 * 思路:
 * 非典型bfs 某个节点可以重复队 因为每个节点到源点的距离是可以被更新的 第一次入队时可能不是最短的距离
 * 所以某个节点出队时 就算是destination 也不能退出循环 需要检查是否可以更新当前的最短距离
 *
 * 时间复杂度: O(nm * (m + n)) each node gets visited m+n times
 */
public class LC505TheMazeII {

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;

        // res[i][j]:当前到达[i,j]点的最短距离
        int[][] res = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(start[0], start[1], 0));

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int k = 0; k < 4; k++) {
                int ci = cur.i;
                int cj = cur.j;
                int cDist = cur.dist;
                while (ci >= 0 && ci < m && cj >= 0 && cj < n && maze[ci][cj] == 0) {
                    ci += dirs[k][0];
                    cj += dirs[k][1];
                    cDist++;
                }

                ci -= dirs[k][0];
                cj -= dirs[k][1];
                cDist--;

                // 当前距离比之前的距离大 不入队
                if (cDist >= res[ci][cj]) {
                    continue;
                }
                // 更新最短路
                res[ci][cj] = cDist;

                que.offer(new Point(ci, cj, cDist));
            }
        }

        return res[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : res[destination[0]][destination[1]];
    }

    class Point {
        public int i;
        public int j;
        public int dist;

        public Point(int i, int j, int dist) {
            this.i = i;
            this.j = j;
            this.dist = dist;
        }
    }

}
