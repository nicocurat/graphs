package graphs.digraph;

import graphs.Graph;

import java.util.ArrayList;
import java.util.List;

public class DigraphList<T> implements Graph<T> {

    private int order = 0;
    private int numberOfEdges = 0;
    private List<T> vertices = new ArrayList<>(0);
    private List<Edge<Integer>>[] edges;

    public DigraphList() {
        this.edges = new ArrayList[20];
        initializeArrayLists();
    }

    public DigraphList(int capacity) {
        this.edges = new ArrayList[capacity];
        initializeArrayLists();
    }

    private void initializeArrayLists() {
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    //O(1)
    @Override
    public void addVertex(T t) {
        vertices.add(t);
        order++;
    }

    //O(1)
    @Override
    public void addEdge(int v, int w, int weight) {
        edges[v].add(0, new Edge<>(weight, w)); //Borrar el 0, no hace falta!!!!
        numberOfEdges++;
    }

    //O(m) --> m cantidad de aristas de v
    @Override
    public void removeEdge(int v, int w) {
        if(edges[v] == null) throw new IllegalArgumentException();
        Edge<Integer> edgeToRemove = new Edge<>(0, w);
        for (int i = 0; i < edges[v].size(); i++) {
            if(edges[v].get(i).equals(edgeToRemove)) {
                edges[v].remove(i);
            }
        }
    }

    /**
     * Borras el vertice en la posicion v, no el vertice v.
     * O(n)
     * @param v
     */
    @Override
    public void removeVertex(int v) {
        if(edges[v].size() != 0)
            throw new IllegalArgumentException(); //No se puede borrar un vertice con edges que tiene presente.
        else {
            vertices.remove(v);
            for(int i = v; i < edges.length - 1; i++) {
                edges[v] = edges[v+1]; //Corre todos los edges un espacio mas a la derecha.
            }
            order--;
        }
    }

    //O(m(
    @Override
    public boolean doesEdgeExist(int v, int w) {
        if (edges[v].size() == 0) return false;
        for (int i = 0; i < edges[v].size(); i++) {
            if(edges[v].get(i).getW() == w) return true;
        }
        return false;
    }

    //O(1)
    @Override
    public int order() {
        return order;
    }

    //O(1)
    @Override
    public int numberOfEdges() {
        return numberOfEdges;
    }

    //O(1)
    @Override
    public T getVertex(int v) {
        return vertices.get(v);
    }

    //O(1)
    @Override
    public List<Edge<Integer>> getListOfAdyacentVertex(int v) {
        return edges[v];
    }
}
