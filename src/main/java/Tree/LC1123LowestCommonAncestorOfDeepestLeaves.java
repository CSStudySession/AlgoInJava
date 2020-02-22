package Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.
 *
 * Recall that:
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
 * The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.
 *
 * Example 1:
 * Input: root = [1,2,3]
 * Output: [1,2,3]
 * Explanation:
 * The deepest leaves are the nodes with values 2 and 3.
 * The lowest common ancestor of these leaves is the node with value 1.
 * The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
 *
 * Example 2:
 * Input: root = [1,2,3,4]
 * Output: [4]
 *
 * Example 3:
 * Input: root = [1,2,3,4,5]
 * Output: [2,4,5]
 *
 * Constraints:
 * The given tree will have between 1 and 1000 nodes.
 * Each node of the tree will have a distinct value between 1 and 1000.
 */
public class LC1123LowestCommonAncestorOfDeepestLeaves {

    // version 1: pure dfs getDepth()被重复计算
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null)  return null;

        int left = getDepth(root.left);
        int right = getDepth(root.right);
        if (left == right) return root;

        return (left > right) ? lcaDeepestLeaves(root.left) : lcaDeepestLeaves(root.right);
    }

    private int getDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }


    // version 2: 使用一个 HashMap 来保存已经算过深度的结点，这样再次遇到的时候，直接从 HashMap 中取值即可，可以使计算效率更高
    Map<TreeNode, Integer> memo = new HashMap<>();
    public TreeNode lcaDeepestLeavesMemo(TreeNode root) {
        if (root == null)  return null;

        int left = getDepthMemo(root.left, memo);
        int right = getDepthMemo(root.right, memo);
        if (left == right) return root;

        return (left > right) ? lcaDeepestLeavesMemo(root.left) : lcaDeepestLeavesMemo(root.right);
    }

    private int getDepthMemo(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int curDepth = 1 + Math.max(getDepthMemo(root.left, memo), getDepthMemo(root.right, memo));
        memo.put(root, curDepth);
        return curDepth;
    }

    // version 3: 自定义类Item 含有当前节点的深度和以当前节点根的子树的最深LCA
    public TreeNode lcaDeepestLeaves3(TreeNode root) {
        return helper(root).root;
    }

    private Item helper(TreeNode root) {
        if (root == null) return new Item(0, null);
        Item left = helper(root.left);
        Item right = helper(root.right);

        if (left.depth > right.depth) {
            left.depth++;
            return left;
        } else if (left.depth < right.depth) {
            right.depth++;
            return right;
        } else {
            return new Item(left.depth + 1, root);
        }
    }

    class Item {
        int depth;
        TreeNode root;

        public Item(int depth, TreeNode root) {
            this.depth = depth;
            this.root = root;
        }
    }

}
