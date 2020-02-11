package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two sparse matrices A and B, return the result of AB.
 * You may assume that A's column number is equal to B's row number.
 *
 * Example:
 * Input:
 *
 * A = [
 *   [ 1, 0, 0],
 *   [-1, 0, 3]
 * ]
 *
 * B = [
 *   [ 7, 0, 0 ],
 *   [ 0, 0, 0 ],
 *   [ 0, 0, 1 ]
 * ]
 *
 * Output:
 *
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 *
 * followup:自定义数据结构去做.
 * 由于是稀疏矩阵 所以很多entries是0 直接存不为零的entries然后遍历中判断是否可以做乘法即可
 */
public class LC311SparseMatrixMultiplication {

    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < nB; j++) {
                for(int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public List<List<Integer>> matrixMultiply(List<List<Integer>> A, List<List<Integer>> B) {
        List<List<Integer>> res = new ArrayList<>();
        if (A == null || B == null || A.size() == 0 || B.size() == 0) return res;
        Map<String, Integer> map = new HashMap<>();
        // [i, j, value]
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < B.size(); j++) {
                // A[j] == B[i]时可以对结果产生贡献
                if (A.get(i).get(1) == B.get(j).get(0)) {
                    // 用 "i j"作为key
                    String curr = A.get(i).get(0) + " " + B.get(j).get(1);
                    // C[i][j] += A[i][k] * B[k][j]
                    map.put(curr, map.getOrDefault(curr, 0) + A.get(i).get(2) * B.get(j).get(2));
                }
            }
        }

        // 取出(i,j) 加上结果 以List形式加入结果集
        for (String point : map.keySet()) {
            String[] coordinates = point.split(" ");
            List<Integer> curAns = new ArrayList<>();
            curAns.add(Integer.valueOf(coordinates[0]));
            curAns.add(Integer.valueOf(coordinates[1]));
            curAns.add(map.get(point));
            res.add(curAns);
        }

        return res;
    }
}
