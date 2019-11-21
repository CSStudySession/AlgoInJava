package BinarySearch;


import java.util.ArrayList;
import java.util.List;

/**
 * There is an integer matrix which has the following features:
 *
 * The numbers in adjacent positions are different.
 * The matrix has n rows and m columns.
 * For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
 * For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].
 * We define a position P is a peek if:
 *
 * A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
 * Find a peak element in this matrix. Return the index of the peak.
 *
 * Note: The matrix may contains multiple peeks, findWithDuplicates any of them.
 *
 * Example
 * Given a matrix:
 *
 * [
 *   [1 ,2 ,3 ,6 ,5],
 *   [16,41,23,22,6],
 *   [15,17,24,21,7],
 *   [14,18,19,20,10],
 *   [13,14,11,10,9]
 * ]
 * return index of 41 (which is [1,1]) or index of 24 (which is [2,2])
 *
 * Challenge
 * Solve it in O(n+m) time.
 *
 * If you come up with an algorithm that you thought it is O(n log m) or O(m log n), can you prove it is actually O(n+m) or propose a similar but O(n+m) algorithm?
 */


//
public class FindPeakElementsII {

    public List<Integer> findPeakII(int[][] A) {
        int i = 1, j = A.length-2;

        while (i <= j) {
            int mid = (i + j) / 2;
            int col = findCol(A[mid]);
            if (A[mid][col] < A[mid-1][col]) {
                j = mid-1;
            } else if (A[mid][col] < A[mid+1][col]) {
                i = mid+1;
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(mid);
                list.add(col);
                return list;
            }
        }

        return new ArrayList<>();
    }

    private int findCol(int[] nums) {
        int col = 1;

        for (int i = 2; i < nums.length; ++i) {
            if (nums[i] > nums[col]) col = i;
        }

        return col;
    }
}
