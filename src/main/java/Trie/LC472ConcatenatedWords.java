package Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a list of words (without duplicates), please write a program that
 * returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely
 * of at least two shorter words in the given array.
 *
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *
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
 */
public class LC472ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }

        // construct Trie tree
        TrieNode root = new TrieNode();
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }

            addWord(word, root);
        }

        // test word is a concatenated word or not
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }

            if (testWord(word.toCharArray(), 0, root, 0)) {
                res.add(word);
            }
        }

        return res;
    }

    // count means how many words during the search path
    private boolean testWord(char[] chars, int index, TrieNode root, int count) {
        TrieNode cur = root;
        int n = chars.length;
        for (int i = index; i < n; i++) {
            if (cur.sons[chars[i] - 'a'] == null) {
                return false;
            }

            if (cur.sons[chars[i] - 'a'].isEnd) {
                if (i == n - 1) { // no next word, so test count to get result.
                    return count >= 1;
                }

                // 回到root重新搜
                if (testWord(chars, i + 1, root, count + 1)) {
                    return true;
                }
            }

            cur = cur.sons[chars[i] - 'a'];
        }
        // 如果不能从for loop里返回 就证明找不到 直接这里返回false
        return false;
    }

    public void addWord(String str, TrieNode root) {
        char[] chars = str.toCharArray();
        TrieNode cur = root;
        for (char c : chars) {
            if (cur.sons[c - 'a'] == null) {
                cur.sons[c - 'a'] = new TrieNode();
            }
            cur = cur.sons[c - 'a'];
        }

        cur.isEnd = true;
    }

    class TrieNode {
        // 假设每个节点只存小写字母
        TrieNode[] sons;
        boolean isEnd;
        public TrieNode() {
            sons = new TrieNode[26];
        }
    }

}


