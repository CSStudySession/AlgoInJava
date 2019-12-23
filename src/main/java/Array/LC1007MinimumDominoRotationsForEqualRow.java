package Array;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.
 * (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * Return the minimum number of rotations so that all the values in A are the same,
 * or all the values in B are the same.
 * If it cannot be done, return -1.
 *
 * Example 1:
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
 *
 * Example 2:
 * Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 * Output: -1
 * Explanation:
 * In this case, it is not possible to rotate the dominoes to make one row of values equal.
 *
 * Note:
 * 1 <= A[i], B[i] <= 6
 * 2 <= A.length == B.length <= 20000
 *
 * 思路：
 * 最终都换完的时候 要么是A数组都是A[0], A[0], .... A[0], 要么都是B[0], ...B[0]
 * 换A[0]或B[0]时 都有两种选择：从A往B换或从B往A换 分别计算出四种可能的交换方式 取最小值即可
 */
public class LC1007MinimumDominoRotationsForEqualRow {

    public int minDominoRotations(int[] A, int[] B) {
        int refA0 = Math.min(countRotations(A[0], A, B), countRotations(A[0], B, A));
        int refB0 = Math.min(countRotations(B[0], A, B), countRotations(B[0], B, A));

        int result = Math.min(refA0, refB0);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int countRotations(int target, int[] to, int[] from) {
        int count = 0;
        for (int i = 0; i < to.length; i++) {
            if (to[i] != target) {
                if (from[i] == target) {
                    count++;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }
        return count;
    }
}
