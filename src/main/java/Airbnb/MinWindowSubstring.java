package Airbnb;


import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, findWithDuplicates the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinWindowSubstring {

    public String minWindow(String s, String t) {
        Map<Character, Integer> dict = new HashMap<>();

        for (int i = 0; i < t.length(); ++i) {
            char c = t.charAt(i);
            dict.put(c, dict.getOrDefault(c, 0)+1);
        }

        int requiredSize = dict.size();
        int matched = 0, l = 0, r = 0;
        int[] ans = {-1, 0, 0};

        Map<Character, Integer> wc = new HashMap<>();

        while (r < s.length()) {
            char c = s.charAt(r);
            wc.put(c, wc.getOrDefault(c, 0) + 1);

            if (dict.containsKey(c) && wc.get(c).equals(dict.get(c))) {
                matched++;
            }

            while (l <= r && matched == requiredSize) {
                char left = s.charAt(l);

                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                wc.put(left, wc.get(left)-1);

                if(dict.containsKey(left) && wc.get(left) < dict.get(left)) {
                    matched--;
                }
                l++;
            }
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2]+1);

    }


    public static void main(String[] args) {
        String S = "ADOBECODEBANC", T = "ABC";
        MinWindowSubstring minWindowSubstring = new MinWindowSubstring();
        System.out.println(minWindowSubstring.minWindow(S, T));
    }
}
