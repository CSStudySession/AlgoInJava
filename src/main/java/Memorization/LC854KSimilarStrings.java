package Memorization;

import java.util.HashMap;
import java.util.Map;

/**
 * Strings A and B are K-similar (for some non-negative integer K)
 * if we can swap the positions of two letters in A exactly K times
 * so that the resulting string equals B.
 *
 * Given two anagrams A and B, return the smallest K for which A and B are K-similar.
 *
 * Example 1:
 * Input: A = "ab", B = "ba"
 * Output: 1
 *
 * Example 2:
 * Input: A = "abc", B = "bca"
 * Output: 2
 *
 * Example 3:
 * Input: A = "abac", B = "baca"
 * Output: 2
 *
 * Example 4:
 * Input: A = "aabc", B = "abca"
 * Output: 2
 *
 * Note:
 * 1 <= A.length == B.length <= 20
 * A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}
 */
public class LC854KSimilarStrings {

    public int kSimilarity(String A, String B) {
        Map<String, Integer> map = new HashMap<>();
        return backtrack(A.toCharArray(), B, map, 0);
    }
    private int backtrack(char[] A, String B, Map<String, Integer> map, int i) {
        String sa = new String(A);
        // 当前字符串已经到达目标串B 所以变换次数为0(不需要变换)
        if (sa.equals(B)) {
            return 0;
        }
        /*
        如果字典里含有对当前串的变换次数 证明之前搜过
        直接返回结果
         */
        if (map.containsKey(sa)) {
            return map.get(sa);
        }

        int min = Integer.MAX_VALUE;
        // 找第一个不匹配的字符位置
        while (i < A.length && A[i] == B.charAt(i)) {
            i++;
        }
        for (int j = i + 1; j < B.length(); j++) {
            if (A[j] == B.charAt(i)) {
                swap(A, i, j);
                int next = backtrack(A, B, map, i + 1);
                if (next != Integer.MAX_VALUE) {
                    min = Math.min(min, next + 1);
                }
                // 回溯时消除影响！
                swap(A, i, j);
            }
        }
        // 更新结果在字典里
        map.put(sa, min);
        // 返回该字符串到目标串的最小变换步数
        return min;
    }

    private void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

}
