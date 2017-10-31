package graphs.search;

import graphs.digraph.DigraphList;
import graphs.digraph.Edge;

import java.util.PriorityQueue;
import java.util.Queue;

public class BFS<T> {

    private boolean[] visited;

    public BFS(DigraphList<T> digraph) {
        visited = new boolean[digraph.order()];
        for (int i = 0; i < digraph.order(); i++) {
            if(!visited[i]){
                bfs(digraph, i);
            }
        }
    }

    private void bfs(DigraphList<T> digraph, int v) {
        Queue<Integer> queue = new PriorityQueue<>();
        visited[v] = true;
        queue.add(v);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (Edge<Integer> edge: digraph.getListOfAdyacentVertex(current)) {
                if(!visited[edge.getW()]) {
                    visited[edge.getW()] = true;
                    queue.add(edge.getW());
                }
            }
        }
    }

    public boolean[] getVisited() {
        return visited;
    }
}
