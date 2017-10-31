package graphs.search;

import graphs.digraph.DigraphList;
import graphs.digraph.Edge;

public class DFS<T> {

    private boolean[] visited;

    public DFS(DigraphList<T> digraphList, int v) {
        visited = new boolean[digraphList.order()];
        dfs(digraphList, v);
    }

    private void dfs(DigraphList<T> digraphList, int v) {
        visited[v] = true;
        for (Edge<Integer> edge: digraphList.getListOfAdyacentVertex(v)) {
            if(!visited[edge.getW()]) {
                dfs(digraphList, edge.getW());
            }
        }
    }

    public boolean visited(int v) {
        return visited[v];
    }

    public boolean[] getVisited() {
        return visited;
    }
}
