package Array;

/**
 * In a binary matrix (all elements are 0 and 1), every row is sorted in ascending order (0 to the left of 1).
 * Find the leftmost column index with a 1 in it.
 *
 * Example 1:
 * Input:
 * [[0, 0, 0, 1],
 *  [0, 0, 1, 1],
 *  [0, 1, 1, 1],
 *  [0, 0, 0, 0]]
 * Output: 1
 *
 * Example 2:
 * Input:
 * [[0, 0, 0, 0],
 *  [0, 0, 0, 0],
 *  [0, 0, 0, 0],
 *  [0, 0, 0, 0]]
 * Output: -1
 * Expected solution better than O(r * c).
 *
 * 思路:
 * Just start at bottom right and go left if you see a one and go up if you see a zero.
 * If we go out of bounds on the left that means the answer is 0. If we go out of bounds on the top the answer is the column we are at+1.
 * Only case left to handle is with no ones.
 */
public class LeftMostColumnIndexOfOne {

    public int lowestOneCol(int[][] matrix){
        if(matrix == null || matrix.length == 0) return -1;

        int i = matrix.length - 1, j = matrix[0].length - 1;
        while (i >= 0 && j >= 0) {
            if (matrix[i][j] == 0) i--;
            else j--;
        }

        return j == matrix[0].length - 1 ? -1 : j + 1;
    }

}
