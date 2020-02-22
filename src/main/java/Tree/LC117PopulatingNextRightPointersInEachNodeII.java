package Tree;

/**
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * Follow up:
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 * Constraints:
 * The number of nodes in the given tree is less than 6000.
 * -100 <= node.val <= 100
 *
 * 思路: 维护三个指针 cur, head, prev
 * head: 下一层的第一个元素 prev 下一层的游标 每次把当前节点next节点指向cur的左/右孩子
 */
public class LC117PopulatingNextRightPointersInEachNodeII {

    public Node connect(Node root) {
        if (root == null) return null;
        Node cur = root;
        Node head = null;
        Node prev = null;

        while (cur != null) {
            // 当前层的连接过程 cur在上一层 head和prev在当前层
            while (cur != null) {
                // 先左后右
                if (cur.left != null) {
                    if (head == null) {
                        head = cur.left;
                        prev = cur.left;
                    } else {
                        prev.next = cur.left;
                        prev = prev.next;
                    }
                }

                if (cur.right != null) {
                    if (head == null) {
                        head = cur.right;
                        prev = cur.right;
                    } else {
                        prev.next = cur.right;
                        prev = prev.next;
                    }
                }
                // cur要一直往右走
                cur = cur.next;
            }

            // 这一层连接完 转到下一层
            cur = head;
            head = null;
            prev = null;
        }

        return root;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

}

