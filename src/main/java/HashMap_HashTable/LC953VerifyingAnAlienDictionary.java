package HashMap_HashTable;

/**
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 * Given a sequence of words written in the alien language, and the order of the alphabet,
 * return true if and only if the given words are sorted lexicographicaly in this alien language.
 *
 * Example 1:
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 *
 * Example 2:
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 *
 * Example 3:
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.)
 * According to lexicographical rules "apple" > "app", because 'l' > '∅',
 * where '∅' is defined as the blank character which is less than any other character (More info).
 *
 * Constraints:
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are English lowercase letters.
 *
 * 思路:
 * Build a transform mapping from order,
 * Find all alien words with letters in normal order.
 *
 * For example, if we have order = "xyz..."
 * We can map the word "xyz" to "abc" or "123"
 *
 * Then we check if all words are in sorted order.
 *
 * Complexity:
 * Time O(NS):
 * N is the length of words since we go through the words list to compare each string with its previous one.
 * S is the max length of word in the words list since in the helper function,
 * the worst case is encounter the max string.
 *
 * Space O(1)
 */
public class LC953VerifyingAnAlienDictionary {

    public boolean isAlienSorted(String[] words, String order) {
        int[] mapping = new int[26];

        for (int i = 0; i < order.length(); i++) {
            mapping[order.charAt(i) - 'a'] = i;
        }

        for (int i = 1; i < words.length; i++) {
            if (bigger(words[i - 1], words[i], mapping)) return false;
        }

        return true;
    }

    boolean bigger(String s1, String s2, int[] mapping) {
        int n = s1.length(), m = s2.length();

        for (int i = 0; i < n && i < m; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return mapping[s1.charAt(i) - 'a'] > mapping[s2.charAt(i) - 'a'];
            }
        }

        return n > m;
    }
}
