package sorting;

public class BubbleSort {

    /* Change the bubble sort algorithm.
       If the array is sorted(before n-1 run) so no need to run through array n-1 times.
       No need to run always till the end of array, after each run the biggest number stands last.
    */

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
