package DFS;

import java.util.Arrays;

/**
 * You have a set of tiles, where each tile has one letter tiles[i] printed on it.
 * Return the number of possible non-empty sequences of letters you can make.
 *
 * Example 1:
 * Input: "AAB"
 * Output: 8
 * Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
 *
 * Example 2:
 * Input: "AAABBC"
 * Output: 188
 *
 * Note:
 * 1 <= tiles.length <= 7
 * tiles consists of uppercase English letters.
 *
 * 思路: 排列+组合
 * 例子中的 "AAB" 可以形成"AB","BA" 所以这个有排列的性质
 *
 * 模拟过程:
 * A - A - B
 *   - B - A
 * A - (不合法)
 * B - A - A
 *   - A (不合法)
 * 上面每一列是一层 所以看出 某一层不能有相同的字符出现
 * 所以利用排列模板 去重的条件有两个：
 * 1. 当前数在该路径上不能被用过
 * 2. 如果有相同的元素 不能跳过第一个去取后面的
 * 举例：  A1     A2    B
 *        i     i+1
 * 指针i遍历完以A1后 移动到A2 此时A1被放回 但是我们不能再从A2开始进行枚举 因为这样等于跳过了A1取A2
 * 如果我们允许A2可以取 那么按照组合模板 下一层递归会重新回到A1 然后接着取A1 但是A2A1之前已经在A1那层遍历过了
 */
public class LC1079LetterTilePossibilities {

    public int numTilePossibilities(String tiles) {
        char[] arr = tiles.toCharArray();
        boolean[] visited = new boolean[tiles.length()];
        int[] res = {0};
        Arrays.sort(arr);
        dfs(arr, visited, res);
        return res[0];
    }

    private void dfs(char[] arr, boolean[] visited, int[] res) {
        for (int i = 0; i < arr.length; i++) {
            if (visited[i] || (i != 0 && arr[i] == arr[i-1] && !visited[i-1])) continue;
            // 每次取一个数 就要更新答案
            res[0]++;
            visited[i] = true;
            dfs(arr, visited, res);
            visited[i] = false;
        }

    }

}
