package BinarySearch;

import java.util.Random;

/**
 * Given an array w of positive integers, where w[i] describes the weight of index i,
 * write a function pickIndex which randomly picks an index in proportion to its weight.
 *
 * Note:
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10000 times.
 *
 * Example 1:
 * Input:
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output: [null,0]
 *
 * Example 2
 * Input:
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output: [null,0,1,1,1,0]
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument,
 * the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 */
public class LC528RandomPickWithWeight {

    Random random;
    int[] wSums;

    public LC528RandomPickWithWeight(int[] w) {
        this.random = new Random();
        wSums = new int[w.length + 1];

        for (int i = 1; i <= w.length; i++) {
            wSums[i] = wSums[i - 1] + w[i - 1];
        }
    }

    /*
    二分答案 这里的答案区间就是概率分布的区间:[1, wSum[len-1]]
    然后随机出一个在答案区间的数 看它离那个坐标最近
    注意这里可能会有个followup:如果输入w数组中 会有0 怎么办？
    有0 -> 前缀和数组wSums会出现重复数 此时我们应该选择重复数的第一个
    故搜到wSums[mid] == idx 时 往回找看w[mid-1]是否为0 如果是 right = mid 接着搜
     */
    public int pickIndex() {
        int len = wSums.length;
        int idx = random.nextInt(wSums[len-1]) + 1;
        // 注意: 前缀和数组下标跟原数组下表差了1 所以返回值要减掉1
        int left = 1, right = len - 1;
        // 二分模板之一：left + 1, right, left + (right - left) / 2
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(wSums[mid] == idx) {
                return mid - 1;
            } else if (wSums[mid] < idx) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

}
