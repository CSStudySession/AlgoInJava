package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.
 *
 * Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".
 *
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.
 *
 *
 *
 * Example 1:
 *
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 *
 * Input 2: ball coordinate (rowBall, colBall) = (4, 3)
 * Input 3: hole coordinate (rowHole, colHole) = (0, 1)
 *
 * Output: "lul"
 *
 * Explanation: There are two shortest ways for the ball to drop into the hole.
 * The first way is left -> up -> left, represented by "lul".
 * The second way is up -> left, represented by 'ul'.
 * Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".
 *
 * Example 2:
 *
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 *
 * Input 2: ball coordinate (rowBall, colBall) = (4, 3)
 * Input 3: hole coordinate (rowHole, colHole) = (3, 0)
 *
 * Output: "impossible"
 *
 * Explanation: The ball cannot reach the hole.
 *
 *
 *
 * Note:
 *
 * There is only one ball and one hole in the maze.
 * Both the ball and hole exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.
 */
public class LC499TheMazeIII {

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length;
        int n = maze[0].length;

        Point[][] res = new Point[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[i][j] = new Point(i, j);
            }
        }

        Queue<Point> que = new LinkedList<>();
        Point startPoint = new Point(ball[0], ball[1], 0, "");
        que.offer(startPoint);
        // 注意起点入队之后 要更新res数组中的起点值 否则起点到自己的距离还是MAX_VALUE
        res[ball[0]][ball[1]] = startPoint;

        // 注意方向数组和方向字符数组要对称
        int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}};
        String[] dirChars = {"u", "r", "d", "l"};

        while (!que.isEmpty()) {
            Point cur = que.poll();
            for (int k = 0; k < 4; k++) {
                int ci = cur.i;
                int cj = cur.j;
                int cDist = cur.dist;

                // 注意判断不为hole的坐标 要整体取反！
                while (ci >= 0 && ci < m && cj >= 0 && cj < n
                        && maze[ci][cj] == 0
                        && !(ci == hole[0] && cj == hole[1])) {
                    ci += dirs[k][0];
                    cj += dirs[k][1];
                    cDist++;
                }

                // falling into hole
                if ((ci == hole[0] && cj == hole[1])) {
                    Point curHole = new Point(ci, cj, cDist, cur.path + dirChars[k]);
                    if (curHole.isLess(res[hole[0]][hole[1]])) {
                        res[hole[0]][hole[1]] = curHole;
                    }
                    continue;
                }

                // not hole coordinate. back one step
                ci -= dirs[k][0];
                cj -= dirs[k][1];
                cDist--;

                Point nowPoint = new Point(ci, cj, cDist, cur.path + dirChars[k]);
                // 只在新路径比之前一条短的时候更新
                if (res[ci][cj].isLess(nowPoint)) {
                    continue;
                }

                res[ci][cj] = nowPoint;
                que.offer(nowPoint);
            }
        }

        return res[hole[0]][hole[1]].dist == Integer.MAX_VALUE ? "impossible" : res[hole[0]][hole[1]].path;
    }

    public class Point {
        public int i;
        public int j;
        public int dist;
        // 存储从起点开始 一步步扩展过来的路径 bfs如何记录路径的方法 每个状态都记录上一个状态的值
        public String path;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
            this.dist = Integer.MAX_VALUE;
            this.path = "";
        }

        public Point(int i, int j, int dist, String path) {
            this.i = i;
            this.j = j;
            this.dist = dist;
            this.path = path;
        }

        public boolean isLess(Point p) {
            // 要么距离起始位置距离短 要么字母表顺序小
            if (this.dist < p.dist || (this.dist == p.dist && this.path.compareTo(p.path) < 0)) {
                return true;
            }

            return false;
        }
    }

    public static void main(String[] args) {
        LC499TheMazeIII inst = new LC499TheMazeIII();
        int[][] mase = {{0,0,0,0,0},
                        {1,1,0,0,1},
                        {0,0,0,0,0},
                        {0,1,0,0,1},
                        {0,1,0,0,0}};
        int[] ball = {4, 3};
        int[] hole = {0, 1};

        inst.findShortestWay(mase, ball, hole);
        System.out.println(".");
    }

}
