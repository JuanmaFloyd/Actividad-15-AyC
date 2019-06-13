package Graph;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {
    int vertices;
    LinkedList<Arco> [] adjacencylist;

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencylist = new LinkedList[vertices];
        //initialize adjacency lists for all the vertices
        for (int i = 0; i <vertices ; i++) {
            adjacencylist[i] = new LinkedList<>();
        }
    }

    public void addEgde(int source, int destination, int weight) {
        Arco arco = new Arco(source, destination, weight);
        adjacencylist[source].add(arco);
    }
}