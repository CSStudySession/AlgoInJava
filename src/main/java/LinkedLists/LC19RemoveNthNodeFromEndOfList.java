package LinkedLists;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 * Note:
 * Given n will always be valid.
 *
 * Follow up:
 * Could you do this in one pass?
 *
 * 思路:
 * 先让快指针走n步，然后快慢一起走，走到头的时候慢指针指向的点删掉。
 */
public class LC19RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode preDelete = dummy;
        for (int i = 0; i < n; i++) {
            if (head == null) {
                return null;
            }

            head = head.next;
        }

        while (head != null) {
            head = head.next;
            preDelete = preDelete.next;
        }

        preDelete.next = preDelete.next.next;
        return dummy.next;
    }


}
