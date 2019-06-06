package com.code.review.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BroadFirstSearchGrap {
	/**
	 *  
	 *
	 * Breadth First Traversal for a Graph
     *
     * Breadth First Traversal (or Search) for a graph is similar to 
     * Breadth First Traversal of a tree (See method 2 of this post). 
     * The only catch here is, unlike trees, graphs may contain cycles, 
     * so we may come to the same node again. To avoid processing a 
     * node more than once, we use a boolean visited array. For simplicity, 
     * it is assumed that all vertices are reachable from the starting vertex.
     * For example, in the following graph, we start traversal from vertex 2.
     *  When we come to vertex 0, we look for all adjacent vertices of it. 
     *  2 is also an adjacent vertex of 0. If we don�t mark visited vertices, 
     *  then 2 will be processed again and it will become a non-terminating
     *  process. A Breadth First Traversal of the following graph is 2, 0, 3, 1.
     *
	 * 
	 */
	/**
	 *  (1) Graph can be present into adjacency LinkedList array LinkedList<Integer> adj[] this foundation data structure
	 *      array is limited to present vertex and each vertex has a linkedList to contain 
	 *      adjacent vertex to linked current vertex, vertex number is v and adjacent vertex is w
	 *  (2) once a vertex is visited , we defined array visited(v),visited(i)=true
	 *  (3) we poll vertex from queue to currV, check if it is goal return 
	 *      else 
	 *       Iterator<Integer> itr = adj[currV].listInterator();
	 *       while (itr.hasNext()) {
	 *       	int data = itr.next();
	 *       	queue.add(data);
	 *       	visited[data]=true;
	 *       }
	 *       
	 *      
	 *    
	 *      
	 *  
	 */
	private int v;                       // vertex value
	private LinkedList<Integer> adj[];   // adjacency list
	public BroadFirstSearchGrap(int v) {
		this.v = v;
		adj = new LinkedList[v];
		for (int i = 0; i<this.v;i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	// v -- array of vectex index, w -- is value of adjacency vertex
	public void addEgg(int v, int w ){
		adj[v].add(w);
	}
	// Print out each vertex by traversal of graph
	public void BFS (int startVertex) {
		// define visited flag for each vertex
		boolean visited[] = new boolean[this.v];
		visited[startVertex] = true;
		// create queue to contain all adjacent vertex
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(startVertex);
		while (queue.size()!=0) {
			int currentV = queue.poll(); // retrieve head of list and remove it
			System.out.print(currentV+" ");
			Iterator<Integer> itr = adj[currentV].listIterator();
			while (itr.hasNext()) {
				int v = itr.next();
				if (!visited[v]) {
					visited[v] = true;
					queue.add(v);
				}
			}
		}
	}
	public void BFSearch(int startVertex, int goal) {
		// define visited flag for each vertex
		boolean visited[] = new boolean[this.v];
		visited[startVertex] = true;
		// create queue to contain all adjacent vertex
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(startVertex);
		while (queue.size()!=0) {
			int currentV = queue.poll(); // retrieve the data without remove
			System.out.print(currentV+" ");
			if (currentV == goal) {
				System.out.print("\nfound currentV="+goal);
				return;
			}
		
			List<Integer> list = adj[currentV];
			for (Integer vi: list) {
				if (!visited[vi]) {
					visited[vi]=true;
					queue.add(vi);
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BroadFirstSearchGrap bfs = new BroadFirstSearchGrap(4);
		bfs.addEgg(0, 2);
		bfs.addEgg(0, 1);
		bfs.addEgg(1, 2);
		bfs.addEgg(2, 0);
		bfs.addEgg(2, 3);
		bfs.addEgg(3, 3);
		bfs.BFS(2);
		System.out.println("\nSeek vertex 3");
		
		bfs.BFSearch(2, 3);
		
		
	}

}
