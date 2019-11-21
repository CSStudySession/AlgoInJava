package Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node
 * to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 *
 * Example 2:
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 *
 *
 * 思路:
 * For each node like following, there should be four ways existing for max path:
 * 1. Node only （因为本题中的节点可能是负值！）
 * 2. L-sub + Node
 * 3. R-sub + Node
 * 4. L-sub + Node + R-sub
 *
 * Keep trace the four path and pick up the max one in the end.
 * 明确递归函数的返回值是什么：这本题中返回值表示通过root节点能走到root的parent的最大和，这个值作为返回对象返给调用父函数
 *
 */
public class LC124BinaryTreeMaximumPathSum {

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> res = new ArrayList<Integer>();
        res.add(Integer.MIN_VALUE);
        helper(root, res);
        return res.get(0);
    }

    private int helper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return 0;
        }

        int left = helper(root.left, res);
        int right = helper(root.right, res);
        int cur = root.val + (left > 0 ? left:0) + (right > 0 ? right:0);
        // 在递归回来的路上 每次比较结果 res(0)存储的是全局变量
        if (cur > res.get(0)) {
            res.set(0, cur);
        }

        // 由于必须能走回上一层 所以这里只能选左边或者右边或者自己 不能同时选左右
        return root.val + Math.max(left, Math.max(right, 0));
    }

}
