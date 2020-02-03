package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 *
 * Example:
 * You may serialize the following tree:
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * as "[1,2,3,null,null,4,5]"
 *
 */
public class LC297SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string using bfs
    public String serialize(TreeNode root) {
        if (root == null) return "";

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                queue.offer(node.left);
                queue.offer(node.right);
                sb.append(node.val + ",");
            } else {
                sb.append("#" + ",");
            }
        }
        //删掉最后一个","
        if (sb.length() != 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        TreeNode head = null;
        if (data == null || data.length() == 0) return head;
        String[] nodes = data.split(",");
        TreeNode[] treeNodes = new TreeNode[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            if (!nodes[i].equals("#")) {
                treeNodes[i] = new TreeNode(Integer.valueOf(nodes[i]));
            }
        }

        /*
        用 k and j 两个指针去重构二叉树 注意循环中break和continue的条件
         */
        int k = 1;
        for (int j = 0; j < treeNodes.length; j++) {
            if (k == treeNodes.length) break;
            if (treeNodes[j] == null) continue;
            treeNodes[j].left = treeNodes[k++];
            treeNodes[j].right = treeNodes[k++];
        }
        return treeNodes[0];
    }


    // Encodes a tree to a single string using pre-order traversal
    public String serialize2(TreeNode root) {
        if (root == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        String left = serialize2(root.left);
        String right = serialize2(root.right);
        sb.append(",");
        sb.append(left);
        sb.append(",");
        sb.append(right);
        return sb.toString();
    }

    // Decodes encoded data to tree.
    public TreeNode deserialize2(String data) {
        Queue<String> queue = new LinkedList<>();
        String[] nodes = data.split(",");
        for (String node : nodes) {
            queue.offer(node);
        }
        return treeConstructor(queue);
    }

    private TreeNode treeConstructor(Queue<String> queue) {
        if (queue == null || queue.size() == 0) return null;
        String cur = queue.poll();
        if (cur.equals("null")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(cur));
        root.left = treeConstructor(queue);
        root.right = treeConstructor(queue);
        return root;
    }

}
