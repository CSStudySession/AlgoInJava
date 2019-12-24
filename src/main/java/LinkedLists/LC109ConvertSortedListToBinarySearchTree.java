package LinkedLists;

import Tree.TreeNode;

/**
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which
 * the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 * Given the sorted linked list: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class LC109ConvertSortedListToBinarySearchTree {

    // version 1: O(nlogn) 快慢指针法找中点
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return toBST(head,null);
    }

    // divide and conquer型递归 有返回值
    private TreeNode toBST(ListNode head, ListNode tail) {
        // 先找终点 利用快慢指针法
        ListNode slow = head;
        ListNode fast = head;
        if (head == tail) return null;

        // 注意这里fast是跟tail比 而不是null 第一次递归调用时会把null传进来
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // while循环结束后 slow会停在中点上
        TreeNode thead = new TreeNode(slow.val);
        // 递归构建左子树和右子树
        thead.left = toBST(head, slow);
        thead.right = toBST(slow.next, tail);

        return thead;
    }
}
