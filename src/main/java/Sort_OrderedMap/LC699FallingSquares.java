package Sort_OrderedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * On an infinite number line (x-axis), we drop given squares in the order they are given.
 *
 * The i-th square dropped (positions[i] = (left, side_length)) is a square
 * with the left-most point being positions[i][0] and sidelength positions[i][1].
 *
 * The square is dropped with the bottom edge parallel to the number line,
 * and from a higher height than all currently landed squares.
 * We wait for each square to stick before dropping the next.
 *
 * The squares are infinitely sticky on their bottom edge,
 * and will remain fixed to any positive length surface they touch (either the number line or another square).
 * Squares dropped adjacent to each other will not stick together prematurely.
 *
 *
 * Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped,
 * after dropping squares represented by positions[0], positions[1], ..., positions[i].
 *
 * Example 1:
 * Input: [[1, 2], [2, 3], [6, 1]]
 * Output: [2, 5, 5]
 * Explanation:
 * After the first drop of positions[0] = [1, 2]: _aa _aa ------- The maximum height of any square is 2.
 * After the second drop of positions[1] = [2, 3]: __aaa __aaa __aaa _aa__ _aa__ --------------
 * The maximum height of any square is 5. The larger square stays on top of the smaller square despite
 * where its center of gravity is, because squares are infinitely sticky on their bottom edge.
 * After the third drop of positions[1] = [6, 1]: __aaa __aaa __aaa _aa _aa___a --------------
 * The maximum height of any square is still 5. Thus, we return an answer of [2, 5, 5].
 *
 * Example 2:
 * Input: [[100, 100], [200, 100]]
 * Output: [100, 100]
 * Explanation: Adjacent squares don't get stuck prematurely - only their bottom edge can stick to surfaces.
 *
 * Note:
 * 1 <= positions.length <= 1000.
 * 1 <= positions[i][0] <= 10^8.
 * 1 <= positions[i][1] <= 10^6.
 */
public class LC699FallingSquares {

    public List<Integer> fallingSquares(int[][] positions) {
        TreeSet<Square> ts = new TreeSet<>((srA, srB) -> (
                srA.start != srB.start ? srA.start - srB.start :
                        (srA.height != srB.height) ? srB.height - srA.height : srA.end - srB.end));
        List<Integer> result = new ArrayList<>();
        int maxHeight = 0;

        for (int[] sqr : positions) {
            int left = sqr[0];
            int side = sqr[1];
            int right  = sqr[0] + side;
            Square curr = new Square(left, right, side);

            Square lo = ts.floor(curr) == null ? ts.higher(curr) : ts.floor(curr);
            int curMax = curr.height;
            while (lo != null && lo.start < curr.end) {
                if (lo.end <= curr.start) {
                    lo = ts.higher(lo);
                    continue;
                }
                // 持续更新最高高度
                curMax = Math.max(curMax, curr.height + lo.height);
                if (lo.start < curr.start && lo.end <= curr.end) {
                    Square leftS = new Square(lo.start, curr.start, lo.height);
                    ts.add(leftS);
                } else if (lo.start <= curr.end && lo.end > curr.end) {
                    Square rightS = new Square(curr.end, lo.end, lo.height);
                    ts.add(rightS);
                }
                ts.remove(lo);
                lo = ts.higher(lo);
            }
            curr.height = curMax;
            ts.add(curr);
            maxHeight = Math.max(curMax, maxHeight);
            result.add(maxHeight);
        }

        return result;
    }

    class Square {
        int start;
        int end;
        int height;

        public Square(int start, int end, int height) {
            this.start = start;
            this.end = end;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        LC699FallingSquares inst = new LC699FallingSquares();
        int[][] positions = {{9,10},{4,1},{2,1},{7,4},{6,10}};
        inst.fallingSquares(positions);
        System.out.println(".");
    }

}
