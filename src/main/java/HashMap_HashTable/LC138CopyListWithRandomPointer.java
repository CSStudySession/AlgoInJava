package HashMap_HashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 *
 * Example 1:
 * Input:
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 *
 * Explanation:
 * Node 1's value is 1, both of its next and random pointer points to Node 2.
 * Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
 *
 * Note:
 * You must return the copy of the given head as a reference to the cloned list.
 *
 */
public class LC138CopyListWithRandomPointer {

    /*
    method 1
    思路：HaspMap
    maintain a HashMap: <key, value> = <original, copy>
    first pass: generate HashMap
    second pass: copy random field
    */
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        Map<Node, Node> map = new HashMap<>();
        Node newHead = new Node(head.val, null, null);
        map.put(head, newHead);
        Node prev = newHead;
        Node cur = head.next;

        while (cur != null) {
            Node newNode = new Node(cur.val, null, null);
            map.put(cur, newNode);
            prev.next = newNode;
            prev = newNode;
            cur = cur.next;
        }

        cur = head;
        Node copiedCur = newHead;
        while (cur != null) {
            copiedCur.random = map.get(cur.random);
            copiedCur = copiedCur.next;
            cur = cur.next;
        }

        return newHead;

    }

    public Node copyRandomList2(Node head) {
        if (head == null) return head;
        Node cur = head;
        // pass 1: copy each node and connect newNode to next of original one
        while (cur != null) {
            Node newNode = new Node(cur.val, null, null);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }
        // pass 2: copy random field to newNode
        cur = head;
        while (cur != null && cur.next != null) {
            if (cur.random != null) {
                // note that cur.random is original, cur.random.next is copy.
                // so we point copy's random to random's copy
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        // pass 3: get a deep copy from mixed list
        Node newHead = head.next;
        cur = head;
        while (cur != null) {
            Node newNode = cur.next;
            cur.next = newNode.next;
            if (newNode.next != null) {
                newNode.next = newNode.next.next;
            }
            cur = cur.next;
        }

        return newHead;
    }

    // Definition for a Node.
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

}
