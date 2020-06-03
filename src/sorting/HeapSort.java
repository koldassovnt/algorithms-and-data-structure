package sorting;

public class HeapSort {
    public static void heapSort(int[] arr) {

        buildHeap(arr);
        int sizeOfHeap = arr.length - 1;

        for (int i = sizeOfHeap; i > 0; i--) {
            swap(arr, 0, i);
            sizeOfHeap = sizeOfHeap - 1;
            heapify(arr, 0, sizeOfHeap);
        }
    }

    public static void buildHeap(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapify(arr, i, arr.length - 1);
        }
    }

    public static void heapify(int[] arr, int i, int size) {
        // below is formula to take left and right elements of certain el
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max;
        // comparing with left side
        if (left <= size && arr[left] > arr[i]) {
            max = left;
        } else {
            max = i;
        }
        // comparing with right side
        if (right <= size && arr[right] > arr[max]) {
            max = right;
        }
        // If max is not current node, exchange it with max of left and right child
        if (max != i) {
            swap(arr, i, max);
            heapify(arr, max, size);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
