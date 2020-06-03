package data_structure.hashmap;


import java.util.Arrays;
import java.util.HashSet;

public class MyHashMap<K, V> {
    private ChainList<K, V>[] bucket;  // each element contains of ChainList (analog of LinkedList)
    private int capacity, elSize;
    private double loadFactor;
    private static final int MAX_LIST_LENGTH = 2;  // formula is = 1.0/(1.0-loadFactor) but I choose 2

    public MyHashMap(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.bucket = new ChainList[capacity];
        for (int j = 0; j < capacity; j++) // filling array in
            bucket[j] = new ChainList<>(); // with chainlist
    }

    public MyHashMap() {  // default constructor
        this.capacity = 16;
        this.loadFactor = 0.6;
        this.bucket = new ChainList[capacity];
        for (int j = 0; j < capacity; j++)
            bucket[j] = new ChainList<>();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public int size() { // return how many elements in hashMap
        return elSize;
    }

    public int getCapacity() { // returns capacity
        return capacity;
    }

    public boolean isEmpty() {
        return elSize == 0;
    } // check for empty or not

    private boolean isLoad() {  // check loadFactor
        return (((double) elSize / (double) capacity)) >= loadFactor;
    }

    private int hashFunc1(int hashCode) {
        return (hashCode % capacity);
    }  // for chaining hash

    public static boolean isPrime(int n) { // check for prime or not
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int hashFunk2(int hashCode) { // for double hashing
        int prime = -1;
        for (int i = capacity - 1; i >= 0; i--) {
            if (isPrime(i)) {
                prime = i;
                break;
            }
        }

        return prime - hashCode % prime;
    }

    public void clear() {  // makes hashMap empty
        Arrays.fill(bucket, null);
        elSize = 0;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    public HashSet<K> keySet() {  // returns all keys in hashMap
        HashSet<K> keys = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (bucket[i] != null) {    // if element in this id is not null
                Entry<K, V> current = bucket[i].getFirst();  // we creates template entry
                while (current != null) { // till our current becomes null
                    keys.add(current.getKey()); // adding key
                    current = current.getNext(); // go to next
                }
            }
        }

        return keys;
    }

    public HashSet<V> valueSet() { // returns all values in hashMap
        HashSet<V> values = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (bucket[i].getFirst() != null) { // if element in this id is not null
                Entry<K, V> current = bucket[i].getFirst(); // we creates template entry
                while (current != null) // till our template becomes null
                {
                    values.add(current.getValue()); // adding value
                    current = current.getNext(); // go to next
                }
            }
        }
        return values;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Entry<K, V> findElementByKey(K key) {
        for (int i = 0; i < capacity; i++) {

            // if element doesnt have next entry
            if (bucket[i].getFirst() != null) {
                if (bucket[i].getFirst().equals(key)) {
                    return bucket[i].getFirst();
                }
                // if element has next entry
                else if (bucket[i].getFirst().getNext() != null) {
                    Entry<K, V> temp = bucket[i].find(key);
                    if (temp != null)
                        return temp;
                }
            }
        }
        return null;
    }

    public V get(K key) {  // we get value by given key
        if (key == null) return null;

        Entry<K, V> entry = findElementByKey(key);

        if (entry != null) {
            return entry.getValue();
        }

        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public void put(K key, V value) {
        if (key == null || value == null)
            System.out.println("Input is incorrect!");

        Entry<K, V> newOne = new Entry<>(key, value); // new Entry
        insert(newOne);

        if (isLoad()) rehash();  // after adding check loadFactor
    }

    public void insert(Entry<K, V> temp) {
        int idx = hashFunc1(temp.getValue().hashCode());  // getting hash code and then getting its id.

        if (bucket[idx] == null) // if element in the id is null
            bucket[idx].insert(temp);

        // if in one idx we got 2 chains, take another idx
        if (bucket[idx].getSize() >= MAX_LIST_LENGTH && bucket[idx].getFirst() != null) {
            int stepSize = hashFunk2(temp.getValue().hashCode());

            while (bucket[idx].getFirst() != null) {
                idx += stepSize; // Adding steps
                idx %= capacity; // Returning back
            }
        }
        bucket[idx].insert(temp); // inserting in position idx
        elSize++;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public void rehash() {
        ChainList<K, V>[] temp = bucket;  // store old bucket
        int oldCapacity = capacity; // store old capacity

        bucket = new ChainList[capacity * 2]; //old bucket becomes new one

        for (int i = 0; i < capacity * 2; i++) {
            bucket[i] = null;  // filling with nulls
            bucket[i] = new ChainList<>(); // then create certain ChainList(LinkedList)
        }
        capacity *= 2; // new capacity
        elSize = 0;

        for (int i = 0; i < oldCapacity; i++) {

            if (temp[i].getFirst() != null) {  // if element in this id is not null
                // head of the chain at that index
                Entry<K, V> head = temp[i].getFirst();  // template entry

                while (head != null) {  // till the end
                    K key = head.getKey();
                    V val = head.getValue();
                    // calling the insert function for each node in temp
                    // as the new list is now the bucketArray
                    put(key, val);
                    head = head.getNext();
                }
            }
        }
    }

    private Entry<K, V> find(Entry<K, V> temp) {
        int idx = hashFunc1(temp.getValue().hashCode());  // getting hash code and then getting its id
        return bucket[idx].find(temp.getKey());  // in certain id we got chainList, so , we search in it, then return
    }

    public void delete(K key) {
        int idx = hashFunc1(get(key).hashCode());  // getting hash code and then getting its id. method get() returns Entry
        bucket[idx].delete(key); // deleting in chainList
        elSize--;
    }

    public void print() {
        if (elSize == 0) {
            System.out.println("HashMap is empty");
        } else {
            for (int j = 0; j < capacity; j++) // For each id
            {
                System.out.print(j + " "); // output id
                bucket[j].displayList(); // output each chainList
            }
        }
    }
}


