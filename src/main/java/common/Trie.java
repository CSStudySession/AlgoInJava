package common;

public class Trie {

    public class TrieNode {

        public boolean isWord;
        public TrieNode[] children;

        public TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    public void insert(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (node.children[c-'a'] == null) {
                node.children[c-'a'] = new TrieNode();
            }
            node = node.children[c-'a'];
        }
        node.isWord = true;
    }


    public boolean[] search(String word) {
        boolean[] ret = new boolean[2];
        TrieNode node = root;

        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (node.children[c-'a'] == null) {
                return ret;
            }
            node = node.children[c-'a'];
        }

        ret[1] = node.isWord;
        ret[0] = true;
        return ret;
    }


}
