package Design;

import java.util.HashMap;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 * LRUCache cache = new LRUCache( 2 / capacity /
 *
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */

public class LC146LRUCache {

    private int capacity;
    private HashMap<Integer, Node> map = new HashMap<>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    public LC146LRUCache(int capacity) {
        this.capacity = capacity;
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        if( !map.containsKey(key)) {    		//key找不到
            return -1;
        }

        // remove current
        Node cur = map.get(key);
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;

        // move cur to tail
        move_to_tail(cur);			// 每次get，使用次数+1，最近使用，放于尾部

        return map.get(key).value;
    }

    public void put(int key, int value) {
        // get 这个方法会把key挪到最末端，因此，不需要再调用 move_to_tail
        if (get(key) != -1) {
            map.get(key).value = value;
            return;
        }

        if (map.size() == capacity) {		//超出缓存上限
            map.remove(head.next.key);		//删除头部数据
            head.next = head.next.next;
            head.next.prev = head;
        }

        Node insert = new Node(key, value);		//新建节点
        map.put(key, insert);
        move_to_tail(insert);					//放于尾部
    }

    private void move_to_tail(Node cur) {    //移动数据至尾部
        cur.prev = tail.prev;
        tail.prev = cur;
        cur.prev.next = cur;
        cur.next = tail;
    }

    public class Node {
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }

    }
}