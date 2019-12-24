package HashMap_HashTable;

import java.util.ArrayList;
import java.util.List;

/**
 * A string S of lowercase letters is given.
 * We want to partition this string into as many parts as possible so that
 * each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 * Note:
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 */
public class LC763PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        // record the last index of the each char
        int[] map = new int[26];

        // "a b c d e f g" -> map[3] = 5: last index of d is 5
        for(int i = 0; i < S.length(); i++) {
            map[S.charAt(i)-'a'] = i;
        }

        // record the end index of the current sub string
        int last = 0;
        int start = 0;
        for (int i = 0; i < S.length(); i++) {
            last = Math.max(last, map[S.charAt(i) - 'a']);
            // i追上了last -> [start, last]之间的所有字符都只出现在这个范围内
            if(last == i) {
                list.add(last - start + 1);
                // 下一次start从last下一个开始
                start = last + 1;
            }
        }

        return list;
    }

}
