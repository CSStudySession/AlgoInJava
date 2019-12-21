package BinarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * Example :
 * Input:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 * Note:
 *
 * All words in words and S will only consists of lowercase letters.
 * The length of S will be in the range of [1, 50000].
 * The length of words will be in the range of [1, 5000].
 * The length of words[i] will be in the range of [1, 50].
 *
 * 思路：
 * hashMap(indexing) + 二分匹配
 */
public class LC792NumberOfMatchingSubsequences {

    public int numMatchingSubseq(String S, String[] words) {
        List<List<Integer>> charToIndx = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            charToIndx.add(new ArrayList<>());
        }
        char[] sArr = S.toCharArray();
        // 预处理
        for (int j = 0; j < sArr.length; j++) {
            charToIndx.get(sArr[j] - 'a').add(j);
        }
        int result = 0;
        for (String word : words) {
            // 二分匹配
            if (isFound(charToIndx, word)) result++;
        }
        return result;
    }

    private boolean isFound(List<List<Integer>> charToIndx, String word) {
        int left = -1;
        for (char c : word.toCharArray()) {
            List<Integer> curList = charToIndx.get(c - 'a');
            int idx = Collections.binarySearch(curList, left+1);
            if (idx < 0) {
                idx = -idx - 1;
            }
            if (idx >= curList.size()) return false;
            left = curList.get(idx);
        }
        return true;
    }

}
