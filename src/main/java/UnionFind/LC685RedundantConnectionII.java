package UnionFind;

import java.util.Arrays;

/**
 * In this problem, a rooted tree is a directed graph such that,
 * there is exactly one node (the root) for which all other nodes are descendants of this node,
 * plus every node has exactly one parent, except for the root node which has no parents.
 *
 * The given input is a directed graph that started as a rooted tree with N nodes
 * (with distinct values 1, 2, ..., N), with one additional directed edge added.
 * The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that
 * represents a directed edge connecting nodes u and v, where u is a parent of child v.
 *
 * Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes.
 * If there are multiple answers, return the answer that occurs last in the given 2D-array.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given directed graph will be like this:
 *                    1
 *                   / \
 *                  v   v
 *                  2-->3
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * Output: [4,1]
 * Explanation: The given directed graph will be like this:
 *              5 <- 1 -> 2
 *                   ^    |
 *                   |    v
 *                   4 <- 3
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */
public class LC685RedundantConnectionII {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] indegree = new int[edges.length + 1];
        int[]  first = null, second = null;
        for (int i = 0; i < edges.length; i++) {
            if (indegree[edges[i][1]] == 0) {
                indegree[edges[i][1]] = edges[i][0];
            } else {
                first = new int[]{indegree[edges[i][1]], edges[i][1]};
                second = Arrays.copyOf(edges[i], edges[i].length);
                edges[i][1] = 0;
            }
        }

        int[] root = new int[edges.length + 1];
        for (int k = 1; k <= edges.length; k++) {
            root[k] = k;
        }

        for (int j = 0; j < edges.length; j++) {
            if (edges[j][1] == 0) continue;
            int prtU = getRoot(edges[j][0], root);
            int prtV = getRoot(edges[j][1], root);
            if (prtU == prtV) {
                return first == null ? edges[j] : first;
            }
            root[prtV] = prtU;
        }
        return second;
    }

    private int getRoot(int cur, int[] root) {
        if (root[cur] == cur) return cur;
        return root[cur] = getRoot(root[cur], root);
    }
}
