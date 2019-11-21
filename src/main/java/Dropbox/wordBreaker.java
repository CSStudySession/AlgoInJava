package Dropbox;

import java.util.*;

public class wordBreaker {


    /**
     *
     * Word Break II
     *
     *
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
     *
     * Note:
     *
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     * Example 1:
     *
     * Input:
     * s = "catsanddog"
     * wordDict = ["cat", "cats", "and", "sand", "dog"]
     * Output:
     * [
     *   "cats and dog",
     *   "cat sand dog"
     * ]
     * Example 2:
     *
     * Input:
     * s = "pineapplepenapple"
     * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
     * Output:
     * [
     *   "pine apple pen apple",
     *   "pineapple pen apple",
     *   "pine applepen apple"
     * ]
     * Explanation: Note that you are allowed to reuse a dictionary word.
     * Example 3:
     *
     * Input:
     * s = "catsandog"
     * wordDict = ["cats", "dog", "sand", "and", "cat"]
     * Output:
     * []
     */


    /*
        Self solution
     */
    public List<String> wordBreakSelf(String s, List<String> wordDict) {
        List<String> ret = new ArrayList<>();
        List<String> list = new ArrayList<>();

        find(ret, list, wordDict, s, 0);

        return ret;
    }


    private void find(List<String> ret, List<String> list, List<String> wordDict, String s, int start) {
        if (start >= s.length()) {
            String str = "";
            int i = 0;
            while (i < list.size()-1) {
                str += list.get(i) + " ";
                ++i;
            }
            str += list.get(i);
            ret.add(str);
            return ;
        }

        for (int i = start+1; i <= s.length(); ++i) {

            String sub = s.substring(start, i);

            if (wordDict.contains(sub)) {
                list.add(sub);

                find(ret, list, wordDict, s, i);

                list.remove(sub);
            }

        }
    }



    /*
        Recursion with memoization
     */
    private Map<Integer, List<String>> cache = new HashMap<>();
    int maxWordLen = 0;
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        for(String word: wordDict) {
            maxWordLen = Math.max(maxWordLen, word.length());
        }
        List<String> last = new ArrayList<>();
        last.add("");
        cache.put(s.length(), last);
        return dfs(s, 0, dict);
    }
    private List<String> dfs(String s, int i, Set<String> wordDict) {
        if(cache.get(i) != null) {
            return cache.get(i);
        }
        List<String> ret = new ArrayList<>();
        for(int j = i+1; j <= Math.min(s.length(), i+maxWordLen); ++j) {
            String w = s.substring(i, j);
            if(wordDict.contains(w)) {
                List<String> res = dfs(s, j, wordDict); // if wordDict contains w, then need to recursively calculate dfs(s,j)
                for(String sub: dfs(s, j, wordDict)) {
                    ret.add("".equals(sub) ? w : w + " " + sub);
                }
            }
        }
        cache.put(i, ret);
        return cache.get(i);
    }



    /*
        Dynamic Programming
     */


    public List<String> wordBreakDP(String s, Set<String> wordDict) {
        LinkedList<String>[] dp = new LinkedList[s.length() + 1];
        LinkedList<String> initial = new LinkedList<>();
        initial.add("");
        dp[0] = initial;
        for (int i = 1; i <= s.length(); i++) {
            LinkedList<String> list = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                if (dp[j].size() > 0 && wordDict.contains(s.substring(j, i))) {
                    for (String l : dp[j]) {
                        list.add(l + (l.equals("") ? "" : " ") + s.substring(j, i));
                    }
                }
            }
            dp[i] = list;
        }
        return dp[s.length()];
    }

}
