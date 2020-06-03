package data_structure;

public class PriorityQueue<T extends Comparable<T>> {
    private int maxSize;
    private T[] queArray;
    private int nItems;

    public PriorityQueue(int s) {
        maxSize = s;
        queArray = (T[]) new Comparable[s];
        nItems = 0;
    }


    public void insert(T item) {
        int j;
        if (nItems == 0)
            queArray[nItems++] = item;

        else {
            for (j = nItems - 1; j >= 0; j--) {
                if (item.compareTo(queArray[j]) == 1)
                    queArray[j + 1] = queArray[j];
                else
                    break;
            }
            queArray[j + 1] = item;
            nItems++;
        }
    }

    public T remove() {
        return queArray[--nItems];
    }

    public T peek() {
        return queArray[nItems - 1];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }
}