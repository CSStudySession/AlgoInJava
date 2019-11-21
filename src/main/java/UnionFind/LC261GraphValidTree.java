package UnionFind;

/**
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 *
 * Example 1:
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 *
 * Example 2:
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 *
 * Note: you can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0,1] is the same as [1,0]
 * and thus will not appear together in edges.
 *
 * 思路：一个n个节点的图是树的条件：1.n-1条边 2.不能有环
 */
public class LC261GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        if (edges == null || edges.length != n-1) return false;
        // 并查集初始化
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }

        // 注意这里循环到n-1停止
        for (int i = 0; i < n-1; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (findRoot(root, u) == findRoot(root, v)) return false;
            // v的根挂到u的根上去 合并两个集合
            root[root[v]] = root[u];
        }
        return true;
    }

    private int findRoot(int[] root, int node) {
        if (root[node] == node) return root[node];
        // 路径压缩 递归返回的途中顺带赋值根节点
        return root[node] = findRoot(root, root[node]);
    }

    public static void main(String[] args) {
        LC261GraphValidTree slu = new LC261GraphValidTree();
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {2, 3}};
        int n = 6;
        System.out.println(slu.validTree(n, edges));
    }

}
