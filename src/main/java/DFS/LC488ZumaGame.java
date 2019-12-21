package DFS;

/**
 * Think about Zuma Game. You have a row of balls on the table,
 * colored red(R), yellow(Y), blue(B), green(G), and white(W).
 * You also have several balls in your hand.
 * Each time, you may choose a ball in your hand, and insert it into the row
 * (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls
 * in the same color touching, remove these balls. Keep doing this until no more balls can be removed.
 * Find the minimal balls you have to insert to remove all the balls on the table.
 * If you cannot remove all the balls, output -1.
 *
 * Examples:
 * Input: "WRRBBW", "RB"
 * Output: -1
 * Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
 *
 * Input: "WWRRBBWW", "WRBRW"
 * Output: 2
 * Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
 *
 * Input:"G", "GGGGG"
 * Output: 2
 * Explanation: G -> G[G] -> GG[G] -> empty
 *
 * Input: "RBYYBBRRB", "YRBGB"
 * Output: 3
 * Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty
 *
 * Note:
 * You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
 * The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
 * The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
 * Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
 */
public class LC488ZumaGame {

    public int findMinStep(String board, String hand) {
        int[] handBall = new int[26];
        for (char c : hand.toCharArray()) {
            int cur = c - 'A';
            handBall[cur]++;
        }
        return dfs(board, handBall);
    }

    private int dfs(String board, int[] handBall) {
        if (board.isEmpty()) return 0;

        int ret = Integer.MAX_VALUE;
        int i = 0;
        while (i < board.length()) {
            int j = i;
            while (j < board.length()) {
                if (board.charAt(j) == board.charAt(i)) j++;
            }
            // board[i] ~ board[j - 1] have the same color
            int color = board.charAt(i) - 'A';
            // j - i 只可能等于1 or 2
            int need = 3 - (j - i);
            // 剩下的所需颜色球数量大于等于这一次所需要的数量
            if (handBall[color] >= need) {
                // Remove board[i] ~ board[j - 1] and update the board
                String next = updateStr(board.substring(0, i) + board.substring(j));
                handBall[color] -= need;
                // Find the solution on new board with updated hand
                int nextStep = dfs(next, handBall);
                if (nextStep >= 0) {
                    ret = Math.min(ret, nextStep + need);
                }
                // 回溯时recover the states
                handBall[color] += need;
            }
            // 下一次i从j开始消除球
            i = j;
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    /*
    Update the board by removing all consecutive 3+ balls.
    "YWWRRRWWYY" -> "YWWWWYY" -> "YYY" -> ""
     */
    private String updateStr(String board) {
        int i = 0;
        while (i < board.length()) {
            int j = i;
            while (j < board.length()) {
                if (board.charAt(j) == board.charAt(i)) j++;
            }
            if (j - i >= 3) {
                board = board.substring(0, i) + board.substring(j);
                i = 0;
            } else {
                i++;
            }
        }
        return board;
    }


}
