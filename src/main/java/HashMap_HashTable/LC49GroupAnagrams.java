package HashMap_HashTable;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 *
 * Example:
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * Note:
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 */
public class LC49GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;

        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] sList = strs[i].toCharArray();
            Arrays.sort(sList);
            String str = new String(sList);
            if (map.containsKey(str)) {
                map.get(str).add(strs[i]);
            } else {
                List<String> curList = new ArrayList<>();
                curList.add(strs[i]);
                map.put(str, curList);
            }
        }

        for (String str : map.keySet()) {
            res.add(map.get(str));
        }

        return res;
    }

}
