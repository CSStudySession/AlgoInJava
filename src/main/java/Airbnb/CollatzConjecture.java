package Airbnb;

import java.util.HashMap;

/**
 * If a number is odd, the next transform is 3*n+1
 * If a number is even, the next transform is n/2
 * The number is finally transformed into 1.
 * The step is how many transforms needed for a number turned into 1.
 * Given an integer n, output the max steps of transform number in [1, n] into 1.
 */
public class CollatzConjecture {

    HashMap<Integer, Integer> map;


    public int findStep(int num) {
        if (num <= 1) return 1;
        if (map.containsKey(num)) return map.get(num);
        if (num % 2 == 0) num /= 2;
        else num = 3*num + 1;
        if (map.containsKey(num)) return map.get(num)+1;
        int t = findStep(num);
        map.put(num, t);
        return t + 1;
    }


    public int getMax(int num) {
        if (num <= 0) return -1;
        map = new HashMap<>();
        int res = 0;
        for (int i = 1; i <= num; ++i) {
            int t = findStep(i);
            map.put(i, t);
            res = Math.max(res, t);
        }

        return res;
    }


    public int getIter(int num) {
        if (num <= 0) return -1;
        map = new HashMap<>();
        int res = 0;
        for (int i = 1; i <= num; ++i) {
            int cur = i, count = 1;

            while(cur != 1) {
                if (map.containsKey(cur)) {
                    count += map.get(cur);
                    break;
                }
                if (cur % 2 == 0) {
                    cur /= 2;
                } else {
                    cur = 3*cur + 1;
                }

                if (map.containsKey(cur)) {
                    count += map.get(cur) + 1;
                    break;
                }
                count++;
            }

            map.put(cur, count);
            res = Math.max(count, res);
        }

        return res;
    }
}
