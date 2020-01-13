package Tree;

import java.util.Stack;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 *
 * Example 1:
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 * Output: [3,1,null,null,2]
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 * Example 2:
 * Input: [3,1,4,null,null,2]
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 * Output: [2,1,4,null,null,3]
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 *
 * Follow up:
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 */
public class LC99RecoverBinarySearchTree {

    // version 1: 递归
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        TreeNode[] candidates = new TreeNode[3];
        inorderTraversal(root, candidates);

        int tmp = candidates[1].val;
        candidates[1].val = candidates[2].val;
        candidates[2].val = tmp;
        return;
    }

    // candidates[0]: prev, candidates[1]: first, candidates[2]: second
    private void inorderTraversal(TreeNode root, TreeNode[] candidates) {
        if (root == null) return;
        inorderTraversal(root.left, candidates);
        if (candidates[0] == null) {
            candidates[0] = root;
        } else {
            // 出现乱序
            if (candidates[0].val > root.val) {
                if (candidates[1] == null) {
                    candidates[1] = candidates[0];
                }
                candidates[2] = root;
            }
            // 每次都更新prev
            candidates[0] = root;
        }
        inorderTraversal(root.right, candidates);
    }

    // version 2: iterative 中根遍历+访问节点时做比较
    public void recoverTree2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        TreeNode first = null;
        TreeNode second = null;

        while(root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // 访问节点
            root = stack.pop();
            if (prev != null) {
                if (prev.val > root.val) {
                    if (first == null) first = prev;
                    second = root;
                }
            }
            prev = root;
            root = root.right;
        }

        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
        return;
    }

}

