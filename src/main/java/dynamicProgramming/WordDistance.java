package dynamicProgramming;


/**
 * Given string s and t, findWithDuplicates out the minimum number of operations to convert s to t.
 *
 * Operations:
 * 1. replace a character
 * 2. insert a character
 * 3. delete a character
 */
public class WordDistance {

    public int getWordDist(String s, String t) {

        int[][] dp = new int[s.length()+1][t.length()+1];

        for (int i = 0; i <= s.length(); ++i) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= t.length(); ++j) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 1; j <= t.length(); ++j) {

                if(s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                }
            }
        }

        return dp[s.length()][t.length()];
    }
}
