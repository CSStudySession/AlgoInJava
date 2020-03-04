package greedy;

/**
 * Given a char array representing tasks CPU need to do.
 * It contains capital letters A to Z where different letters represent different tasks.
 * Tasks could be done without original order. Each task could be done in one interval.
 * For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two same tasks,
 * there must be at least n intervals that CPU are doing different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 * Example:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 *
 * Note:
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 *
 * 思路:贪心的分配出现最多的任务
 * 假如A任务出现次数最多 为k次 n == 3 按照下面的分配方式去分配
 *
 * (n+1)
 * <-->
 * ABCx ABCx ... ABDx   AB
 * <---------------->   <->
 *     (k-1)group      lenEnd
 * 由于有间隔限制 两组A和A之间必须有n个间隔 所以每组的长度为(n+1)
 * 最后一组的长度lenEnd为所有跟A频率相同的任务数 由于最后一组做完就没了 所以不需要有间隔
 * 根据上面的图 总结出ans = (k-1) * (n+1) + lenEnd
 * 除了上面这种情况外 还有一种情况是 tasks.length > ans 直观理解是单个出现的任务很多 比如QWERTYU,n==1
 * 这样不需要额外插入任何间隔 直接按顺序执行所有任务即可
 * 所以最后的答案是 Math.max(ans, tasks.length)
 */
public class LC621TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        int max = 0;

        for (int i = 0; i < tasks.length; i++) {
            freq[tasks[i] - 'A']++;
            max = Math.max(max, freq[tasks[i] - 'A']);
        }

        int maxCnt = 0;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] == max) {
                maxCnt++;
            }
        }

        int ans = (max - 1) * (n + 1) + maxCnt;
        return ans >= tasks.length ? ans : tasks.length;
    }

}
