package DP.Coordinates;

/**
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix.
 * The line could be horizontal, vertical, diagonal or anti-diagonal.
 *
 * Example:
 * Input:
 * [[0,1,1,0],
 *  [0,1,1,0],
 *  [0,0,0,1]]
 * Output: 3
 * Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class LC562LongestLineOfConsecutiveOneInMatrix {

    public int longestLine(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) return 0;
        int rows = M.length;
        int cols = M[0].length;

        // 三维dp 第三维: 横0 竖1 正对角线2 反对角线3
        int[][][] dp = new int[rows + 1][cols + 2][4];

        int res = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length - 1; j++) {
                if (M[i-1][j-1] == 1) {
                    dp[i][j][0] = dp[i][j-1][0] + 1;
                    res = Math.max(res, dp[i][j][0]);

                    dp[i][j][1] = dp[i-1][j][1] + 1;
                    res = Math.max(res, dp[i][j][1]);

                    dp[i][j][2] = dp[i-1][j-1][2] + 1;
                    res = Math.max(res, dp[i][j][2]);

                    dp[i][j][3] = dp[i-1][j+1][3] + 1;
                    res = Math.max(res, dp[i][j][3]);
                }
            }
        }
        return res;
    }

    // version 2: 滚动数组 dp[i][j][4]更新只需要本行和上一行的数据
    public int longestLineRollingArray(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) return 0;
        int rows = M.length;
        int cols = M[0].length;

        // 三维dp 第三维: 横0 竖1 正对角线2 反对角线3
        int[][][] dp = new int[2][cols][4];

        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (M[i][j] == 1) {
                    // 横着
                    dp[i%2][j][0] = (j == 0) ? 1 : dp[i%2][j-1][0] + 1;

                    // 竖着
                    dp[i%2][j][1] = (i == 0) ? 1 : dp[(i-1)%2][j][1] + 1;

                    // 对角线
                    dp[i%2][j][2] = (i == 0 || j == 0) ? 1 : dp[(i-1)%2][j-1][2] + 1;

                    // 反对角线
                    dp[i%2][j][3] = (i == 0 || j == cols - 1) ? 1 : dp[(i-1)%2][j+1][3] + 1;

                    // 四个方向里面取一个最大的 更新res
                    for (int k = 0; k < 4; k++) {
                        res = Math.max(dp[i%2][j][k], res);
                    }
                } else {
                    for (int k = 0; k < 4; k++) {
                        dp[i%2][j][k] = 0;
                    }
                }
            }
        }

        return res;
    }

}
