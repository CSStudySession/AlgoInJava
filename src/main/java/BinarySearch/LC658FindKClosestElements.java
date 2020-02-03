package BinarySearch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array.
 * The result should also be sorted in ascending order.
 * If there is a tie, the smaller elements are always preferred.
 *
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 *
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 *
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 10^4
 * Absolute value of elements in the array and x will not exceed 10^4
 *
 * 思路:二分答案区间的起点
 * One tricky part worth call out is, A[mid + k] is the right open boundary,
 * i.e. given the current range is [i, i+k-1], we compare x - A[i] with A[i+k] - x instead of A[i+k-1] - x.
 * Why? An example is [...3,3,4,7...], k = 3, x = 5,
 * if the range is [3, 3, 4], compare 5-3 and 4-5 will move the range right, get a wrong answer [3,4,7].
 * So the comparison is to answer the question: if we move the range right, can we get a better option?
 */
public class LC658FindKClosestElements {

    public List<Integer> findClosestElements(int[] A, int k, int x) {
        int left = 0, right = A.length - k;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - A[mid] > A[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return Arrays.stream(A, left, left + k).boxed().collect(Collectors.toList());
    }
}
