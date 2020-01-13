package Sort;

/**
 * Created by Aaron Liu on 1/9/20.
 */
public class TwoWayQuickSort {

    private static void quickSort(int[] arr, int l, int r) {
        // 递归结束条件
        if (l >= r) return;
        int index = partition(arr, l, r);
        quickSort(arr, l, index - 1);
        quickSort(arr, index + 1, r);
    }

    private static int partition(int[] arr, int lo, int hi) {
        int i = lo + 1;
        int j = hi;

        int pivot = arr[lo];

        while (i <= j) {
            while (i <= j && arr[i] < pivot) {
                i++;
            }

            while (i <= j && arr[j] > pivot) {
                j--;
            }

            if (i >= j) break;

            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, lo, j);

        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2,1,4,1,5,3,10,5};
        quickSort(arr, 0, arr.length  - 1);
        System.out.println(".");
    }

}
