package Tree;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class LC114FlattenBinaryTreeToLinkedList {

    // version 1: traverse 先根遍历思想
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        dfs(root, new TreeNode[] {null});
    }

    /*
    注意为什么用数组去存储一个之前的根节点？这里相当于用了一个全局变量
    去追踪每次被flatten到右边的最左节点 画个图就好理解了
     */
    private void dfs(TreeNode root, TreeNode[] prev) {
        if (root == null) return;

        if (prev[0] != null) {
            prev[0].right = root;
            prev[0].left = null;
        }

        TreeNode tmp = root.right;
        prev[0] = root;
        dfs(root.left, prev);
        dfs(tmp, prev);
    }

    /*
    version 2: traverse 后根遍历思想
    递归函数返回到上一层调用时 应该确保这一层左右子树都已经
    被flatten完了 所以到某一层时 先把左子树flatten 再把右子树
    flatten 最后处理自己
     */
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);

        TreeNode tmp = root.right; // 先标记右子树 防止丢失
        root.right = root.left;
        root.left = null; // 注意需要把左指针置null

        // 右边现在是左边flatten过来的 往右走到头
        while (root.right != null) {
            root = root.right;
        }

        //把之前的右子树节点接在后面
        root.right = tmp;
    }
}
