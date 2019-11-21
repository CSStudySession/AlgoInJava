package dynamicProgramming;


import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of strings which just has lower case letters and a target string, output all the strings for each the edit distance with the target no greater than k.
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 * Example
 * Given words = ["abc", "abd", "abcd", "adc"] and target = "ac", k = 1
 * Return ["abc", "adc"]
 */

class TrieNode{
    public TrieNode[] sons;
    public boolean isWord;
    public String word;

    public TrieNode() {
        int i;
        sons = new TrieNode[26];
        for (i = 0; i < 26; ++i) {
            sons[i] = null;
        }

        isWord = false;
    }

    public static void Insert(TrieNode p, String word) {
        int i;
        char[] s = word.toCharArray();
        for (i = 0; i < s.length; ++i) {
            int c = s[i] - 'a';
            if (p.sons[c] == null) {
                p.sons[c] = new TrieNode();
            }

            p = p.sons[c];
        }

        p.isWord = true;
        p.word = word;
    }
}

public class kEditDistance {
    int K;
    int n;
    char[] target;
    List<String> res;

    // p is the current TrieNode
    // f[] representss f[Sp][...]
    void dfs(TrieNode p, int[] f) {
        int[] newf;
        int i;
        if (p.isWord && f[n] <= K) {
            res.add(p.word);
        }

        for (int c = 0; c < 26; ++c) {
            if (p.sons[c] == null) {
                continue;
            }

            // calc newf
            newf = new int[n + 1];
            // newf[...]: f[Sp + c][....]

            // newf[j] = Math.min(Math.min(f[j], newf[j-1]), f[j-1]) + 1;
            for (i = 0; i <= n; ++i) {
                newf[i] = f[i] + 1;
            }

            for (i = 1; i <= n; ++i) {
                newf[i] = Math.min(newf[i], f[i - 1] + 1);
            }

            for (i = 1; i <= n; ++i) {
                if (target[i - 1] - 'a' == c) {
                    newf[i] = Math.min(newf[i], f[i - 1]);
                }

                newf[i] = Math.min(newf[i - 1] + 1, newf[i]);
            }

            dfs(p.sons[c], newf);
        }
    }

    public List<String> kDistance(String[] words, String targets, int k) {
        res = new ArrayList<String>();
        K = k;
        TrieNode root = new TrieNode();
        int i;
        for (i = 0; i < words.length; ++i) {
            TrieNode.Insert(root, words[i]);
        }

        target = targets.toCharArray();
        n = target.length;
        int[] f = new int[n + 1];
        for (i = 0; i <= n; ++i) {
            f[i] = i;
        }

        dfs(root, f);
        return res;
    }
}
