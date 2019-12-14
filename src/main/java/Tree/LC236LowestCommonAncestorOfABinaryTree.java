package Tree;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Note:
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the binary tree.
 *
 * 复杂度 时间：O(h) 空间：O(h)
 * 思路：二分dfs
 * 3种情况:
 * 第一种情况是两个节点是在公共祖先的左右两侧,
 * 第二种情况是都在公共祖先的左侧,
 * 第三种情况是都在公共祖先的右侧,
 * 如果是第二，第三种情况的话，公共祖先就在给定的两个点中比较上面的那一个
 */
public class LC236LowestCommonAncestorOfABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        if (root == p) return p;
        if (root == q) return q;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        TreeNode res = (left != null) ? left : right;
        return res;
    }
}
