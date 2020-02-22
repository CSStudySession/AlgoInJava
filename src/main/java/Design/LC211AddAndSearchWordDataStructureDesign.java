package Design;

/**
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or ..
 * A . means it can represent any one letter.
 *
 * Example:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 */
public class LC211AddAndSearchWordDataStructureDesign {

    private TrieNode root;

    /** Initialize your data structure here. */
    public LC211AddAndSearchWordDataStructureDesign() {
        this.root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode(c);
            }
            node = node.children[idx];
        }

        node.isWord = true;
    }

    /** Returns if the word is in the data structure.
     * A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return helper(word, 0, root);
    }

    // use DFS to search given input word
    private boolean helper(String word, int idx, TrieNode node) {
        // base case
        if (idx == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(idx);
        if (c != '.') {
            if (node.children[c - 'a'] == null) {
                return false;
            }

            return helper(word, idx + 1, node.children[c - 'a']);
        } else {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null && helper(word, idx + 1, node.children[i])) {
                    return true;
                }
            }

            return false;
        }
    }

    class TrieNode {
        private char ch;
        private boolean isWord;
        private TrieNode[] children;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
        public TrieNode(char c) {
            this.children = new TrieNode[26];
            this.ch = c;
        }
    }

}
