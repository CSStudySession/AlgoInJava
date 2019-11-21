package Tree;

/**
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.
 *
 * (A subtree of a tree is any node of that tree plus all its descendants.
 * The average value of a tree is the sum of its values, divided by the number of nodes.)
 *
 * Example 1:
 * Input: [5,6,1]
 * Output: 6.00000
 * Explanation:
 * For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
 * For the node with value = 6 we have an average of 6 / 1 = 6.
 * For the node with value = 1 we have an average of 1 / 1 = 1.
 * So the answer is 6 which is the maximum.
 *
 * Note:
 * The number of nodes in the tree is between 1 and 5000.
 * Each node will have a value between 0 and 100000.
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 *
 */
public class LC1120MaximumAverageSubtree {

    public double maximumAverageSubtree(TreeNode root) {
        ResultType ret = helper(root);
        return ret.maxAvg;
    }

    private ResultType  helper(TreeNode root) {
        if (root == null) return new ResultType(0, 0, 0,
                0, 0,null);

        ResultType leftSub = helper(root.left);
        ResultType rightSub = helper(root.right);

        ResultType cur = new ResultType(leftSub.size + rightSub.size + 1,
                            leftSub.sum + rightSub.sum + root.val,
                leftSub.size + rightSub.size + 1,
                leftSub.sum + rightSub.sum + root.val,
        (leftSub.sum + rightSub.sum + root.val)/(double)(leftSub.size + rightSub.size + 1),
                root);

        if (leftSub.size > 0 && leftSub.maxAvg > cur.maxAvg) {
            cur.maxSubtreeSize = leftSub.maxSubtreeSize;
            cur.maxSubtreeSum = leftSub.maxSubtreeSum;
            cur.maxAvg = leftSub.maxAvg;
            cur.maxNode = leftSub.maxNode;
        }

        if (rightSub.size > 0 && rightSub.maxAvg > cur.maxAvg) {
            cur.maxSubtreeSize = rightSub.maxSubtreeSize;
            cur.maxSubtreeSum = rightSub.maxSubtreeSum;
            cur.maxAvg = rightSub.maxAvg;
            cur.maxNode = rightSub.maxNode;
        }

        return cur;
    }

    public class ResultType {

        int maxSubtreeSize;
        int maxSubtreeSum;
        int size;
        int sum;
        double maxAvg;
        TreeNode maxNode;

        public ResultType(int size, int sum, int maxSubtreeSize,
                          int maxSubtreeSum, double maxAvg,
                          TreeNode maxNode) {
            this.size = size;
            this.sum = sum;
            this.maxSubtreeSize = maxSubtreeSize;
            this.maxSubtreeSum = maxSubtreeSum;
            this.maxAvg = maxAvg;
            this.maxNode= maxNode;
        }
    }
}


