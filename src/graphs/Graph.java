package graphs;

import java.util.List;

public interface Graph<T> {

    void addVertex(T t);
    void addEdge(int v, int w);
    void removeEdge(int v, int w);
    void removeVertex(int v);
    boolean doesEdgeExist(int v, int w);
    int order();
    int numberOfEdges();
    T getVertex(int v);
    List<Integer> getListOfAdyacentVertex(int v);

}
