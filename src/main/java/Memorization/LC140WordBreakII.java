package Memorization;

import java.util.*;

/**
 * 给一字串s和单词的字典dict,在字串中增加空格来构建一个句子，并且所有单词都来自字典。
 * 返回所有有可能的句子。
 *
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
 *
 * 记忆化搜索
 */
public class LC140WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> memo = new HashMap<>();
        Set<String> dict = new HashSet<>(wordDict);
        return wordBreakHelper(s, dict, memo);
    }

    // 返回以s为母串的所有拼凑方式
    public List<String> wordBreakHelper(String s,
                                        Set<String> dict,
                                        Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> results = new ArrayList<>();

        if (s.length() == 0) {
            return results;
        }

        if (dict.contains(s)) {
            results.add(s);
        }

        // 上面已经检查过s本身是否存在于字典中 所以这里len只需要遍历到s.length()-1即可
        for (int len = 1; len < s.length(); len++) {
            String word = s.substring(0, len);
            if (!dict.contains(word)) {
                continue;
            }

            //取出从[len, s.length()-1]这部分后缀字符串 进入下一层进行切割
            String suffix = s.substring(len);
            List<String> segments = wordBreakHelper(suffix, dict, memo);

            for (String segment: segments){
                results.add(word + " " + segment);
            }
        }

        memo.put(s, results);
        return results;
    }

}
