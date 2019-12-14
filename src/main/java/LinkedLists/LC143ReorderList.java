package LinkedLists;

/**
 * Given a singly linked list L: L0 → L1 → … → Ln-1 → Ln,
 * reorder it to: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 →…
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example 1:
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 *
 * Example 2:
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 *
 * 复杂度
 * 时间 O(n) 空间O(1)
 *
 * 思路：双指针+翻转+合并+排序
 * 主函数reorderList()：
 * 对于长度为0或1的链表，返回；找到中点mid；分割链表并翻转后半段为tail；与前半段head合并
 *
 * 找中点findMid()：
 * 快慢指针，当fast == null || fast.next == null时，返回慢指针
 *
 * 翻转reverse()：
 * 建立空链表结点pre，先存head.next为temp，令head指向pre，令pre等于head，再令temp为head。
 * 这样翻转就会令链表的首元素head移动到尾部，并让pre移动到所有已翻转结点的头部。
 * 当head移动到最后一个元素null，pre正好移动到整个链表的头部。返回pre，翻转完毕。
 *
 * 合并merge()：
 * 建立新链表结点dummy，复制到新链表结点cur。用index判断当前结点是奇数还是偶数。
 * 偶数则加入n1的结点，n1后移；否则加入n2的结点，n2后移。
 * 每加入一个结点后，cur后移。最后若n1或n2有剩余结点，则加入cur.next。返回dummy.next。
 */
public class LC143ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode mid = getMid(head);
        ListNode tail = reverse(mid.next);
        mid.next = null;
        merge(head, tail);
    }

    private ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }

    private ListNode merge(ListNode n1, ListNode n2) {
        int idx = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (n1 != null && n2 != null) {
            if (idx % 2 == 0) {
                cur.next = n1;
                n1 = n1.next;
            } else {
                cur.next = n2;
                n2 = n2.next;
            }
            idx++;
            cur = cur.next;
        }
        if (n1 != null) {
            cur.next = n1;
        } else {
            cur.next = n2;
        }
        return dummy.next;
    }

}
