package DFS;

import java.util.*;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 *
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary that
 * has the smallest lexical order when read as a single string. For example,
 * the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 *
 * Example 1:
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * Example 2:
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 *
 * 思路: 建图+后根遍历
 * 首先 对行程建模 机票可以看成有向图的边 [from to] 然后题目转化成 找一条路径 使得所有边被不重不漏得走过一次
 * 如果有多个满足条件的路径 返回字母排序最小的一个
 * 对某个节点的出边集合 考虑用有些队列存储(因为可以快速得到字母表顺序最小的)
 * 在具体遍历的时候 需要用后根遍历 为什么要最后遍历某个节点？
 * 考虑下面这个例子: [a,b] [a,c] [c,a]
 *      a
 *     / \
 *    b   c
 * 当遍历到a这一层 仅从字母表顺序看 b比c要靠前 但是b没有回边到a 如果先走b 会形成死路 所以不能先走b 要从c走 然后再走b
 * 所以首先要先把这一层的子节点都遍历完(保证子节点都能触达到) 然后遍历自己 最后只需要把整个顺序reverse过来 就是满足条件的结果
 */
public class LC332ReconstructItinerary {

    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        if (tickets == null || tickets.size() < 1) return res;
        // 建图
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).offer(to);
        }
        List<String> result = new ArrayList<>();
        dfs("JFK", graph, result);
        // 需要reverse post order traversal
        Collections.reverse(result);
        return result;
    }

    private void dfs(String from,
                     Map<String, PriorityQueue<String>> graph,
                     List<String> result) {
        // HashMap.get(key) 返回null 如果key不存在
        PriorityQueue<String> pq = graph.get(from);
        while (pq != null && !pq.isEmpty()) {
            dfs(pq.poll(), graph, result);
        }
        // result类似于一个post order traversal 序列
        result.add(from);
    }

}













