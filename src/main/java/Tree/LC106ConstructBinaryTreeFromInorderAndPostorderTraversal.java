package Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 思路:
 * take the last element in postorder array as the root, find the position of the root in the inorder array;
 * then locate the range for left sub-tree and right sub-tree and do recursion.
 *
 * Use a HashMap to record the index of root in the inorder array.
 */
public class LC106ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public TreeNode buildTreePostIn(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0;i < inorder.length; i++) {
            hm.put(inorder[i], i);
        }

        return buildTreePostIn(inorder, 0, inorder.length-1, postorder, 0,
                postorder.length-1, hm);
    }

    private TreeNode buildTreePostIn(int[] inorder, int is, int ie,
                                     int[] postorder, int ps, int pe,
                                     Map<Integer,Integer> hm) {
        if (ps > pe || is > ie) return null;
        // end of postorder is the root
        TreeNode root = new TreeNode(postorder[pe]);
        int ri = hm.get(postorder[pe]); // root index
        // the number of children in the left subtree is: ri - is
        int numLeft = ri - is;

        TreeNode leftchild = buildTreePostIn(inorder, is, ri-1, postorder, ps, ps+ numLeft - 1, hm);
        TreeNode rightchild = buildTreePostIn(inorder,ri+1, ie, postorder, ps + numLeft, pe-1, hm);

        root.left = leftchild;
        root.right = rightchild;

        return root;
    }

}
