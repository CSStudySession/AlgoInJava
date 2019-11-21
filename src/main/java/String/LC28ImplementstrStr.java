package String;

/**
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 *
 * Example 1:
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 *
 * Example 2:
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 * What should we return when needle is an empty string?
 * This is a great question to ask during an interview.
 * For the purpose of this problem, we will return 0 when needle is an empty string.
 * This is consistent to C's strstr() and Java's indexOf().
 */
public class LC28ImplementstrStr {

    /*
    Rabin Karp 算法: 把字符串的比较转化成数值比较
    "a b c d e" = (a * 33^4 + b * 33^3 + c * 33^2 + d * 33 + e) % 10^6
    其中33是个经验值, hash size 10^6, 越大越不容易重, 越小越容易冲突, 所以选一个不越界的大的值
    如果用int的话, 保证 31 * 10^6不越界. 如果用10^8的话, 就需要long int 了
    key到value唯一, 反之不成立.  abc 只等于123, cde也只等与123, 但是123对应两个string
    算法中每次算哈希值时 要边乘33边取模 防止越界！
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;

        int m = needle.length();
        if (m == 0) {
            return 0;
        }

        /*
        mod can be any big number that less than INT_MAX/33
         */
        int mod = Integer.MAX_VALUE / 33;
        int tgtHash = 0;
        for (int i = 0; i < m; i++) {
            tgtHash = (tgtHash * 33 + needle.charAt(i) - 'a') % mod;
        }

        int power = 1;
        for (int k = 0; k < m; k++) {
            power = (power * 33) % mod;
        }

        int srcHash = 0;
        for (int j = 0; j < haystack.length(); j++) {
            srcHash = (srcHash * 33 + haystack.charAt(j) - 'a') % mod;
            if (j < m - 1) continue;

            if (j >= m) {
                srcHash = srcHash - ((haystack.charAt(j - m) - 'a') * power) % mod;
                // 负数的情况最好不要直接模 因为不同的语言负数的模结果不同 所以特殊处理一下
                if (srcHash < 0) srcHash += mod;
            }

            // hash code一样 真实字符串可能不一样/false positive 最后要特判一次
            if (srcHash == tgtHash) {
                if (haystack.substring(j - m + 1, j + 1).equals(needle)) {
                    return j - m + 1;
                }
            }
        }
        // 遍历整个之后都找不到 匹配
        return -1;
    }

}
