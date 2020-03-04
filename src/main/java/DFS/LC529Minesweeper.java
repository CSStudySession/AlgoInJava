package DFS;

/**
 * Let's play the minesweeper game.
 * You are given a 2D char matrix representing the game board.
 * 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square,
 * 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines,
 * digit ('1' to '8') represents how many mines are adjacent to this revealed square,
 * and finally 'X' represents a revealed mine.
 *
 * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'),
 * return the board after revealing this position according to the following rules:
 *
 * If a mine ('M') is revealed, then the game is over - change it to 'X'.
 * If an empty square ('E') with no adjacent mines is revealed,
 * then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
 * If an empty square ('E') with at least one adjacent mine is revealed,
 * then change it to a digit ('1' to '8') representing the number of adjacent mines.
 * Return the board when no more squares will be revealed.
 *
 * Example 1:
 * Input:
 * [['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'M', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E']]
 * Click : [3,0]
 * Output:
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 *
 * Example 2:
 * Input:
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 * Click : [1,2]
 * Output:
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'X', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 *
 * Note:
 * The range of the input matrix's height and width is [1,50].
 * The click position will only be an unrevealed square ('M' or 'E'),
 * which also means the input board contains at least one clickable square.
 * The input board won't be a stage when game is over (some mines have been revealed).
 * For simplicity, not mentioned rules should be ignored in this problem. For example,
 * you don't need to reveal all the unrevealed mines when the game is over,
 * consider any cases that you will win the game or flag any squares.
 */
public class LC529Minesweeper {

    int[] pos1 = new int[]{1, -1, 1, 1, -1, -1, 0, 0};
    int[] pos2 = new int[]{0, 0, 1, -1, 1, -1, 1, -1};
    int y = 0;
    int x = 0;

    public char[][] updateBoard(char[][] board, int[] click) {
        y = board.length;
        x = board[0].length;
        dfs(board, click[0], click[1]);
        return board;
    }

    // dfs
    public void dfs(char[][] board, int cur1, int cur2){
        // If it is a bomb, it will end
        if(board[cur1][cur2] == 'M') {
            // 修改后返回
            board[cur1][cur2] = 'X';
            return;
        }

        //If you click on E
        if (board[cur1][cur2] == 'E') {
            // Calculate the bomb around the current location
            int count = countor(board, cur1, cur2);
            // Zero is changed to B and as long as B can continue recursion.
            // Otherwise, only the Count value is marked to end the recursion.
            if(count == 0) {
                board[cur1][cur2] = 'B';

                for(int i = 0; i < 8; i++) {
                    int temp1 = cur1 + pos1[i];
                    int temp2 = cur2 + pos2[i];
                    if(temp1 < 0 || temp1 >= y || temp2 < 0 || temp2 >= x){
                        continue;
                    }

                    if (board[temp1][temp2] == 'E') {
                        dfs(board, temp1, temp2);
                    }
                }

            } else {
                // 直接mark周围炸弹的数量 然后返回
                board[cur1][cur2] = (char)(count + '0');
            }
        }
    }

    // calculate the eight points around the current position
    public int countor(char[][] board, int cur1, int cur2){
        int count = 0;
        for(int i = 0; i < 8; i++) {
            int temp1 = cur1 + pos1[i];
            int temp2 = cur2 + pos2[i];
            if(temp1 < 0 || temp1 >= y || temp2 < 0 || temp2 >= x) {
                continue;
            }

            if(board[temp1][temp2] == 'M') {
                count++;
            }
        }

        return count;
    }

}
