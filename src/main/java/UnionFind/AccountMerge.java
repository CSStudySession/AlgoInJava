package UnionFind;


import java.util.*;

/**
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example
 * Given
 *
 * [
 *   ["John", "johnsmith@mail.com", "john00@mail.com"],
 *   ["John", "johnnybravo@mail.com"],
 *   ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 *   ["Mary", "mary@mail.com"]
 * ]
 * Return
 *
 * [
 *   ["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
 *   ["John", "johnnybravo@mail.com"],
 *   ["Mary", "mary@mail.com"]
 * ]
 */
public class AccountMerge {

    HashMap<Integer, Integer> father;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        father = new HashMap<>();

        Map<String, List> emailToIds = getEmailToIds(accounts);
        for (String email : emailToIds.keySet()) {
            List<Integer> ids = emailToIds.get(email);
            for (int i = 1; i < ids.size(); ++i) {
                union(ids.get(i), ids.get(0));
            }
        }


        List<List<String>> res = new ArrayList<>();
        Map<Integer, Set<String>> idToEmail = getIdToEmail(accounts);
        for (Integer id : idToEmail.keySet()) {
            List<String> emails = new ArrayList<>(idToEmail.get(id));
            Collections.sort(emails);
            emails.add(0, accounts.get(id).get(0));
            res.add(emails);
        }

        return res;
    }

    private Map<String, List> getEmailToIds(List<List<String>> accounts) {
        Map<String, List> emailToIds = new HashMap<>();
        for (int i = 0; i < accounts.size(); ++i) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); ++j) {
                List<Integer> ids = emailToIds.getOrDefault(account.get(j), new ArrayList<Integer>());
                ids.add(i);
                emailToIds.put(account.get(i), ids);
            }
        }
        return emailToIds;
    }


    private Map<Integer, Set<String>> getIdToEmail(List<List<String>> accounts) {
        Map<Integer, Set<String>> map = new HashMap<>();
        for (int i = 0; i < accounts.size(); ++i) {
            int root = find(i);
            Set<String> set = map.getOrDefault(root, new HashSet<>());
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); ++j) {
                set.add(account.get(j));
            }
            map.put(root, set);
        }

        return map;
    }


    private int find(int id) {
        List<Integer> path = new ArrayList<>();
        while(father.containsKey(id)) {
            path.add(id);
            id = father.get(id);
        }

        for (Integer i : path) {
            father.put(i, id);
        }

        return id;
    }

    private void union(int id1, int id2) {
        int root1 = find(id1);
        int root2 = find(id2);
        if (root1 != root2) {
            father.put(root1, root2);
        }
    }
}
