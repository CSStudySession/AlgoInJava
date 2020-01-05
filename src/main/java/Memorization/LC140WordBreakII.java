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

        // base case之一 当前字符串就是字典中的一个 先不分割 直接放入结果集
        if (dict.contains(s)) {
            results.add(s);
        }

        // 上面已经检查过s本身是否存在于字典中 所以这里len只需要遍历到s.length()-1即可
        for (int len = 1; len < s.length(); len++) {
            String word = s.substring(0, len);
            // 剪枝 如果前缀切出的word不在字典里 直接continue
            if (!dict.contains(word)) {
                continue;
            }

            /*
            s此时由两部分组成："word + [len, s.length-1]"
            取出[len, s.length()-1]这部分后缀字符串 进入下一层进行切割
             */
            String suffix = s.substring(len);
            // segments里面存的是以suffix为母串的所有合法切分组合
            List<String> segments = wordBreakHelper(suffix, dict, memo);

            // 这里等于做一个笛卡尔并集操作
            for (String segment: segments){
                results.add(word + " " + segment);
            }
        }

        memo.put(s, results);
        return results;
    }

}
