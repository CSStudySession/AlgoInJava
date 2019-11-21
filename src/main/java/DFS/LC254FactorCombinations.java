package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Numbers can be regarded as product of its factors. For example,
 *
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 *
 * Note:
 *
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 *
 * Example 1:
 * Input: 1
 * Output: []
 *
 * Example 2:
 * Input: 37
 * Output:[]
 *
 * Example 3:
 * Input: 12
 * Output:
 * [
 *   [2, 6],
 *   [2, 2, 3],
 *   [3, 4]
 * ]
 *
 * Example 4:
 * Input: 32
 * Output:
 * [
 *   [2, 16],
 *   [2, 2, 8],
 *   [2, 2, 2, 4],
 *   [2, 2, 2, 2, 2],
 *   [2, 4, 4],
 *   [4, 8]
 * ]
 *
 */
public class LC254FactorCombinations {

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ret = new ArrayList<>();
        if (n < 2) return ret;
        getFactor(ret, new ArrayList<>(), 1, 2, n);
        return ret;
    }

    private void getFactor(List<List<Integer>> ret, List<Integer> tmp,
                           int curProd, int level, int n) {
        if (level == n) {
            if (curProd == n) {
                ret.add(new ArrayList<>(tmp));
            }
            return;
        }

        if (curProd == n) {
            ret.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = level; i < n; i++) {
            // 当前乘积如果乘以i大于n时 直接break i增大不会有可行解
            if (curProd * i > n) break;
            // 剪枝 只可能选择能整除n的因子
            if (n % i == 0) {
                tmp.add(i);
                getFactor(ret, tmp, curProd*i, i, n);
                tmp.remove(tmp.size()-1);
            }
        }
    }
}
