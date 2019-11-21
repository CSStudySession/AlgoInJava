package DFS;

import java.util.*;

/**
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 *
 *
 *
 * Example 1:
 *          2
 *        /  \
 *       1 —— 0
 *       |
 *       3
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 *
 * 思路：Tarjan算法求桥
 * 一条边(u,v)是桥，当且仅当(u,v)为树枝边(即非负边)，且满足dfn(u)<low(v)（前提是其没有重边）。
 * 也就是，u的儿子v之间只有一条边（前提是无重边），且v点只能到v点到不了v点前，
 * 所以(u,v)边去掉就是两个连通分支，所以(u,v)为桥
 */
public class LC1192CriticalConnectionsInANetwork {

    private int ts = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> ret =  new ArrayList<>();
        // 可以换成用Map表示图 但是leetcode上会TLE
        List<Integer>[] graph = new List[n];
        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);
            if (graph[u] == null) graph[u] = new ArrayList<>();
            if (graph[v] == null) graph[v] = new ArrayList<>();
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] dfn = new int[n+1];
        int[] low = new int[n+1];

        // 题目假设图是连通的 所以跑一遍tarjan就可以了 否则还要遍历一遍所有节点
        tarjan(0, 0, dfn, low, graph, ret);

        return ret;
    }

    private void tarjan(int u, int fa, int[] dfn, int[] low,
                        List<Integer>[] graph,
                        List<List<Integer>> ret) {
        ts += 1;
        dfn[u] = ts;
        low[u] = ts;

        //对每个从u出发的边 拿出对应点v
        for (int i = 0; i < graph[u].size(); i++) {
            int v = graph[u].get(i);
            //v没被访问过 对v进行tarjan
            if (dfn[v] == 0) {
                tarjan(v, u, dfn, low, graph, ret);
                low[u] = Math.min(low[u], low[v]);
                // 如果是求割点 就是low[v] >= dfn[u]
                if (low[v] > dfn[u]) {
                    ret.add(Arrays.asList(u, v));
                }
            } else if (fa != v) { // 只能由非父子边更新
                low[u] = Math.min(low[u], dfn[v]);
            }
        }

        if (u == 0 && graph[u].size() == 1) {
            int v = graph[u].get(0);
            ret.add(Arrays.asList(u, v));
        }
    }

    public static void main(String[] args) {
        LC1192CriticalConnectionsInANetwork inst =
                new LC1192CriticalConnectionsInANetwork();

        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0, 1));
        connections.add(Arrays.asList(1, 2));
        connections.add(Arrays.asList(2, 0));
        connections.add(Arrays.asList(1, 3));

        List<List<Integer>> ret = inst.criticalConnections(4, connections);
        System.out.println(".");

    }

}
