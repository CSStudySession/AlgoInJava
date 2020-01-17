package Graph;

import java.util.*;

/**
 * given a directed graph and a node in this graph,
 * find the shortest cycle that includes the given node.
 */
public class shortestCycleIncludesTargetNode {

    public List<Integer> getShortestCycleNoWeight(
            Map<Integer, List<Integer>> digraph,
            int targetNode,
            int n) {
        List<Integer> result = new ArrayList<>();
        if (!digraph.containsKey(targetNode)) return result;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(targetNode);
        int[] parent = new int[n+1];
        boolean[] visited = new boolean[n+1];

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curNode = queue.poll();
                for (int next : digraph.get(curNode)) {
                    if (next == targetNode) {
                        int temp = curNode;
                        result.add(0, curNode);
                        while (temp != targetNode) {
                            temp = parent[temp];
                            result.add(0, temp);
                        }
                        return result;
                    } else {
                        if (visited[next]) continue;
                        visited[next] = true;
                        parent[next] = curNode;
                        queue.offer(next);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        shortestCycleIncludesTargetNode inst = new shortestCycleIncludesTargetNode();
        int n = 5;
        Map<Integer, List<Integer>> digraph = new HashMap<>();
        digraph.put(1, Arrays.asList(2));
        digraph.put(2, Arrays.asList(3));
        digraph.put(3, Arrays.asList(2,4));
        digraph.put(4, Arrays.asList(2,5));
        digraph.put(5, Arrays.asList(2,1));

        List<Integer> shorestCycle = inst.getShortestCycleNoWeight(digraph, 2, 5);
        System.out.println(".");
    }

}
