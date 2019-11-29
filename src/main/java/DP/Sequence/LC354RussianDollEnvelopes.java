package DP.Sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 * One envelope can fit into another if and only if
 * both the width and height of one envelope is greater than the width
 * and height of the other envelope.
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Note:
 * Rotation is not allowed.
 *
 * Example:
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3
 * ([2,3] => [5,4] => [6,7]).
 *
 * 思路:
 * This problem is asking for LIS in two dimensions, width and height.
 * Sorting the width reduces the problem by one dimension.
 * If width is strictly increasing, the problem is equivalent to
 * finding LIS in only the height dimension.
 * However, when there is a tie in width, a strictly increasing sequence in height
 * may not be a correct solution.
 * For example, [[3,3] cannot fit in [3,4]].
 * Sorting height in descending order when there is a tie prevents such a sequence
 * to be included in the solution.
 *
 */
public class LC354RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int len = envelopes.length;
        List<Integer> dp = new ArrayList<>();
        // 先按width从小到大排序 再按height从大到小排序
        Arrays.sort(envelopes, (e1, e2) ->
                e1[0] == e2[0] ? e2[1] - e1[1] : e1[0] - e2[0]);

        for (int[] envelope : envelopes) {
            // 二分 找第一个比envelope[1]大的位置
            int left = 0;
            int right = dp.size(); // 如果不存在满足条件的位置 默认right取不到
            while (left < right) {
                int mid = left + (right -left) / 2;
                if (dp.get(mid) < envelope[1]) left = mid + 1;
                else right = mid; // right会停在第一个比目标大的位置
            }
            // 找不到第一个比target大的位置 -> 都小于等于target
            if (right == dp.size()) dp.add(envelope[1]);
            else dp.set(right, envelope[1]);
        }

        return dp.size();
    }

}
