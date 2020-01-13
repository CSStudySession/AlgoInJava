package UnionFind;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array of integers,
 * find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 * Therefore its length is 4.
 */
public class LC128LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        UF uf = new UF(nums.length);
        // <value, index>
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(nums[i])) {
                continue;
            }
            map.put(nums[i],i);

            if (map.containsKey(nums[i]+1)) {
                uf.union(i, map.get(nums[i]+1));
            }

            if (map.containsKey(nums[i]-1)) {
                uf.union(i,map.get(nums[i]-1));
            }
        }

        return uf.maxUnion();
    }

    public class UF {
        private int[] root;
        public UF(int n) {
            root = new int[n];
            for(int i = 0; i < n; i++){
                root[i] = i;
            }
        }

        private int root(int i) {
            if (root[i] == i) return i;
            return root[i] = root(root[i]);
        }

        public boolean connected(int i, int j){
            return root(i) == root(j);
        }

        public void union(int p, int q){
            int i = root(p);
            int j = root(q);
            root[i] = j; // 注意这里是root[i]而不是i!!
        }

        // returns the max size of union
        public int maxUnion(){ // O(n)
            int[] count = new int[root.length];
            int ret = 0;
            for(int i = 0; i < root.length; i++){
                count[root(i)]++;
                ret = Math.max(ret, count[root(i)]);
            }

            return ret;
        }
    }

}
