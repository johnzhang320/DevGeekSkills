package com.code.review.matrix.mdidle.backtracking;

import com.code.review.matrix.mdidle.backtracking.MazeGame.Cell;

public class LC490BollingMaze {
/**
 *  There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

	Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
	
	The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
	
	Example 1
	
	Input 1: a maze represented by a 2D array
	
	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0
	
	Input 2: start coordinate (rowStart, colStart) = (0, 4)
	Input 3: destination coordinate (rowDest, colDest) = (4, 4)
	--------------------- 
	作者：魔豆Magicbean 
	来源：CSDN 
	原文：https://blog.csdn.net/magicbean2/article/details/78706612 
	版权声明：本文为博主原创文章，转载请附上博文链接！
 * 
 *   This is standard maze question
 *   using recursive (DBF) to solve this problem
 *   check if row >=0 && row<maze.length && col >=0 && col<maze[0].length
 *   
 *   又是图的遍历问题，就是简单的遍历，所以dfs和bfs都可以做，复杂度也是一样的。这道题要求球不能停下来，即使碰到destination，必须是碰到wall才能停下来。
 */
	final int PASSAGE=0;
	final int VISITED=2;
	final int dist[]= {4,4};
	static int [][] mazeArray = {
			{0, 0, 1, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 1, 0},
			{1, 1, 0, 1 ,1},
			{0, 0, 0, 0, 0},
	};
	public boolean recursiveMaze(int row, int col,int dist[]) {
 		boolean done = false;
   		
 		if (row>=0 && row< mazeArray.length && col>=0 && col < mazeArray[0].length) {	 	
 			
 			if (mazeArray[row][col]==PASSAGE) {
 				
		 		mazeArray[row][col] = VISITED;
		 		
		 		if (row==dist[0] && col==dist[1]) {
		 			done = true;
		 		} else {
		 			// reduce complex by writing four directory DFS , try four times make code readable
		 			// one direction could not find the dist, try rest directions
		 			done = recursiveMaze(row-1, col,dist);
		 			if (!done) {
		 				done = recursiveMaze(row+1,col,dist);
		 			}
		 			if (!done) {
		 				done = recursiveMaze(row,col-1,dist);
		 			}
		 			if (!done) {
		 				done = recursiveMaze(row,col+1,dist);
		 			}
		 			 
		 		}
 			}
 		}
 		return done;
 	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 LC490BollingMaze ref = new  LC490BollingMaze ();
		System.out.println("MazeSearchByRecursion");
 		//ref.setEntryCell(new Cell(5,12));
 		//ref.setExitCell(new Cell(3,0));
		int dist[] = {4,4};
 		Boolean done =ref.recursiveMaze(0,4,dist);
 		
 		System.out.println("Reachable " +done);
 		
 		for (int i=0; i<mazeArray.length;i++) {
 			System.out.println(" ");
 			for (int j=0; j<mazeArray[0].length;j++) {
 				System.out.print(mazeArray[i][j]+" ");
 			}
 		}
 		System.out.println(" ");
	}

}
