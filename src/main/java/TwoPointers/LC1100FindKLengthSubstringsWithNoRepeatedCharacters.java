package TwoPointers;

/**
 * Given a string S, return the number of substrings of length K with no repeated characters.
 *
 * Example 1:
 * Input: S = "havefunonleetcode", K = 5
 * Output: 6
 * Explanation:
 * There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.
 *
 * Example 2:
 * Input: S = "home", K = 5
 * Output: 0
 * Explanation:
 * Notice K can be larger than the length of S. In this case is not possible to find any substring.
 *
 * Note:
 * 1 <= S.length <= 10^4
 * All characters of S are lowercase English letters.
 * 1 <= K <= 10^4
 */
public class LC1100FindKLengthSubstringsWithNoRepeatedCharacters {

    public int numKLenSubstrNoRepeats(String S, int K) {
        int res = 0;
        if (K >  26 || S.length() < K)  return 0;
        int[] map = new int[26];
        int charCnt = 0;

        for (int i = 0, j = 0; i < S.length(); i++) {
            while (j < S.length() && j < i + K) {
                if (map[S.charAt(j) - 'a'] == 0) {
                    charCnt++;
                }
                map[S.charAt(j) - 'a']++;
                j++;
            }
            // update result if condition met
            if (charCnt == K) {
                res++;
            }

            if (j == S.length()) break;

            map[S.charAt(i) - 'a']--;
            if (map[S.charAt(i) - 'a'] == 0) {
                charCnt--;
            }
        }

        return res;
    }

}
