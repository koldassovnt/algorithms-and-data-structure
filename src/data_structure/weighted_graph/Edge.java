package data_structure.weighted_graph;

public class Edge<V extends Comparable<V>>{
    private Vertex vertex;
    private int weight;

    public Edge(Vertex vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString(){
        return "( "+ vertex + ", " + weight + " )";
    }
}
