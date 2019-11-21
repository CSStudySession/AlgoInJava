package sorting;

import common.CommonUtils;

public class HeapSort {

    private static void maxifyHeap(int[] arr, int start, int end) {
        int root = start;
        while(true) {
            int child = root*2 + 1;
            if (child > end) break;
            if (child + 1 <= end && arr[child] < arr[child+1]) {
                child++;
            }
            if (arr[root] < arr[child]) {
                CommonUtils.swap(arr, root,child);
                root = child;
            } else {
                break;
            }
        }
    }

    public static void sort(int[] arr) {
        int n = arr.length;
        int first = n / 2;

        for (int i = first; i >= 0; --i) {
            maxifyHeap(arr, i, n-1);
        }

        for (int i = n-1; i >= 0; --i) {
            CommonUtils.swap(arr, 0, i);
            maxifyHeap(arr, 0, i-1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {9,3,5,6,8,1,4,2,4,6,7};
        sort(arr);
        for (int i : arr) {
            System.out.println(i);
            System.out.println("-------");
        }
    }
}
