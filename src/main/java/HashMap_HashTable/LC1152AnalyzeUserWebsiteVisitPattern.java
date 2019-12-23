package HashMap_HashTable;

import java.util.*;

/**
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.
 * (The websites in a 3-sequence are not necessarily distinct.)
 * Find the 3-sequence visited by the largest number of users. If there is more than one solution,
 * return the lexicographically smallest such 3-sequence.
 *
 * Example 1:
 * Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
 * Output: ["home","about","career"]
 * Explanation:
 * The tuples in this example are:
 * ["joe", 1, "home"]
 * ["joe", 2, "about"]
 * ["joe", 3, "career"]
 * ["james", 4, "home"]
 * ["james", 5, "cart"]
 * ["james", 6, "maps"]
 * ["james", 7, "home"]
 * ["mary", 8, "home"]
 * ["mary", 9, "about"]
 * ["mary", 10, "career"]
 * The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
 * The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
 * The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
 * The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
 * The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 *
 * Note:
 * 3 <= N = username.length = timestamp.length = website.length <= 50
 * 1 <= username[i].length <= 10
 * 0 <= timestamp[i] <= 10^9
 * 1 <= website[i].length <= 10
 * Both username[i] and website[i] contain only lowercase characters.
 * It is guaranteed that there is at least one user who visited at least 3 websites.
 * No user visits two websites at the same time.
 */
public class LC1152AnalyzeUserWebsiteVisitPattern {

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        // <userId, <timestamp, websiteId>>
        Map<String, TreeMap<Integer,String>> map = new HashMap<>();
        // 以userId为key 建<时间戳, websiteId>映射 用TreeMap的原因是 pattern是有序的 abc != bca
        for (int i = 0; i < username.length; i++) {
            map.putIfAbsent(username[i], new TreeMap<>());
            map.get(username[i]).put(timestamp[i], website[i]);
        }

        int max = Integer.MIN_VALUE;
        String ans = "";
        // <三元组, 出现次数>
        HashMap<String,Integer> patternToFreq = new HashMap<>();
        for(String user : map.keySet()) {
            TreeMap<Integer,String> timeStampToWeb = map.get(user);
            HashSet<String> set = new HashSet<>();
            // list of websites for a user
            List<String> temp = new ArrayList<>();

            for(int tStamp : timeStampToWeb.keySet()) {
                temp.add(timeStampToWeb.get(tStamp));
            }

            // 按时间戳顺序建立三元组 用空格分开
            for (int i = 0; i < temp.size() - 2; i++) {
                String web1 = temp.get(i) + " ";
                for (int j = i + 1; j < temp.size() - 1; j++) {
                    String web2 = temp.get(j) + " ";
                    for (int k = j + 1; k < temp.size(); k++) {
                        String web3 = temp.get(k);
                        String pattern = web1 + web2 + web3;
                        // 利用set去重
                        if (set.contains(pattern)) continue;
                        set.add(pattern);
                        patternToFreq.put(pattern, patternToFreq.getOrDefault(pattern,0) + 1);

                        if (patternToFreq.get(pattern) > max) {
                            max = patternToFreq.get(pattern);
                            ans = pattern;
                        } else if (patternToFreq.get(pattern) == max && ans.compareTo(pattern) > 0) {
                            ans = pattern;
                        }
                    }
                }
            }
        }
        // 为了去重加入了空格 返回结果的时候需要把空格拿掉
        return Arrays.asList(ans.split(" "));
    }
}
