package StackQueue;

import Tree.TreeNode;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by Aaron Liu on 2/20/20.
 */
public class ImplementPreorderIteratorForBinaryTree {
    Stack<TreeNode> stack;

    public ImplementPreorderIteratorForBinaryTree(TreeNode root) {
        stack = new Stack<>();
        if (root != null) stack.push(root);
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /*
     * @return: return next value
     */
    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        TreeNode node = stack.pop();
        if (node.right != null) stack.push(node.right);
        if (node.left != null) stack.push(node.left);

        return node.val;
    }
}

