package Analisis;
import java.util.LinkedList;

import Graph.Graph;

public class Metodos {
	private static final int BLANCO = 0;
	private static final int GRIS = 1;
	private static final int NEGRO = 2;

	public boolean esConexo(Graph grafo){
		int[] color = new int[grafo.getVertices()];
		
		for (int i = 0; i < grafo.getVertices(); i++)
			color[i] = BLANCO;
		
		LinkedList<Integer> cola = new LinkedList<>();
		
		for (int v : color)
			if (color[v] == 0){
				color[v] = GRIS;
				cola.addLast(v);
			}
	}
	
}
