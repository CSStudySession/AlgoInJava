package DFS;

/*
Given a string, find all permutations of it without duplicates.
Example
Given “abb”, return [“abb”, “bab”, “bba”].

Given “aabb”, return [“aabb”, “abab”, “baba”, “bbaa”, “abba”, “baab”].
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIC10StringPermutationII {
    public List<String> stringPermutation2(String str) {
        char[] strArr = str.toCharArray();
        boolean[] isUsed = new boolean[strArr.length];
        Arrays.sort(strArr);
        List<String> ret = new ArrayList<>();
        stringPermutation2Helper(ret, new String(), strArr, isUsed);
        return ret;
    }

    private void stringPermutation2Helper(List<String> ret,
                                          String tmp,
                                          char[] strArr,
                                          boolean[] isUsed) {
        if (tmp.length() == strArr.length) {
            ret.add(tmp);
            return;
        }

        for (int i = 0; i < strArr.length; i++) {
            if (isUsed[i] == true || (i != 0 &&
                    isUsed[i-1] == false &&
                    strArr[i] == strArr[i-1])) {
                continue;
            }
            isUsed[i] = true;
            stringPermutation2Helper(ret, tmp+strArr[i], strArr, isUsed);
            isUsed[i] = false;
        }
    }
}
