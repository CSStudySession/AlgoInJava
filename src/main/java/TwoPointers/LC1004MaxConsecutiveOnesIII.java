package TwoPointers;

/**
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 *
 * Example 1:
 * Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * Output: 6
 * Explanation:
 * [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 *
 * Example 2:
 * Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * Output: 10
 * Explanation:
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 *
 * Note:
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] is 0 or 1
 */
public class LC1004MaxConsecutiveOnesIII {

    public int longestOnes(int[] A, int K) {
        if (A == null || A.length == 0) return 0;
        int res = 0, j = 0, zeros = 0;

        for (int i = 0; i < A.length; i++) {

            while (j < A.length) {
                if (A[j] == 0 && zeros == K) break;
                if (A[j] == 0) {
                    zeros++;
                }
                res = Math.max(res, j - i + 1);
                j++;
            }

            if (A[i] == 0) {
                zeros--;
            }
        }

        return res;
    }

}
