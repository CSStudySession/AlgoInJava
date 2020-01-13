package BFS;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *
 * Note:
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 *
 * Example 1:
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 *
 * Example 2:
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class LC126WordLadderII {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (wordList == null || wordList.size() == 0) return res;

        Set<String> dict = new HashSet<>();
        dict.addAll(wordList);

        Map<String, Integer> dist = new HashMap<>();
        getDist(beginWord, endWord, dict, dist);
        if (!dist.containsKey(endWord)) return res;

        List<String> path = new ArrayList<>();
        path.add(endWord);
        getAllMinPath(res, path, dist, endWord, beginWord);
        return res;
    }

    // 先BFS得出从beginWord到endWord的最短距离,方便之后的DFS剪枝.BFS过程中记录所有中间路径的可能单词以及该词跟startWord的距离
    private void getDist(String beginWord, String endWord, Set<String> dict, Map<String, Integer> dist) {
        dist.put(beginWord, 1);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (int i = 0; i < cur.length(); i++) {
                char[] curArr = cur.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    curArr[i] = c;
                    String tmp = new String(curArr);
                    //剪枝 只有字典里有的词才继续搜索
                    if (dict.contains(tmp)) {
                        if (tmp.equals(endWord)) {
                            dist.put(tmp, dist.get(cur)+1);
                            return;
                        }

                        //剪枝 每个词只入队一次
                        if (!dist.containsKey(tmp)) {
                            dist.put(tmp, dist.get(cur)+1);
                            queue.offer(tmp);
                        }
                    }
                }
            }
        }
    }

    private void getAllMinPath(List<List<String>> res,
                               List<String> path,
                               Map<String, Integer> dist,
                               String cur,
                               String beginWord) {
        if (cur.equals(beginWord)) {
            Collections.reverse(path);
            res.add(new ArrayList<>(path));
            Collections.reverse(path);
            return;
        }

        for (int i = 0; i < cur.length(); i++) {
            char[] curArr = cur.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                curArr[i] = c;
                String next = new String(curArr);
                //剪枝 新生成的词只有满足下面两个条件才继续深搜：
                //1.该词在哈希表里面存在 2.该词跟上一个词距离为1
                if (dist.containsKey(next) && dist.get(cur) - dist.get(next) == 1) {
                    path.add(next);
                    getAllMinPath(res, path, dist, next, beginWord);
                    path.remove(path.size()-1);
                }
            }
        }
    }

}
