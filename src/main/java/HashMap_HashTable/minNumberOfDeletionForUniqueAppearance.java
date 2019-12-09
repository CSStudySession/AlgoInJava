package HashMap_HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * given a string with only small letter (a-z), to make each letter appearance time unique,
 * find min number of deletion operation has to make.
 *
 * Examples:
 * 1. S = "example"             -> result: 4
 * 2. S = "ccaaffddecee"        -> result: 6
 * 3. S = "eeee"                -> result: 0 (only one letter)
 */
public class minNumberOfDeletionForUniqueAppearance {

    public int solution(String S) {
        // write your code in Java SE 8
        if (S == null || S.length() == 0) return 0;

        Map<Integer, Integer> charToFreq = new HashMap<>();
        Map<Integer, List<Integer>> FreqToChars = new HashMap<>();

        int maxFreq = Integer.MIN_VALUE;

        for (int i = 0; i < S.length(); i++) {
            int cur = S.charAt(i) - 'a';
            charToFreq.put(cur, charToFreq.getOrDefault(cur, 0) + 1);
        }

        for (Integer charInS : charToFreq.keySet()) {
            int curFreq = charToFreq.get(charInS);
            maxFreq = Math.max(maxFreq, curFreq);
            FreqToChars.putIfAbsent(curFreq, new ArrayList<>());
            FreqToChars.get(curFreq).add(charInS);
        }

        int result = 0;
        int putToNext = 0;
        for (int curFreq = maxFreq; curFreq > 0; curFreq--) {
            FreqToChars.putIfAbsent(curFreq - 1, new ArrayList<>());
            if (FreqToChars.get(curFreq).size() == 0 && putToNext == 0) continue;
            int curEffectiveLen = FreqToChars.get(curFreq).size() + putToNext;
            result += curEffectiveLen - 1;
            putToNext = curEffectiveLen - 1;
        }
        return result;
    }
}
