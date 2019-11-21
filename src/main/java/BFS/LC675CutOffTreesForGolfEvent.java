package BFS;

import java.util.*;

/**
 * You are asked to cut off trees in a forest for a golf event.
 * The forest is represented as a non-negative 2D map, in this map:
 *
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 * The place with number bigger than 1 represents a tree can be walked through,
 * and this positive number represents the tree's height.
 *
 * You are asked to cut off all the trees in this forest in the order of tree's height -
 * always cut off the tree with lowest height first. And after cutting,
 * the original place has the tree will become a grass (value 1).
 *
 * You will start from the point (0, 0) and you should output the minimum steps
 * you need to walk to cut off all the trees. If you can't cut off all the trees,
 * output -1 in that situation.
 *
 * You are guaranteed that no two trees have the same height and there is at least one tree
 * needs to be cut off.
 *
 * Example 1:
 * Input:
 * [
 *  [1,2,3],
 *  [0,0,4],
 *  [7,6,5]
 * ]
 * Output: 6
 *
 * Example 2:
 * Input:
 * [
 *  [1,2,3],
 *  [0,0,0],
 *  [7,6,5]
 * ]
 * Output: -1
 *
 * Example 3:
 * Input:
 * [
 *  [2,3,4],
 *  [0,0,5],
 *  [8,7,6]
 * ]
 * Output: 6
 * Explanation: You started from the point (0,0) and you can cut off the tree in (0,0)
 * directly without walking.
 *
 * Hint: size of the given matrix will not exceed 50x50.
 */
public class LC675CutOffTreesForGolfEvent {

    public int cutOffTree(List<List<Integer>> forest) {
        List<List<Integer>> trees = new ArrayList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(0).size(); j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(Arrays.asList(forest.get(i).get(j), i, j));
                }
            }
        }

        // 按照树高从小到大排序
        Collections.sort(trees, (t1, t2) -> t1.get(0) - t2.get(0));
        trees.add(0, Arrays.asList(1, 0, 0));

        int ret = 0;
        for (int k = 0; k + 1 < trees.size(); k++) {
            int si = trees.get(k).get(1);
            int sj = trees.get(k).get(2);
            int vi = trees.get(k+1).get(1);
            int vj = trees.get(k+1).get(2);
            int dist = getShortestDist(forest, new int[] {si, sj},
                    new int[] {vi, vj});
            if (dist == -1) return -1;
            ret += dist;
        }
        return ret;
    }

    private int getShortestDist(List<List<Integer>> forest,
                                int[] cur, int[] dest) {
        int m = forest.size();
        int n = forest.get(0).size();
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int[] di = {0,0,1,-1};
        int[] dj = {1,-1,0,0};
        queue.offer(cur);
        visited[cur[0]][cur[1]] = true;
        int len = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int w = 0; w < size; w++) {
                int[] coord = queue.poll();
                if (coord[0] == dest[0] && coord[1] == dest[1]) {
                    return len;
                }

                for (int x = 0; x < 4; x++) {
                    int ni = coord[0] + di[x];
                    int nj = coord[1] + dj[x];

                    if (ni < 0 || ni >= forest.size() ||
                            nj < 0 || nj >= forest.get(0).size() ||
                            forest.get(ni).get(nj) == 0 ||
                            visited[ni][nj]) continue;

                    visited[ni][nj] = true;
                    queue.offer(new int[] {ni, nj});
                }
            }
            len++;
        }
        return -1;
    }

}
