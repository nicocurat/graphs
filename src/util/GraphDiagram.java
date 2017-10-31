package util;


import graphs.digraph.DigraphList;
import graphs.digraph.Edge;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GraphDiagram {

    private FileWriter fileWriter;

    public GraphDiagram(String fileName) {
        create(fileName);
    }

    private void create(String fileName) {
        try{
            fileWriter = new FileWriter(fileName);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void createGraphFile(DigraphList<Integer> diGraph){
        try{
            fileWriter.write("digraph {\n");
            fileWriter.write("rankdir = \"LR\"\n\n");
            for (int i = 0; i < diGraph.order(); i++) {
                writeNodeToFile(diGraph.getVertex(i));
            }
            fileWriter.write("\n");
            for (int i = 0; i < diGraph.order(); i++) {
                List<Edge<Integer>> listOfAdyacentes = diGraph.getListOfAdyacentVertex(i);
                for (Edge<Integer> edge : listOfAdyacentes) {
                    writeTransitionToFile(i, edge.getW(), edge.getWeight());
                }
            }
            fileWriter.write("\n");
            fileWriter.write("}");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeTransitionToFile(int v, int w, int weight) {
        String text;
        try{
            text = "Node" + v + " -> Node" + w +
                    " [label=\"" + weight + "\"];\n";
            fileWriter.write(text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeNodeToFile(Integer value) {
        String text;
        try {
            text = "node [shape=circle] Node" + value +
                        " [label=\"" + value + "\"];\n";
            fileWriter.write(text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
