package Airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> getCombination(int[] arr, int target) {
        int n = arr.length;
        List<List<List<Integer>>> dp = new ArrayList<>();
        Arrays.sort(arr);

        for (int i = 1; i <= target; ++i) {
            List<List<Integer>> lists = new ArrayList<>();
            for (int j = 0; j < arr.length && arr[j] <= i; ++j) {
                if (i == arr[j]) lists.add(Arrays.asList(arr[j]));
                else{
                    for (List<Integer> l : dp.get(i-arr[j]-1)) {
                        if (arr[j] <= l.get(0)) {
                            List cl = new ArrayList();
                            cl.add(arr[j]);
                            cl.addAll(l);
                            lists.add(cl);
                        }
                    }
                }
            }
            dp.add(lists);
        }

        return dp.get(target-1);
    }
}
