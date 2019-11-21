package BinarySearch;


/**
 * Given n books and the ith book has A[i] pages. You are given k people to copy the n books.
 *
 * n books list in a row and each person can claim a continous range of the n books. For example one copier can copy the books from ith to jth continously, but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).
 *
 * They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. What's the best strategy to assign books so that the slowest copier can finish at earliest time?
 *
 * Example
 * Given array A = [3,2,4], k = 2.
 *
 * Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person spends 4 minutes to copy book 3. )
 */
public class CopyBooks {

    public int copyBooks(int[] pages, int k) {

        if (pages == null || pages.length == 0) {
            return 0;
        }

        int start = 0, end = 0;

        for (int page : pages) {
            end += page;
            start = Math.max(start, page);
        }

        while (start + 1 < end) {
            int mid = (start + end) / 2;

            if (people(pages, mid) > k) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (people(pages, start) <= k) {
            return start;
        }

        return end;
    }


    public int people(int[] pages, int min) {

        if (pages.length == 0) return 0;

        // 1st careless : people should be initialized as 1
        int sum = 0, people = 1;

        for (int page : pages) {
            if (sum + page > min) {
                sum = 0;
                people++;
            }

            sum += page;
        }

        return people;
    }
}
