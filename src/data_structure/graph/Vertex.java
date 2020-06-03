package data_structure.graph;

public class Vertex<V> implements Comparable<V> {
    private V value;
    private boolean wasVisited = false;
    private int common;  // only for task c

    public Vertex(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public int getCommon() {
        return common;
    }

    public void setCommon(int common) {
        this.common += common;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean isWasVisited() {
        return wasVisited;
    }

    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }

    @Override
    public String toString() {
        return "value=" + value;
    }

    @Override
    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    @Override
    public int compareTo(V o) {
        if (o instanceof Vertex) {
            return Integer.compare(this.common, ((Vertex) o).common);
        }

        return 0;
    }
}
