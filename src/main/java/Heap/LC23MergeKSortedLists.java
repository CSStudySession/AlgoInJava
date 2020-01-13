package Heap;

import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class LC23MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((n1, n2) -> (n1.val - n2.val));

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) continue;  // 注意这里要判断null！
            pq.offer(lists[i]);
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;  // 需要个游标指针指向dummy dummy一直是静态的

        while (!pq.isEmpty()) {
            ListNode front = pq.poll();
            cur.next = front;
            if (front.next != null) {
                pq.offer(front.next);
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
