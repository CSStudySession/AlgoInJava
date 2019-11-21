package common;

public class RedBlackTree {

    RedBlackTree left, right;

    public RedBlackTree() {}

    public void leftRotate() {
        RedBlackTree r = right;
        right = r.left;

    }
}
