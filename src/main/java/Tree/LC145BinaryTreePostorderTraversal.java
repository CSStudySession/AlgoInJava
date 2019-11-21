package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class LC145BinaryTreePostorderTraversal {

    /*
    version1: iterative
    Important, when you pop a node, ensure its children are traversed.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
                //visit left first
            } else {
                // left has done, check whether we have right side unvisited
                TreeNode node = stack.peek().right;
                if (node == null) {
                    // right side is null, no need to visit, so pop out the node, we can add to list
                    node = stack.pop();
                    ret.add(node.val);
                    while (!stack.isEmpty() && stack.peek().right == node) {
                        node = stack.pop();
                        ret.add(node.val); // right side has done
                    }
                } else {
                    //deal with right side
                    cur = node;
                }
            }
        }

        return ret;
    }

    private boolean isLeaf(TreeNode r) {
        if (r == null) return true;
        return r.left == null && r.right == null;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private void dfs (TreeNode root, List<Integer> result) {
        if (root == null) return;
        dfs(root.left, result);
        result.add(root.val);
        dfs(root.right, result);
    }

}
