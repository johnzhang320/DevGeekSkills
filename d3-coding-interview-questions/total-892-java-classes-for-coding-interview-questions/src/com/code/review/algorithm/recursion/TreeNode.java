package com.code.review.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

 

public class TreeNode {
	  
	    int time; 
	    String emp;
	    // A neighbour Vector which contains references to 
	    // all the neighbours of a Node 
	    List<TreeNode> neighbours;  
	    public TreeNode(String name,int val) 
	    { 
	    	this.emp=name;
	        this.time = val; 
	        neighbours = new ArrayList<TreeNode>(); 
	    } 
	} 
 
