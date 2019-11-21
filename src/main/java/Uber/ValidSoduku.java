package Uber;


import java.util.ArrayList;
import java.util.List;

/**
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * A partially filled sudoku which is valid.
 *
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 *
 * Example 1:
 *
 * Input:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 * Example 2:
 *
 * Input:
 * [
 *   ["8","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 *     modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 */
public class ValidSoduku {

    public boolean isValidSudoku(char[][] board) {
        return isValidBox(board) && isValidRowAndCol(board);
    }

    private boolean isValidBox(char[][] board) {
        for (int i = 0; i <= 6; i+=3) {
            for (int j = 0; j <= 6; j+=3) {
                if (!isValidBox(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidBox(char[][] board, int r, int c) {
        boolean[] visited = new boolean[9];

        for (int i = r; i <= r+2; ++i) {
            for (int j = c; j <= c+2; ++j) {
                if (board[i][j] != '.') {
                    if (!visited[board[i][j] - '0']) {
                        visited[board[i][j] - '0'] = true;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean isValidRowAndCol(char[][] board) {
        List<List<Character>> rows = new ArrayList<>();
        List<List<Character>> cols = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            rows.add(new ArrayList<>());
            cols.add(new ArrayList<>());
        }

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char c = board[i][j];
                if (c != '.') {
                    if (rows.get(i).contains(c) || cols.get(j).contains(c)) {
                        return false;
                    }
                    rows.get(i).add(c);
                    cols.get(j).add(c);
                }
            }
        }
        return true;
    }
}
