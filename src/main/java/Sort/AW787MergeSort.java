package Sort;

import java.util.Scanner;
/**
 * 给定你一个长度为n的整数数列
 * 请你使用归并排序对这个数列按照从小到大进行排序。
 * 并将排好序的数列按顺序输出。
 *
 * 输入格式
 * 输入共两行，第一行包含整数 n。
 * 第二行包含 n 个整数（所有整数均在1~109范围内），表示整个数列。
 *
 * 输出格式
 * 输出共一行，包含 n 个整数，表示排好序的数列。
 *
 * 数据范围
 * 1≤n≤100000
 *
 * 输入样例：
 * 5
 * 3 1 2 4 5
 * 输出样例：
 * 1 2 3 4 5
 */
public class AW787MergeSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        mergeSort(arr, 0, n - 1);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);

        // 临时数组, 用于临时存储 [lo,hi]区间内排好序的数据
        int[] tmp = new int[hi - lo + 1];
        int i = lo;
        int j = mid + 1;
        int k = 0;

        // 进行归并
        while (i <= mid && j <= hi) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        while (j <= hi) {
            tmp[k++] = arr[j++];
        }

        // copy back to original array
        for (i = lo, j = 0; i <= hi; i++, j++) {
            arr[i] = tmp[j];
        }

    }
}
