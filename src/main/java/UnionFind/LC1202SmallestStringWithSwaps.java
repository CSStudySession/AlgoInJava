package UnionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * You are given a string s, and an array of pairs of indices in the string pairs
 * where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 * Example 1
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 *
 * Example 2:
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 *
 * Example 3:
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 *
 * 思路：并查集+排序
 * 如果我们将索引看做点，将每个索引对看做一条无向边，则这个图可以有若干个连通块，
 * 每个连通块内部可以任意排列组合对应的字符。
 * 所以我们通过并查集的方式来求出每个连通块，然后在各个连通块内从小到大排序字符，
 * 然后再将排序好的字符放回。
 *
 * 时间复杂度
 * 遍历pairs的时间复杂度为 O(m)
 * 并查集的复杂度近似为 O(n) (初始化并查集的复杂度)
 * 最坏情况下，所有索引在一个连通块内，优先队列调整结构的时间复杂度为 O(nlogn)
 * 故时间复杂度为 O(m+nlogn).
 *
 * 空间复杂度
 * 需要额外 O(n) 的空间建立并查集，O(n)的HashMap构造答案。
 */
public class LC1202SmallestStringWithSwaps {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        UnionFind uf = new UnionFind(n);
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }

        Map<Integer, PriorityQueue<Character>> graphs = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int fa = uf.find(i);
            graphs.putIfAbsent(fa, new PriorityQueue<>());
            graphs.get(fa).offer(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < n; k++) {
            PriorityQueue<Character> cur = graphs.get(uf.find(k));
            if (!cur.isEmpty()) {
                sb.append(cur.poll());
            }
        }
        return sb.toString();
    }

    private class UnionFind {
        public int[] size;
        public int[] father;

        public UnionFind(int cnt) {
            size = new int[cnt];
            father = new int[cnt];
            for (int i = 0; i < cnt; i++) {
                size[i] = 1;
                father[i] = i;
            }
        }

        public int find(int cur) {
            if (father[cur] == cur) return father[cur];
            return father[cur] = find(father[cur]);
        }

        public int union(int u, int v) {
            int faU = find(u);
            int faV = find(v);
            if (faU == faV) return size[faU];

            if (size[faU] > size[faV]) {
                father[faV] = faU;
                size[faU] += size[faV];
                return size[faU];
            } else {
                father[faU] = faV;
                size[faV] += size[faU];
                return size[faV];
            }
        }
    }

}
