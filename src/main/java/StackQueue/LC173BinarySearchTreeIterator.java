package StackQueue;

import Tree.TreeNode;
import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Example:
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // return 3
 * iterator.next();    // return 7
 * iterator.hasNext(); // return true
 * iterator.next();    // return 9
 * iterator.hasNext(); // return true
 * iterator.next();    // return 15
 * iterator.hasNext(); // return true
 * iterator.next();    // return 20
 * iterator.hasNext(); // return false
 *
 * Note:
 * next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * You may assume that next() call will always be valid, that is,
 * there will be at least a next smallest number in the BST when next() is called.
 *
 * 思路：
 * 每次 next 的时候把 stack 的头拿出来，如果这个节点有右子树的话，把右子树里的左边这条 path 全都给加到 stack 里面。（BST 节点的是顺序是这样的）
 * 比如：
 *
 *        5
 *       / \
 *      2   8
 *     /   /
 *    1   6
 *
 * 上面这个树，在initialize的时候我们得到节点5，然后把左边这一条path所有节点[5,2,1]全都放进去。next的时候的顺序就会是1->2->5
 * 当再次走到5的时候，发现有右子树。所以我们把右子树的左边path[8,6]就丢进去。然后5之后的next就是 5->6->8
 */
public class LC173BinarySearchTreeIterator {

    Stack<TreeNode> stack;
    public LC173BinarySearchTreeIterator(TreeNode root) {
        stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
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
        if (stack.isEmpty())
            return -1;

        TreeNode node = stack.pop();
        // push all left path of right subtree
        TreeNode right = node.right;
        while (right != null) {
            stack.push(right);
            right = right.left;
        }

        return node.val;
    }

}
