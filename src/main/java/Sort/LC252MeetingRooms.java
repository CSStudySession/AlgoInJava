package Sort;

import java.util.Arrays;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 *
 * Example 1:
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 *
 * Example 2:
 * Input: [[7,10],[2,4]]
 * Output: true
  */
public class LC252MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;

        Arrays.sort(intervals, (i0, i1) -> i0[0] - i1[0]);

        int curEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (curEnd > intervals[i][0]) return false;
            curEnd = intervals[i][1];
        }
        return true;
    }
}
