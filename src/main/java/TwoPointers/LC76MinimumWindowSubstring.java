package TwoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T,
 * find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 *
 *  Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class LC76MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        int countT = t.length();
        for(int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int countS = 0;
        int min = Integer.MAX_VALUE;
        String result  = "";
        for(int i = 0, j = 0; i < s.length(); i++){
            // countS>=countT时 再移动j指针 不会有更好的结果
            while(j < s.length() && countS < countT) {
                char cj = s.charAt(j);
                if(map.containsKey(cj)) {
                    if (map.get(cj) > 0) {
                        // 只有当cj对应的次数大于零时 才是原来t串里的cj
                        countS++;
                    }
                    // 即使cj对应的次数小于等于0 也要减一 后面的ci会加回来
                    map.put(cj, map.get(cj) - 1);
                }

                j++; // 别忘了j++!
            }

            // 退出while loop时 要么j到头了 要么找到了一组合法覆盖
            if(countS == countT) {
                if(j - i < min){
                    result = s.substring(i, j);
                    min = j - i;
                }
            }

            //注意这里即使j到头了 也不能break！！

            char ci = s.charAt(i);
            if(map.containsKey(ci)) {
                if (map.get(ci) >= 0) {
                    // 只有ci对应的次数大于或等于零的时候 才是原来t串里的字符
                    countS--;
                }
                // 把cj减掉的次数都要还回去
                map.put(ci, map.get(ci) + 1);
            }
        }

        return result;
    }

}
