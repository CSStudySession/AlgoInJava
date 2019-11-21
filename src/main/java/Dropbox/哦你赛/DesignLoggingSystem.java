package Dropbox.哦你赛;

public class DesignLoggingSystem {


    private int[] hits;
    private String[] ids;
    private int times[];
    private int timeWindow;

    public DesignLoggingSystem(int timeWindows) {
        this.hits = new int[timeWindows];
        this.ids = new String[timeWindows];
        this.times = new int[timeWindows];
        this.timeWindow = timeWindows;
    }

    public void log(String id, int timeStamp) {
        int index = timeStamp % timeWindow;
        if (ids[index] == null || !ids[index].equals(id)) {
            ids[index] = id;
            hits[index] = 1;
            times[index] = timeStamp;
        } else {
            hits[index]++;
        }
    }


    public int get(String id) {

        for (int i = 0; i < ids.length; ++i) {
            if (id.equals(ids[i])) {
                return hits[i];
            }
        }

        return 0;
    }

    // given a timestamp, get the total hit of last 5 mins
    public int get(int timeStamp, int timeBucket) {
        int count = 0;
        for (int i = 0; i < times.length; ++i) {
            if (timeStamp - times[i] < timeBucket) {
                count += hits[i];
            }
        }
        return count;
    }
}
