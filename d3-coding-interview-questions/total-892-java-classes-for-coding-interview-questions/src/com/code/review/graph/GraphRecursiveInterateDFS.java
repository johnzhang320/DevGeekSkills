package com.code.review.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
/**
 * 
 * ProgramGeek 
 * Standard graph presentation and BFS, DFS, Clone
 *Clone an Undirected Graph

	Cloning of a LinkedList and a Binary Tree with random pointers has already been discussed. The idea behind cloning a graph is pretty much similar.
	
	Recommended: Please try your approach on {IDE} first, before moving on to the solution.
	
	  
	
	The idea is to do a BFS traversal of the graph and while visiting a node make a clone node of it (a copy of
	 original node). If a node is encountered which is already visited then it already has a clone node.
	
	How to keep track of the visited/cloned nodes?
	A HashMap/Map is required in order to maintain all the nodes which have already been created.
	Key stores: Reference/Address of original Node
	Value stores: Reference/Address of cloned Node
	
	A copy of all the graph nodes has been made, how to connect clone nodes?
	While visiting the neighboring vertices of a node u get the corresponding cloned node for u , let’s call that 
	cloneNodeU , now visit all the neighboring nodes for u and for each neighbor find the corresponding clone 
	node(if not found create one) and then push into the neighboring vector of cloneNodeU node.
	
	How to verify if the cloned graph is a correct?
	Do a BFS traversal before and after the cloning of graph. In BFS traversal display the value of a node along 
	with its address/reference.
	Compare the order in which nodes are displayed, if the values are same but the address/reference is different 
	for both the traversals than the cloned graph is correct.
 */
public class GraphRecursiveInterateDFS {
	// Node class represents each 
	// Node of the Graph 
	 
  //  1--2 
  //  |  | 
  //  4--3--5
  // source is 1, then BFS 1->2->4->3->5	
	 
