package Airbnb;

import common.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a board which is a 2D matrix includes a-z and dictionary dict, findWithDuplicates the largest collection of words on the board, the words can not overlap in the same position. return the size of largest collection.
 *
 * The words in the dictionary are not repeated.
 * You can reuse the words in the dictionary.
 *
 * Example
 * Give a board below
 *
 * {{'a', 'b', 'c'},
 *  {'d', 'e', 'f'},
 *  {'g', 'h', 'i'}}
 * dict = {"abc", "cfi", "beh", "defi", "gh"}
 * Return 3 // we can get the largest collection{"abc", "defi", "gh"}
 */
public class BoggleGame {

    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};

    public String flatten(char[][] board, List<int[]> list) {
        String ret = "";
        for (int[] arr : list) {
            ret += board[arr[0]][arr[1]];
        }
        return ret;
    }

    public void helper(Trie root, List<List<int[]>> lists, List<int[]> list,
                       char[][] board, boolean[][] visited, int x, int y) {

        String str = flatten(board, list);
        boolean[] retType = root.search(str);
        if (!retType[0]) {
            return ;
        }
        if (retType[1]) {
            lists.add(new ArrayList<>(list));
        }

        for (int i = 0; i < dx.length; ++i) {
            int fx = x + dx[i];
            int fy = y + dy[i];
            if (fx < 0 || fx >= board.length || fy < 0 || fy >= board.length || visited[fx][fy]) {
                continue;
            }

            visited[fx][fy] = true;
            list.add(new int[]{fx, fy});
            helper(root, lists, list, board, visited, fx, fy);
            list.remove(list.size() - 1);
            visited[fx][fy] = false;
        }
    }

    public void searchWords(List<String> ret, List<String> curWords,
                            List<List<int[]>> paths, boolean[][] visited, char[][] board, int start) {
        if (start == paths.size()) {
            if (curWords.size() > ret.size()) {
                ret.clear();
                ret.addAll(curWords);
            }
            return;
        }

        for (int i = start; i < paths.size(); i++) {
            boolean canUse = true;
            for (int[] coor : paths.get(i)) {
                if (visited[coor[0]][coor[1]]) {
                    canUse = false;
                    break;
                }
            }

            if (canUse) {
                for (int[] coor : paths.get(i)) {
                    visited[coor[0]][coor[1]] = true;
                }
                curWords.add(flatten(board, paths.get(i)));
                searchWords(ret, curWords, paths, visited, board, i + 1);
                curWords.remove(curWords.size() - 1);
                for (int[] coor : paths.get(i)) {
                    visited[coor[0]][coor[1]] = false;
                }
            }
        }
    }

    public int boggleGame(char[][] board, String[] words) {
        return find(board, words).size();
    }

    private List<String> find(char[][] board, String[] words) {
        Trie root = new Trie();
        int n = board.length;

        for (String word : words) {
            root.insert(word);
        }

        List<List<int[]>> lists = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                boolean[][] visited = new boolean[n][n];
                List<int[]> list = new ArrayList<>();
                list.add(new int[]{i, j});
                visited[i][j] = true;
                helper(root, lists, list, board, visited, i, j);
            }
        }

        List<String> ret = new ArrayList<>();
        searchWords(ret, new ArrayList<>(), lists, new boolean[n][n], board, 0);
        return ret;
    }


    public static void main(String[] args) {
        char[][] board = {
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'}
        };
        String[] dict = {"abc", "cfi", "beh", "defi", "gh"};
        BoggleGame boggleGame = new BoggleGame();
        System.out.println(boggleGame.boggleGame(board, dict));
        for (String str : boggleGame.find(board, dict)) {
            System.out.println(str);
        }


    }
}
