package Sort_OrderedMap;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 */
public class LC253MeetingRoomsII {

    /*
    method 1: treeMap
    遍历时间区间，对于起始时间，映射值自增1，对于结束时间，映射值自减1，
    然后定义结果变量 ret，和房间数 rooms，遍历 TreeMap，时间从小到大，房间数每次加上映射值，然后更新结果 ret，
    遇到起始时间，映射是正数，则房间数会增加，如果一个时间是一个会议的结束时间，也是另一个会议的开始时间，
    则映射值先减后加仍为0，并不用分配新的房间，而结束时间的映射值为负数更不会增加房间数
     */
    public int minMeetingRooms(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ret = 0;
        int cnt = 0;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
        }

        for (int time : map.keySet()) {
            cnt += map.get(time);
            // 这里一定要用打擂台的方式更新答案
            ret = Math.max(ret, cnt);
        }

        return ret;
    }

    /*
     method 2: 排序
     */
    public int minMeetingRooms2(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int ret = 0;
        int endPos = 0;
        for (int j = 0; j < intervals.length; j++) {
            if (start[j] < end[endPos]) ret++;
            else endPos++;
        }
        return ret;
    }

}
