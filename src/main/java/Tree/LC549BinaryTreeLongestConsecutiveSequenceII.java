package Tree;

/**
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 *
 * Especially, this path can be either increasing or decreasing.
 * For example, [1,2,3,4] and [4,3,2,1] are both considered valid,
 * but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order,
 * where not necessarily be parent-child order.
 *
 * Example 1:
 * Input:
 *         1
 *        / \
 *       2   3
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 *
 * Example 2:
 * Input:
 *         2
 *        / \
 *       1   3
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 *
 * Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 *
 * 思路:
 * 找到从任何一个node为起点的consecutive sequence的个数，而且sequence可能是increasing，decreasing 两种情况。
 * 容易想到，从bottom(leaf)开始计算比较容易，然后可以用bottom-up的思路。
 * 由于要return从该node起increasing，decreasing两种情况sequence的个数，
 * 所以helper function 返回的值用数组 int[]
 */
public class LC549BinaryTreeLongestConsecutiveSequenceII {

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        int[] result = {0};
        dfs(root, result);
        return result[0];
    }

    // 返回以root为节点的子树 increasing(idx==0)和decreasing(idx==1)的最长长度值
    private int[] dfs(TreeNode root, int[] result) {
        if (root == null) {
            return new int[] {0,0};
        }

        int increasingLCnt = 1;
        int decreasingLCnt = 1;
        int increasingRCnt = 1;
        int decreasingRCnt = 1;

        if (root.left != null) {
            int[] leftRes = dfs(root.left, result);
            if (root.val == root.left.val + 1) {
                increasingLCnt += leftRes[0];
            } else if (root.val == root.left.val - 1) {
                decreasingLCnt += leftRes[1];
            }
        }

        if (root.right != null) {
            int[] rightRes = dfs(root.right, result);
            if (root.val == root.right.val + 1) {
                increasingRCnt += rightRes[0];
            } else if (root.val == root.right.val - 1) {
                decreasingRCnt += rightRes[1];
            }
        }

        int maxIncreasing = Math.max(increasingLCnt, increasingRCnt);
        int maxDecreasing = Math.max(decreasingLCnt, decreasingRCnt);
        // 根节点在increasing和decreasing都被算在内 所以要减掉重复多算的1次
        result[0] = Math.max(result[0], maxIncreasing + maxDecreasing - 1);

        // 返回给上层调用节点的 只能是左边或右边选一个
        return new int[] {maxIncreasing, maxDecreasing};
    }
}
