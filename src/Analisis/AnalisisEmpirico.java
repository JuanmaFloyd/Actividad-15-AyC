package Analisis;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Graph.Arco;
import Graph.Graph;

	public class AnalisisEmpirico{
		
		public static void main(String[] args) throws IOException {
			
			try{
				Grafo grafo = getGrafo(5,5);
				System.out.println("Grafo conexo con "+ grafo.getNodosCount() + " nodos y "+ grafo.getArcosCount() + " arcos construido");
				Graph g = new Graph(9);
				g.addEgde(0, 1, 4);
				g.addEgde(0, 7, 8);
				//g.addEgde(1, 2, 8);
				//g.addEgde(1, 7, 11);
				g.addEgde(2, 8, 2);
				g.addEgde(2, 5, 4);
				g.addEgde(2, 3, 7);
				g.addEgde(3, 4, 9);
				//g.addEgde(3, 5, 10);
				//g.addEgde(4, 5, 14);
				//g.addEgde(5, 6, 2);
				//g.addEgde(6, 8, 6);
				g.addEgde(6, 7, 1);
				//g.addEgde(8, 7, 7);
				
				//LinkedList<Arco> r = Metodos.kruskalAA(g);
				System.out.println(Metodos.esConexo(g));
				System.out.println(Metodos.esConexoDS(g));
				//for (Arco arco : r)
					//System.out.println(arco.source+" "+arco.destination+" "+arco.weight);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
			/*
			 * Generar varios grafos de diferente configuración y buscar 
			 * árbol de cubrimiento minimal para cada uno. 
			 * 
			 * Medir el rendimiento usando timestamps.
			 * 
			 */
			
			
		}

		private static Grafo getGrafo(int nodos, int arcos) throws Exception {
			// TODO Auto-generated method stub
			String consulta = "curl http://cs.uns.edu.ar/~mom/AyC2019/grafo.php?nodos="+nodos+"&arcos="+arcos+"&conexo="+0;
			Process process = Runtime.getRuntime().exec(consulta);
			InputStream inputSt = process.getInputStream();
			@SuppressWarnings("resource")
			Scanner s = new Scanner(inputSt).useDelimiter("\\A");
			String jsonString = s.hasNext() ? s.next() : "";
			System.out.println("Tengo el grafo en formato JSON. Lo convierto...");
			Gson gson = new GsonBuilder().create();
			try{
				Grafo.GrafoObj gr = gson.fromJson(jsonString, Grafo.GrafoObj.class);
				return new Grafo(gr);
			} catch (Exception e) {
				throw new Exception(jsonString);
			}
		}
		
	}