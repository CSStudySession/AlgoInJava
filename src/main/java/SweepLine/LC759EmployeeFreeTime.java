package SweepLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * We are given a list schedule of employees, which represents the working time for each employee.
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * Return the list of finite intervals representing common,
 * positive-length free time for all employees, also in sorted order.
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals,
 * not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 * Example 1:
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 *
 * Example 2:
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 *
 * Constraints:
 * 1 <= schedule.length , schedule[i].length <= 50
 * 0 <= schedule[i].start < schedule[i].end <= 10^8
 */
public class LC759EmployeeFreeTime {

    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<Interval> result = new ArrayList<>();
        List<Interval> timeLine = new ArrayList<>();
        avails.forEach(e -> timeLine.addAll(e));
        // 按照起点给区间排序
        Collections.sort(timeLine, ((a, b) -> a.start - b.start));

        // 先拿第一个区间作为比较
        Interval temp = timeLine.get(0);
        for(Interval each : timeLine) {
            /*
            tmp: ---
            each:             ----
            free:   |可行解之一| [tmp.end, each.start]
             */
            if(temp.end < each.start) {
                result.add(new Interval(temp.end, each.start));
                temp = each;
            } else {
                // 有overlap 选end大的宽展
                temp = temp.end < each.end ? each : temp;
            }
        }
        return result;
    }

    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start,int _end) {
            start = _start;
            end = _end;
        }
    }
}
