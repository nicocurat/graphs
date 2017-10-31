package graphs;

import graphs.api.DirectedAPI;
import graphs.builders.DigraphListBuilder;
import graphs.digraph.DigraphList;
import graphs.search.Warshall;
import util.GraphDiagram;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DigraphList<Integer> graph = new DigraphList<>(10);

        Scanner scanner = new Scanner(System.in);

        int cases = 1;
        while(cases > 0){
            System.out.println("1-Add Vertex || 2-Add Edges || 3-Remove Vertex || 4-Remove Edge || 5-Show Graph || 6-Random Graph || 7-DigraphAPI || 8-Warshall || 0-Exit");
            cases = scanner.nextInt();
            switch (cases){
                case 1: //Add Vertex
                    System.out.println("Enter Vertex:   (-1 to finish)");
                    int n = scanner.nextInt();
                    while(n >= 0) {
                        graph.addVertex(n);
                        n = scanner.nextInt();
                    }
                    break;
                case 2: //Add Edges
                    System.out.println("Enter Edges:   (Ej.: 1 enter 2 enter 8)(-1 to finish)");
                    System.out.println("First: ");
                    int a = scanner.nextInt();
                    System.out.println("Second: ");
                    int b = scanner.nextInt();
                    System.out.println("Weight: ");
                    int weight = scanner.nextInt();
                    System.out.println("You chose: " + a + " || " + b + " || " + weight);
                    graph.addEdge(a, b, weight);
                    break;
                case 3: //Remove Vertex
                    System.out.println("Enter Vertex to Delete: ");
                    graph.removeVertex(scanner.nextInt());
                    break;
                case 4: //Remove Edges
                    System.out.println("Enter Edge to remove:   (Ej.: 1 enter 2 enter)");
                    int first = scanner.nextInt();
                    int second = scanner.nextInt();
                    graph.removeEdge(first, second);
                    break;
                case 5: //Show Graph
                    GraphDiagram graphDiagram = new GraphDiagram("graph.dot");
                    graphDiagram.createGraphFile(graph);
                    break;
                case 6:
                    System.out.println("Choose how many vertices you want to add");
                    int amount = scanner.nextInt();
                    DigraphListBuilder digraphListBuilder = new DigraphListBuilder(amount);
                    graph = digraphListBuilder.getDigraph();
                    GraphDiagram diagram = new GraphDiagram("randomGraph.dot");
                    diagram.createGraphFile(digraphListBuilder.getDigraph());
                    break;
                case 7:
                    System.out.println("The moment of truth!");
                    System.out.println("Are you sure you want to try it? (1 to try it, -1 to exit)");
                    int sure = scanner.nextInt();
                    List<Integer> deadEnds = DirectedAPI.getDeadEnds(graph);
                    List<Integer> sources = DirectedAPI.getSources(graph);
                    System.out.println("The dead ends are: ");
                    for (Integer deadEnd : deadEnds) {
                        System.out.println(deadEnd);
                    }
                    System.out.println("The sources are: ");
                    for (Integer source : sources) {
                        System.out.println(source);
                    }
                    System.out.println("Is weakly connected?");
                    System.out.println(DirectedAPI.isWeaklyConnected(graph));
                    while (sure != -1) {
                        System.out.println("Enter the two vertices you want to find the path of length two (ex.: 1 enter 2)");
                        int v = scanner.nextInt();
                        int w = scanner.nextInt();
                        System.out.println("Is there? " + DirectedAPI.existsLengthTwoPath(graph, v, w));
                        System.out.println("Try again? 1 to keep playing, -1 to exit!");
                        sure = scanner.nextInt();
                    }
                    break;
                case 8:
                    GraphDiagram bla = new GraphDiagram("warshall.dot");
                    Warshall.transitive(graph);
                    bla.createGraphFile(graph);
                    break;
                default:
                    System.out.println("Option not Available");
            }
        }
    }
}
