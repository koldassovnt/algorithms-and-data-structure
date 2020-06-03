package data_structure;

class SNode<T> {
    private SNode<T> next = null;
    private T data;

    public SNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public SNode<T> getNext() {
        return next;
    }

    public void setNext(SNode<T> next) {
        this.next = next;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", data=" + data +
                '}';
    }
}

public class SortedLinkedList<T extends Comparable<T>> {
    private SNode<T> head = null;
    private int size = 0;

    public void addSorted(T data) {
        SNode<T> newNode = new SNode<>(data);
        SNode<T> current = head;
        SNode<T> previous = null;
        while (current != null && data.compareTo(current.getData()) == 1) {
            previous = current;
            current = current.getNext();
        }
        if (previous == null) {
            head = newNode;
        } else {
            previous.setNext(newNode);
        }

        newNode.setNext(current);

        size++;
    }

    public void print() {
        SNode<T> curNode = this.head;
        while (curNode != null) {
            System.out.println(curNode.getData());
            curNode = curNode.getNext();
        }
    }

    public void remove(int idx) {
        SNode<T> current = this.head;
        SNode<T> prev = null;

        if (head == null)
            System.out.println("List is empty");

        else if (idx >= size)
            System.out.println("Out of bounds");

        else if (idx == 0) {
            SNode<T> temp = current.getNext();
            current = null;
            head = temp;
            size--;
        } else {
            int i = 0;
            while (i < idx) {
                prev = current;
                current = current.getNext();
                i++;
            }

            prev.setNext(current.getNext());
            current = null;
        }
        size--;
    }
}
