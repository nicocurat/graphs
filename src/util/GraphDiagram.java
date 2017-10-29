package util;

import graphs.digraph.DiGraph;

import java.io.FileWriter;
import java.io.IOException;

public class GraphDiagram<T> {

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

    public void createGraphFile(DiGraph<Integer> diGraph){
        try{
            fileWriter.write("digraph {\n");
            fileWriter.write("rankdir = \"LR\"\n\n");
            for (int i = 0; i < diGraph.order(); i++) {
                writeNodeToFile(diGraph.getVertex(i));
            }
            boolean[][] aux = diGraph.getAdjacentMatrix();
            for (int i = 0; i < aux.length; i++) {
                for (int j = 0; j < aux.length; j++) {
                    if(aux[i][j]) writeTransitionToFile(i, j);
                }
            }
            fileWriter.write("\n");
            fileWriter.write("}");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeTransitionToFile(int v, int w) {
        String text;
        try{
            text = "Node" + v + " -> Node" + w +
                    " [label=\"\"];\n";
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
