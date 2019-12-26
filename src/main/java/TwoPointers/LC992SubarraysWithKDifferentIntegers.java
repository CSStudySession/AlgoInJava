package TwoPointers;


/**
 * Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good
 * if the number of different integers in that subarray is exactly K.
 *
 * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 *
 * Return the number of good subarrays of A.
 *
 * Example 1:
 * Input: A = [1,2,1,2,3], K = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers:
 * [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 *
 * Example 2
 * Input: A = [1,2,1,3,4], K = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 *
 * Note:
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 */
public class LC992SubarraysWithKDifferentIntegers {

    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKSubArr(A, K) - atMostKSubArr(A, K - 1);
    }

    private int atMostKSubArr(int[] A, int K) {
        // 由于A[i]范围是[1, A.length] 可以用数组代替哈希表
        int[] map = new int[A.length + 1];
        int result = 0;

        // 滑窗模板
        for (int i = 0, j = 0; i < A.length; i++) {
            /*
            维护一个窗口[i,j] 使得窗口内的不同元素个数小于或等于K
             */
            while (j < A.length && K >= 0) {
                if (map[A[j]] == 0) {
                    // A[j]是新元素且此时窗口内已有K个不同元素 不能更新答案 需要移动i指针
                    if (K == 0) break;
                    K--;
                }
                map[A[j]]++;
                // 每次看到新的j 就要更新答案：以当前j结尾的所有满足要求的子数组
                result += j - i + 1;
                j++;
            }
            // j到头了 直接退出计算
            if (j == A.length) break;
            // 移动i指针之前 把i指向的元素频率重置 然后判断是否需要增加K
            map[A[i]]--;
            if (map[A[i]] == 0) K++;
        }

        return result;
    }

}
