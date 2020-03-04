package StackQueue;

import Tree.TreeNode;

import java.util.Stack;

/**
 * 判断两个BST是否相似，相似的定义是具有相同的元素，树的结构可以不一样
 *
 * 要求: 用非递归方式实现
 *
 * 思路: 用两个盏优化到空间为树的高度
 * 1. 先把根结点和所有左子节点入栈
 * 2. 每次从栈顶取一个节点进行比较
 * 3. 然后把取出来的节点的右子节点的所有左子节点入盏
 * 4. 回到2继续比较
 * 相当于分别维护两个iterator，然后一起遍历比较
 */
public class SimilarBST {

    public boolean isSimilarBST(TreeNode r1, TreeNode r2) {
        if (r1 == null || r2 == null) return false;
        Stack<TreeNode> st1 =  new Stack<>();
        Stack<TreeNode> st2 =  new Stack<>();

        while (!st1.isEmpty() && !st2.isEmpty()) {
            TreeNode n1 = st1.pop();
            TreeNode n2 = st2.pop();

            if (n1.val != n2.val) return false;

            pushLeftNodes(n1.right, st1);
            pushLeftNodes(n2.right, st2);
        }

        return st1.isEmpty() && st2.isEmpty();
    }

    private void pushLeftNodes(TreeNode root, Stack<TreeNode> st) {
        while (root != null) {
            st.push(root);
            root = root.left;
        }
    }

}
