package graphs.api;

import graphs.digraph.DigraphList;
import graphs.digraph.Edge;
import graphs.search.DFS;

import java.util.*;

public class DirectedAPI {

    /**
     * Uso la misma logica que DFS pero marco como 'true' nada mas los que son parte
     * de alguna lista de adyacencia. Los que no lo sean van a ir a la lista result.
     * @param digraph
     * @param <T>
     * @return
     */
    public static <T> List<Integer> getSources(DigraphList<T> digraph){
        List<Integer> result = new LinkedList<>();

        boolean[] marked = new boolean[digraph.order()];
        for(int i = 0; i < digraph.order(); i++) {
            for(Edge<Integer> edge: digraph.getListOfAdyacentVertex(i)){
                marked[edge.getW()] = true;
            }
        }

        for(int i = 0; i < digraph.order(); i++){
            if(!marked[i]) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Uso BFS para el deadEnd. Primero recorro toda la lista de adyacencia de uno y despues
     * me meto en la lista de adyacencia del otro! Mi condicion de visitado es si tiene lista de adyacencia.
     * @param digraph
     * @param <T>
     * @return
     */
    public static <T> List<Integer> getDeadEnds(DigraphList<T> digraph) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[digraph.order()];
        for (int i = 0; i < digraph.order(); i++) {
            Queue<Integer> queue = new PriorityQueue<>();
            queue.add(i);
            while (!queue.isEmpty()){
                int current = queue.poll();
                if(digraph.getListOfAdyacentVertex(current).size() > 0) visited[current] = true;
                for (Edge<Integer> edge: digraph.getListOfAdyacentVertex(current)) {
                    if(!visited[edge.getW()]){
                        queue.add(edge.getW());
                    }
                }
            }
        }

        for (int i = 0; i < digraph.order(); i++) {
            if(!visited[i]) result.add(i);
        }

        return result;
    }

    /**
     * Primero me fijo que no haya mas de un dead end ya que por logica,
     * si esto pasa no es posible que se encuentre un camino de un vertice a otro vertice extremo.
     * Luego 'simulo' un diagrama no dirigido al agregar edges de un nodo a otro que antes le apuntaba.
     * Por ultimo utilizo DFS para fijarme si es conexo.
     * @param digraph
     * @param <T>
     * @return
     */
    public static <T> boolean isWeaklyConnected(DigraphList<T> digraph){

        List<Integer> deadEnds = getDeadEnds(digraph);

        for (int i = 0; i < digraph.order(); i++) {
            for (Edge<Integer> edge: digraph.getListOfAdyacentVertex(i)) {
                digraph.addEdge(edge.getW(), i, edge.getWeight());
            }
        }

        DFS<T> dfs = new DFS<>(digraph, 0);
        for (Boolean visited: dfs.getVisited()){
            if (!visited) return false;
        }

        return deadEnds.size() != 0;
    }

    /**
     * En este caso quise usar busqueda plana ya que necesitaba recorrer todos los vertices
     * para fijarme en su lista de adyacencia si habia un vertices que apunte al vertice a encontrar.
     * @param digraph
     * @param v
     * @param w
     * @param <T>
     * @return
     */
    public static <T> boolean existsLengthTwoPath(DigraphList<T> digraph, int v, int w) {
        for (int i = 0; i < digraph.getListOfAdyacentVertex(v).size(); i++) {
            int next = digraph.getListOfAdyacentVertex(v).get(i).getW();
            for (int j = 0; j < digraph.getListOfAdyacentVertex(next).size(); j++) {
                if(digraph.getListOfAdyacentVertex(next).get(j).getW() == w) return true;
            }
        }
        return false;
    }
}
