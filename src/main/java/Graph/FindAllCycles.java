package Graph;

import java.util.*;

/**
 * given a directed graph, find all cycles in this graph.
 */
public class FindAllCycles {

    public void getAllCyclesInDigraph(
            int u,
            Map<Integer, List<Integer>> graph,
            int[] index,
            List<Integer> trace,
            boolean[] visited,
            List<List<Integer>> cycles) {
            // 节点u出现在当前正在搜索的路径上 且之前访问过
            if (index[u] != -1) {
                List<Integer> curCycle = new ArrayList<>();
                // 取出u在正在拓展路径上的下标
                int k = index[u];
                // 从k到trace.size()-1 都是环上的节点
                while (k < trace.size()) {
                    curCycle.add(trace.get(k));
                    k++;
                }
                cycles.add(curCycle);
                return;
            }

            trace.add(u);
            index[u] = trace.size() - 1;
            visited[u] = true;

            for (int next : graph.get(u)) {
                getAllCyclesInDigraph(next, graph,
                        index, trace, visited, cycles);
            }

            // 回溯时需要消除影响
            index[trace.get(trace.size()-1)] = -1;
            trace.remove(trace.size()-1);

    }

    public static void main(String[] args) {
        FindAllCycles inst = new FindAllCycles();
        int n = 5;
        Map<Integer, List<Integer>> digraph = new HashMap<>();
        digraph.put(1, Arrays.asList(2));
        digraph.put(2, Arrays.asList(3));
        digraph.put(3, Arrays.asList(4));
        digraph.put(4, Arrays.asList(2,5));
        digraph.put(5, Arrays.asList(2,1));

        boolean[] visited = new boolean[n+1];
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                int[] index = new int[n+1];
                Arrays.fill(index, -1);
                inst.getAllCyclesInDigraph(i,
                        digraph, index,
                        new ArrayList<>(),
                        visited,result);
            }
        }

        System.out.println(".");
    }

}
