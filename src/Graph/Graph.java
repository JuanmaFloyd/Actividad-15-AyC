package Graph;
import java.util.LinkedList;

public class Graph {
    private int vertices;
    private LinkedList<Arco> [] adjacencylist;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencylist = new LinkedList[vertices];
        //initialize adjacency lists for all the vertices
        for (int i = 0; i <vertices ; i++) {
            adjacencylist[i] = new LinkedList<>();
        }
    }

    public void addEgde(int source, int destination, int weight) {
        Arco arco1 = new Arco(source, destination, weight);
        Arco arco2 = new Arco(destination, source, weight);
        adjacencylist[source].add(arco1);
        adjacencylist[destination].add(arco2);
    }
    
    public int getVertices(){
    	return vertices;
    }
    
    public LinkedList<Arco> getAdyacentes(int ver){
    	return adjacencylist[ver];
    }
}