package TopologicalSort;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs,
 * is it possible for you to finish all courses?
 *
 * Example 1:
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 *
 * 思路：bfs型拓扑排序
 */
public class LC207CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1) return true;
        if (prerequisites == null || prerequisites.length == 0) return true;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegr = new HashMap<>();

        for (int[] edge : prerequisites) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[1]).add(edge[0]);
            inDegr.put(edge[0], inDegr.getOrDefault(edge[0], 0)+1);
        }

        Queue<Integer> queue = new LinkedList<>();
        int cnt = 0;
        for (int i = 0; i < numCourses; i++) {
            // 有可能存在没有先修关系的课程号 作为孤立点也要放到图中
            if (!graph.containsKey(i)) graph.put(i, new ArrayList<>());
            // 入度为0的点入队 拓扑序计数器自增
            if (!inDegr.containsKey(i)) {
                queue.offer(i);
                cnt++;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int v : graph.get(cur)) {
                inDegr.put(v, inDegr.get(v)-1);
                if (inDegr.get(v) == 0) {
                    queue.offer(v);
                    cnt++;
                }
            }
        }

        return cnt == numCourses;
    }

}
