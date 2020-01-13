package HashMap_HashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points,
 * with sides parallel to the x and y axes.
 * If there isn't any rectangle, return 0.
 *
 * Example 1:
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 *
 * Example 2:
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 *
 * Note:
 * 1 <= points.length <= 500
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 *
 * 思路:
 * Try all pairs of points to form a diagonal and see whether pointers of another diagonal exist or not.
 * Assume two points are (x0, y0), (x1, y1) x0 != x1 and y0 != y1.
 * The other two points will be (x0, y1), (x1, y0)
 *
 * Time complexity: O(n^2)
 * Space complexity: O(n)
 */
public class LC939MinimumAreaRectangle {

    public int minAreaRect(int[][] points) {

        // 存储点的方法 <i_x, {j_0, j_1, ..., j_n-1}>
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] point : points) {
            if (!map.containsKey(point[0])) {
                map.put(point[0], new HashSet<>());
            }
            map.get(point[0]).add(point[1]);
        }

        int min = Integer.MAX_VALUE;
        for (int[] point1 : points) {
            for (int[] point2 : points) {
                // if have the same i or j (不能形成对角线)
                if (point1[0] == point2[0] || point1[1] == point2[1]) {
                    continue;
                }

                // find other two points that can form diagonal
                if (map.get(point1[0]).contains(point2[1]) &&
                        map.get(point2[0]).contains(point1[1])) {
                    min = Math.min(min, Math.abs(point1[0] - point2[0]) * Math.abs(point1[1] - point2[1]));
                }
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

}
