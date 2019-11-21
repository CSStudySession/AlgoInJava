package BFS;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *
 * Note:
 * Return 0 if there is no such transformation sequence.
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
 *
 * Output: 5
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 * Example 2:
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 */
public class LC127WordLadder {
    // 解法1 朴素bfs
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        Set<String> dict = new HashSet<>(wordList);
        int cnt = 1;

        while(!queue.isEmpty()) {
            //必须先取出size 不能在for循环中写k<queue.size() 队列的大小在循环中会变化！
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                String frontStr = queue.poll();
                for (int i = 0; i < frontStr.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        //把位置i的字符改成c 然后i+1到结尾接上去
                        String cur = frontStr.substring(0, i) + c + frontStr.substring(i + 1);
                        //要先与dict对照 如果cur不在dict里面 直接continue
                        if (!dict.contains(cur) || visited.contains(cur)) continue;
                        //过了dict对比后 再看是否已经到达终点
                        if (endWord.equals(cur)) {
                            return ++cnt; //这里要++cnt
                        }
                        visited.add(cur);
                        queue.offer(cur);
                    }
                }
            }
            //for循环结束后 等于层序遍历的一层结束了 cnt子增  进入可能的下一层
            cnt++;
        }
        return 0;
    }

    //解法2:双向bfs 由于起始点均已知 需要重点背诵的模板！
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;

        /*
        queue0起始状态存起点拓展的状态 queue1起始状态存终点拓展的状态
        每次拓展一个状态 看另外的队列是否已经存在该状态 如果有 则证明两个队列已经相遇
        如果没有 把该状态入队
        为什么需要Set?->O(1)时间知道某状态是否在另一个队列里
        */
        Queue<String> queue0 = new LinkedList<>();
        Queue<String> queue1 = new LinkedList<>();
        queue0.offer(beginWord);
        queue1.offer(endWord);
        Set<String> visited0 = new HashSet<>();
        Set<String> visited1 = new HashSet<>();
        visited0.add(beginWord);
        visited1.add(endWord);
        int cnt = 1;

        /*
        这里有个优化 每次开始新的一层拓展时 都从队列元素少的队列开始
        ->queue0一直指向短的那个队列 注意如果队列指针互换 Set指针也要互换
        */
        while (!queue0.isEmpty() && !queue1.isEmpty()) {
            if (queue0.size() > queue1.size()) {
                Queue tempQ = queue0;
                queue0 = queue1;
                queue1 = tempQ;
                Set setS = visited0;
                visited0 = visited1;
                visited1 = setS;
            }

            //先取出size!
            int size = queue0.size();
            for (int k = 0; k < size; k++) {
                String front = queue0.poll();
                for (int i = 0; i < front.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == front.charAt(i)) continue;
                        String nxt = front.substring(0,i) + c + front.substring(i+1);
                        if (visited1.contains(nxt)) {
                            return ++cnt;
                        }
                        if (dict.contains(nxt) && !visited0.contains(nxt)) {
                            queue0.offer(nxt);
                            visited0.add(nxt);
                        }
                    }
                }
            }
            //这层都拓展完了 cnt再++
            cnt++;
        }
        return 0;
    }




}
