package BinarySearch;

/**
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 *
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result
 * is returned.
 *
 * Example 1:
 * Input: 4
 * Output: 2
 *
 * Example 2:
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 * the decimal part is truncated, 2 is returned.
 */
public class LC69Sqrtx {

    public int mySqrt(int x) {
        if (x < 0)  throw new IllegalArgumentException();
        else if (x <= 1)    return x;
        int start = 1, end = x / 2;
        // 直接对答案可能存在的区间进行二分 => 二分答案
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // writing in this way instead of "nums[mid] * nums[mid]" to avoid overflow
            if (mid == x / mid)  return mid;
                // possible root must be larger than or equal to current mid
            else if (mid < x / mid) start = mid;
                // possible root must be smaller than or equal to current mid
            else    end = mid;
        }
        if (end > x / end)  return start;
        return end;
    }

}
