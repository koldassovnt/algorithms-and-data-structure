package data_structure.hashmap;

public class Entry<K, V> implements Comparable<K> {
    private K key;
    private V value;
    private Entry<K, V> next;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Entry<K, V> getNext() {
        return next;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }

    public Entry() {
    }

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(K o) {
        if (key instanceof String && o instanceof String) {
            if (((String) key).compareTo((String) o) > 0)
                return 1;
            else if (((String) key).compareTo((String) o) < 0)
                return -1;
            else return 0;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "value=" + value;
    }
}
