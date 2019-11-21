package TopologicalSort;

import java.util.*;

/**
 * Given an directed graph, a topological order of the graph nodes is defined as follow:
 *
 * For each directed edge A -> B in graph, A must before B in the order list.
 * The first node in the order can be any node in the graph with no nodes direct to it.
 * Find any topological order for the given graph.
 *
 * You can assume that there is at least one topological order in the graph.
 */
public class LIC127TopologicalSorting {

    // 解法1 bfs 求拓扑序列
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList<>();

        HashMap<DirectedGraphNode, Integer> map = new HashMap();
        // map： <节点, 入度> 对应表
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                map.putIfAbsent(neighbor, 0);
                map.put(neighbor, map.get(neighbor)+1);
            }
        }

        Queue<DirectedGraphNode> q = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            // 初始化 将入度为0的节点入队
            if (!map.containsKey(node)) {
                q.offer(node);
                // 入度为0的节点可以直接放入结果集中
                result.add(node);
            }
        }

        // 队列中的节点均入度为0 所以在图中把与之相邻的点入度都减掉1之后 就可以移除
        while (!q.isEmpty()) {
            DirectedGraphNode node = q.poll();
            // 把与node相邻的点入度都减一 过程中检查是否产生新的入度为0的节点 有就入队
            for (DirectedGraphNode n : node.neighbors) {
                map.put(n, map.get(n) - 1);
                if (map.get(n) == 0) {
                    result.add(n);
                    q.offer(n);
                    map.remove(n);
                }
            }
        }
        return result;
    }

    // 解法2 dfs 求拓扑序列
    public ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> results = new ArrayList<>();
        if (graph == null || graph.isEmpty()) {
            return results;
        }
        Map<DirectedGraphNode, Integer> nodeToDegree = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbour : node.neighbors) {
                int degree = nodeToDegree.getOrDefault(neighbour, 0);
                nodeToDegree.put(neighbour, degree + 1);
            }
        }
        int size = graph.size();
        for (DirectedGraphNode node : graph) {
            // 是否graph的长度对应节点数？
            if (!nodeToDegree.containsKey(node) && size != results.size()) {
                dfs(node, results, nodeToDegree);
            }
        }
        return results;
    }

    // 递归的定义：以入度为0的node开始的拓扑排序
    private void dfs(DirectedGraphNode node, ArrayList<DirectedGraphNode> results,
                     Map<DirectedGraphNode, Integer> map) {
        results.add(node);
        // 递归的出口：当没有邻边的时候，返回；代表已经遍历不下去了，走到死胡同了
        if (node.neighbors.isEmpty()) {
            return;
        }
        for (DirectedGraphNode next : node.neighbors) {
            int degree = map.get(next) - 1;
            map.put(next, degree);
            if (degree == 0) {
                // 递归的拆解：以入度为0的next为起点的拓扑排序
                dfs(next, results, map);
            }
        }
    }




}


