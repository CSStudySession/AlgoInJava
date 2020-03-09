package LinkedLists;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 *
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 *
 * 思路: Merge Sort
 * 复杂度: T(n) = O(nlgn) S(n) = O(lgn)
 */
public class LC148SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMiddle(head);

        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);

        return merge(left, right);
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }

        if (head1 != null) {
            cur.next = head1;
        } else {
            cur.next = head2;
        }

        return dummy.next;
    }


    /*
    quick sort变形
    1. 先找pivot 这里每次找中点作为pivot
    2. 划分当前列表: 比pivot.val小的,相等的,大的
    3. 递归排序比pivot小的和大的两部分
    4. 连接(小 相等 大)三部分
     */
    public ListNode sortListQuickSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 找pivot
        ListNode mid = findMedian(head); // O(n)

        ListNode leftDummy = new ListNode(0), leftTail = leftDummy;
        ListNode rightDummy = new ListNode(0), rightTail = rightDummy;
        ListNode middleDummy = new ListNode(0), middleTail = middleDummy;

        // 小于mid.val的节点 归leftDummy, 大于mid.val的节点 归rightDummy 等于mid.val的节点 归middleDummy
        while (head != null) {
            if (head.val < mid.val) {
                leftTail.next = head;
                leftTail = head;
            } else if (head.val > mid.val) {
                rightTail.next = head;
                rightTail = head;
            } else {
                middleTail.next = head;
                middleTail = head;
            }
            head = head.next;
        }

        // 三个tail后面接上null
        leftTail.next = null;
        middleTail.next = null;
        rightTail.next = null;

        // 递归排序leftDummy.next和rightDummy.next
        ListNode left = sortList(leftDummy.next);
        ListNode right = sortList(rightDummy.next);

        // 链接三部分分散的链表
        return concat(left, middleDummy.next, right);
    }

    private ListNode findMedian(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode concat(ListNode left, ListNode middle, ListNode right) {
        ListNode dummy = new ListNode(0), tail = dummy;

        tail.next = left;
        tail = getTail(tail);

        tail.next = middle;
        tail = getTail(tail);

        tail.next = right;

        return dummy.next;
    }

    // 找以head为头结点链表的最后一个节点
    private ListNode getTail(ListNode head) {
        if (head == null) {
            return null;
        }

        while (head.next != null) {
            head = head.next;
        }

        return head;
    }

}
