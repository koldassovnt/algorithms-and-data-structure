package sorting;


public class ShellSort {


    public static void shellsort(int[] arr, int len) {
        for (int i = len / 2; i > 0; i = i / 2) {
            for (int j = i; j < len; j++) {
                for (int k = j - i; k >= 0; k = k - i) {
                    if (arr[k + i] >= arr[k])
                        break;
                    else {
                        swap(arr, k, k + i);
                    }
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
