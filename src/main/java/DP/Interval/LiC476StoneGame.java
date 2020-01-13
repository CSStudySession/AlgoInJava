package DP.Interval;

/**
 * There is a stone game.At the beginning of the game the player picks n piles of stones in a line.
 *
 * The goal is to merge the stones in one pile observing the following rules:
 * At each step of the game, the player can merge two adjacent piles to a new pile.
 * The score is the number of stones in the new pile.
 * You are to determine the minimum of the total score.
 *
 * Example
 * For [4, 1, 1, 4], in the best solution, the total score is 18:
 * 1. Merge second and third piles => [4, 2, 4], score +2
 * 2. Merge the first two piles => [6, 4]，score +6
 * 3. Merge the last two piles => [10], score +10
 * Other two examples:
 * [1, 1, 1, 1] return 8
 * [4, 4, 5, 9] return 43
 *
 * 思路:
 * DP四要素
 * State:
 * dp[i][j]表示把第i到第j个石子合并到一起的最小花费
 * Function:
 * 预处理sum[i,j]表示i到j所有石子价值和
 * dp[i][j] = min(dp[i][k]+dp[k+1][j]+sum[i,j]) 对于所有k属于{i,j}
 * Initialize:
 * for each i
 * dp[i][i] = 0
 * Answer:
 * dp[0][n-1]
 * 区间型DP，利用二维数组下标表示下标范围。
 * 需要注意的是对状态转移方程的理解，也就是对每一种分割方式进行遍历.
 *
 * 分割成子问题的思路在于用不同的pivot将原有的数组分割成为不同的区间，并且递归地每一个子区间重复同样的分割过程。
 * 计算interval sum时，已知start和end，那么最好的方式就是预先生成一个prefix sum数组，
 * 这样区间和就可以用sum[end + 1] - sum[start + 1 - 1]来计算.
 *
 * 对于 interval sum ，根据搜索结构可以做一个显而易见的优化，因为每次 split 的 start, pivot, end 我们都知道，
 * 而且合并（start, end） 区间的两堆石子，最终的区间和一定为 (start, end) 的区间和，用一维的 prefix sum 数组就可以了。
 * 用 prefix sum 数组要记得初始化时候的 int[n + 1] zero padding，还有取值时候对应的 sum[end + 1] - sum[start + 1 - 1] offset.
 * 每次归并的 cost = 归并两个区间的最优 cost + 两个区间的区间和，即：
 * int cost = memoizedSearch(start, i, A, dp, sum) + memoizedSearch(i + 1, end, A, dp, sum) + sum[end + 1] - sum[start];
 *
 * 比较要注意的是循环pivot时数组的下标，以下两种实现方式都是可行的 (注意防止出现无限循环，即recursive调用搜索函数时，
 * 不能出现start, end又被当成输入的情况)：
 *
 * i ~ [start, end - 1], => [start, i], [i + 1, end - 1]
 */
public class LiC476StoneGame {

    public int stoneGame(int[] A) {
        if(A == null || A.length == 0) return 0;
        int n = A.length;

        // Minimum cost to merge interval dp[i][j]
        int[][] dp = new int[n][n];
        int[] sum = new int[n + 1];

        // Pre-process interval sum
        for(int i = 0; i < n; i++){
            sum[i + 1] = sum[i] + A[i];
        }

        return memoizedSearch(0, n - 1, A, dp, sum);
    }

    private int memoizedSearch(int start, int end, int[] A, int[][] dp, int[] sum){
        if(start >= end) return 0;

        if(start + 1 == end) return A[start] + A[end];

        if(dp[start][end] != 0) return dp[start][end];

        int min = Integer.MAX_VALUE;
        for(int i = start; i < end; i++){
            int cost = memoizedSearch(start, i, A, dp, sum) +
                    memoizedSearch(i + 1, end, A, dp, sum) +
                    sum[end + 1] - sum[start];
            // running min
            min = Math.min(min, cost);
        }

        dp[start][end] = min;

        return min;
    }

}
