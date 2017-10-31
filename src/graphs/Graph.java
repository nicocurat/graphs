package graphs;

import graphs.digraph.Edge;

import java.util.List;

public interface Graph<T> {

    void addVertex(T t);
    void addEdge(int v, int w, int weight);
    void removeEdge(int v, int w);
    void removeVertex(int v);
    boolean doesEdgeExist(int v, int w);
    int order();
    int numberOfEdges();
    T getVertex(int v);
    List<Edge<Integer>> getListOfAdyacentVertex(int v);

}
