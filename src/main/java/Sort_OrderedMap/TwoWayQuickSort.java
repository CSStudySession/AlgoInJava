package Sort_OrderedMap;

/**
 * 快速排序要点:
 * 1. 边界条件 当(l<=r)时 不需要排序 直接返回
 * 2. partition操作 选择中点位置为pivot 然后返回一个数组 数组里存的是两个分割点[right, left]
 *    分割点满足条件: right左边的所有元素都小于等于pivot left右边的值都大于等于pivot
 * 3. 递归sort[lo, right] [left, hi] 注意这里划分的要点是 划分出来的左右区间不能有交集!
 *
 * e.g: [3,5,2,3,4,3,6] -> [3, 3, 2, 5, 4, 3, 6]
 *                                r  l
 *  all nums in [lo,l) <= pivot, all nums (r, hi] >= pivot
 */
public class TwoWayQuickSort {

    public void quickSort(int[] arr, int l, int r) {
        // 递归结束条件
        if (l >= r) return;
        int[] indices = partition(arr, l, r);
        quickSort(arr, l, indices[0]);
        quickSort(arr, indices[1], r);
    }

    private int[] partition(int[] arr, int lo, int hi) {
        int i = lo, j = hi;
        int mid = lo +  (hi - lo) / 2;
        int pivot = arr[mid];

        while (i <= j) {
            while (i <= j && arr[i] < pivot) {
                i++;
            }

            while (i <= j && arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                swap(arr, i, j);
            }
        }

        return new int[] {j, i};
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        TwoWayQuickSort inst = new TwoWayQuickSort();
        int[] arr = {2,1,4,1,5,3,10,5};
        inst.quickSort(arr, 0, arr.length  - 1);
        System.out.println(".");
    }

}
