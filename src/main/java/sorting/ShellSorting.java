package sorting;



// unstable sorting


public class ShellSorting {

    public static void sort(int[] arr) {
        int n = arr.length, gap = n / 2;

        while (gap > 0) {
            for (int i = gap; i < n; ++i) {
                int tmp = arr[i];
                int j = i;
                while (j >= gap && arr[j-gap] > tmp) {
                    arr[j] = arr[j-gap];
                    j = j - gap;
                }
                arr[j] = tmp;
            }
            gap /= 2;
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
