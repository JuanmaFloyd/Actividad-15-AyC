package Analisis;

import java.util.LinkedList;
import Graph.Arco;
import Graph.Graph;
import disjointSets.DisjointSets;
import disjointSets.DisjointSetsHeuristic;

public class Metodos {
	private static final int BLANCO = 0;
	private static final int GRIS = 1;
	private static final int NEGRO = 2;
	
	public static LinkedList<Arco> kruskalAA(Graph grafo){
		LinkedList<Arco> T = new LinkedList<>();
		int cantArcos = 0;
		int i = 0;
		DisjointSetsHeuristic ds = new DisjointSetsHeuristic(grafo.getVertices());		
		
		for (LinkedList<Arco> lista : grafo.getAdjacencylist())
			cantArcos += lista.size();
		
		Arco[] arcos = new Arco[cantArcos];
		
		for(LinkedList<Arco> lista : grafo.getAdjacencylist())
			for(Arco arco : lista){
				arcos[i] = arco;
				i++;
			}
		
		mergeSort(arcos, 0, cantArcos-1);
		
		i = 0;
		Arco arco;
		do{
			arco = arcos[i];
			i++;
			if(!ds.inSameSet(arco.destination, arco.source)){
				ds.union(arco.destination, arco.source);
				T.add(arco);
			}
		} while(!(T.size() == grafo.getVertices()-1));
		
		return T;
	}
	
	public static boolean esConexoDS(Graph grafo){
		DisjointSetsHeuristic ds = new DisjointSetsHeuristic(grafo.getVertices());
		
		for(LinkedList<Arco> lista : grafo.getAdjacencylist())
			for(Arco arco : lista)
				ds.union(arco.destination, arco.source);
				
		return ds.getNumberOfSets() == 1;
	}

	public static boolean esConexo(Graph grafo){
		int[] color = new int[grafo.getVertices()];
		
		for (int i = 0; i < grafo.getVertices(); i++)
			color[i] = BLANCO;
		
		LinkedList<Integer> cola = new LinkedList<>();
		
		int cont = 0;
		
		for (int v = 0; v < grafo.getVertices(); v++){
			if (color[v] == BLANCO){
				cont++;
				color[v] = GRIS;
				cola.addLast(v);
				visitar(grafo, cola, color);
			}
			if (cont > 1)
				return false;
		}
		return true;
	}
	
	private static void visitar(Graph grafo, LinkedList<Integer> cola, int[] color){
		while(!cola.isEmpty()){
			int u = cola.getFirst();
			for (Arco arco : grafo.getAdyacentes(u)){
				if (color[arco.destination] == BLANCO){
					color[arco.destination] = GRIS;
					cola.addLast(arco.destination);
				}
			}
			
			color[u] = NEGRO;
			cola.removeFirst();
		}
	}
	
	private static void mergeSort(Arco arcos[], int l, int r){
		if (l < r) { 
            int m = (l+r)/2; 
  
            mergeSort(arcos, l, m); 
            mergeSort(arcos, m+1, r); 
  
            merge(arcos, l, m, r); 
        }
	}
	
	private static void merge(Arco arr[], int l, int m, int r) { 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        Arco L[] = new Arco[n1]; 
        Arco R[] = new Arco[n2]; 
  
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
  
        int i = 0, j = 0; 

        int k = l; 
        while (i < n1 && j < n2){ 
            if (L[i].weight <= R[j].weight){ 
                arr[k] = L[i]; 
                i++; 
            } 
            else{ 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        while (i < n1){ 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
        
        while (j < n2){ 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    }
	
}
