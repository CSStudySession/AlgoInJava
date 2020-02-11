package LinkedLists;

/**
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 *
 * Follow up:
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class LC206ReverseLinkedList {

    // version 1: iteration
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }

        return prev;
    }

    // version 2: recursion
    public ListNode reverseListRecusion(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode[] newH = {null};
        reverse(head, null, newH);
        return newH[0];
    }

    private ListNode reverse(ListNode cur, ListNode prev, ListNode[] newH) {
        if (cur == null || cur.next == null) {
            newH[0] = cur;
            return cur;
        }

        ListNode rev = reverse(cur.next, cur, newH);
        rev.next = cur;
        cur.next = prev;
        return cur;
    }

}
