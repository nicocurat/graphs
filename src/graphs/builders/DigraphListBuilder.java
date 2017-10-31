package graphs.builders;

import graphs.digraph.DigraphList;

import java.util.Random;

public class DigraphListBuilder {

    private Random random = new Random();
    private DigraphList<Integer> digraph;
    private boolean[] visited;

    public DigraphListBuilder(int amount) {
        digraph = new DigraphList<>(amount);
        visited = new boolean[amount];
        addVertices();
        addEdges();
    }

    private void addVertices() {
        for (int i = 0; i < visited.length; i++) {
            digraph.addVertex(i);
        }
    }

    private void addEdges() {
        for (int i = 0; i < visited.length-1; i++) {
            int v = random.nextInt(digraph.order());
            int w = random.nextInt(digraph.order());
            while (v == w || visited[w]) {
                w = random.nextInt(digraph.order());
            }
            digraph.addEdge(v, w, random.nextInt(100));
            visited[w] = true;
        }
    }

    public DigraphList<Integer> getDigraph() {
        return digraph;
    }
}
