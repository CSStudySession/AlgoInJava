package Airbnb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There is a new alien language which uses the latin alphabet.
 * However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary,
 * where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 *
 * Example 1:
 *
 * Input:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 *
 * Output: "wertf"
 * Example 2:
 *
 * Input:
 * [
 *   "z",
 *   "x"
 * ]
 *
 * Output: "zx"
 * Example 3:
 *
 * Input:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 *
 * Output: ""
 *
 * Explanation: The order is invalid, so return "".
 */
public class AlienDictionary {

    public String alienOrder(String[] words) {
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, List<Character>> edges = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                map.put(c, 0);
                edges.put(c, new ArrayList<>());
            }
        }

        for (int j = 1; j < words.length; ++j) {
            String pre = words[j-1];
            String cur = words[j];

            int i = 0, len = Math.min(pre.length(),cur.length());

            while (i < len) {
                char p = pre.charAt(i);
                char c = cur.charAt(i);
                if (p != c) {
                    map.put(c, map.get(c)+1);
                    edges.get(p).add(c);
                    break;
                }
                ++i;
            }
        }

        Queue<Character> queue = new LinkedList<>();

        for (Character key : map.keySet()) {
            if (map.get(key) == 0) {
                queue.offer(key);
            }
        }

        String ret = "";
        while(!queue.isEmpty()) {
            Character c = queue.poll();
            ret += c;
            for (Character n : edges.get(c)) {
                map.put(n, map.get(n)-1);
                if (map.get(n) == 0) {
                    queue.offer(n);
                }
            }
        }

        return ret;
    }


    public static void main(String[] args) {
        String[] strs = {"wrt", "wrf", "er", "ett", "rftt"};

        AlienDictionary alienDictionary = new AlienDictionary();

        System.out.println(alienDictionary.alienOrder(strs));
    }
}
