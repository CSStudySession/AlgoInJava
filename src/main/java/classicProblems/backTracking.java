package classicProblems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * General Algo:
 *
 * ALGORITHM try(v1,...,vi)  // 这里的V1.....V2携带的参数说明 “可能解”
 *    // 入口处验证是否是全局解，如果是，直接返回。
 *    // 实际编程中也需要查看是否是无效解，如果是，也是直接返回
 *    IF (v1,...,vi) is a solution THEN RETURN (v1,...,vi)
 *    FOR each v DO  // 对于每一个可能的解，进行查看
 *       // 下面的含义是形成一个可能解 进行递归
 *       IF (v1,...,vi,v) is acceptable vector  THEN
 *         sol = try(v1,...,vi,v)
 *         IF sol != () THEN RETURN sol
 *         // 这个地方其实需要增加“回溯” 处理，实际编程中通常是函数参数的变化
 *       END
 *    END
 *    RETURN ()
 */


public class backTracking {


    /**
     * Given a pattern and a string str, findWithDuplicates if str follows the same pattern.
     *
     * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
     *
     * Example 1:
     *
     * Input: pattern = "abab", str = "redblueredblue"
     * Output: true
     * Example 2:
     *
     * Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
     * Output: true
     * Example 3:
     *
     * Input: pattern = "aabb", str = "xyzabcxzyabc"
     * Output: false
     * Notes:
     * You may assume both pattern and str contains only lowercase letters.
     *
     *
     */
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }

    private boolean isMatch(String str, int i, String pattern, int j, Map<Character, String> map, Set<String> set) {

        if (i == str.length() && j == pattern.length()) return true;
        if (i == str.length() || j == pattern.length()) return false;

        char c = pattern.charAt(j);

        if (map.containsKey(c)) {

            String s = map.get(c);

            if (!str.startsWith(s, i)) {
                return false;
            }

            return isMatch(str, i+s.length(), pattern, j+1, map, set);
        }

        for (int k = i; k < str.length(); ++k) {
            String p = str.substring(i, k+1);
            if (set.contains(p)) {
                return false;
            }

            map.put(c, p);
            set.add(p);

            if (isMatch(str, i+p.length(), pattern, j+1, map, set)) {
                return true;
            }

            map.remove(c);
            set.remove(p);
        }

        return false;
    }
}
