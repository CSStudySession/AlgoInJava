package StackQueue;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    /*
    模拟中序遍历 在遍历的过程中 比较两树当前最小值 取较小值放入结果集中 有点类似于merge sort中的merge操作
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        getSmallest(root1, s1);
        getSmallest(root2, s2);
        List<Integer> result = new ArrayList<>();

        Stack<TreeNode> curStack;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (s1.isEmpty()) {
                curStack = s2;
            } else if (s2.isEmpty()) {
                curStack = s1;
            } else {
                curStack = s1.peek().val < s2.peek().val ? s1 : s2;
            }

            TreeNode curNode = curStack.pop();
            result.add(curNode.val);
            // curNode 的左子树都已经遍历完了 把它的右子树节点压栈 然后往左走
            getSmallest(curNode.right, curStack);
        }
        return result;
    }

    private void getSmallest(TreeNode root, Stack<TreeNode> stack) {
        if (root == null) return;
        stack.push(root);
        // 越往左边数越小
        getSmallest(root.left, stack);
    }

}
