package Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Print a binary tree in an m*n 2D string array following these rules:
 *
 * The row number m should be equal to the height of the given binary tree.
 * The column number n should always be an odd number.
 *
 * The root node's value (in string format) should be put in the exactly middle of the first row it can be put.
 * The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
 *
 * Each unused space should contain an empty string "".
 * Print the subtrees following the same rules.
 *
 * Example 1:
 * Input:
 *      1
 *     /
 *    2
 * Output:
 * [["", "1", ""],
 *  ["2", "", ""]]
 *
 * Example 2:
 * Input:
 *      1
 *     / \
 *    2   3
 *     \
 *      4
 * Output:
 * [["", "", "", "1", "", "", ""],
 *  ["", "2", "", "", "", "3", ""],
 *  ["", "", "4", "", "", "", ""]]
 * Example 3:
 * Input:
 *       1
 *      / \
 *     2   5
 *    /
 *   3
 *  /
 * 4
 * Output:
 *
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 * Note: The height of binary tree is in the range of [1, 10].
 *
 * ref link: https://www.cnblogs.com/grandyang/p/7489097.html
 */
public class LC655PrintBinaryTree {

    public List<List<String>> printTree(TreeNode root) {
        int h = getHeight(root);
        int w = (int)Math.pow(2, h) - 1;
        List<List<String>> res = new ArrayList<>();

        for (int i = 0; i < h; i++) {
            List<String> tmp = new ArrayList<>();
            for (int j = 0; j < w; j++) {
                tmp.add("");
            }
            res.add(tmp);
        }

        helper(root, 0, w - 1, 0, h, res);
        return res;
    }

    private void helper(TreeNode root, int i, int j, int curH, int height, List<List<String>> res) {
        // curH范围: [0, heights-1]
        if (root == null || curH == height) return;
        // 每次都把中点:(i+j)/2 的位置赋值成当前root的值
        res.get(curH).set((i+j) / 2, root.val + "");

        // 递归处理左右子节点 注意左右端点的 index
        helper(root.left, i, (i+j) /  2, curH + 1, height, res);
        helper(root.right, (i+j) / 2 + 1, j, curH + 1, height, res);
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left),  getHeight(root.right));
    }

}
