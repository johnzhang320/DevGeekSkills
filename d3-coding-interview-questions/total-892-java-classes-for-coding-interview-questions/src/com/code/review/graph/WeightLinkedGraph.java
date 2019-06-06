package com.code.review.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.code.review.graph.UnweightGraph.Edge;
/**
 *  Weight Graph 
 *  one source node can be linked multiple adjacency node, which edge has weight with node name
 *  
 *
 */
public class WeightLinkedGraph {
	// data structure to store edge
	public static class Edge {
		int src, dest, weight;
		public Edge(int src, int dest,int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	};
	// data structure to store adjacency node , node name values and weight
	public static class Node {
		int dest;
		int weight;
		public Node(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	};
	
	public static ArrayList<ArrayList<Node>> adj= new ArrayList<>();
	
	public WeightLinkedGraph(List<Edge> edges) {
		for (int i=0; i<edges.size();i++) {
			adj.add(i, new ArrayList<Node>());
		}
		for (Edge curr: edges) {
			adj.get(curr.src).add(new Node(curr.dest,curr.weight));
		}
		
	}
	public static void printGraph(WeightLinkedGraph graph) {
		for (int src=0;src<WeightLinkedGraph.adj.size();src++) { // number of edges
			for (Node n:WeightLinkedGraph.adj.get(src) ) {
				System.out.print(src+"->"+n.dest+"("+n.weight+") ");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Edge> edges = Arrays.asList(
				new Edge(0,1,6), 
				new Edge(1,2,7),
				new Edge(2,0,5),
				new Edge(2,1,4),
				new Edge(3,2,10),
				new Edge(4,5,1), 
				new Edge(5,4,3)
 				
				);
		WeightLinkedGraph graph = new WeightLinkedGraph(edges);
		printGraph(graph);
		
	}
	 

}
