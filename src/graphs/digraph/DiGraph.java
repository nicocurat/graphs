package graphs.digraph;

import graphs.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DiGraph<T> implements Graph<T> {

    private Vertex<T>[] vertices;
    private boolean[][] adjacentMatrix;
    private int n; //Cantidad de vertices
    private int numberOfEdges;

    public DiGraph(){
        vertices = new Vertex[10];
        adjacentMatrix = new boolean[10][10];
        numberOfEdges = 0;
        n = 0;
    }

    public DiGraph(int capacity){
        vertices = new Vertex[capacity];
        adjacentMatrix = new boolean[capacity][capacity];
        numberOfEdges = 0;
        n = 0;
    }

    // O(1) - O(n²)
    @Override
    public void addVertex(T t) {
        if(n == vertices.length){
            growVerticeArray();
            growAdjacentMatrix();
        }
        vertices[n] = new Vertex<>(t, n);
        n++;
    }

    //Ver si puedo cambiar el for() por el forEach o el system....
    private void growVerticeArray(){
        Vertex<T>[] aux = new Vertex[n*2];
        for(int i = 0; i < vertices.length; i++) {
            aux[i] = vertices[i];
        }
        vertices = aux;
    }

    private void growAdjacentMatrix(){
        boolean[][] aux = new boolean[n*2][n*2];
        for(int i = 0; i < adjacentMatrix.length; i++){
            for(int j = 0; j < adjacentMatrix[j].length; j++) {
                aux[i][j] = adjacentMatrix[i][j];
            }
        }
        adjacentMatrix = aux;
    }

    private boolean doesVertexExist(int v){
        if(n == 0){
            throw new RuntimeException("There are no vertices in the graph");
        }
        if(v < 0 || v > n){
            throw new IllegalArgumentException("The arguments entered are illegal");
        }
        for(int i = 0; i < n; i++){
            if(vertices[i].getId() == v){
                return true;
            }
        }
        return false;
    }

    // O(1)
    @Override
    public void addEdge(int v, int w) {
        if(doesVertexExist(v) && doesVertexExist(w)){
            adjacentMatrix[v][w] = true;
            numberOfEdges++;
        }else{
            throw new IllegalArgumentException("One or both of the arguments is illegal");
        }
    }

    // O(1)
    @Override
    public void removeEdge(int v, int w) {
        if(doesVertexExist(v) && doesVertexExist(w)){
            if(doesEdgeExist(v, w)){
                adjacentMatrix[v][w] = false;
            }else{
                throw new IllegalArgumentException("There is no edge between the two vertices");
            }
        }else{
            throw new IllegalArgumentException("One or both arguments are wrong");
        }
    }

    // O(1)
    @Override
    public void removeVertex(int v) {
        if(doesVertexExist(v)){
            List<Vertex<T>> aux = new LinkedList<>();
            for (Vertex<T> value: vertices){
                if(value.getId() != v) aux.add(value);
            }
            aux.toArray(vertices);
            n--;
        }else{
            throw new IllegalArgumentException("The argument provided is illegal");
        }
    }

    // O(1)
    @Override
    public boolean doesEdgeExist(int v, int w) {
        if(doesVertexExist(v) && doesVertexExist(w)){
            return adjacentMatrix[v][w];
        }
        throw new IllegalArgumentException("One or both arguments are wrong");
    }

    // O(1)
    @Override
    public int order() {
        return n;
    }

    // O(1)
    @Override
    public int numberOfEdges() {
        return numberOfEdges;
    }

    // O(1)
    @Override
    public T getVertex(int v) {
        if(doesVertexExist(v)) {
            for (int i = 0; i < n; i++) {
                if (vertices[i].getId() == v) {
                    return vertices[i].getElement();
                }
            }
        }
        throw new IllegalArgumentException();
    }

    // O(n)
    @Override
    public List<Integer> getListOfAdyacentVertex(int v) {
        if(doesVertexExist(v)){
            List adjacentList = new ArrayList();
            for(int i = 0; i < n; i++){
                if(adjacentMatrix[v][i]){
                    adjacentList.add(vertices[i]);
                }
            }
            return adjacentList;
        }
        throw new IllegalArgumentException();
    }

    public boolean[][] getAdjacentMatrix() {
        return adjacentMatrix;
    }
}
