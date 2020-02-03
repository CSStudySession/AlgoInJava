package BinaryIndexedTree;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by
 * its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3),
 * which contains sum = 8.
 *
 * Example:
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 *
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 *
 * 思路: 树状数组模板题
 * 树状数组tree[i][j]维护的是原数组[i-1][j-1]到源点[0][0]的矩形和
 * 注意坐标的转换:matrix[i][j]->tree[i+1][j+1]
 * 初始化的时候 计算每个位置上的delta = matrix[i][j] - nums[i][j] (nums[i][j] == 0 at first)
 * 调用update更新树状数组即可
 *
 * 初始化树状数组时间复杂度O(MN * logN * logM) 空间O(NM)
 * 每次更新复杂度 O(logM * logN)
 */
public class LC308RangeSumQuery2DMutable {

    private int[][] tree;
    private int[][] nums;
    private int m;
    private int n;

    public LC308RangeSumQuery2DMutable(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        m = matrix.length;
        n = matrix[0].length;
        // BIT的下标从1开始
        tree = new int[m+1][n+1];
        nums = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        if (m == 0 || n == 0) return;
        // 注意树状数组维护的是差值 所以每次需要求delta
        int delta = val - nums[row][col];
        nums[row][col] = val;
        // 梳妆数组的下标比原数组下标多1
        for (int i = row + 1; i <= m; i += lowerBit(i)) {
            for (int j = col + 1; j <= n; j += lowerBit(j)) {
                tree[i][j] += delta;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (m == 0 || n == 0) return 0;
        return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
    }

    // 计算[0][0]到[row][col]之间的矩形数字和
    public int sum(int row, int col) {
        int sum = 0;
        for (int i = row + 1; i > 0; i -= lowerBit(i)) {
            for (int j = col + 1; j > 0; j -= lowerBit(j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }

    private int lowerBit(int x) {
        return x & (-x);
    }

}

