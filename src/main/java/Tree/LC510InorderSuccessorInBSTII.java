package Tree;

/**
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * The successor of a node p is the node with the smallest key greater than p.val.
 * You will have direct access to the node but not to the root of the tree.
 * Each node will have a reference to its parent node.
 *
 * Note:
 * If the given node has no in-order successor in the tree, return null.
 * It's guaranteed that the values of the tree are unique.
 * Remember that we are using the Node type instead of TreeNode type so their string representation are different.
 *
 * Follow up:
 * Could you solve it without looking up any of the node's values?
 *
 * 思路:
 * 两种情况：1. 如果当前节点有有孩子 则答案是有孩子的最左的孩子
 *         2. 如果当前节点没有右孩子 则从该节点往上找 直到往上层找到的某个parent满足条件
 */
public class LC510InorderSuccessorInBSTII {

    public Node inorderSuccessor(Node x) {
        if (x.right == null) {
            Node cur = x;
            // 找到当前节点为parent的左孩子 则parent即为答案
            while (cur.parent != null && cur.parent.right == cur) {
                cur = cur.parent;
            }

            return cur.parent;
        }

        Node cur = x.right;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    public class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}