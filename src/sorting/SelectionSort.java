package sorting;

public class SelectionSort {

    /*Implement Selection sort using recursion.*/

    static int minIndex(int[] arr, int i, int j) {
        if (i == j)
            return i;

        int minId = minIndex(arr, i + 1, j);

        return (arr[i] < arr[minId]) ? i : minId;
    }

    public static void selectionSort(int[] arr, int startID, int endID) {
        if (startID == endID)
            return;

        int minID = minIndex(arr, startID, endID);

        int temp = arr[minID];
        arr[minID] = arr[startID];
        arr[startID] = temp;

        selectionSort(arr, startID + 1, endID);
    }
}
