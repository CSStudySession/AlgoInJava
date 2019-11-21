package PrimeNumber;


/**
 * Given a number n, print all primes smaller than or equal to n. It is also given that n is a small number.
 *
 * Following is the algorithm to findWithDuplicates all the prime numbers less than or equal to a given integer n by Eratosthenes’ method:
 *
 * 1. Create a list of consecutive integers from 2 to n: (2, 3, 4, …, n).
 * 2. Initially, let p equal 2, the first prime number.
 * 3. Starting from p^2, count up in increments of p and mark each of these numbers greater than or equal to p^2 itself in the list.
 * These numbers will be p(p+1), p(p+2), p(p+3), etc..
 * 4. Find the first number greater than p in the list that is not marked.
 * If there was no such number, stop. Otherwise, let p now equal this number (which is the next prime), and repeat from step 3.
 */
public class SieveOfEratosthenes {

    public String findPrime(int n) {
        boolean[] nums = new boolean[n+1];

        int p = 2;

        while (p <= n) {
            int start = (int)Math.pow(p, 2);
            for (int i = 0; start + p * i <= n; ++i) {
                int index = start + p * i;
                nums[index] = true;
            }

            ++p;
            for (; p <= n; ++p) {
                if (!nums[p]) break;
            }
        }

        String res = "";
        for (int i = 2; i < nums.length; ++i) {
            if (!nums[i]) {
                res += i + ", ";
            }
        }

        return res;
    }

    public static void main(String[] args) {
        SieveOfEratosthenes sieveOfEratosthenes = new SieveOfEratosthenes();

        System.out.println(sieveOfEratosthenes.findPrime(50));
    }
}
