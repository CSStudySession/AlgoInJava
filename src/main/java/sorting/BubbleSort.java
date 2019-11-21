package sorting;

public class BubbleSort {

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {9,3,5,6,8,1,4,2,4,6,7};
        sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }

    }
}
