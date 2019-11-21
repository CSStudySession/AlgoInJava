package Airbnb;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to findWithDuplicates the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * n = 3, Nodes = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph looks like this:
 *
 *
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 * Example 2:
 * Input:
 * n = 3, Nodes = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph looks like this:
 *
 *
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 * Note:
 *
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 */
public class CheapestFlightsWithinKStops {

    class Node{
        int dest;
        int cost;
        int stop;

        Node(int dest, int cost, int stop) {
            this.dest = dest;
            this.cost = cost;
            this.stop = stop;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<Node>> map = new HashMap<>();

        for (int[] f : flights) {
            List<Node> list = new ArrayList<>();
            if (map.containsKey(f[0])) {
                list = map.get(f[0]);
            }
            list.add(new Node(f[1], f[2], 0));
            map.put(f[0], list);
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));

        // K is number of stop without adding source and destination - so we need to add k+1
        pq.add(new Node(src, 0, K+1)); // add source

        while(!pq.isEmpty()){
            Node curr = pq.poll();
            if(curr.dest == dst) {
                return curr.cost;
            }
            visited.add(curr.dest);

            List<Node> currNodes = map.getOrDefault(curr.dest,new ArrayList<Node>());
            if(curr.stop > 0){ // No need to go further and add into stack if the no.stops already crossed for curr
                for(Node e: currNodes){
                    if(!visited.contains(e.dest)){
                        pq.add(new Node(e.dest, curr.cost + e.cost, curr.stop - 1));
                    }
                }
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        int[][] flights = {{0,1,100}, {1,2,100}, {0,2,500}};
        CheapestFlightsWithinKStops cheapestFlightsWithinKStops = new CheapestFlightsWithinKStops();
        System.out.println(cheapestFlightsWithinKStops.findCheapestPrice(3, flights, 0, 2, 1));
    }
}
