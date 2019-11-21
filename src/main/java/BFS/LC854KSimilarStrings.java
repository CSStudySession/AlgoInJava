package BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Strings A and B are K-similar (for some non-negative integer K)
 * if we can swap the positions of two letters in A exactly K times
 * so that the resulting string equals B.
 *
 * Given two anagrams A and B, return the smallest K for which A and B are K-similar.
 *
 * Example 1:
 * Input: A = "ab", B = "ba"
 * Output: 1
 *
 * Example 2:
 * Input: A = "abc", B = "bca"
 * Output: 2
 *
 * Example 3:
 * Input: A = "abac", B = "baca"
 * Output: 2
 *
 * Example 4:
 * Input: A = "aabc", B = "abca"
 * Output: 2
 *
 * Note:
 * 1 <= A.length == B.length <= 20
 * A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}
 */
public class LC854KSimilarStrings {

/*
首先来想，为啥要限定A和B是异构词，这表明A和B中的字符的种类及其个数都相同，就是排列顺序不同，
则A经过交换是一定能变为B的，而且交换的次数在区间 [0, n-1] 内，n是A的长度。
再来想，是不是A中的每个字符都需要交换呢？答案是否定的，当A中某个位置i上的字符和B中对应位置的字符相等，
即 A[i]=B[i] 时，就不需要交换，这样就可以用一个 while 循环，找到第一个不相等的i。交换的第一个字符确定了，
就可以再往后遍历，去找第二个字符了，同理，第二个字符位置j，不能存在 A[j]=B[j]，比如 ab 和 bb，
交换之后变为 ba 和 bb，还是不相等，最好是存在 A[j]=B[i]，比如 ab 和 ba，这样交换之后就变为 ba 和 ba，
完美 match 了。找到了i和j之后，就可以进行交换了，然后判断新状态不在 visited 中的话，加入 visited 集合，
同时加入队列 queue，之后还要交换i和j还原状态，每一层遍历结束后，结果 res 自增1即可
 */
    public int kSimilarity(String A, String B) {
        if (A.equals(B)) return 0;
        Set<String> vis = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(A);
        vis.add(A);
        int res = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                String s = q.poll();
                int i = 0;
                while (s.charAt(i) == B.charAt(i)) i++;
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) == B.charAt(j) || s.charAt(j) != B.charAt(i) ) continue;
                    String temp = swap(s, i, j);
                    if (temp.equals(B)) return res+1;
                    if (vis.add(temp)) q.add(temp);
                }
            }
            res++;
        }

        return res;
    }
    public String swap(String s, int i, int j){
        char[] ca=s.toCharArray();
        char temp=ca[i];
        ca[i]=ca[j];
        ca[j]=temp;
        return new String(ca);
    }
}
