package Dropbox;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 *
 * Example:
 *
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * Follow up:
 *
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */

public class GameOfLife {

    public void gameOfLife(int[][] board) {

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                int liveCount = update(board, i, j);
                if (board[i][j] == 1) {
                    if (liveCount < 2) {
                        board[i][j] = 2;
                    } else if (liveCount > 3) {
                        board[i][j] = 3;
                    }
                } else if(board[i][j] == 0) {
                    if (liveCount == 3) {
                        board[i][j] = -1;
                    }
                }
            }
        }


        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == 2 || board[i][j] == 3) {
                    board[i][j] = 0;
                } else if (board[i][j] == -1) {
                    board[i][j] = 1;
                }
            }
        }
    }

    // Any live cell with fewer than two live neighbors dies, as if caused by under-population.   ->   2
    // Any live cell with two or three live neighbors lives on to the next generation.  ->   1
    // Any live cell with more than three live neighbors dies, as if by over-population..  ->  3
    // Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.    -> -1

    private int update(int[][] board, int i, int j) {
        int countLive = 0;
        if (i - 1 >= 0) {
            countLive += board[i-1][j] > 0 ? 1 : 0;
            if (j - 1 >= 0) {
                countLive += board[i-1][j-1] > 0 ? 1 : 0;
            }
            if (j + 1 < board[i-1].length) {
                countLive += board[i-1][j+1] > 0 ? 1 : 0;
            }
        }

        if (i + 1 < board.length) {
            countLive += board[i+1][j] > 0 ? 1 : 0;
            if (j + 1 < board[i+1].length) {
                countLive += board[i+1][j+1] > 0 ? 1 : 0;
            }
            if (j - 1 >= 0) {
                countLive += board[i+1][j-1] > 0 ? 1 : 0;
            }
        }

        if (j - 1 >= 0) {
            countLive += board[i][j-1] > 0 ? 1 : 0;
        }

        if (j + 1 < board[i].length) {
            countLive += board[i][j+1] > 0 ? 1 : 0;
        }
        return countLive;
    }
}
