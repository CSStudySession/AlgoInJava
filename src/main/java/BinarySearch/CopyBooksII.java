package BinarySearch;


/**
 * Given n books( the page number of each book is the same) and an array of integer with size k means k people to copy the book and the i th integer is the time i th person to copy one book). You must distribute the continuous id books to one people to copy. (You can give book A[1],A[2] to one people, but you cannot give book A[1], A[3] to one people, because book A[1] and A[3] is not continuous.) Return the number of smallest minutes need to copy all the books.
 *
 * Example
 * Given n = 4, array A = [3,2,4], .
 *
 * Return 4( First person spends 3 minutes to copy book 1, Second person spends 4 minutes to copy book 2 and 3, Third person spends 4 minutes to copy book 4. )
 */
public class CopyBooksII {

    public int copyBooksII(int n, int[] times) {

        if (times == null || times.length == 0) return 0;

        int start = 0, end = 0;

        for (int time : times) {
            end = Math.max(end, time);
        }

        end *= n;

        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (get(times, mid) >= n) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (get(times, end) >= n) {
            return end;
        }

        return start;
    }


    public int get(int[] times, int threshold) {
        if (times.length == 0) return 0;

        int sum = 0;

        for (int time : times) {
            sum += threshold / time;
        }

        return sum;
    }
}
