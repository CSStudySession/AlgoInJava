package Graph;

import java.util.*;

/**
 * Dijkstra algorithm implementation
 */
public class DijkstraSP {

    public void getShortestPaths(Map<Integer, List<Edge>> graph,
                                 int source, int n,
                                 int[] distTo, int[] parent) {
        if (!graph.containsKey(source)) return;
        for (int i = 0; i < n; i++) {
            distTo[i] = Integer.MAX_VALUE;
        }
        distTo[source] = 0;

        TreeSet<Distance> minHeap = new TreeSet<>((d1, d2) ->
                d1.distance != d2.distance ?
                        d1.distance-d2.distance : d1.to - d2.to);
        Distance[] inHeap = new Distance[n];
        Distance cur = new Distance(0, 0);
        minHeap.add(cur);
        inHeap[0] = cur;

        while (!minHeap.isEmpty()) {
            Distance closest = minHeap.pollFirst();
            int v = closest.to;
            int distToV = closest.distance;
            // 对v的出边进行逐一松弛操作
            for (Edge edge : graph.get(v)) {
                int w = edge.to;
                if (distTo[w] > distToV + edge.weight) {
                    distTo[w] = distToV + edge.weight;
                    parent[w] = v;
                    if (inHeap[w] != null) {
                        minHeap.remove(inHeap[w]);
                    }
                    inHeap[w] = new Distance(w, distTo[w]);
                    minHeap.add(inHeap[w]);
                }
            }
        }
    }

    public static void main(String[] args) {
        DijkstraSP inst = new DijkstraSP();

        int n = 5;
        Map<Integer, List<Edge>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(new Edge(0,1,1),
                new Edge(0,3,7), new Edge(0,4,6)));
        graph.put(1, Arrays.asList(new Edge(1,2,1),
                new Edge(1,3,2)));
        graph.put(2, Arrays.asList(new Edge(2,3,1)));
        graph.put(3, Arrays.asList(new Edge(3,4,1)));
        graph.put(4, new ArrayList<>());

        int[] distTo = new int[n];
        int[] parent = new int[n];

        inst.getShortestPaths(graph, 0, n, distTo, parent);
        System.out.println(".");
    }

}
