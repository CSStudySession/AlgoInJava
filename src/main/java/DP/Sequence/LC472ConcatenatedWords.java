package DP.Sequence;

import java.util.*;

/**
 * Given a list of words (without duplicates),
 * please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of
 * at least two shorter words in the given array.
 *
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 *
 * Note:
 * The number of elements of the given array will not exceed 10,000
 * The length sum of elements in the given array will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.
 *
 * 思路:
 * 对于words中的每个单词w，定义一个数组dp[n+1]，如果dp[i] == true，
 * 则表示w.substr(0, i)可以由words中的已有单词连接而成。
 * 那么状态转移方程就是：dp[i] = || {dp[j] && w.substr(j + 1, i - j) is in words}，
 * 其中j < i。
 * 最终检查dp[n]是否为true，如果是则将其加入结果集中。
 *
 * 为了加速对words中的单词的查找，用一个哈希表来保存各个单词。
 * 这样时间复杂度可以降低到O(n * m^2)，其中n是words中的单词的个数，m是每个单词的平均长度
 * 或者最大长度
 */
public class LC472ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        Set<String> set = new HashSet<>();// using hash for acceleration

        for(int i = 0; i < words.length;i++){
            set.add(words[i]);
        }

        List<String> res = new ArrayList<>();
        for (String word : words) {
            int n = word.length();
            // store whether w.substr(0, j) can be formed by existing words
            boolean[] dp = new boolean[n+1];

            dp[0] = true; // empty string is always valid
            for(int i = 0; i < n;i++) {
                if (!dp[i]) continue; // cannot start from here
                // check whether w.substr(i, j - i) can be concatenated from i
                for (int j = i + 1; j <= n; j++) {
                    // cannot be itself -> (j - i < n)
                    if (j - i < n && set.contains(word.substring(i,j))) {
                        dp[j] = true;
                    }
                }

                if (dp[n]) {
                    res.add(word);
                    break;
                }
            }
        }

        return res;
    }
}
