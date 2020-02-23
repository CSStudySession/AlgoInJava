package String;

import java.util.HashMap;
import java.util.Map;

/**
 * Suppose we abstract our file system by a string in the following manner:
 *
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 * dir
 *     subdir1
 *     subdir2
 *         file.ext
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2
 * containing a file file.ext.
 *
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
 * represents:
 * dir
 *     subdir1
 *         file1.ext
 *         subsubdir1
 *     subdir2
 *         subsubdir2
 *             file2.ext
 * The directory dir contains two sub-directories subdir1 and subdir2.
 * subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1.
 * subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
 *
 * We are interested in finding the longest (number of characters) absolute path
 * to a file within our file system. For example, in the second example above,
 * the longest absolute path is "dir/subdir2/subsubdir2/file2.ext",
 * and its length is 32 (not including the double quotes).
 *
 * Given a string representing the file system in the above format,
 * return the length of the longest absolute path to file in the abstracted file system.
 * If there is no file in the system, return 0.
 *
 * Note:
 * The name of a file contains at least a "." and an extension.
 * The name of a directory or sub-directory will not contain a ".".
 * Time complexity required: O(n) where n is the size of the input string.
 *
 * Notice that a/aa/aaa/file1.txt is not the longest file path,
 * if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 *
 * 思路：hashmap
 * 1. 用HashMap存下来不同深度的文件夹的长度；
 * 2. 遇到file，更新最大file路径长度；
 * 3. 遇到文件夹，更新HashMap里文件夹长度；
 *
 * 我们这里只要储存文件夹长度就行，因为遇到file其实就直接能算出结果了。然后遇到文件夹如果没有那个深度的对应map，
 * 就加进去，有的话就更新。Java里put这个函数，有的话直接更新，不用再判断一次了。
 * 观察要点：\t的个数即为该层的深度！top level没有\t->第0层 往后扫 遇到第一个\t 之后的文件就在第二层！
 */
public class LC388LongestAbsoluteFilePath {

    public int lengthLongestPath(String input) {
        String[] list = input.split("\n");
        Map<Integer, Integer> depthHash = new HashMap<>();
        depthHash.put(0, 0); // 第0层啥都没有
        int result = 0;

        for (String str : list) {
            //多个\t的情况 跳过前面的多个 +1作为offset -> 因为lastIndexOf找不到token的时候返回-1
            int depth = str.lastIndexOf("\t") + 1; // 当前层是depth+1层
            int len = str.length() - depth;
            // 遇到 '.' 证明是个文件 可以更新答案 但不能更新depthHash
            if (str.contains(".")) {
                // 拿到上一层的长度
                int dirLen = depthHash.get(depth);
                result = Math.max(result, dirLen + len);
            } else {
                // 这一层的层数是depth+1, len + 1 -> +1 for '/'
                depthHash.put(depth + 1, depthHash.get(depth) + len + 1);
            }
        }

        return result;
    }
}
