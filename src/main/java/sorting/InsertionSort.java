package sorting;

public class InsertionSort {

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int tmp = arr[i];
            int index = i;

            for(int j = i-1; j >= 0; --j) {
                if (tmp < arr[j]) {
                    arr[j+1] = arr[j];
                    index = j;
                } else {
                    break;
                }
            }

            arr[index] = tmp;
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
