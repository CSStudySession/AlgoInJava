package BFS;

import Tree.TreeNode;

import java.util.*;

/**
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * Return a list of the values of all nodes that have a distance K from the target node.
 * The answer can be returned in any order.
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * Output: [7,4,1]
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 *
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 *
 * Note:
 * The given tree is non-empty.
 * Each node in the tree has unique values 0 <= node.val <= 500.
 * The target node is a node in the tree.
 * 0 <= K <= 1000.
 */
public class LC863AllNodesDistanceKInBinaryTree {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return  ret;
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        construct(root, null, graph);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> set = new HashSet<>();
        queue.offer(target);
        set.add(target);

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (K == 0) {
                for (int k = 0; k < size; k++) {
                    ret.add(queue.poll().val);
                }
                return ret;
            }

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (graph.containsKey(curr)) {
                    for (TreeNode next : graph.get(curr)) {
                        if (set.contains(next)) continue;
                        set.add(next);
                        queue.offer(next);
                    }
                }
            }
            K--;
        }

        return ret;
    }

    private void construct(TreeNode root, TreeNode pre,
                           Map<TreeNode, List<TreeNode>> graph) {
        if (root == null) return;
        if (graph.containsKey(root)) return;

        if (pre != null) {
            graph.putIfAbsent(root, new ArrayList<>());
            graph.putIfAbsent(pre, new ArrayList<>());
            graph.get(root).add(pre);
            graph.get(pre).add(root);
        }

        construct(root.left, root, graph);
        construct(root.right, root, graph);
    }

}
