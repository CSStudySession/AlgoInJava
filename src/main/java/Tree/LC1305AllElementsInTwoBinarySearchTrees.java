package Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two binary search trees root1 and root2.
 * Return a list containing all the integers from both trees sorted in ascending order.
 *
 * Example 1:
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 *
 * Example 2:
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * Output: [-10,0,0,1,2,5,7,10]
 *
 * Example 3:
 * Input: root1 = [], root2 = [5,1,7,0,2]
 * Output: [0,1,2,5,7]
 *
 * Example 4:
 * Input: root1 = [0,-10,10], root2 = []
 * Output: [-10,0,10]
 *
 * Example 5:
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 *
 * Constraints:
 * Each tree has at most 5000 nodes.
 * Each node's value is between [-10^5, 10^5].
 */
public class LC1305AllElementsInTwoBinarySearchTrees {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> tree1Vals = new ArrayList<>();
        List<Integer> tree2Vals = new ArrayList<>();
        inorderTraversal(root1, tree1Vals);
        inorderTraversal(root2, tree2Vals);

        List<Integer> result = new ArrayList<>();
        int idx1 = 0;
        int idx2 = 0;
        for (int k = 0; k < tree1Vals.size() + tree2Vals.size(); k++) {
            if (idx1 == tree1Vals.size()) {
                result.add(tree2Vals.get(idx2++));
            } else if (idx2 == tree2Vals.size()) {
                result.add(tree1Vals.get(idx1++));
            } else {
                if (tree1Vals.get(idx1) < tree2Vals.get(idx2)) {
                    result.add(tree1Vals.get(idx1++));
                } else {
                    result.add(tree2Vals.get(idx2++));
                }
            }
        }
        return result;
    }

    private void inorderTraversal(TreeNode root, List<Integer> vals) {
        if (root == null) return;
        inorderTraversal(root.left, vals);
        vals.add(root.val);
        inorderTraversal(root.right, vals);
    }

}

















