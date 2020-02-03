package BinarySearch;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero.
 *
 * Example 1:
 * Input: dividend = 10, divisor = 3
 * Output: 3
 *
 * Example 2:
 * Input: dividend = 7, divisor = -3
 * Output: -2
 *
 * Note:
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
 * For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 *
 * 思路:
 * 涉及除法/乘法运算 先考虑下面几个问题：
 * 1. + - 号
 * 2. 越界
 * 3. = 0 -> 3/5
 * 4. 正常情况
 *
 * 时间复杂度: O(logN^2)
 *
 * 理解这个时间复杂度:
 * A doubly nested for loop that looks like the following would have a time complexity of O(n^2):
 *
 * for(int i = 0; i < n; i++){
 *      for(int j = 0; j < i; j++){
 *          //...
 *      }
 * }
 * Similarly, the code I'm running is doing the following:
 * Let's say a is 100, b is 2,4,8,16,32,64, stopping before 100.
 * while( a - (b << 1 << x) >= 0) {
 *      x++;
 * }
 *
 * In our code, b is like the j pointer, a is like the i pointer.
 * Then a is decremented because of this line
 * a -= b << x;
 * so a would be readjusted to 36 ( 100 - 64 ). b starts again from 2. So b loops from 2,4,8,16,32, stopping before 36.
 *
 * So you can deduce by analogy the n^2 where n would be dividend.
 * The reason log comes into this is because we are squaring b at each step. The log is a logarithm of base 2.
 */
public class LC29DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);

        long dividendL = Math.abs((long)dividend);
        long divisorL = Math.abs((long)divisor);
        if (dividendL < divisorL) return 0;
        int res = 0;

        while (dividendL >= divisorL) {
            int shift = 0;
            while (dividendL >= (divisorL << shift)) {
                shift++;
            }
            dividendL -= (divisorL << (shift - 1));
            res += 1 << (shift - 1);
        }

        return isNegative ? -res : res;
    }

    public static void main(String[] args) {
        LC29DivideTwoIntegers inst = new LC29DivideTwoIntegers();
        int res = inst.divide(-2147483648, 2);
        System.out.println(".");
    }

}
