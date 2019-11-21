package BitManipulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
 * for example: "ACGAATTCCG". When studying DNA,
 * it is sometimes useful to identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings)
 * that occur more than once in a DNA molecule.
 *
 * Example:
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */
public class LC187RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ret = new ArrayList<>();
        if (s.length() <= 10) return ret;
        int mask = 0x7ffffff;
        int cur = 0;
        Map<Integer, Integer> strToFreq = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            cur = (cur << 3) | (s.charAt(i) & 7);
        }

        for (int j = 9; j < s.length(); j++) {
            cur = ((cur & mask) << 3) | (s.charAt(j) & 7);
            if (strToFreq.containsKey(cur) && strToFreq.get(cur) == 1) {
                ret.add(s.substring(j-9, j+1));
                strToFreq.put(cur, 0);
            } else {
                strToFreq.put(cur, strToFreq.getOrDefault(cur, 1));
            }
        }

        return ret;
    }

}
