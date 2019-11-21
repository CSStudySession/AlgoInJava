package Microsoft;

public class findCelebrity {

    /**
     * This is a helper method provided
     * return true if i knows j
     * @param i
     * @param j
     * @return
     */
    public boolean knows(int i, int j) {
        return false;
    }

    public int find(int n) {
        int candidate = 0;
        for (int i = 1; i < n; ++i) {
            if (knows(i, candidate)) {
                candidate = i;
            }
        }

        for (int i = 0; i < n; ++i) {
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) {
                return -1;
            }
        }
        return candidate;
    }
}
