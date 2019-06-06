package com.code.review.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UnweightGraph {
	/**
	 *  Unweight Grouph
	 *  one source node can have multiple adjacency nodes, which contains node name only without weight
	 *  (1) create vertex or edge object which contain src and dest, dest is adjacent edge name
	 *  (2) create List<List<Edge> edges = new ArrayList<>();
	 * 
	 */
	// data structure to store edge
	public static class Edge {
		int src, dest;
		public Edge(int src, int dest) {
			this.src = src;
			this.dest = dest;
		}
	};
	// data structure to store adjacency node , node name is Integer
	public static ArrayList<ArrayList<Integer>> adj= new ArrayList<>();
	
	public UnweightGraph(List<Edge> edges) {
		for (int i=0; i<edges.size();i++) {
			adj.add(i, new ArrayList<Integer>());
		}
		for (Edge curr: edges) {
			adj.get(curr.src).add(curr.dest);
		}
		
	}
	public static void printGraph(UnweightGraph graph) {
		for (int src=0;src<UnweightGraph.adj.size();src++) { // number of edges
			for (int dest:UnweightGraph.adj.get(src) ) {
				System.out.print("("+src+"->"+dest+") ");
			}
			System.out.println("");
		}
	}
	/**
	 * Broad First Search BFS 
	 * we create one queue and visited array
	 * do BFS and loop all linked nodes if it is not visited
	 * once find goal return it
	 * @param args
	 */
	public static boolean BFS(UnweightGraph graph, int targetNode) {
		boolean visited[] = new boolean[graph.adj.size()];
		for (int i=0; i<visited.length;i++) visited[i] = false;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		while(!queue.isEmpty()) {
			int v = queue.remove();
			 
			if (v==targetNode) {
				System.out.println("Found vertex src = " + v+" linked vertex is "+graph.adj.get(v));
				return true;
			}
			for (int u: graph.adj.get(v)) {
				if (!visited[u]) {
					queue.add(u);
					visited[u]=true;
				}
			}
		}
		
		return false;
	}
	/**
	 * Depth First Search DFS 
	 * very simple , we have caller function and DFS function , put each edge number u as parameter
	 * u change from 0 to graph.adj.get(v)
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Edge> edges = Arrays.asList(
				new Edge(1,2),
				new Edge(1,3),
				new Edge(1,4),
				new Edge(2,5),
				new Edge(2,6),
				new Edge(5,9),
				new Edge(5,10),
				new Edge(4,7),
				new Edge(4,8),
				new Edge(7,11),
				new Edge(7,12) 
 				
				);
		UnweightGraph graph = new UnweightGraph(edges);
		//printGraph(graph);
		BFS(graph, 4);
		BFS(graph, 7);
		BFS(graph, 1);
	}

}
