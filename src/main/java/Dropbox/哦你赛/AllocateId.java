package Dropbox.哦你赛;

public class AllocateId {

    private int idCounter = 0;
    private Node parent;
    private int maxNum = 0;

    class Node {
        boolean isLeftUsed, isRightUsed;
        int id;
        Node left, right;

        public Node(int id) {
            this.id = id;
            this.isLeftUsed = false;
            this.isRightUsed = false;
        }
    }

    public void init(int maxId) {
        this.maxNum = maxId;
        this.parent = new Node(-1);
        init(this.parent, 0, maxId);
    }

    private void init(Node root, int start, int end) {
        if (start == end) {
            return ;
        }
        int mid = start + (end - start) / 2;

        if (root.left == null) {
            int val = -1;
            if (start == mid) {
                val = idCounter++;
            }
            root.left = new Node(val);
            init(root.left, start, mid);
        }

        if (root.right == null) {
            int val = -1;
            if (mid+1 == end) {
                val = idCounter++;
            }
            root.right = new Node(val);
            init(root.right, mid+1, end);
        }

    }

    public int allocate() {
        return allocateHelper(this.parent);
    }

    private int allocateHelper(Node root) {
        if (root == null || (root.isRightUsed && root.isLeftUsed)) {
            return -1;
        }

        int left = -1, right = -1;
        if (!root.isLeftUsed) {
            left = allocateHelper(root.left);
        }

        if (left == -1 && !root.isRightUsed) {
            right = allocateHelper(root.right);
        }

        if (left != -1) {
            root.isLeftUsed = true;
            return left;
        } else if (right != -1) {
            root.isRightUsed = true;
            return right;
        }
        return root.id;
    }

    public void deallocate() {

    }
}
