package data_structure.graph;

import java.util.*;

public class Graph<V> {
    private Map<Vertex, List<Vertex>> vertexList;
    private boolean isDirected;

    ///////////////////////////////////////////////////////////////////////////////////////

    public Graph() {
        vertexList = new HashMap<>();
        isDirected = false;
    }

    private Graph(HashMap<Vertex, List<Vertex>> vertexList) {
        this.vertexList = vertexList;
    }

    public Graph(boolean isDirected) {
        vertexList = new HashMap<>();
        this.isDirected = isDirected;
    }

    public Map<Vertex, List<Vertex>> getVertexList() {
        return vertexList;
    }


    private Vertex getOrigVertex(V temp) {
        Vertex v1 = new Vertex(temp);

        for (Vertex v : vertexList.keySet()) {
            if (v.getValue().equals(temp))
                v1 = v;
        }

        return v1;
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    public void addVertex(V label) {
        Vertex v = new Vertex(label);
        vertexList.putIfAbsent(v, new ArrayList<>());
        System.out.println(vertexList.keySet());
    }

    private void addDirectedEdge(V source, V destination) {
        Vertex sourceVertex = getOrigVertex(source);
        Vertex destVertex = getOrigVertex(destination);

        vertexList.get(sourceVertex).add(destVertex);

        System.out.println(sourceVertex.getValue() + " is connected with " + destVertex.getValue());
    }

    private boolean existsInAdjList(Vertex root, Vertex val) {
        for (Vertex v : vertexList.get(root)) {
            if (v.equals(val))
                return true;
        }
        return false;
    }

    private void addUndirectedEdge(V source, V destination) {
        Vertex v1 = getOrigVertex(source);
        Vertex v2 = getOrigVertex(destination);

        vertexList.get(v1).add(v2);
        vertexList.get(v2).add(v1);

        System.out.println(v1.getValue() + " is connected with " + v2.getValue());
        System.out.println(v2.getValue() + " is connected with " + v1.getValue());
    }

    public void addEdge(V source, V destination) {

        if (isDirected)  // directed graph
            addDirectedEdge(source, destination);

        else // if undirected graph
            addUndirectedEdge(source, destination);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

//    void removeEdge(V label1, V label2) {
//        Vertex v1 = new Vertex(label1);
//        Vertex v2 = new Vertex(label2);
//        List<Vertex> eV1 = vertexList.get(v1);
//        List<Vertex> eV2 = vertexList.get(v2);
//        if (eV1 != null)
//            eV1.remove(v2);
//        if (eV2 != null)
//            eV2.remove(v1);
//    }
//
//    void removeVertex(V label) {
//        Vertex v = new Vertex(label);
//        vertexList.values().stream().forEach(e -> e.remove(v));
//        vertexList.remove(new Vertex(label));
//    }

    ///////////////////////////////////////////////////////////////////////////////////////////


    private Vertex getKey(V label) {
        for (Vertex v : vertexList.keySet()) {
            if (label.equals(v.getValue()))
                return v;
        }
        return null;
    }

    private Vertex getAdjList(Vertex v) {

        for (Vertex vertex : vertexList.get(v)) {
            if (!vertex.isWasVisited()) {
                return vertex;
            }
        }

        return null;
    }

    public void breadthFirstTraversal(V root) {
        Vertex v1 = getOrigVertex(root);

        Queue<Vertex> queue = new LinkedList<>();  //queue
        queue.add(v1); // adding root to the beginning of queue
        v1.setWasVisited(true); // setting root as visited

        while (!queue.isEmpty()) {
            Vertex v2 = queue.poll();  // getting and deleting element from the beginning of queue
            System.out.println("1st in queue = " + v2.getValue() + " " + vertexList.get(v2));

            while ((v1 = getAdjList(v2)) != null) { // till v2's list of values is null
                queue.add(v1); // adding into queue
                v1.setWasVisited(true); // setting this vertex as visited
            }
        }

        for (Vertex v : vertexList.keySet()) {
            v.setWasVisited(false);
        }
        queue.clear();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    private boolean checkForContaining(Vertex dest, ArrayList<Vertex> list) {
        for (Vertex v : list) {
            if (v.getValue().equals(dest.getValue()))
                return true;
        }
        return false;
    }

    public boolean isConnected(V source, V destination) {
        Vertex sourceVertex = getOrigVertex(source);
        Vertex destVertex = getOrigVertex(destination);

        if (!vertexList.containsKey(sourceVertex) || !vertexList.containsKey(destVertex))
            return false;
        return breadthFirstTraversalSearch(sourceVertex, destVertex);
    }

    private boolean breadthFirstTraversalSearch(Vertex source, Vertex destination) {
        ArrayList<Vertex> appearedVertexesList = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();  //queue

        queue.add(source); // adding source to the beginning of queue
        source.setWasVisited(true); // setting source as visited

        Vertex v1 = source;

        while (!queue.isEmpty()) {
            Vertex v2 = queue.poll();  // getting and deleting element from the beginning of queue
            System.out.println("1st in queue = " + v2.getValue() + " " + vertexList.get(v2));
            appearedVertexesList.add(v2);

            while ((v1 = getAdjList(v2)) != null) { // till v2's list of values is null
                queue.add(v1); // adding into queue
                v1.setWasVisited(true); // setting this vertex as visited
            }
        }

        for (Vertex v : vertexList.keySet()) {
            v.setWasVisited(false);
        }

        if (checkForContaining(destination, appearedVertexesList))
            return true;
        else
            return false;
    }
}
