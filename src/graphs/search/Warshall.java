package graphs.search;

import graphs.digraph.DigraphList;

/**
 * Algoritmo para determinar si existe un camino de un vertica a hacia otro
 * vertice b para todos los pares de vertices (a, b) en el grafo.
 */
public class Warshall {

    public static <T> void transitive(DigraphList<T> digraphList) {
        for (int i = 0; i < digraphList.order(); i++) {
            for (int j = 0; j < digraphList.order(); j++) {
                if(!digraphList.doesEdgeExist(j, i)){
                    continue;
                }
                for (int k = 0; k < digraphList.order(); k++) {
                    if(digraphList.doesEdgeExist(i, k)) {
                        if(!digraphList.doesEdgeExist(j, k)) digraphList.addEdge(j, k, 0);
                    }
                }
            }
        }
    }

}
