package sorting;

public class InsertionSort {

    /* 	Normal insertion sort runs from left to write, finds small number and puts it to its place.
        Your task to change it vice versa. So you run from right to left and search for big number.
        After you found your number you search for its place at right side and put it.
    */

    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = n - 2; i >= 0; i--) {
            int key = arr[i];
            int j = i + 1;

            while (j < n && arr[j] < key) {
                arr[j - 1] = arr[j];
                j++;
            }
            arr[j - 1] = key;
        }
    }
}
