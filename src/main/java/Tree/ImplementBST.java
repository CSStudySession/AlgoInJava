package Tree;

/**
 * Implement a BST
 */
public class ImplementBST {

    private Node root;

    public ImplementBST() {
        root = null;
    }

    public boolean search(int val) {
        Node cur = root;
        while (cur != null) {
            if (cur.val == val) return true;
            if (cur.val < val) cur = cur.right;
            if (cur.val > val) cur = cur.left;
        }
        return false;
    }

    public void insert(int val) {
        Node cur = root;
        Node prt = null;
        if (root == null) {
            root = new Node(val, null, null);
            return;
        }

        while (cur != null) {
            if (cur.val == val) return;
            prt = cur;
            if (cur.val > val) {
                cur = cur.left;
            } else if (cur.val < val) {
                cur = cur.right;
            }
        }

        if (prt.val < val) {
            prt.right = new Node(val, null, null);
        } else {
            prt.left = new Node(val, null, null);
        }
    }

    public boolean delete(int val) {
        if (root == null) return false;
        Node cur = root;
        Node prt = null;

        while (cur != null && cur.val != val) {
            if (cur.val > val) {
                prt = cur;
                cur = cur.left;
            } else if (cur.val < val) {
                prt = cur;
                cur = cur.right;
            }
        }
        if (cur == null) return false;

        if (cur.left == null) { // 只有右子节点
            if (cur == root) root = root.right;
            else if (cur == prt.left) prt.left = cur.right;
            else prt.right = cur.right;
        } else if (cur.right == null) { // 只有左子节点
            if (cur == root) root = root.left;
            else if (cur == prt.left) prt.left = cur.left;
            else prt.right = cur.left;
        } else { // 找到右子树的最左节点 然后跟待删节点互换
            Node toRmv = cur.right;
            prt = cur;
            while (toRmv.left != null) {
                prt = toRmv;
                toRmv = toRmv.left;
            }
            cur.val = toRmv.val;
            if (prt.left == toRmv) prt.left = toRmv.right;
            else prt.right = toRmv.right;
        }

        cur = null;
        return true;
    }

    public class Node {
        int val;
        Node left;
        Node right;

        public Node() {
            this.val = 0;
            this.left = null;
            this.right = null;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
