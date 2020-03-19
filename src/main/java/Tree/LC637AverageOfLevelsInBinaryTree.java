package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * Example 1:
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 * Note:
 * The range of node's value is in the range of 32-bit signed integer.
 */
public class LC637AverageOfLevelsInBinaryTree {

    // version 1: BFS
    public List<Double> averageOfLevelsBFS(TreeNode root) {
        List<Double> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            double sum = 0;
            for (int i = 0; i < count; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(sum / count);
        }

        return res;
    }

    // version 2: dfs
    public List<Double> averageOfLevelsDFS(TreeNode root) {
        List<Node> temp = new ArrayList<>();
        helper(root, temp, 0);
        List<Double> result = new LinkedList<>();

        for (int i = 0; i < temp.size(); i++) {
            result.add(temp.get(i).sum / temp.get(i).count);
        }

        return result;
    }

    public void helper(TreeNode root, List<Node> temp, int level) {
        if (root == null) return;

        if (level == temp.size()) {
            Node node = new Node((double)root.val, 1);
            temp.add(node);
        } else { // 该层已经被访问过 这是该层的未访问节点
            temp.get(level).sum += root.val; // 取出该层的节点值 加上当前节点值
            temp.get(level).count++; // 取出该层节点数 加一
        }

        // 递归访问左右子节点 这里可以扩展成n叉树
        helper(root.left, temp, level + 1);
        helper(root.right, temp, level + 1);
    }

    class Node {
        double sum;
        int count;

        Node (double sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

}
