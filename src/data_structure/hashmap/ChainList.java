package data_structure.hashmap;

public class ChainList<K, V> {   // contains Entries
    private Entry<K, V> first;
    private int size;

    public ChainList() {
        first = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public Entry<K, V> getFirst() {
        return first;
    }

    public void insert(Entry<K, V> temp) {
        Entry<K, V> previous = null; // start from 1st element
        Entry<K, V> current = first;
        //till hte end
        while (current != null) {
            previous = current;
            current = current.getNext(); // go to next
        }
        if (previous == null) // insert as first element
            first = temp; // first --> new element
        else
            previous.setNext(temp); // prev --> new element
        temp.setNext(current); // new element --> current

        size++;
    }
    // -------------------------------------------------------------

    // -------------------------------------------------------------
    public void delete(K key) {
        Entry<K, V> previous = null; // start from 1st element
        Entry<K, V> current = first;
        //till hte end
        while (current != null && (!key.equals(current.getKey()))) { // or till key != current.key()
            previous = current;
            current = current.getNext(); // going to nex element
        }

        if (previous == null)          // if 1st element,
            first = first.getNext(); // change first
        else {
            assert current != null;
            previous.setNext(current.getNext()); // delete current element
        }
        size--;
    } // end delete()

    // -------------------------------------------------------------
    public Entry<K, V> find(K key) {
        Entry<K, V> current = first; // start from 1st element
        //till hte end
        while (current != null) {
            if (current.getKey().equals(key)) // if we found it
                return current; // return
            current = current.getNext(); // go to next element
        }
        return null; // element didnt find
    }

    // -------------------------------------------------------------
    public void displayList() {
        StringBuilder res = new StringBuilder();
        Entry<K, V> current = first;
        while (current != null) {
            res.append(current);
            res.append("            ");
            current = current.getNext();
        }
        System.out.println(res);
    }
}
