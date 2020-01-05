package DP.Match;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
* Given a string s and a dictionary of words dict, determine if s can be segmented into a
* space-separated sequence of one or more dictionary words.
*
* For example, given
* s = "leetcode",
* dict = ["leet", "code"].
*
* Return true because "leetcode" can be segmented as "leet code".
*
* f[i] 表示前i个字符是否可以分。
* 从前往后枚举结尾。对于每个结尾枚举分成的最后一段的长度j。然后看f[i-j]是否可分。
*
* initialize dp[s.length() + 1], dp[0] = true
* dp function: dp[i] = dp[j] & (s[j, i] in dict)
* result: dp[s.length()]
 */
public class LC139WordBreak {

    public boolean wordBreak(String s, List<String> dict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Set<String> set = new HashSet<>(dict);

        int maxLength = getMaxLength(set);
        // dp[i]:前i位是否能被匹配
        boolean[] canSegment = new boolean[s.length() + 1];

        canSegment[0] = true; //空字符串默认可以匹配
        for (int i = 1; i <= s.length(); i++) {
            // maxLength用来剪枝 注意j,i都是长度(位数) 不是index!
            for (int j = 1; j < maxLength && j <= i; j++) {
                if (!canSegment[i-j]) continue;
                String str = s.substring(i-j,i);
                if (set.contains(str)) {
                    canSegment[i] = true;
                    break;
                }
            }
        }
        return canSegment[s.length()];
    }

    //找字典set里面最长的单词长度 在dp时会用到
    private int getMaxLength(Set<String> set) {
        int maxLength = 0;
        for (String word : set) {
            maxLength = Math.max(maxLength, word.length());
        }
        return maxLength;
    }

}
