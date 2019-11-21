package Airbnb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


/**
 * 他们公司list价格分成好几个部分，但是都是整数，如果在美金是整数，到了欧洲的网页显示汇率转换之后就变成了floating point，然后要round成整数，但是全部加起来round，和单独round再加起来，结果会不一样
 * # base price    100 =>  131.13   => 131
 * # cleaning fee   20 =>   26.23   => 26
 * # service fee    10 =>   13.54   => 14
 * # tax                5 =>    6.5      => 7
 * #                        =>  177.4E   => 178E
 * # sum           135$ => 178.93E => 179E
 *
 * 那么问题就来了，给个input list of floating points, 要求output list of integers, 满足以下两个constraint， 就是和跟Round(x1+x2+... +xn)的结果一样，但是minimize output 和input的绝对值差之和
 * #Input: A = [x1, x2, ..., xn]
 * # Sum T = Round(x1+x2+... +xn)  ;  178.93E => 179
 * # Output: B = [y1, y2, ...., yn]
 *
 * # Constraint #1: y1+y2+...+yn = T
 * # Constraint #2: minimize sum(abs(diff(xi - yi)))
 *
 * 举例
 * # A = [1.2, 2.3, 3.4]-baidu 1point3acres
 * # Round(1.2 + 2.3 + 3.4) = 6.9 => 7
 * # 1 + 2 + 3 => 6
 *
 * # 1 + 3 + 3 => 7
 * # 0.2 + 0.7 + 0.4 = 1.3
 *
 * # 1 + 2 + 4 => 7
 * # 0.2 + 0.3 + 0.6 = 1.1
 * 所以[1,2,4]比[1,3,3]要好
 */

public class PricesRoundUp {

    float diff = Float.MAX_VALUE;
    List<Integer> ret = new ArrayList<>();

    public List<Integer> solve(List<Float> prices) {
        int[] lower = new int[prices.size()];
        int[] upper = new int[prices.size()];

        int i = 0;
        float sum = 0;

        for (Float p : prices) {
            sum += p;
            lower[i] = (int)Math.floor(p);
            upper[i] = (int)Math.ceil(p);
            ++i;
        }

        backTrack(new ArrayList<>(), prices, lower, upper, Math.round(sum), 0, 0, 0);

        return ret;
    }


    private void backTrack(List<Integer> list, List<Float> prices, int[] lowers, int[] uppers, int sum, int cur, float curDiff, int index) {

        if (cur == sum && curDiff < diff) {
            ret = new ArrayList<>(list);
            diff = curDiff;
            return ;
        } else if (cur > sum || index >= prices.size()) {
            return;
        }

        Integer lower = lowers[index], upper = uppers[index];
        float price = prices.get(index);

        list.add(lower);
        backTrack(list, prices, lowers, uppers, sum, cur+lower, curDiff+(price-lower), index+1);
        list.remove(lower);

        list.add(upper);
        backTrack(list, prices, lowers, uppers, sum, cur+upper, curDiff+(upper-price), index+1);
        list.remove(upper);
    }


    class Node {
        double diff;
        Float val;

        Node(double diff, Float val) {
            this.diff = diff;
            this.val = val;
        }
    }


    public List<Integer> solve2(List<Float> prices) {
        double sum = 0;
        int floorSum = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> Double.compare(b.diff, a.diff));
        for (Float price : prices) {
            floorSum += Math.floor(price);
            sum += price;
            pq.offer(new Node(price - Math.floor(price), price));
        }

        int diff = (int)Math.round(sum) - floorSum;

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < diff; ++i) {
            list.add((int)Math.ceil(pq.poll().val));
        }

        while(!pq.isEmpty()) {
            list.add((int)Math.floor(pq.poll().val));
        }

        return list;
    }


    public static void main(String[] args) {
        List<Float> list = new ArrayList<>();
        list.add(1.2f);
        list.add(2.3f);
        list.add(3.4f);
        PricesRoundUp pricesRoundUp = new PricesRoundUp();
        List<Integer> ret = pricesRoundUp.solve(list);

        for (Integer i : ret) {
            System.out.println(i);
        }


        System.out.println("#################   Solution 2   ##################");
        List<Integer> ret1 = pricesRoundUp.solve2(list);

        for (Integer i : ret1) {
            System.out.println(i);
        }
    }
}
