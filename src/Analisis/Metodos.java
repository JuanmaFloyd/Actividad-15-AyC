package Analisis;
import java.util.LinkedList;

import Graph.Arco;
import Graph.Graph;

public class Metodos {
	private static final int BLANCO = 0;
	private static final int GRIS = 1;
	private static final int NEGRO = 2;

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
			System.out.println("u="+u);
			for (Arco arco : grafo.getAdyacentes(u)){
				if (color[arco.destination] == BLANCO){
					System.out.println(arco.destination);
					color[arco.destination] = GRIS;
					cola.addLast(arco.destination);
				}
			}
			
			color[u] = NEGRO;
			cola.removeFirst();
		}
	}
	
}
