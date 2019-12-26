package Tree;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class LC543DiameterOfBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) {
        int[] result = {0};
        maxDepth(root, result);
        return result[0];
    }

    // 分治法 分别计算经过左右节点的最长直径 然后跟自己比较 并将结果返回给上一层
    private int maxDepth(TreeNode root, int[] result) {
        if (root == null) return 0;

        int left = maxDepth(root.left, result);
        int right = maxDepth(root.right, result);

        // 这里要把左边的树高加上右边树高作为结果候选与result[0]进行对比
        result[0] = Math.max(result[0], left + right);

        return Math.max(left, right) + 1;
    }

}
