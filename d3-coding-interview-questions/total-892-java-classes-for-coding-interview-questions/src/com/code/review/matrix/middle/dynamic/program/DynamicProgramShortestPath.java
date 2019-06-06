package com.code.review.matrix.middle.dynamic.program;

import java.util.Stack;

import junit.framework.TestCase;
/**
 * Because Recursion is easy and straightforward choice for DFS , BFS and BackTracking, however in real world
 * it is too expensive to be applied because it store parameter, return address to runtime 
 * stack, it 's slow and more serious problem is runtime stack is very limit space and very easy to overflow
 * we explicitly trend to apply the java stack or queue to implement the algorithm like backtracking, DFS and BFS  
 * the data structure of stack and queue are using heap memory which is much larger than runtime stack and very 
 * easy to store subproblem solution into data structure to avoid each time calculate them repeatedly.
 * we save coordinate x and y of matrix as Cell object into stack or queue 
 * @author John Zhang
 *
 */
public class DynamicProgramShortestPath extends TestCase {
	DynamicProgramShortestPath ref ;
	public void setUp() {
		ref = new DynamicProgramShortestPath();
	}
	/**
	 *  Using Dynamic Programming to solve the shortest path problem
	 *  1. f(i) = min(f(j)+dij)) substructure to calculate total f(i). j is node which has connection to i node dij is distance between
	 *     i and j
	 *  2. Calculate Shortest Path Min sum f[10]=13
	 *  
		(f[0]=0 + distance[0][1]=5) = 5
		Min sum f[1]=5
		(f[0]=0 + distance[0][2]=3) = 3
		Min sum f[2]=3
		(f[1]=5 + distance[1][3]=1) = 6
		(f[2]=3 + distance[2][3]=8) = 11
		Min sum f[3]=6
		(f[1]=5 + distance[1][4]=6) = 11
		Min sum f[4]=11
		(f[1]=5 + distance[1][5]=3) = 8
		Min sum f[5]=8
		(f[2]=3 + distance[2][6]=4) = 7
		Min sum f[6]=7
		(f[3]=6 + distance[3][7]=5) = 11
		(f[4]=11 + distance[4][7]=5) = 16
		Min sum f[7]=11
		(f[3]=6 + distance[3][8]=6) = 12
		Min sum f[8]=12
		(f[5]=8 + distance[5][9]=8) = 16
		(f[6]=7 + distance[6][9]=3) = 10
		Min sum f[9]=10
		(f[7]=11 + distance[7][10]=3) = 14
		(f[8]=12 + distance[8][10]=4) = 16
		(f[9]=10 + distance[9][10]=3) = 13
		Min sum f[10]=13
		Node 0 to Node 10 distance is 13
		Node 0 to Node 10 path is 0-->2-->6-->9-->10-->
	 *  
	 *  3. Create adjacent matrix
	 */
	 int cityDistance[][] = {
			 		//       0 1 2 3 4 5 6 7 8 9 10   --- j cols
			 //i --	/*0*/	{0,5,3,0,0,0,0,0,0,0,0},
			 		/*1*/	{0,0,0,1,6,3,0,0,0,0,0},
			 		/*2*/	{0,0,0,8,0,0,4,0,0,0,0},
			 		/*3*/	{0,0,0,0,0,0,0,5,6,0,0},
			 		/*4*/	{0,0,0,0,0,0,0,5,0,0,0},
			 		/*5*/	{0,0,0,0,0,0,0,0,0,8,0},
			 		/*6*/	{0,0,0,0,0,0,0,0,0,3,0},
			 		/*7*/	{0,0,0,0,0,0,0,0,0,0,3},
			 		/*8*/	{0,0,0,0,0,0,0,0,0,0,4},
			 		/*9*/	{0,0,0,0,0,0,0,0,0,0,3},
			 		/*10*/	{0,0,0,0,0,0,0,0,0,0,0} 
	 					};
	 // Calculate shortest distance between node 0 and node 10 using dynamic programming
	
	 
	 
	 
	 public int[] calShortestDistance(int distance[][]) {
		 if (null==distance || distance.length==0) return null;
		 
		  
		 int f[] = new int[distance.length];
		  
		 f[0]=0;
		 for (int i=1; i<distance.length; i++) {
			 int k = Integer.MAX_VALUE;
			 for (int j=0; j<i; j++) {
				 if (distance[j][i]!=0) {
					 System.out.println("(f["+j+"]="+f[j]+" + distance["+j+"]["+i+"]="+distance[j][i]+") = "+(f[j]+distance[j][i]));
					 if (f[j]+distance[j][i] < k) {
						 k = f[j]+distance[j][i];
					 }
				 }
			 }
			 f[i] = k;
			 System.out.println("Min sum f["+i+"]="+f[i]);
		 }
		 return f;
	 }
	 
		 
	 
	 public String calShortestPath(int distance[][], int f[]) {
		 Stack<Integer> stack = new Stack<Integer>();
		 StringBuffer result = new StringBuffer();
		 int j = distance.length-1;
		 stack.add(j);   // add tail node
		 while (j>0) {
			 // check current column all row elements
			 for (int i=0;i<j;i++) {
				 
				 if (distance[i][j]!=0) {
					 // find prevent node make f[i] make f[j] = f[i]+ distance[i][j]
					 if (f[i]==f[j]-distance[i][j]) {
						 stack.add(i);
						 
					 }
				 }
			 }
			 j = stack.peek();
		 }
		 
		 while(!stack.isEmpty()) {
			 result.append(stack.pop()).append("-->");
		 }
		 return result.toString();
	 }
	
	 
	 

	 
	 public void testCalShortestPath() {
		 System.out.println("Calculate Shortest Path");
		 int f[] = ref.calShortestDistance(cityDistance);
		 String path = ref.calShortestPath(cityDistance, f);
		 System.out.println("Node 0 to Node 10 distance is "+f[f.length-1]);
		// Utils.print(f);
		 System.out.println("Node 0 to Node 10 path is "+path);
		 
	 }
	 
	 
	 
}
