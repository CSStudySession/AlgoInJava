package Tree;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that
 * adding up all the values along the path equals the given sum.
 *
 * Note: A leaf is a node with no children.
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 *
 * 思路:
 * 当搜索到叶子节点时，比较与目标和的值，若相同，则结果为真，若搜索完毕没有出现目标和，返回假
 */
public class LC112PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        if (root.val == sum && root.left == null && root.right == null) return true;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
