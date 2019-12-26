package Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string S, check if the letters can be rearranged so that
 * two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 * Input: S = "aab"
 * Output: "aba"
 *
 * Example 2:
 * Input: S = "aaab"
 * Output: ""
 *
 * Note:
 * S will consist of lowercase letters and have length in range [1, 500].
 */
public class LC767ReorganizeString {

    public String reorganizeString(String S) {
        Map<Character, Integer> charToFreq = new HashMap<>();
        for (char ch : S.toCharArray()) {
            charToFreq.put(ch, charToFreq.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> (p2.freq - p1.freq));
        for (char ch : charToFreq.keySet()) {
            // 如果有字符出现的次数大于总长度的一半 直接返回""
            if (charToFreq.get(ch) > (S.length() + 1) / 2) return "";
            pq.offer(new Pair(ch, charToFreq.get(ch)));
        }

        StringBuilder sb = new StringBuilder();
        // 两个两个取字符
        while (pq.size() >= 2) {
            Pair first = pq.poll();
            Pair second = pq.poll();

            sb.append(first.ch);
            sb.append(second.ch);

            if (first.freq - 1 > 0) {
                first.freq--;
                pq.offer(first);
            }
            if (second.freq - 1 > 0) {
                second.freq--;
                pq.offer(second);
            }
        }

        /*
         while 循环退出后，有可能优先队列中还剩下了一个映射对，此时将其加入结果 res 即可。
         这个多余的映射对一定只有一个字母了，因为提前判断过各个字母的出现次数是否小于等于总长度的一半，
         按这种机制来取字母，不可能会剩下多余一个的相同的字母
         */
        if (pq.size() > 0) {
            sb.append(pq.poll().ch);
        }

        return sb.toString();
    }

    public class Pair {
        char ch;
        int freq;

        public Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }
}
