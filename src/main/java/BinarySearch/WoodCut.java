package BinarySearch;


/**
 * Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.
 *
 * Example
 * For L=[232, 124, 456], k=7, return 114.
 *
 * Challenge
 * O(n log Len), where Len is the longest length of the wood.
 *
 * Notice
 * You couldn't cut wood into float length.
 *
 * If you couldn't get >= k pieces, return 0.
 */


public class WoodCut {

    public int woodCut(int[] L, int k) {

        int start = 1, end = 0;

        for (int l : L) {
            end = Math.max(l, end);
        }


        while (start < end) {
            int mid = (start + end) / 2;
            if (getPieces(L, mid) >= k) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }

        if (getPieces(L, end) >= k) {
            return end;
        }

        if (getPieces(L, start) >= k) {
            return start;
        }

        return 0;

    }

    public int getPieces(int[] L, int length) {
        int count = 0;
        for (int l : L) {
            count += l / length;
        }
        return count;
    }
}
