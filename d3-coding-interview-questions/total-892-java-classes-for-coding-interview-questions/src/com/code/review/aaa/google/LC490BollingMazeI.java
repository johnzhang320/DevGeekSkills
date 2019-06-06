package com.code.review.aaa.google;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.code.review.matrix.mdidle.backtracking.MazeGame.Cell;

public class LC490BollingMazeI {
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
	 
	public int [][] resetMaze() {
		int mazeArray [][]= {
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0},
				{1, 1, 0, 1 ,1},
				{0, 0, 0, 0, 0}
		};
		return mazeArray;
	}
	public boolean recursiveMaze(int mazeArray[][],int row, int col,int dist[]) {
 		boolean done = false;
   		
 		if (row>=0 && row< mazeArray.length && col>=0 && col < mazeArray[0].length) {	 	
 			
 			if (mazeArray[row][col]==PASSAGE) {
 				
		 		mazeArray[row][col] = VISITED;
		 		
		 		if (row==dist[0] && col==dist[1]) {
		 			done = true;
		 		} else {
		 			// reduce complex by writing four directory DFS , try four times make code readable
		 			// one direction could not find the dist, try rest directions
		 			done = recursiveMaze(mazeArray,row-1, col,dist);
		 			if (!done) {
		 				done = recursiveMaze(mazeArray,row+1,col,dist);
		 			}
		 			if (!done) {
		 				done = recursiveMaze(mazeArray,row,col-1,dist);
		 			}
		 			if (!done) {
		 				done = recursiveMaze(mazeArray,row,col+1,dist);
		 			}
		 			 
		 		}
 			}
 		}
 		return done;
 	}
	
	/**
	 * Other people sulotions 
	 *  DFS : create dirs [][] as dx, dy forward to up, low, left and right 
	 *        create visited [][] in same rows and cols as maze array[][], avoid modify maze[][]
	 *        if not wall and maze[x,y] is accessable, 
	 */
	   public boolean hasPathdfs(int[][] maze, int[] start, int[] destination) {
	        if(maze.length == 0 || maze[0].length == 0) return false;
	        if(start[0] == destination[0] && start[1] == destination[1]) return true;
	        
	        m = maze.length; n = maze[0].length;
	        boolean[][] visited = new boolean[m][n];
	        return dfs(maze, start, destination, visited);
	    }
	    int m, n;
	    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    
	    private boolean dfs(int[][] maze, int[] cur, int[] dest, boolean[][] visited) {
	        // already visited
	        if(visited[cur[0]][cur[1]]) return false;
	        // reach destination
	        if(Arrays.equals(cur, dest)) return true;
	        
	        visited[cur[0]][cur[1]] = true;
	        for(int[] dir : dirs) {
	            int nx = cur[0], ny = cur[1];
	            while(notWall(nx + dir[0], ny + dir[1]) && maze[nx+dir[0]][ny+dir[1]] != 1) {  // if not wall and accessable, go n step on same direction dir[x,y]
	                nx += dir[0]; ny += dir[1];
	            } 
	            if(dfs(maze, new int[] {nx, ny}, dest, visited)) return true;
	        }
	        return false;
	    }
	    
	    private boolean notWall(int x, int y) {
	        return x >= 0 && x < m && y >= 0 && y < n;
	    }
	    
	    /**
	     *  BFS solution
	     *  (1) initialize rows = maze.length, cols = maze[0].length;
	        (2) boolean visited[][] = new boolean[rows][cols];
	        (3) int dx[] = {0,-1,0,1} int dy[] = {-1,0,1.0};
	        (4) save current x, y position into Queue<int[]>
	     */ 
	    public boolean hasPath(int[][] maze, int start[] , int[] destinations) {
	        int rows = maze.length,cols = maze[0].length;
	        boolean visited[][] = new boolean[rows][cols];
	        int PASSABLE=0;
	        
	        int[] dx = new int[]{0, -1, 0, 1};
		    int[] dy = new int[]{1, 0, -1, 0};
	        Queue<int[]> queue = new LinkedList<int[]>();
	        queue.offer(start);
	       visited[start[0]][start[1]] = true;
	        while(!queue.isEmpty()) {
	            int curPos[] = queue.poll();
	            if (curPos[0]==destinations[0] && curPos[1]==destinations[1]) {
	                return true;
	            }
	            // try for directions until it hits the wall
	            for (int dir=0;dir<4;dir++) {
	                int row = curPos[0];
	                int col = curPos[1];
	                while(row>=0 && row<rows && col>=0 && col<cols && maze[row][col]==PASSABLE) {
	                    row+=dx[dir];
	                    col+=dy[dir];
	                }
	                // hits wall and go back
	                row -=dx[dir];
	                col -=dy[dir];
	                if (!visited[row][col]) {
	                    visited[row][col] = true;
	                    int newPos[] = new int [] {row,col};
	                    queue.offer(newPos);
	                }
	            } 
	        }
	        return false;
	    }
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 LC490BollingMazeI ref = new  LC490BollingMazeI ();
		System.out.println("MazeSearchByRecursion");
 		//ref.setEntryCell(new Cell(5,12));
 		//ref.setExitCell(new Cell(3,0));
		int dist[] = {4,4};
		int mazeArray[][] = ref.resetMaze();
 		Boolean done =ref.recursiveMaze(mazeArray,0,4,dist);
 		
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
