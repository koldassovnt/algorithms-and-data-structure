package data_structure;

class DNode<T> {
    private DNode<T> next = null;
    private DNode<T> prev = null;
    private T data;

    public DNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public DNode<T> getNext() {
        return next;
    }

    public DNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DNode<T> prev) {
        this.prev = prev;
    }

    public void setNext(DNode<T> next) {
        this.next = next;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DNode{" +
                "next=" + next +
                ", prev=" + prev +
                ", data=" + data +
                '}';
    }
}

public class DoubleLinkedList<T> {

    private DNode<T> head = null;
    private DNode<T> tail = null;
    private int size = 0;

    public void add(T data, boolean istail) {
        if (istail) {
            if (tail == null) {
                tail = new DNode<>(data);
                head = tail;
            } else {
                DNode<T> temp = new DNode<>(data); // new node
                DNode<T> prevTail = tail; // previous tail
                tail = temp; // setting new tail
                prevTail.setNext(tail); // connect last tail to the new
                tail.setPrev(prevTail);
            }
            size++;
        } else {
            if (head == null) {
                head = new DNode<>(data);
                tail = head;
            } else {
                DNode<T> temp = new DNode<>(data); // new node
                DNode<T> prevHead = head;
                temp.setNext(prevHead); // adding new node to the list
                head = temp; // setting new head
                head.setPrev(temp);
            }
            size++;
        }
    }


    private void addFromTail(DNode<T> temp, int idx) {
        int decrease = size - 1;

        DNode<T> current = this.tail;
        DNode<T> prev = this.tail.getPrev();
        ;

        while (decrease >= idx) {
            prev = current;
            current = current.getPrev();
            decrease--;
        }

        temp.setNext(prev);
        temp.setPrev(current);
        current.setNext(temp);
        prev.setPrev(temp);
        size++;
    }

    private void addFromHead(DNode<T> temp, int idx) {
        int increase = 0;

        DNode<T> current = this.head;
        DNode<T> prev = null;

        while (increase < idx) {
            prev = current;
            current = current.getNext();
            increase++;
        }

        temp.setNext(current);
        temp.setPrev(prev);
        prev.setNext(temp);
        current.setPrev(temp);

        size++;
    }

    private boolean cheackFromWhere(int idx) {
        boolean fromHead;
        if ((size - idx) >= idx)
            return fromHead = true;
        else
            return fromHead = false;
    }

    public void add(int idx, T data) {

        DNode<T> temp = new DNode<>(data);
        temp.setNext(null);

        if (this.head == null) {
            //if head is null and position is zero then exit.
            if (idx != 0) {
                return;
            } else { //node set to the head.
                this.head = temp;
                this.tail = temp;
                size++;
            }
        } else if (tail != null && idx == size) {
            DNode<T> cur = tail;
            temp.setPrev(cur);
            cur.setNext(temp);
            tail = temp;
            size++;
            return;
        } else if (idx == 0) {
            DNode<T> cur = head;
            temp.setNext(cur);
            cur.setPrev(temp);
            this.head = temp;
            size++;
            return;
        }

        if (cheackFromWhere(idx))
            addFromHead(temp, idx);
        else
            addFromTail(temp, idx);

    }

    private T getFromTail(int idx) {
        int decrease = size - 1;

        DNode<T> current = this.tail;
        DNode<T> prev = this.tail.getPrev();
        ;

        while (decrease >= idx) {
            prev = current;
            current = current.getPrev();
            decrease--;
        }
        return prev.getData();
    }

    private T getFromHead(int idx) {
        int increase = 0;

        DNode<T> current = this.head;
        DNode<T> prev = null;

        while (increase < idx) {
            prev = current;
            current = current.getNext();
            increase++;
        }

        return current.getData();
    }

    public T get(int idx) {
        if (idx == 0)
            return head.getData();
        else if (idx == size - 1)
            return tail.getData();
        else if (idx > size)
            return null;

        if (cheackFromWhere(idx)) { // if true, from head
            return getFromHead(idx);
        } else // from tail
        {
            return getFromTail(idx);
        }
    }

    public T getFirst() {
        return head.getData();
    }

    public T getLast() {
        return tail.getData();
    }

    public void remove(boolean ishead) {
        if (size == 0)
            System.out.println("Empty list");

        else if (ishead) {
            DNode<T> current = head;
            head = current.getNext();
            head.setPrev(null);
        } else {
            DNode<T> current = tail;
            tail = current.getPrev();
            tail.setNext(null);
        }
        size--;
    }

    private void removeFromHead(int idx) {
        int increase = 0;

        DNode<T> current = this.head;
        DNode<T> prev = null;

        while (increase < idx) {
            prev = current;
            current = current.getNext();
            increase++;
        }

        prev.setNext(current.getNext());
        current.getNext().setPrev(prev);
        current = null;

        size--;
    }

    private void removeFromTail(int idx) {
        int decrease = size - 1;

        DNode<T> current = this.tail;
        DNode<T> prev = this.tail.getPrev();

        while (decrease >= idx) {
            prev = current;
            current = current.getPrev();
            decrease--;
        }

        current.setNext(prev.getNext());
        prev.getNext().setPrev(current);
        prev = null;
        size--;
    }

    public void remove(int idx) {
        if (size == 0)
            System.out.println("Empty list");
        else if (idx == 0)
            remove(true);
        else if (idx == (size - 1))
            remove(false);
        else if (idx >= size)
            System.out.println("index doesnt exist");

        else if (cheackFromWhere(idx)) {  // if true, from head
            removeFromHead(idx);
        } else {  // from tail
            removeFromTail(idx);
        }
    }

    private void setFromHead(int idx, T data) {
        int increase = 0;

        DNode<T> current = this.head;
        DNode<T> prev = null;

        while (increase < idx) {
            prev = current;
            current = current.getNext();
            increase++;
        }
        current.setData(data);

    }

    private void setFromTail(int idx, T data) {
        int decrease = size - 1;

        DNode<T> current = this.tail;
        DNode<T> prev = this.tail.getPrev();

        while (decrease >= idx) {
            prev = current;
            current = current.getPrev();
            decrease--;
        }
        prev.setData(data);
    }

    public void set(int idx, T data) {
        DNode<T> temp = new DNode<>(data);
        if (head == null) {
            if (idx != 0)
                return;
            else {
                head = temp;
                tail = temp; // change set
            }
        } else if (cheackFromWhere(idx))
            setFromHead(idx, data);
        else
            setFromTail(idx, data);
    }


    public void print(boolean toTail) {
        StringBuilder str = new StringBuilder();

        if (toTail) {
            DNode<T> current = head;
            while (current != null) {
                str.append(current.getData()).append(" \n");
                current = current.getNext();
            }
        }
        System.out.println(str);
    }

    public int size() {
        return size;
    }

}
