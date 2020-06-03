package data_structure;


public class ArrayDeque<T> {
    private T[] array;
    private int head = 0;
    private int tail = 0;

    private int size = 0;
    private int elementSize = 0;

    public ArrayDeque() {
        size = 16;
        array = (T[]) new Comparable[size];
    }

    public ArrayDeque(int size) {
        this.size = size;
        array = (T[]) new Comparable[size];
    }

    public int size() {
        return this.elementSize;
    }

    public boolean isEmpty() {
        return elementSize == 0;
    }

    public T first() {
        return isEmpty() ? null : array[head];
    }

    public T last() {
        return isEmpty() ? null : array[tail];
    }

    public void doubleCapacity() {
        int newSize = size * 2;
        T[] temp = (T[]) new Comparable[newSize];

        for (int i = 0; i < size; i++) {
            temp[i] = array[i];
            head = (head + 1) % elementSize;
        }
        head = 0;
        tail = elementSize;
        array = temp;
        size = newSize;
    }

    public void addFirst(T data) {
        if (elementSize == size)
            doubleCapacity();

        if (isEmpty()) {
            array[head] = data;
        } else {
            head = (head - 1 + size) % size;
            array[head] = data;
        }
        elementSize++;
    }

    public void addLast(T data) {
        if (elementSize == size)
            doubleCapacity();

        if (isEmpty()) {
            array[tail] = data;
        } else {
            tail = (tail + 1) % size;
            array[tail] = data;
        }
        elementSize++;
    }

    public T removeFirst() {
        if (isEmpty())
            return null;

        T returner = array[head];
        array[head] = null;

        head = (head + 1) % size;
        elementSize--;

        return returner;
    }

    public T removeLast() {
        if (isEmpty())
            return null;

        T returner = array[tail];
        tail = (tail - 1 + size) % size;
        elementSize--;

        return returner;
    }
}
