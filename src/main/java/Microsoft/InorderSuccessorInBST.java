package Microsoft;

import common.TreeNode;

public class InorderSuccessorInBST {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            if(root == null) return null;
            if(p.right != null) {
                return leftMost(p.right);
            } else {
                TreeNode parent = findParent(root,p);
                while(parent != null && p == parent.right) {
                    p = parent;
                    parent = findParent(root,p);
                }
                return parent;
            }
        }

        TreeNode findParent(TreeNode root, TreeNode child) {
            if(root == null) return null;
            if((root.left != null && root.left.val == child.val)
                    || (root.right != null && root.right.val == child.val)) {
                return root;
            }
            else if(root.val > child.val) return findParent(root.left, child);
            else return findParent(root.right, child);
        }

        TreeNode leftMost(TreeNode root) {
            while(root.left != null) {
                root = root.left;
            }
            return root;
        }
    }
}
