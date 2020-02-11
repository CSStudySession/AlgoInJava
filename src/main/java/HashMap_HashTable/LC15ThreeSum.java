package HashMap_HashTable;

import java.util.*;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 思路:
 * 如果不让使用sort 就需要hashMap和HashSet去重
 *
 * 用Map记录每个元素极其出现频率的对应关系 用Set记录结果<{a,b,c}>
 * 外层遍历a,b的时候 要求a>=b>=c 这里可以由a,b计算出 这样有序的{a,b,c} 放入Set时可以去重
 * 否则无法判断{0, -1, 1} {-1, 0, 1} 这种重情况
 *
 * 为什么要Map记录频率？有可能有重复元素 在遍历的过程中如果原数组里有{0,0,0}
 * 那么{0,0,0} 是一组合法解 如果原数组里只有两个0 那么最后就需要用频率来说明
 * 到底是取了同一个元素多次还是不同元素
 */
public class LC15ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        // <num, frequency>
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int i = 0; i < nums.length; i++) {
            // 注意 j 从 0 开始，因为内部还是需要判断升降序来去重，如果从 i + 1 开始可能会漏解
            for (int j = 0; j < nums.length; j++) {
                int a = nums[i];
                int b = nums[j];
                int c = 0 - a - b;
                // 去重 强制要求 (a,b,c) 有序: a >= b >= c
                if (!(a >= b && b >= c)) {
                    continue;
                }

                // c必须在数组里出现过  注意这里为什么用频率
                if (map.containsKey(c)) {
                    map.put(a, map.get(a) - 1);
                    map.put(b, map.get(b) - 1);
                    map.put(c, map.get(c) - 1);
                } else {
                    continue;
                }

                if (map.get(a) >= 0 && map.get(b) >= 0 && map.get(c) >= 0) {
                    // 去重
                    set.add(Arrays.asList(a, b, c));
                }

                map.put(a, map.get(a) + 1);
                map.put(b, map.get(b) + 1);
                map.put(c, map.get(c) + 1);
            }
        }

        results.addAll(set);
        return results;
    }
}
