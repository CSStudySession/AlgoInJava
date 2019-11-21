package Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aaron Liu on 11/7/19.
 */
public class Longest01SubString {

    public int longestZeroOneEqualSubString(String str) {
        if (str == null || str.length() == 0) return 0;

        int sum = 0;
        // 映射： 前缀和sum -> 位数i
        Map<Integer, Integer> sumToIdx = new HashMap<>();
        sumToIdx.put(0, 0);
        int maxLen = 0;

        for (int i = 1; i <= str.length(); i++) {
            sum += (str.charAt(i-1) == '0' ? -1 : 1);

            // sum在之前的某个位置j出现过: [j,i]这部分的和为0
            if (sumToIdx.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - sumToIdx.get(sum));
            } else {
                // 只在第一次出现sum的时候保存
                sumToIdx.put(sum, i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Longest01SubString inst = new Longest01SubString();
        String str  = "110100011";
        System.out.println(inst.longestZeroOneEqualSubString(str));
    }
}
