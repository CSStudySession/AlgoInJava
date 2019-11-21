package Uber;

import java.util.Arrays;

public class RussianDolls {


    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if (arr1[0] == arr2[0])
                return arr2[1] - arr1[1];
            else
                return arr1[0] - arr2[0];
        });
        int dp[] = new int[envelopes.length];
        int len = 0;
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if (index < 0)
                index = -(index + 1);
            dp[index] = envelope[1];
            if (index == len)
                len++;
        }
        return len;
    }


    public static void main(String[] agrs) {
        int[][] test = {{5,4}, {6,4},{6,7},{2,3}};
        RussianDolls russianDolls = new RussianDolls();
        System.out.println(russianDolls.maxEnvelopes(test));
    }
}
