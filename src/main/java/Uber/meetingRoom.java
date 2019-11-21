package Uber;

import common.Interval;

import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: true
 */
public class meetingRoom {

    public boolean canAttendMeetings(Interval[] intervals) {
        PriorityQueue<Interval> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.start));

        for (Interval i : intervals) {
            queue.offer(i);
        }

        int last = 0;
        while(!queue.isEmpty()) {
            Interval i = queue.poll();
            if (i.start >= last) {
                last = i.end;
            } else {
                return false;
            }
        }

        return true;
    }
}
