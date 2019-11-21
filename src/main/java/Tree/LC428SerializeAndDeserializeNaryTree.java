package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Design an algorithm to serialize and deserialize an N-ary tree.
 * An N-ary tree is a rooted tree in which each node has no more than N children.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that an N-ary tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 *
 * Note:
 * N is in the range of [1, 1000]
 * Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 *
 * 思路：bfs level oder traversal
 */
public class LC428SerializeAndDeserializeNaryTree {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) return null;
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        sb.append(root.val);
        sb.append("#");
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (curr != null) {
                    List<Node> children = curr.children;
                    if (children == null || children.size() == 0) {
                        sb.append("null");
                    } else {
                        for (Node c : children) {
                            sb.append(c.val);
                            sb.append(",");
                            queue.offer(c);
                        }
                    }
                    sb.append("#");
                }
            }
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] elements = data.split("#");
        Queue<Node> nodes = new LinkedList<>();
        Node root = new Node(Integer.valueOf(elements[0]), null);
        nodes.offer(root);
        for (int i = 1; i < elements.length; i++) {
            Node parent = nodes.poll();
            List<Node> children = new ArrayList<>();
            if (elements[i].equals("null")) {
                parent.children = children;
                continue;
            }
            String[] kids = elements[i].split(",");
            for (String child : kids) {
                if (child.equals("")) continue;
                if (child.equals("null")) continue;
                Node curChild = new Node(Integer.valueOf(child), null);
                children.add(curChild);
                nodes.offer(curChild);
            }
            parent.children = children;
        }
        return root;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}
