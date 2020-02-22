package UnionFind;

import java.util.*;

/**
 * Given a list accounts, each element accounts[i] is a list of strings,
 * where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts.
 * Two accounts definitely belong to the same person if there is some email that is common to both accounts.
 * Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
 * A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name,
 * and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 *
 * Note:
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 *
 * 思路: 维护两个Map: mailToUser(一对一) userToEmails(一对多)
 * 建立mailToUser的map时 同时维护并查集fa数组 初始化fa数组每个user都指向自己
 * 当某个email对应的user在之前已经出现过 就把这两个user连接起来
 * e.g. m0 -> u0  m1 -> u2 : u0, u2 归并到一个并查集中
 * 建立userToEmails的时候 遍历每一个user 通过并查集找到father 然后把该user下面的所有emails都挂在father下面
 * userToEmails的keySet就是归并之后的user集合 最后遍历一遍keySet里的user 建立结果集合
 *
 * 复杂度:
 * we say the number of emails in the list is A(sum of all accounts[i]),
 * for a union find method if we don't have path compression and rank we have the complexity(worst case) of O(A^2),
 * if we have path compression its AlogA,
 * if we have path compression and rank we can reduce the time complexity to O(A).
 * and the space complexity is O(A) for three conditions
 */
public class LC721AccountsMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.size() == 0) return new ArrayList<>();

        int len = accounts.size();
        int[] fa = new int[len];
        // 注意并查集 需要初始化
        for (int i = 0; i < fa.length; i++) {
            fa[i] = i;
        }
        Map<String, Integer> mailToUser = new HashMap<>();

        for (int i = 0; i < len; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String curMail = accounts.get(i).get(j);
                mailToUser.putIfAbsent(curMail, i);
                int curFa = mailToUser.get(curMail);
                union(fa, curFa, i);
            }
        }

        Map<Integer, Set<String>> userToEmails =  new HashMap<>();
        for (int i = 0; i < len; i++) {
            int pi = find(fa, i);
            userToEmails.putIfAbsent(pi, new HashSet<>());
            Set<String> curSet = userToEmails.get(pi);
            for (int j  = 1; j < accounts.get(i).size(); j++)  {
                curSet.add(accounts.get(i).get(j));
            }
        }

        List<List<String>> res = new ArrayList<>();

        for (int cur : userToEmails.keySet()) {
            List<String> curList = new ArrayList<>();
            curList.addAll(userToEmails.get(cur));
            // 这里需要排序!
            Collections.sort(curList);
            curList.add(0, accounts.get(cur).get(0));
            res.add(curList);
        }
        return res;
    }

    // 注意这里是 find(fa, i) 不是fa[i]! 要调用find函数源头
    private void union(int[] fa, int i, int j) {
        int pi = find(fa, i);
        int pj = find(fa, j);
        fa[pi] = pj;
    }

    private int find(int[] fa, int cur) {
        if (fa[cur] ==  cur)  return fa[cur];
        return fa[cur] = find(fa, fa[cur]);
    }
}

