package HashMap_HashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant,
 * then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * (Note that because the prison is a row, the first and the last cells in the row
 * can't have two adjacent neighbors.)
 *
 * We describe the current state of the prison in the following way:
 * cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 *
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
 *
 * Example 1:
 *
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation:
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 * Example 2:
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 *
 * Note:
 * cells.length == 8
 * cells[i] is in {0, 1}
 * 1 <= N <= 10^9
 *
 * 思路:
 * 用signature作为hashmap的key记录当前的cells，找到循环之后直接days - period。
 * Time: O(n * m / k)
 * Space: O(n * k) - k 是循环周期，n是cells数，m是days
 *
 */
public class LC957PrisonCellsAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String, Integer> seen = new HashMap<>();
        while (N-- > 0) {
            int[] nxtCells = new int[8];
            seen.put(Arrays.toString(cells), N);
            for (int i = 1; i < 7; ++i) {
                nxtCells[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            }
            cells = nxtCells;
            if (seen.containsKey(Arrays.toString(cells))) {
                N %= seen.get(Arrays.toString(cells)) - N + 1;
            }
        }
        return cells;
    }
}