	  //  1--2 
	  //  |  | 
	  //  4--3--5
	 //      |
	 //      6
	  // source is 1, then DFS , 1,2,3,4,5,6, from last one can not 
	public void iterateDFS(Node source) {
		Stack<Node> stack = new Stack<Node>();
		stack.add(source);
		Map<Node,Boolean> visited = new HashMap<Node,Boolean>();
		visited.put(source, true);
	 
		while(!stack.isEmpty()) {
			Node n = stack.pop();
			System.out.print(n.name+"("+n.val+") ");
			if (n.neighbours!=null) {
				for (Node g:n.neighbours) {
					if (visited.get(g)==null) {
						stack.push(g);
						visited.put(g, true);
					}
				}
			}
		}
		System.out.println("\n");
	}
	public void recursiveDFS(Node source) {
		Map<Node,Boolean> visited = new HashMap<Node,Boolean>();
		visited.put(source, true);
		reDFS(source, visited);
		System.out.print("\n");
	}
	public void reDFS(Node n, Map<Node,Boolean> visited) {
		System.out.print(n.name+"("+n.val+") ");
		if (n.neighbours!=null) {
			for (Node g:n.neighbours) {
				if (visited.get(g)==null) {
					visited.put(g,true);
					reDFS(g,visited);
				}
			}
		}
	}
	/**
	 *  How to keep track of the visited/cloned nodes?
		A HashMap/Map is required in order to maintain all the nodes which have already been created.
		Key stores: Reference/Address of original Node
		Value stores: Reference/Address of cloned Node
		
		A copy of all the graph nodes has been made, how to connect clone nodes?
		While visiting the neighboring vertices of a node u get the corresponding cloned node for u , let’s call that 
		cloneNodeU , now visit all the neighboring nodes for u and for each neighbor find the corresponding clone 
		node(if not found create one) and then push into the neighboring vector of cloneNodeU node.
	
	 */
	 public Node cloneGraph(Node source) {
		 /*
		  *  BFS traversal the graph
		  */
		 Queue<Node> q = new LinkedList<Node>();
		 q.add(source);
		 // key is original node and value is cloned node, similar to BFS and DFS visited map , value here is 
		 // cloned Node instead of Boolean, 
		 
		 Map<Node,Node> map = new HashMap<Node,Node>();
		 // clone (deep copy) first node as source
		 map.put(source, new Node(source.name,source.val));
		
		 while (!q.isEmpty()) {
			 // Get Node (large A)
			 Node n = q.poll();
			// Get corresponding Cloned Graph Node (small a), now from neighboors of large A , we clone small a(clone) neighboor
			 Node cloneNodeU = map.get(n);
			 if (n.neighbours!=null) {
				 List<Node> adj = n.neighbours;
				 for (Node g : adj) {   // get Large B
					 // Get the corresponding cloned node 
	                 // If the node is not cloned then we will 
	                 // simply get a null 
					Node cloneNodeG = map.get(g);  // take boolean similar to BFS, A->B, now we can get large B
					 if (cloneNodeG == null) {
						 q.add(g);
						  // If not then create a new Node and 
	                        // put into the HashMap 
						 cloneNodeG = new Node(g.name,g.val);   // create small b by deep copy name and value of large B
						 map.put(g, cloneNodeG);                // add large B and small b to map
					 } // keep going create all small bs
					 // add the 'cloneNodeG' to neighbour 
	                    // vector of the cloneNodeG 
	                    cloneNodeU.neighbours.add(cloneNodeG);   // add current small bs as neighboors to small a
				 }
			 }
			
		 }
		 return map.get(source);
	 }
	 //* Original node is A , its neighboors are Bs and clone node is a,  neighboors are bs
	 public Node cloneGraphAB(Node source) {
		 /*
		  *  BFS traversal the graph
		  */
		 Queue<Node> q = new LinkedList<Node>();
		 Node A = source;
		 q.add(A);
		 // key is original node and value is cloned node, similar to BFS and DFS visited map , value here is 
		 // cloned Node instead of Boolean, 
		 
		 Map<Node,Node> map = new HashMap<Node,Node>();
		 // clone (deep copy) first node a as source
		 Node cloneNode_a = new Node(A.name,A.val);
		 map.put(A, cloneNode_a);
		
		 while (!q.isEmpty()) {
			 // Get Node (large A)
			 A = q.poll();
			// Get corresponding Cloned Graph Node (small a), now from neighboors of large A , we clone small a(clone) neighboor
			 cloneNode_a = map.get(A);
			 if (A.neighbours!=null) {
				 List<Node> adj = A.neighbours;
				 for (Node B : adj) {   // get Large B
					 // Get the corresponding cloned node 
	                 // If the node is not cloned then we will 
	                 // simply get a null 
					Node cloneNode_b = map.get(B);  // take boolean similar to BFS, A->B, now we can get small b
					 if (cloneNode_b == null) {     // small b is not created yet 
						 q.add(B);                  // BFS use the neighboors of A : B
						  // If not then create a new Node and 
	                        // put into the HashMap 
						 cloneNode_b = new Node(B.name,B.val);   // create small b by deep copy name and value of large B
						 map.put(B, cloneNode_b);                // add large B and small b to map
					 } // keep going create all small bs
					 // add the 'cloneNode_b' to neighbours of small a 
	                    // vector of the cloneNodeG 
	                    cloneNode_a.neighbours.add(cloneNode_b);   // add current small bs as neighboors to small a
				 }
			 }
			
		 }
		 return map.get(source);
	 }
	 public Node buildGraph() 
	    { 
	        /* 
	            Note : All the edges are Undirected 
	            Given Graph: 
	            1--2 
	            |  | 
	            4--3 --5
	        */
	        Node node1 = new Node("A",1); 
	        Node node2 = new Node("B",2); 
	        Node node3 = new Node("C",3); 
	        Node node4 = new Node("D",4); 
	        Node node5 = new Node("E",5); 
	        Node node6 = new Node("F",6); 
	        List<Node> v = new ArrayList<Node>(); 
	        v.add(node2); 
	        v.add(node4); 
	        node1.neighbours = v; 
	        v = new ArrayList<Node>(); 
	        v.add(node1); 
	        v.add(node3); 
	        node2.neighbours = v; 
	        v = new ArrayList<Node>(); 
	        v.add(node2); 
	        v.add(node4); 
	        v.add(node5); 
	        v.add(node6); 
	        node3.neighbours = v; 
	        v = new ArrayList<Node>(); 
	        v.add(node3); 
	        v.add(node1); 
	        node4.neighbours = v; 
	        return node1; 
	    } 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphRecursiveInterateDFS graph = new GraphRecursiveInterateDFS(); 
	         Node source = graph.buildGraph(); 
	        System.out.println("Iterate DFS traversal of a graph"); 
	        graph.iterateDFS(source); 
	        System.out.println("Recursive DFS traversal of a graph"); 
	        graph.recursiveDFS(source);
	     
	        Node clone = graph.cloneGraphAB(source);
	        System.out.println("DFS traversal cloned graph!");
	        graph.recursiveDFS(clone); 
	        
	        
	}

}
