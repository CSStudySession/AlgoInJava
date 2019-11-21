package Amazon;

import java.util.List;

/**
 * Given an N-ary tree, find the subtree with the maximum average. Return the root of the subtree.
 * A subtree of a tree is the node which have at least 1 child plus all its descendants.
 * The average value of a subtree is the sum of its values, divided by the number of nodes.
 *
 * Example 1:
 * Input:
 * 		   20
 * 	     /   \
 * 	   12     18
 *   /  |  \   / \
 * 11   2   3 15  8
 *
 * Output: 18
 * Explanation:
 * There are 3 nodes which have children in this tree:
 * 12 => (11 + 2 + 3 + 12) / 4 = 7
 * 18 => (18 + 15 + 8) / 3 = 13.67
 * 20 => (12 + 11 + 2 + 3 + 18 + 15 + 8 + 20) / 8 = 11.125
 *
 * 18 has the maximum average so output 18.
 *
 * 思路: divide and conquer 在某个节点上 先得到所有子树的结果
 * 然后比较后得出这一层的结果 返回给上一层
 */
public class NArySubTreeWithMaxAvg {

    public Node maximumAverageSubtree(Node root) {
        if (root == null || root.children.size() == 0) return null;
        return helper(root).maxNode;
    }

    private ResultType helper(Node root) {
        if (root == null) return new ResultType(0, 0, 0,
                0, 0,null);

        if (root.children.size() == 0) return new ResultType(1, root.val, 1,
                root.val, root.val, root);

        ResultType[] subRet = new ResultType[root.children.size()];
        int idx = 0;
        for (Node child : root.children) {
            subRet[idx++] = helper(child);
        }

        int subSize = 0;
        int subSum = 0;
        for (int i = 0; i < subRet.length; i++) {
            subSize += subRet[i].size;
            subSum += subRet[i].sum;
        }

        ResultType cur = new ResultType(subSize + 1,
                subSum+ root.val,
                subSize + 1,
                subSum + root.val,
                (subSum + root.val) / (double)(subSize + 1),
                root);

        for (int j = 0; j < subRet.length; j++) {
           if (subRet[j].size > 1 && subRet[j].maxAvg > cur.maxAvg) {
               cur.maxSubtreeSize = subRet[j].maxSubtreeSize;
               cur.maxSubtreeSum = subRet[j].maxSubtreeSum;
               cur.maxAvg = subRet[j].maxAvg;
               cur.maxNode = subRet[j].maxNode;
           }
        }

        return cur;
    }

    public class ResultType {

        int maxSubtreeSize;
        int maxSubtreeSum;
        int size;
        int sum;
        double maxAvg;
        Node maxNode;

        public ResultType(int size, int sum, int maxSubtreeSize,
                          int maxSubtreeSum, double maxAvg,
                          Node maxNode) {
            this.size = size;
            this.sum = sum;
            this.maxSubtreeSize = maxSubtreeSize;
            this.maxSubtreeSum = maxSubtreeSum;
            this.maxAvg = maxAvg;
            this.maxNode = maxNode;
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}
