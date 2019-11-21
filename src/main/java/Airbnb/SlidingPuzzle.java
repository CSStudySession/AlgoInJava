package Airbnb;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
 *
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 *
 * Examples:
 *
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 * Input: board = [[3,2,4],[1,5,0]]
 * Output: 14
 * Note:
 *
 * board will be a 2 x 3 array as described above.
 * board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
 */
public class SlidingPuzzle {

    // BFS


    private String swap(String str, int i, int j) {
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.setCharAt(i, str.charAt(j));
        stringBuilder.setCharAt(j, str.charAt(i));
        return stringBuilder.toString();
    }

    public int findByBFS(int[][] board) {
        String target = "123450";
        String start = "";

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                start += board[i][j];
            }
        }

        int[][] dirs = {{1,3}, {0,2,4}, {1,5}, {0,4}, {1,3,5}, {2,4}};

        Set<String> set = new HashSet<>();
        Queue<String> queue =new LinkedList<>();
        queue.offer(start);
        set.add(start);
        int ret = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                String cur = queue.poll();
                if(target.equals(cur)) {
                    return ret;
                }
                int zero = cur.indexOf("0");
                for (int dir : dirs[zero]) {
                    String next = swap(cur, zero, dir);
                    if (set.contains(next)) {
                        continue;
                    }
                    set.add(next);
                    queue.offer(next);
                }
            }
            ret++;
        }

        return -1;
    }




    public static void main(String[] args) {
        int[][] map = new int[][] {
                {1,2,3},
                {0,4,5}
        };

        SlidingPuzzle slidingPuzzle = new SlidingPuzzle();

        System.out.println(slidingPuzzle.findByBFS(map));
    }


}
