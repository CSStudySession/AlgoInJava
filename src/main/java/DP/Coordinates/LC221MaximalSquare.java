package DP.Coordinates;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 *
 * Example:
 * Input:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Output: 4
 *
 * 思路：
 * 题意是问矩阵中子正方形（不是长方形）的最大面积。也就是说我们的思路应该是去判断正方形这一子状态以及相应的状态转移方程。
 * 正方形的可能有边长为1，2，3等等... 边长为2的可由边长为1 的转化而来，边长为3的可由边长为2的转化而来。
 * 那么问题来了，边长的转化是如何得到的？边长由1变为2容易得知，即左上、左边以及上边的值均为1，
 * 边长由2变为3这一状态转移方程不容易直接得到。直观上来讲，我们需要边长为3的小正方形内格子中的数均为1.
 * 抽象来讲也可以认为边长为3的正方形是由若干个边长为2的正方形堆叠得到的，这就是这道题的核心状态转移方程。
 *
 * 令状态dp[i][j]表示为从左上角(不一定是(0,0))到矩阵中坐标(i,j)为止能构成正方形的最大边长。那么有如下状态转移方程：
 *
 * dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1; if matrix[i][j] == 1
 * dp[i][j] = 0; if matrix[i][j] = 0
 * 初始化直接用第一行和第一列即可。
 */
public class LC221MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int max = 0;
        int[][] dp = new int[m][n];
        // 第一列赋值
        for(int i = 0; i < m; i++){
            dp[i][0] = matrix[i][0] - '0';
            max = Math.max(max, dp[i][0]);
        }
        // 第一行赋值
        for(int i = 0; i < n; i++){
            dp[0][i] = matrix[0][i] - '0';
            max = Math.max(max, dp[0][i]);
        }
        // 递推
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = matrix[i][j] == '1' ? Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1 : 0;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }

}
