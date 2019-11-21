package classicProblems;

import java.util.*;


/**
 *
 */


// not successfully yet

public class WordLadderII {

    // first impression : BFS + DFS

    // BFS : construct the map to link between each work

    // DFS : based on beginWord and the map, construct each minimum path

    private boolean isDiffByOne(String a, String b) {
        int ret = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                ret++;
            }
        }
        return ret == 1;
    }

    Map<String, HashSet<String>> map;
    List<List<String>> ret;
    int size = Integer.MAX_VALUE;

    public List<List<String>> findLaddersWithBFSAndDFS(String beginWord, String endWord, List<String> wordList) {
        map = new HashMap<>();
        ret = new ArrayList<>();
        size = Integer.MAX_VALUE;
        if (!wordList.contains(endWord)) {
            return ret;
        }


        // optimization : after parsed each word, delete every word from wordList, so that it wont build duplicated links
        for (int i = 0; i < wordList.size(); ++i) {
            if (isDiffByOne(beginWord, wordList.get(i))) {
                HashSet<String> list = map.getOrDefault(beginWord, new HashSet<>());
                list.add(wordList.get(i));
                map.put(beginWord, list);
            }
            map.put(wordList.get(i), new HashSet<>());
        }

        for (int i = 0; i < wordList.size(); ++i) {
            if (!wordList.get(i).equals(endWord)) {
                for (int j = 0; j < wordList.size(); ++j) {
                    if (i != j
                            && isDiffByOne(wordList.get(i), wordList.get(j))
                    ) {
                        map.get(wordList.get(i)).add(wordList.get(j));
                    }
                }
            }
        }

        List<String> list = new ArrayList<>();
        list.add(beginWord);
        dfs(list, beginWord, endWord);

        List<List<String>> res = new ArrayList<>();
        for (List<String> l : ret) {
            if (l.size() == size) {
                res.add(l);
            }
        }

        return res;
    }

    private void dfs(List<String> list, String cur, String endWord) {
        if (cur.equals(endWord) || map.get(cur).contains(endWord)) {
            list.add(endWord);
            ret.add(new ArrayList<>(list));
            size = Math.min(size, list.size());
            list.remove(endWord);
            return ;
        }

        if (list.size() >= size) {
            return ;
        }

        Set<String> wordList = map.get(cur);
        for (String word : wordList) {
            if (!list.contains(word)) {
                list.add(word);
                dfs(list, word, endWord);
                list.remove(list.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        String[] dict = {"hot","dot","dog","lot","log","cog"};
        WordLadderII wordLadderII = new WordLadderII();

        for (List<String> list : wordLadderII.findLaddersWithBFSAndDFS(beginWord, endWord, Arrays.asList(dict))) {
            System.out.println("#######################");
            for (String str : list) {
                System.out.print(str + ",");
            }
            System.out.println("\n#######################");
        }
    }
}
