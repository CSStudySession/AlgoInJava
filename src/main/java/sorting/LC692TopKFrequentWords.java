package sorting;

import java.util.*;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest.
 * If two words have the same frequency,
 * then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 * with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 * Follow up:
 * Try to solve it in O(n log k) time and O(n) extra space.
 */
public class LC692TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        List<String> ret = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        TreeSet<String>[] bucket = new TreeSet[words.length];
        for (String key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq-1] == null) {
                bucket[freq-1] = new TreeSet<>();
            }
            bucket[freq-1].add(key);
        }

        for (int i = bucket.length -1; i >= 0; i--) {
            if (ret.size() == k) break;
            if (bucket[i] == null) continue;

            if (bucket[i].size() <= k - ret.size()) {
                ret.addAll(bucket[i]);
            } else {
                while (ret.size() < k) {
                    ret.add(bucket[i].pollFirst());
                }
                break;
            }
        }
        return ret;
    }

}
