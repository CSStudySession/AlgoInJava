package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of numbers (in an integer array), find subsets whose
 * elements add up to a specific number. For example:
 * array: {1, 3, 4, 5} target: 5 -> two subsets {1, 4} {5}
 *
 */
public class NumberOfSubsetsSumToX {

    public int numbSubSets(int[] arr, int target) {
        if (arr == null || arr.length == 0) return 0;
        List<List<Integer>>ret = new ArrayList<>();
        subSets(arr, target, new ArrayList<>(), ret, 0);
        return ret.size();
    }

    private void subSets(int[] arr, int target,
                         List<Integer>temp, List<List<Integer>>ret,
                         int level) {
        if (target == 0) {
            ret.add(new ArrayList<>(temp));
            return;
        }

        for (int i = level; i < arr.length; i++) {
            if (target - arr[i] >= 0) {
                temp.add(arr[i]);
                subSets(arr, target-arr[i], temp, ret, i+1);
                // 回溯时消除影响
                temp.remove(temp.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfSubsetsSumToX slu = new NumberOfSubsetsSumToX();
        int[] arr = {1,3,4,5};
        int target = 11;
        int ret = slu.numbSubSets(arr, target);
        System.out.println(ret);
    }
}
