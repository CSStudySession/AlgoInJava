package sorting;

import common.CommonUtils;

public class QuickSort {

    private static void quickSort(int arr[], int left, int right){
        int index = partition(arr, left, right);
        if (left < index - 1){
            quickSort(arr, left, index - 1);
        }
        if (index < right){
            quickSort(arr, index, right);
        }
    }

    private static int partition(int arr[], int left, int right){
        int pivot = arr[(left + right) / 2];
        while (left <= right){
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;

            // Swap elements, and move left and right indices
            if (left <= right){
                CommonUtils.swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }

    public static void main(String[] args) {
        int[] arr = {9,3,5,6,8,1,4,2,4,6,7};
        sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
