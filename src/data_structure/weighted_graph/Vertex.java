package data_structure.weighted_graph;

public class Vertex<V extends Comparable<V>> {
    private V data;
    private int weight;

    public Vertex(V data, int weight) {
        this.data = data;
        this.weight =weight;
    }

    public Vertex(V data) {
        this.data = data;
        weight = 1;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
