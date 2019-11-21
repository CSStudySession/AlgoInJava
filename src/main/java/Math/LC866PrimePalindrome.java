package Math;

/**
 * Find the smallest prime palindrome greater than or equal to N.
 *
 * Recall that a number is prime if it's only divisors are 1 and itself,
 * and it is greater than 1.
 * For example, 2,3,5,7,11 and 13 are primes.
 * Recall that a number is a palindrome if it reads the same from left to right
 * as it does from right to left.
 * For example, 12321 is a palindrome.
 *
 * Example 1:
 * Input: 6
 * Output: 7
 *
 * Example 2:
 * Input: 8
 * Output: 11
 *
 * Example 3:
 * Input: 13
 * Output: 101
 *
 * Note:
 * 1 <= N <= 10^8
 * The answer is guaranteed to exist and be less than 2 * 10^8.
 *
 * 思路:
 * 先找出比N大的第一个回文数，判断其是否为素数，
 * 若为否，则直接寻找下一个回文数，并判断，循环直至找到回文素数。
 */
public class LC866PrimePalindrome {

    public int primePalindrome(int N) {

        while (!isPal(N)) N++;
        while(!isPrime(N)) N = nextPal(N);
        return N;
    }

    private boolean isPal(int x) {
        int reverse = 0;
        int src = x;
        while (x != 0) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }

        return reverse == src;
    }

    private int nextPal(int x) {
        int n = 0, m, tmp;
        while(Math.pow(10, n) <= x) n ++;
        for (m = (n - 1) / 2; m >= 0; m --) {
            tmp = x / (int)Math.pow(10, m) % 10;
            if (tmp < 9) {
                if (n % 2 != 0 && m == (n-1)/2) return x + (int)Math.pow(10, m);
                return x + (int)Math.pow(10, m+1) + (int)Math.pow(10, m);
            }
        }
        return x + 2;
    }

    private boolean isPrime(int x) {
        if (x < 2) return false;
        int s = (int)Math.sqrt(x);
        for (int i = 2; i <= s; i ++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
