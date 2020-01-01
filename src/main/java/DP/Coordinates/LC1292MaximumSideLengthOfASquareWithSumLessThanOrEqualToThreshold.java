package DP.Coordinates;

/**
 * Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square
 * with a sum less than or equal to threshold or return 0 if there is no such square.
 *
 * Example 1:
 * Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * Output: 2
 * Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
 *
 * Example 2:
 * Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * Output: 0
 *
 * Example 3:
 * Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
 * Output: 3
 *
 * Example 4:
 * Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
 * Output: 2

 * Constraints:
 * 1 <= m, n <= 300
 * m == mat.length
 * n == mat[i].length
 * 0 <= mat[i][j] <= 10000
 * 0 <= threshold <= 10^5
 */
public class LC1292MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold {

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        // Precompute the sums of sub-matrixes whose left-top corner is at (0,0).
        int[][] sum = new int[m+1][n+1];

        // sum[][]加了一行一列padding 方便计算 注意坐标的变换
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + mat[i-1][j-1];
            }
        }

        int maxSide = 0;
        int lo = 0;
        int hi = Math.min(m, n);
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (foundSquare(sum, mid, threshold)) {
                maxSide = Math.max(maxSide, mid);
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return maxSide;
    }

    private boolean foundSquare(int[][] sum, int side, int threshold) {
        int m = sum.length - 1;
        int n = sum[0].length - 1;
        for (int i = side; i <= m; i++) {
            for (int j = side; j <= n; j++) {
                if (sum[i][j] - sum[i-side][j] - sum[i][j-side] + sum[i-side][j-side] <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }

}
