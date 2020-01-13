package Memorization;

/**
 * Given a Binary Tree, find size of the Largest Independent Set(LIS) in it.
 * A subset of all tree nodes is an independent set if there is no edge between any two nodes of the subset.
 * For example, consider the following binary tree.
 *                        10
 *                    /        \
 *                 20           30
 *               /    \           \
 *             40      50          60
 *                   /    \
 *                 70      80
 *
 * The largest independent set(LIS) is {10, 40, 60, 70, 80} and size of the LIS is 5.
 *
 * 思路：树形dp + 记忆化
 * If a node is considered as part of LIS, then its children cannot be part of LIS,
 * but its grandchildren can be. Following is optimal substructure property.
 *
 * Let LISS(X) indicates size of largest independent set of a tree with root X.
 *
 *      LISS(X) = MAX { (1 + sum of LISS for all grandchildren of X),
 *                      (sum of LISS for all children of X) }
 * The idea is simple, there are two possibilities for every node X, either X is a member of the set or not a member.
 * If X is a member, then the value of LISS(X) is 1 plus LISS of all grandchildren.
 * If X is not a member, then the value is sum of LISS of all children.
 *
 * 2) Overlapping Subproblems -> resolved by memo search
 */
public class LargestIndependentSetProblem {

    public int liss(Node root) {
        if (root == null) return 0;

        // 记忆化搜索
        if (root.liss != 0) return root.liss;

        if (root.left == null && root.right == null) return root.liss = 1;

        // Calculate size excluding the current node
        int excludeSelf = liss(root.left) + liss(root.right);

        // Calculate size including the current node
        int includeSelf = 1;
        if (root.left != null) {
            includeSelf += (liss(root.left.left) + liss(root.left.right));
        }

        if (root.right != null) {
            includeSelf += (liss(root.right.left) + liss(root.right.right));
        }

        // Maximum of two sizes is LISS, store it for future uses.
        return root.liss = Math.max(excludeSelf, includeSelf);
    }

    public class Node {
        int data, liss;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.liss = 0;
        }
    }
}