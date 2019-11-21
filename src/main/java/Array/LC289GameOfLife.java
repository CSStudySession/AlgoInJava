package Array;

/**
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
 * using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * The next state is created by applying the above rules simultaneously to every cell in the current state,
 * where births and deaths occur simultaneously.
 *
 * Example:
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
 * Could you solve it in-place? Remember that the board needs to be updated at the same time:
 * You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array.
 * In principle, the board is infinite, which would cause problems when the active area encroaches
 * the border of the array. How would you address these problems?
 */
public class LC289GameOfLife {
    /*
    这道题方法比较直接，直接遍历找live及dead的neighbors数量，然后更新矩阵即可。关键是如何保存之前状态，
    我们可以暴力的直接新建一个矩阵来作修改或者用两位int来暂时存原来状态及更新后的状态，高位表示下一步状态，
    低位表示原先状态，然后最后更新完后再遍历矩阵根据高位来把矩阵更新到最新状态。
    (将下轮该cell要变的值存入bit1中，然后还原的时候右移就行了)

    往往这道题的follow up会围绕board的存储来展开，因为根本没必要用int来表示状态，太耗空间。
    最优存储方法是不用二维矩阵来表示，而是存所有live点的坐标。更新的方法就是扫一遍已存在的live点，
    扫其邻居，对其邻居而言，每被扫一次，次数加1， 用一个Map记录邻居及邻居被扫的次数，这些邻居最后会可能成为新的live点，
    最后根据Map里的value来确定新的live点。
      // 00 dead -> dead
      // 01 live -> dead
      // 10 dead -> live
      // 11 live -> live
    */
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        int row = board.length;
        int col = board[0].length;

        // define the direction of neighbors
        int[][] dirs = {{-1,-1}, {-1,0}, {-1, 1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // keep track of live neighbor count
                int liveCnt = 0;
                for (int k = 0; k < dirs.length; k++) {
                    int x = i + dirs[k][0];
                    int y = j + dirs[k][1];
                    if (x >= 0 && x < row && y >= 0 && y < col) {
                        // get old state from lower bit
                        if (board[x][y] % 2 == 1) {
                            liveCnt++;
                        }
                    }
                }
                if (board[i][j] % 2 == 1) {
                    if (liveCnt == 2 || liveCnt == 3) {
                        board[i][j] += 2;
                    }
                } else {
                    if (liveCnt == 3) {
                        board[i][j] += 2;
                    }
                }
            }
        }
        // update state based on higher bit
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] >>= 1;
            }
        }
    }
}
