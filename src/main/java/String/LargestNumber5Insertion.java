package String;

/**
 * given a string representation  of number, return the largest number it can make
 * by inserting a '5' in it.
 *
 * note: focus on correctness.
 */
public class LargestNumber5Insertion {

    public int solution(int N) {
        // write your code in Java SE 8
        if (N == 0) return 50;

        boolean isNegative = N < 0 ? true : false;

        if (N < 0) {
            N = -N;
        }

        String str = Integer.toString(N);

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < str.length(); i++) {
            String nextStr = str.substring(0, i+1) + "5" + str.substring(i+1);
            int nextInt = Integer.parseInt(nextStr);
            max = Math.max(nextInt, max);
            min = Math.min(nextInt, min);
        }

        String firstStr = "5" + str;
        int firstInt = Integer.parseInt(firstStr);
        max = Math.max(firstInt, max);
        min = Math.min(firstInt, min);

        return isNegative ? -min : max;
    }
}
