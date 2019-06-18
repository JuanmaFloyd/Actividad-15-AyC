package Analisis;

import java.util.LinkedList;
import Graph.Arco;
import Graph.Graph;
import Heap.Heap;
import disjointSets.DisjointSets;
import disjointSets.DisjointSetsHeuristic;

public class Metodos {
	private static final int BLANCO = 0;
	private static final int GRIS = 1;
	private static final int NEGRO = 2;
	
	public static LinkedList<Arco> kruskalBB(Graph grafo){
		LinkedList<Arco> T = new LinkedList<>();
		int i = 0;
		DisjointSetsHeuristic ds = new DisjointSetsHeuristic(grafo.getVertices()); //creamos DS
		
		Arco[] arcos = new Arco[grafo.getCantArcos()];
		
		for(LinkedList<Arco> lista : grafo.getAdjacencylist()) //agregamos cada arco al arreglo de arcos
			for(Arco arco : lista){
				arcos[i] = arco;
				i++;
			}
		
		Heap heap = new Heap(arcos); //transformamos el arreglo de arcos en un min heap
		
		Arco arco;
		do{
			arco = heap.removeMin(); //para cada arco en el heap tomando de manera ascendente
			if(!ds.inSameSet(arco.destination, arco.source)){ //si el arco conecta dos nodos disjuntos
				ds.union(arco.destination, arco.source); //uno los nodos
				T.add(arco); //agrego el arco a la solucion
			}
		} while(!(T.size() == grafo.getVertices()-1)); //termino cuando tengo N-1 arcos
		
		return T;
	}
	
	public static LinkedList<Arco> kruskalBA(Graph grafo){
		LinkedList<Arco> T = new LinkedList<>();
		int i = 0;
		DisjointSets ds = new DisjointSets(grafo.getVertices()); //creamos DS
		
		Arco[] arcos = new Arco[grafo.getCantArcos()];
		
		for(LinkedList<Arco> lista : grafo.getAdjacencylist()) //agregamos cada arco al arreglo de arcos
			for(Arco arco : lista){
				arcos[i] = arco;
				i++;
			}
		
		Heap heap = new Heap(arcos); //transformamos el arreglo de arcos en un min heap
		
		Arco arco;
		do{
			arco = heap.removeMin(); //para cada arco en el heap tomando de manera ascendente
			if(!ds.inSameSet(arco.destination, arco.source)){ //si el arco conecta dos nodos disjuntos
				ds.union(arco.destination, arco.source); //uno los nodos
				T.add(arco); //agrego el arco a la solucion
			}
		} while(!(T.size() == grafo.getVertices()-1)); //termino cuando tengo N-1 arcos
		
		return T;
	}
	
	public static LinkedList<Arco> kruskalAB(Graph grafo){
		LinkedList<Arco> T = new LinkedList<>();
		int i = 0;
		DisjointSetsHeuristic ds = new DisjointSetsHeuristic(grafo.getVertices()); //creamos DS
		
		Arco[] arcos = new Arco[grafo.getCantArcos()];
		
		for(LinkedList<Arco> lista : grafo.getAdjacencylist()) //agregamos cada arco al arreglo de arcos
			for(Arco arco : lista){
				arcos[i] = arco;
				i++;
			}
		
		mergeSort(arcos, 0, grafo.getCantArcos()-1); //ordenamos el arreglo por peso ascendente
		
		i = 0;
		Arco arco;
		do{
			arco = arcos[i]; //para cada arco de manera ascendente
			i++;
			if(!ds.inSameSet(arco.destination, arco.source)){ //si el arco conecta dos nodos disjuntos
				ds.union(arco.destination, arco.source); //uno los nodos
				T.add(arco); //agrego el arco a la solucion
			}
		} while(!(T.size() == grafo.getVertices()-1)); //termino cuando tengo N-1 arcos
		
		return T;
	}
	
	public static LinkedList<Arco> kruskalAA(Graph grafo){
		LinkedList<Arco> T = new LinkedList<>();
		int i = 0;
		DisjointSets ds = new DisjointSets(grafo.getVertices()); //creamos DS
		
		Arco[] arcos = new Arco[grafo.getCantArcos()];
		
		for(LinkedList<Arco> lista : grafo.getAdjacencylist()) //agregamos cada arco al arreglo de arcos
			for(Arco arco : lista){
				arcos[i] = arco;
				i++;
			}
		
		mergeSort(arcos, 0, grafo.getCantArcos()-1); //ordenamos el arreglo por peso ascendente
		
		i = 0;
		Arco arco;
		do{
			arco = arcos[i]; //para cada arco de manera ascendete
			i++;
			if(!ds.inSameSet(arco.destination, arco.source)){ //si el arco conecta dos nodos disjuntos
				ds.union(arco.destination, arco.source); //uno los nodos
				T.add(arco); //agrego el arco a la solucion
			}
		} while(!(T.size() == grafo.getVertices()-1));// termino cuando tengo N-1 arcos
		
		return T;
	}
	
	public static boolean esConexoDS(Graph grafo){
		DisjointSetsHeuristic ds = new DisjointSetsHeuristic(grafo.getVertices()); //creo DS de nodos
		
		for(LinkedList<Arco> lista : grafo.getAdjacencylist()) //para cada arco de mi grafo
			for(Arco arco : lista){
				ds.union(arco.destination, arco.source); //si el destino y el origen son disjuntos, los uno
				if (ds.getNumberOfSets() == 1) return true; //si la cantidad de conjuntos es 1 luego el grafo es conexo y termino
			}
				
		return false; //si llego a esta instancia entonces nunca tuve un solo conjunto y por ende el grafo no es conexo
	}

	public static boolean esConexoBFS(Graph grafo){
		int[] color = new int[grafo.getVertices()]; //creamos arreglo de colores
		
		for (int i = 0; i < grafo.getVertices(); i++) //seteamos todos los nodos en blanco
			color[i] = BLANCO;
		
		LinkedList<Integer> cola = new LinkedList<>();
		
		int cont = 0; //creo contador de nodos blancos
		
		for (int v = 0; v < grafo.getVertices(); v++){ //para cada vertice
			if (color[v] == BLANCO){ //si encuentro un nodo blanco
				cont++; //aumento el contador
				
				if (cont > 1) //si es el segundo nodo que encuentro termino con falso
					return false; //segundo nodo blanco no pude llegar a el a partir del primer nodo blanco (grafo desconexo)
				
				color[v] = GRIS; //pongo el nodo en gris
				cola.addLast(v); //agrego el nodo a la cola
				visitar(grafo, cola, color); //visito el resto del grafo a partir del nodo que agregue
			}
		}
		return true;
	}
	
	private static void visitar(Graph grafo, LinkedList<Integer> cola, int[] color){
		while(!cola.isEmpty()){ //mientras haya nodos en la cola
			int u = cola.getFirst(); //tomo el primer nodo de la cola
			for (Arco arco : grafo.getAdyacentes(u)){ //para cada arco adyacente al nodo u
				if (color[arco.destination] == BLANCO){ //si encuentro un nodo destino blanco
					color[arco.destination] = GRIS; //lo pinto de gris
					cola.addLast(arco.destination); //y lo agrego a la cola
				}
			}
			
			color[u] = NEGRO; //pinto al nodo de negro cuando ya no tiene adyacentes blancos para visitar
			cola.removeFirst(); //y lo remuevo de la cola, ya termine con este nodo
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
