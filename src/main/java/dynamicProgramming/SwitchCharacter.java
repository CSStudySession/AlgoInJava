package dynamicProgramming;

/**
 * Given 3 strings, s1, s2, and s3, determine whether s3 can be combine by s1 and s2 while the character order in s1 and s2 are still the same.
 *
 * For example,
 *
 * s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac", then return true
 * s1 = "aabcc", s2 = "dbbca", s3 = "accabdbbca", return false;
 */
public class SwitchCharacter {

    public boolean isCharacterSwitched(String s1, String s2, String s3) {

        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];

        dp[0][0] = true;

        for (int i = 0; i <= s1.length(); ++i) {
            for (int j = 0; j <= s2.length(); ++j) {


                if (dp[i][j]
                        || (i-1 >= 0 && dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1))
                        || (j-1 >= 0 && dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1))) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = false;
                }


            }
        }

        return dp[s1.length()][s2.length()];
    }


    public static void main(String[] args) {
        SwitchCharacter switchCharacter = new SwitchCharacter();

        System.out.println(switchCharacter.isCharacterSwitched("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(switchCharacter.isCharacterSwitched("aabcc", "dbbca", "accabdbbca"));
    }
}
