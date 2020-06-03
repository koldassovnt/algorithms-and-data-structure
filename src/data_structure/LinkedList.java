package data_structure;

class Node<T> {
    private Node<T> next = null;
    private T data;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
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

public class LinkedList<T> {
    private Node<T> head = null;
    private int size = 0;

    public void add(T data) {
        if (head == null)
            head = new Node<>(data);
        else {
            Node<T> last = head;

            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(new Node<T>(data));
        }
        size++;
    }

    public void add(int position, T data) {
        //create new node.
        Node<T> node = new Node<>(data);
        node.setNext(null);

        if (this.head == null) {
            //if head is null and position is zero then exit.
            if (position != 0) {
                return;
            } else { //node set to the head.
                this.head = node;
                size++;
            }
        }

        if (head != null && position == 0) {
            node.setNext(this.head);
            this.head = node;
            size++;
            return;
        }

        Node<T> current = this.head;
        Node<T> previous = null;

        int i = 0;

        while (i < position) {
            previous = current;
            current = current.getNext();

            if (current == null)
                break;

            i++;
        }

        node.setNext(current);
        previous.setNext(node);
        size++;
    }

    public void addAll(LinkedList<T> list2) {
        Node<T> last = this.head;
        Node<T> newNode = list2.head;

        if (last == null)
            last = newNode;
        else
            while (last != null) {
                last = last.getNext();
            }
        if (newNode != null) {
            last.setNext(newNode);
            size += list2.size();
        }

    }

    public void addFirst(T data) {
        Node<T> current = this.head;
        Node<T> newNode = new Node<>(data);

        newNode.setNext(current);
        this.head = newNode;
        size++;
    }

    public void clear() {
        Node<T> previous = this.head;
        Node<T> current = this.head;

        while (current != null) {
            previous = current;
            current = current.getNext();
            previous = null;
        }
        size = 0;
    }

    public LinkedList<T> clone() {
        LinkedList<T> cloneList = new LinkedList<>();
        Node<T> cur = head;
        for (int i = 0; i < size(); i++) {
            cloneList.add(cur.getData());
            cur = cur.getNext();
        }

        return cloneList;
    }

    public T get(int idx) {
        int i = 0;
        Node<T> previous = this.head;
        Node<T> current = this.head;

        if (idx == 0)
            return current.getData();

        if (idx > size)
            return null;

        while (i != idx) {
            previous = current;
            current = current.getNext();
            i++;
        }
        return current.getData();
    }

    public T getFirst() {
        return this.head.getData();
    }

    public T getLast() {
        Node<T> last = this.head;
        Node<T> previous = null;

        while (last != null)
            previous = last;
        last = last.getNext();

        return previous.getData();
    }

    public int indexOf(T data) {
        Node<T> current = this.head;
        int idx = 0;

        while (current != null) {
            if (current.getData().equals(data))
                return idx;
            else {
                current = current.getNext();
                idx++;
            }
        }
        return -1;
    }

    public void remove() {
        Node<T> current = this.head;
        Node<T> newHead = null;
        newHead = current.getNext();
        this.head = newHead;
        current = null;
        size--;
    }

    public void remove(int idx) {
        Node<T> current = this.head;
        Node<T> prev = null;

        if (idx == 0) {
            Node<T> temp = current.getNext();
            current = null;
            head = temp;
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

    public void remove(T data) {
        Node<T> cur = head;

        if (head.getData().equals(data)) {
            head = head.getNext();
        } else {
            while (cur.getNext() != null) {
                if (cur.getNext().getData().equals(data)) {
                    cur.setNext(cur.getNext().getNext());
                    break;
                }
                cur = cur.getNext();
            }
        }
        size--;
    }

    public void set(int idx, T data) {
        Node<T> current = this.head;

        if (idx == 0)
            current.setData(data);

        int i = 0;
        while (i < idx) {
            current = current.getNext();
            i++;
        }
        current.setData(data);
    }

    public void print() {
        Node<T> current = head;
        String str = "";

        while (current != null) {
            str += current.getData() + " \n";
            current = current.getNext();
        }

        System.out.println(str);

    }

    public int size() {
        return size;
    }

}
