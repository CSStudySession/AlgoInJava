package Tree;

/**
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node
 * in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 *
 * Example 1:
 *
 * Input:
 *
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 *
 * Output: 3
 *
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 * Example 2:
 *
 * Input:
 *
 *    2
 *     \
 *      3
 *     /
 *    2
 *   /
 *  1
 *
 * Output: 2
 *
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 */
public class LC298BinaryTreeLongestConsecutiveSequence {

    int max = Integer.MIN_VALUE;
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;

        dfs(root, 0, root.val + 1);
        return max;
    }

    private void dfs(TreeNode root, int length, int target) {
        if (root == null) {
            return;
        }

        if (root.val == target) {
            length++;
        } else {
            length = 1;
        }

        // 更新答案
        max = Math.max(max, length);

        dfs(root.left, length, root.val + 1);
        dfs(root.right, length, root.val + 1);
    }
}
