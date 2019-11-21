package Tree;

/**
 * Given two non-empty binary trees s and t, check whether tree t has
 * exactly the same structure and node values with a subtree of s.
 * A subtree of s is a tree consists of a node in s and all of this node's descendants.
 * The tree s could also be considered as a subtree of itself.
 *
 * Example 1:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 *
 * Example 2:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return false.
 *
 * 思路:
 * 在遍历树的同时，当树t的根节点值与树s的某个节点值相等时，
 * 比较树t与树s的这个子树，相同则返回true.
 */
public class LC572SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode s, TreeNode t) {

        if(s == null) return t == null;
        if(s.val == t.val && isSametree(s,t) ) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    boolean isSametree(TreeNode s, TreeNode t) {
        if(s == null) return t == null;
        if(t == null || s.val != t.val) return false;
        return isSametree(s.left, t.left) && isSametree(s.right, t.right);
    }

}
