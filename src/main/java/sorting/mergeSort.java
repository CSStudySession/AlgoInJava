package sorting;

public class mergeSort {

    private static int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            res[k++] = left[i] < right[j] ? left[i++] : right[j++];
        }

        if (i != left.length) {
            while (k < res.length && i < left.length) {
                res[k++] = left[i];
            }
        }

        if (j != right.length) {
            while (k < res.length && j < right.length) {
                res[k++] = right[j++];
            }
        }

        return res;
    }

    private static int[] divide(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int start = 0, end = arr.length-1;
        int mid = (start + end) / 2;
        int[] left = new int[mid-start+1];
        System.arraycopy(arr, start, left, 0, left.length);

        int[] right = new int[end-mid];
        System.arraycopy(arr, mid+1, right, 0, right.length);
        left = divide(left);
        right = divide(right);
        return merge(left, right);
    }

    public static int[] sort(int[] arr) {
        return divide(arr);
    }

    public static void main(String[] args) {
        int[] arr = {9,3,5,6,8,1,4,2,4,6,7};
        int[] res = sort(arr);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
