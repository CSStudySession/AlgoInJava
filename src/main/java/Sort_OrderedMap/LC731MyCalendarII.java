package Sort_OrderedMap;

import java.util.Map;
import java.util.TreeMap;

/**
 * Implement a MyCalendarTwo class to store your events.
 * A new event can be added if adding the event will not cause a triple booking.
 *
 * Your class will have one method, book(int start, int end).
 * Formally, this represents a booking on the half open interval [start, end),
 * the range of real numbers x such that start <= x < end.
 *
 * A triple booking happens when three events have some non-empty intersection
 * (ie., there is some time that is common to all 3 events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully
 * without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 * Example 1:
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 *
 * Explanation:
 * The first two events can be booked.  The third event can be double booked.
 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
 * the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 * Note:
 * The number of calls to MyCalendar.book per test case will be at most 1000.
 * In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 *
 * 思路: 扫描线
 * 把一个区间看成两个实践 start事件用值1表示 end事件用值-1表示 这样表示的好处是 扫描过去 结果还是0
 * 符合事件开始结束的标志
 * 每次Insert一个新的区间 先无脑加入集合中 然后遍历整个集合 看是否出现了大于2的时刻 如果有 证明这个区间不合法
 * 然后删去即可
 */
public class LC731MyCalendarII {

    private TreeMap<Integer, Integer> map;

    public LC731MyCalendarII() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int count = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            // 统计某个时刻对应事件的值
            count += entry.getValue();
            // 大于2 该时刻有两个以上的开始事件 不合法
            if (count > 2) {
                map.put(start, map.get(start) - 1);
                if (map.get(start) == 0) {
                    map.remove(start);
                }

                map.put(end, map.get(end) + 1);
                if (map.get(end) == 0) {
                    map.remove(end);
                }

                return false;
            }
        }

        return true;
    }

}
