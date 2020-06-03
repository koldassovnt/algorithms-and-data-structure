package data_structure.weighted_graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class WeightedGraph<V extends Comparable<V>> {
    private HashMap<Vertex, ArrayList<Edge<V>>> vertexList;
    private boolean isDirected;

    public WeightedGraph(boolean isDirected) {
        this.vertexList = new HashMap<Vertex, ArrayList<Edge<V>>>();
        this.isDirected = isDirected;
    }

    public WeightedGraph() {
        this.vertexList = new HashMap<Vertex, ArrayList<Edge<V>>>();
        this.isDirected = false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addWeightedVertex(V element, int weight) {
        Vertex v = new Vertex(element, weight);
        vertexList.putIfAbsent(v, new ArrayList<Edge<V>>());
    }

    public void addVertex(V element) {
        Vertex v = new Vertex(element);
        vertexList.putIfAbsent(v, new ArrayList<Edge<V>>());
    }

    private Vertex getOrigVertex(V temp) {
        Vertex v1 = new Vertex(temp);

        for (Vertex v : vertexList.keySet()) {
            if (v.getData().equals(temp))
                v1 = v;
        }
        return v1;
    }

    public void addWeightedEdge(V from, V to, int weight) {
        Vertex source = getOrigVertex(from);  // get existed one or new one
        Vertex dest = getOrigVertex(to); // get existed one or new one

        if (!vertexList.containsKey(source)){  // if source is new one
            addVertex(from); // add it
            source = getOrigVertex(from);  // get the added
        }
        if (!vertexList.containsKey(dest)){ // if destination is new one
            addVertex(to);  // add it
            dest = getOrigVertex(to); // get the added
        }

        vertexList.get(source).add(new Edge<>(dest, weight));  // add edge: from --> to

        if (!isDirected){  // if it's undirected
            vertexList.get(dest).add(new Edge<>(source, weight)); // add edge to --> from
        }
    }

    public void addEdge(V from, V to) {
        addWeightedEdge(from, to, 1);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void printKeys() {
        String forPrint = "";
        for (Vertex v : vertexList.keySet()){
            forPrint += v.getData();
            forPrint += "\n";
        }
        System.out.println(forPrint);
    }

    public void printEdgeOfKey(V el){
        Vertex key = getOrigVertex(el);
        String forPrint = "";
        for (Edge ed : vertexList.get(key)){
            forPrint += ed.getVertex().getData() + " " + ed.getWeight();
            forPrint += "\n";
        }

        System.out.println(forPrint);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void maxProfitFromAcc() {
        ArrayList<Vertex> vertices = new ArrayList<>();  // vertexes
        ArrayList<Integer> subscribers = new ArrayList<>();  // common subscribers

        for (Vertex v : vertexList.keySet()) {  // running over all keys
            vertices.add(v); // adding into vertices
            subscribers.add(getCountOfCommonSubs(v)); // adding own and close friends followers in the same id as 'vertices'
        }

        int max = Collections.max(subscribers);   // getting max element in subscribers array
        int idx = subscribers.indexOf(max);  // getting id of max value element

        System.out.println("Account with max profit is " + vertices.get(idx).getData() + "its all viewers are " + max + " people");
    }


    private boolean existsInAdjList(Vertex root, Vertex val) {
        for (Edge ed : vertexList.get(root)) {  // running over all elements of given root
            if (ed.getVertex().getData().compareTo(val.getData()) == 0)   // if our given val equals to one of root's elements
                return true; // true
        }
        return false;
    }

    private int getCountOfCommonSubs(Vertex acc) {
        int count = acc.getWeight();

        for (Vertex v : vertexList.keySet()){  // running through all keys
            if (v.getData().compareTo(acc.getData()) == 0)  // if given acc == v
                continue; // skip it

            if (existsInAdjList(v, acc)) // if our acc exists in edge of other acc
                count += v.getWeight(); // count will add weight of this acc
        }

        return count;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
}
