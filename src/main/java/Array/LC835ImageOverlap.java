package Array;

import java.util.*;

/**
 * Two images A and B are given, represented as binary, square matrices of the same size.
 * (A binary matrix has only 0s and 1s as values.)
 * We translate one image however we choose (sliding it left, right, up, or down any number of units),
 * and place it on top of the other image.  After, the overlap of this translation is the number of positions
 * that have a 1 in both images.
 * (Note also that a translation does not include any kind of rotation.)
 * What is the largest possible overlap?
 *
 * Example 1:
 * Input: A = [[1,1,0],
 *             [0,1,0],
 *             [0,1,0]]
 *        B = [[0,0,0],
 *             [0,1,1],
 *             [0,0,1]]
 * Output: 3
 * Explanation: We slide A to right by 1 unit and down by 1 unit.
 *
 * Notes:
 * 1 <= A.length = A[0].length = B.length = B[0].length <= 30
 * 0 <= A[i][j], B[i][j] <= 1
 */
public class LC835ImageOverlap {

    public int largestOverlap(int[][] A, int[][] B) {
        int n = A.length;
        List<Point> aList = new ArrayList<>();
        List<Point> bList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) aList.add(new Point(i, j));
                if (B[i][j] == 1) bList.add(new Point(i, j));
            }
        }

        Set<Point> bSet = new HashSet<>(bList);

        int ret = 0;
        Set<Point> seen = new HashSet<>();
        for (int i = 0; i < aList.size(); i++) {
            // aList里每一个1都去跟bList的1重合
            Point aP = aList.get(i);
            for (int j = 0; j < bList.size(); j++) {
                Point bP = bList.get(j);
                Point delta = new Point(bP.i - aP.i, bP.j - aP.j);
                /*
                为了避免相同的位移。比如A中（0，1）处的1想到B中（1，2）处的1也是需要向右移动1，向下移动1
                那么我们之前计算过一遍就不需要再计算一次了。
                 */
                if (!seen.contains(delta)) {
                    seen.add(delta);
                    int cur = 0;
                    //对于aList中的每个点加上位移delta，去判断是否与bList重合
                    for (int k = 0; k < aList.size(); k++) {
                        Point curP = aList.get(k);
                        Point nxtP = new Point(curP.i + delta.i, curP.j + delta.j);
                        if (bSet.contains(nxtP)) {
                            cur++;
                        }
                    }
                    ret = Math.max(ret, cur);
                }
            }
        }
        return ret;
    }


    public class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) return false;
            if (other == this) return true;
            if (! (other instanceof Point)) {
                return false;
            }

            Point otherP = (Point)other;
            return i == otherP.i && j == otherP.j;
        }
    }
}
