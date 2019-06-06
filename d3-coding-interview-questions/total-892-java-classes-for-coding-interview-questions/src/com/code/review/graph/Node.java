package com.code.review.graph;

import java.util.ArrayList;
import java.util.List;

 

public class Node {
	  
	    int val; 
	    String name;
	    // A neighbour Vector which contains references to 
	    // all the neighbours of a Node 
	    List<Node> neighbours;  
	    public Node(String name,int val) 
	    { 
	    	this.name=name;
	        this.val = val; 
	        neighbours = new ArrayList<Node>(); 
	    } 
	} 
 
