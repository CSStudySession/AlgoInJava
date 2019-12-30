package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a non-empty special binary tree consisting of nodes with the non-negative value,
 * where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes,
 * then this node's value is the smaller value among its two sub-nodes.
 * More formally, the property root.val = min(root.left.val, root.right.val) always holds.
 *
 * Given such a binary tree, you need to output the second minimum value
 * in the set made of all the nodes' value in the whole tree.
 *
 * If no such second minimum value exists, output -1 instead.
 *
 * Example 1:
 * Input:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 *
 * Example 2:
 * Input:
 *     2
 *    / \
 *   2   2
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 */
public class LC671SecondMinimumNodeInABinaryTree {

    // version 1: recursion
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        return getSecMin(root, root.val);
    }

    private int getSecMin(TreeNode root, int min) {
        if (root == null) return -1;

        if (root.val > min) return root.val;

        int retLeft = getSecMin(root.left, min);
        int retRight = getSecMin(root.right, min);

        if (retLeft == -1) return retRight;
        if (retRight == -1) return retLeft;

        return Math.min(retLeft, retRight);
    }

    // version 2: iteration
    public int findSecondMinimumValue2(TreeNode root) {
        if (root == null) return -1;

        int smallest = root.val;
        int sec = Integer.MAX_VALUE;
        boolean found = false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode front = queue.poll();
            if (front.val > smallest && front.val <= sec) {
                sec = front.val;
                found = true;
                // no need to add it's children because next level all greater than itself
                continue;
            }

            // each node has two or zero children
            if (front.left == null) continue;

            queue.offer(front.left);
            queue.offer(front.right);
        }

        return found ? sec : -1;
    }

}
