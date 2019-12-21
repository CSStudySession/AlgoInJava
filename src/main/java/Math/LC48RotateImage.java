package Math;

/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 *
 * Note:
 * You have to rotate the image in-place,
 * which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * Example 2:
 * Given input matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 * 思路:
 * 1. transpose the matrix
 * 2. flip each row of transposed matrix
 *
 * followup: what if counter clock wise? -> 先flip再transposes
 */
public class LC48RotateImage {

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int rowNum = matrix.length;
        int colNum = rowNum;
        // transpose the matrix
        for (int i = 0; i < rowNum; i++) {
            for (int j = i + 1; j < colNum; j++) {
                swap(i, j, matrix);
            }
        }
        // flip each row of transposed matrix
        for (int i = 0; i < rowNum; i++) {
            flip(matrix[i]);
        }
    }

    private void swap(int i, int j, int[][] matrix) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = tmp;
    }

    private void flip(int[] row) {
        int i = 0;
        int j = row.length - 1;
        while (i < j) {
            int tmp = row[i];
            row[i] = row[j];
            row[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        LC48RotateImage inst = new LC48RotateImage();
        int[][] matrix = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        inst.rotate(matrix);
        System.out.println(".");
    }
}
