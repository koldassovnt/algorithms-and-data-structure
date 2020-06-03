package data_structure;

public class MyArray<T extends Comparable<T>> {
    private T[] arr;
    private int size;
    private int n = 0;

    public MyArray() {
        this(10);
    }

    public MyArray(int size) {
        arr = (T[]) new Comparable[size];
        this.size = size;
    }

    public T get(int i) {
        return arr[i];
    }

    public void add(T el) {
        if (n == size) {

            copyTo(arr, size + 10);
            this.size += 10;
        }

        arr[n] = el;
        n++;
    }

    public void remove(int k) {
        if (n - 1 - k >= 0) System.arraycopy(arr, k + 1, arr, k, n - 1 - k);
        n--;
        size = n;

        copyTo(arr, size);
    }

    private void copyTo(T[] arr, int size) {
        T arr2[] = (T[]) new Comparable[size];

        if (n >= 0) System.arraycopy(arr, 0, arr2, 0, n);

        this.arr = arr2;

    }

    public int size() {
        return size;
    }

    public void sort() {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

    }

    public void show() {
        for (int i = 0; i < n; i++) {
            System.out.print(this.get(i) + " ");
        }
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            str.append(arr[i]).append(" \n");
        }
        return str.toString();
    }
}

