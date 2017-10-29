package graphs;

import graphs.digraph.DiGraph;
import util.GraphDiagram;

public class Main {

    public static void main(String[] args) {
        DiGraph<Integer> diGraph = new DiGraph<>();
        diGraph.addVertex(1);
        diGraph.addVertex(2);
        diGraph.addVertex(3);
        diGraph.addVertex(4);
        diGraph.addVertex(5);
        diGraph.addEdge(1, 2);
        diGraph.addEdge(1, 4);
        diGraph.addEdge(2, 1);
        diGraph.addEdge(2, 3);
        diGraph.addEdge(3, 4);
        GraphDiagram<Integer> graphDiagram = new GraphDiagram<>("grafoD.dot");
        graphDiagram.createGraphFile(diGraph);
    }
}
