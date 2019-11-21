package HashMap_HashTable;

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
        Map<String, Integer> map = new HashMap<>();
        List<String> ret = new ArrayList<>();

        for (int i = 0; i + 9 <= s.length() - 1; i++) {
            String curSubStr = s.substring(i, i+10);
            if (map.containsKey(curSubStr) && map.get(curSubStr) == 1) {
                ret.add(curSubStr);
                map.put(curSubStr, 0);
            } else {
                map.put(curSubStr, map.getOrDefault(curSubStr, 1));
            }
        }
        return ret;
    }

}
