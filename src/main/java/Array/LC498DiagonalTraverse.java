package Array;

/**
 * Given a matrix of M x N elements (M rows, N columns),
 * return all elements of the matrix in diagonal order as shown in the below image.
 *
 * Example:
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 *
 * Output:  [1,2,4,7,5,3,6,8,9]
 *
 * Note:
 * The total number of elements of the given matrix will not exceed 10,000.
 *
 * 思路：找规律
 * 1. 扫描的次数记为scans 行数记为rows 列数记为cols 则:scans = rows + cols
 * 2. 以题设里的输入为例:
 * scans    数值          方向
 * 0:       1             up right
 * 1:       2 4           down left
 * 2:       7 5 3         up right
 * 3:       6 8           down left
 * 4:       9             up right
 *
 * 规律：even scans方向up right, odd scans方向down left
 * 每次扫描的起点坐标:
 * even scans:
 * x = scans < rows ? scans : rows - 1;
 * y = scans < rows ? 0 : scans - (rows - 1);
 *
 * odd scans:
 * x = scans < cols ? 0 : scans - (col - 1);
 * y = scans < cols ? scans : cols - 1;
 *
 * 每次新起一次scan line都是从起点开始 然后横纵坐标相应改变(+1 or -1) 所以找起点很重要
 */
public class LC498DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[] {};

        int rows = matrix.length, cols = matrix[0].length;
        int[] res = new int[rows * cols];
        int scans = rows + cols - 1;
        int index = 0;
        int x = 0, y = 0;

        for (int i = 0; i < scans; i++) {
            if (i % 2 == 0) { // even scans
                x = i < rows ? i : rows - 1;
                y = i < rows ? 0 : i - (rows - 1);
                while (x >= 0 && y < cols) {
                    // 方向up right 所以 (x--, y++)
                    res[index++] = matrix[x--][y++];
                }
            } else { // odd scans
                x = i < cols ? 0 : i - (cols - 1);
                y = i < cols ? i : cols - 1;
                while (x < rows && y >= 0) {
                    // 方向down left 所以 (x++, y--)
                    res[index++] = matrix[x++][y--];
                }
            }
        }

        return res;
    }
}
