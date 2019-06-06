package com.code.review.matrix.mdidle.backtracking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.code.review.utils.Utils;

import junit.framework.TestCase;

public class MazeGame extends TestCase {
/**
 *  Maze game is typical example of Backtracking, which means retaining information that allows resuming the initial
 *  search after underneath search reaches dead end. 
 *  Backtracking is typical recursive operation and stack operation
 *  '1'. Stack Solution
 *  Use: one array initialize the maze and one stack implements backtracking, mazeStack
 *  Suppose maze like an 2D array, '1's is wall and '0's is passage, one 'e' is exit and one 'm' is mouse
 *  '.' is visited 
 *  Create Cell class contain the row and column of mazeArray, entryCell = m position (5,12), exitCell = e (3,0)
 *  as below array
 *  Before a loop, currentCell = entryCell, in this loop check if not exitCell
 *  if get currentCell.x and currentCell.y , check if not entryCell, mazeArray[x][y] = visited
 *  then currentCell four direction check by pushing into mazeStack if mazeArray[x][y] is passage or is exit
 *  then check mazeStack.isEmpty() , failure
 *  otherwise mazeStack.pop(); // backtracking
 *  
 *  
 *  
 */
	public MazeGame () {
		entryCell = findCell('m');
		exitCell = findCell('e');
	}
	public MazeGame ref = null;
	public void setUp() {
		ref = new MazeGame();
	}
	public static char[][] mazeArray = 
	         {
        		 {'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
        		 {'1','1','1','1','0','1','1','0','0','0','1','1','1','1','1'},
        		 {'1','1','0','1','1','1','0','1','1','1','1','0','0','1','1'},
        		 {'e','0','0','0','0','1','0','1','0','1','0','1','0','0','1'},
        		 {'1','1','0','1','0','1','1','1','0','1','0','1','1','1','1'},
        		 {'1','1','0','1','0','0','0','0','0','0','0','m','0','1','1'},
        		 {'1','1','0','1','1','1','1','1','1','0','1','1','0','1','1'},
        		 {'1','1','0','0','0','0','0','0','0','0','0','0','0','0','1'},
        		 {'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}

             };	
	private Cell currentCell, exitCell, entryCell;
	private List<Cell> result = new ArrayList<Cell>();
	private Stack<Cell> mazeStack = new Stack<Cell>();
	private final static char EXIT='e';
	private final static char ENTRY='m';	
	private final static char VISITED='.';
	private final static char PASSAGE='0';
	private final static char WALL='1';
	 
	public Cell getExitCell() {
		return exitCell;
	}
	public void setExitCell(Cell exitCell) {
		this.exitCell = exitCell;
	}
	public Cell getEntryCell() {
		return entryCell;
	}
	public void setEntryCell(Cell entryCell) {
		this.entryCell = entryCell;
	}
	public MazeGame(Cell exitCell, Cell entryCell) {
		this.exitCell = exitCell;
		this.entryCell = entryCell;
	}
 	public class Cell {
 		public int x;
 		public int y;
 		public Cell(int x, int y) {
 			this.x = x;
 			this.y = y;
 		}
 		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;			 
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;			 
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
   	}
 	public void pushUnvisited(int row, int col) {
 		if (mazeArray[row][col]==PASSAGE || mazeArray[row][col] == EXIT) {
 			Cell cell = new Cell(row,col);
 			mazeStack.push(cell);
 			result.add(cell);
 		} 
 		
 	}
 	public Cell findCell(char c) {
 		 
 		for (int i=0; i<mazeArray.length;i++) {
 			 
 			for (int j=0; j<mazeArray[0].length;j++) {
 				if (mazeArray[i][j]==c) {
 					return new Cell(i,j);
 				}
 			}
 		}
 		return null;
 	}
 	public void mazeSearch() {
 		int row, col;
 		if (null==entryCell || null == exitCell) {
 			System.out.println("Not defined entryCell or exitCell");
 			return;
 		}
 		currentCell = entryCell;
 		while (!currentCell.equals(exitCell)) {
 			row = currentCell.x;
 			col = currentCell.y;
 			
 			// put visited to prevent infinited loop
 			if (!currentCell.equals(entryCell)) {
 				mazeArray[row][col] = VISITED;
 			}
 			// search four directions
 			pushUnvisited(row-1, col);  // downward
 			pushUnvisited(row+1, col);  // upward
 			pushUnvisited(row, col-1);  // left
 			pushUnvisited(row, col+1);  // right
 			if (mazeStack.isEmpty()) {
 				System.out.println("Search failure....");
 				return;
 			} else {
 				currentCell = mazeStack.pop(); // reached wall, backtracking
 				 
 			}
  		}
 		// code come here means currentCell == exitCell, find EXIT;
 		System.out.println("\nFind Exit successfully, show as following:");
 		for (Cell cell: result) {
 			 
 			System.out.print(cell.toString()+" ");
 		}
 		System.out.println("");
 	}
 /*	public void testMazeSearchByStack() {
 		System.out.println("MazeSearchByStack");
 		//ref.setEntryCell(new Cell(5,12));
 		//ref.setExitCell(new Cell(3,0));
 		ref.mazeSearch();
 		for (int i=0; i<mazeArray.length;i++) {
 			System.out.println(" ");
 			for (int j=0; j<mazeArray[0].length;j++) {
 				System.out.print(mazeArray[i][j]+" ");
 			}
 		}
 		System.out.println(" ");
 	}*/
 	/**
 	 * Recursion way to find path in maze game 
 	 * @param row
 	 * @param col
 	 * @return
 	 */
	 
 	public boolean recursiveMaze(int row, int col) {
 		boolean done = false;
 		
 		currentCell = new Cell(row, col);
 		
 		if (row>=0 && row< mazeArray.length || col>=0 && col < mazeArray[0].length) {	 	
 			
 			if (mazeArray[row][col]==PASSAGE) {
 				
		 		mazeArray[row][col] = VISITED;
		 		
		 		if (currentCell.equals(exitCell)) {
		 			done = true;
		 		} else {
		 			done = recursiveMaze(row-1, col);
		 			if (!done) {
		 				done = recursiveMaze(row+1,col);
		 			}
		 			if (!done) {
		 				done = recursiveMaze(row,col-1);
		 			}
		 			if (!done) {
		 				done = recursiveMaze(row,col+1);
		 			}
		 			 
		 		}
 			}
 		}
 		return done;
 	}
 	public void testMazeSearchByRecursive() {
 		System.out.println("MazeSearchByRecursion");
 		//ref.setEntryCell(new Cell(5,12));
 		//ref.setExitCell(new Cell(3,0));
 		ref.recursiveMaze(5,12);
 		for (int i=0; i<mazeArray.length;i++) {
 			System.out.println(" ");
 			for (int j=0; j<mazeArray[0].length;j++) {
 				System.out.print(mazeArray[i][j]+" ");
 			}
 		}
 		System.out.println(" ");
 	}
 	/** 
 	 * give sum subarray from array is typical backtracking using recursion
 	 * arr is not sorted
 	 * int candidates[] = {1, 4, 2, 15, 12, 6, 3,8};   	 
	 * int target = 18;
 	 */
 	public List<List<Integer>> combinationSum(int arr[], int target) {
 		List<List<Integer>> result = new ArrayList<List<Integer>>();
 		List<Integer> curr = new ArrayList<Integer>();
 		
 		helper(result,curr,0,target,arr);
 		return result;
 	}
 	public void helper(List<List<Integer>> result, List<Integer> curr, int start, int target, int[] arr) {
 		if (target == 0) {
 			result.add(new ArrayList<Integer> (curr));
 			return;
 		}
 		if (target<0) {
 			return ;
 		}
 		int prev = -1;
 		for (int i=start; i<arr.length;i++) {
 			if (prev != arr[i]) {
 				curr.add(arr[i]);
 				helper(result,curr,i+1,target - arr[i], arr);
 				curr.remove(curr.size()-1);
 			}
 			prev = arr[i];
 		}
 	}
 	public void testCombinationSum () {
    	Utils.print("CombinationSum" );
  	 	
	 	int candidates[] = {1, 4, 2, 15, 12, 6, 3,8};   	 
	 	int target = 18;
	    
	 	List<List<Integer>> result = ref.combinationSum(candidates, target); 
	    for (List<Integer> res: result) {
	    	System.out.print("\n[");
	    	for (Integer i:res) {
	    		System.out.print(i+" ");
	    	}
	    	System.out.println("]\n");
	    }
    }
 	/**
 	 *  Subarray Combination is given sum, we can print out and using private variable 
 	 *  to simplify the code 
 	 */
 	// set target sum , in code we just compare current summary with it
 	private final static int TARGET_SUM=18;
 	// each combination from i=start to recursive bottom
 	private Stack<Integer> resultStack = new Stack<Integer>();
 	// store summary in current elements in resultStack
    private int stackSum=0;	
    
    public void fetchSubarray(int[] arr, int start) {
    	// check if stackSum == TARGET_SUM, if yes, print out but do not return
    
    	
    	if (stackSum==TARGET_SUM) {    		
    		System.out.print("\n[ ");
    	 
    		for (Integer i: resultStack) {
    			System.out.print(i+" ");
    		}
    		System.out.println("]\n");
      	}
    	
    	for (int i=start; i< arr.length; i++) {
    	 
    		if (stackSum+arr[i]<=TARGET_SUM) {
    			
    			resultStack.push(arr[i]);
    			
    			stackSum+=arr[i];
    			
    			fetchSubarray(arr, i+1);
        		
    			stackSum -= resultStack.pop();   // if stackSum+arr[i]> TARGET_SUM, fetchSubarrary return to
    			                           // execute this statement last SP points in system stack
    			                            
    		}
     	}
    }
	public void testFetchSubarray() {
    	Utils.print("fetchSubarray" );
  	 	
	 	int arr[] = {1, 4, 2, 15, 12, 6, 3, 8};   	 
	  
	    
	 	ref.fetchSubarray(arr, 0); 
	    
    }
	/**
	 *  By stack Using iteration Find the sum of sub arrays combination from array is given sum
	 *     
	 */
	 public void sumByStackDFS(int arr[], int targetSum) {
		 for (int i=0; i<arr.length;i++) {
		     Stack<Integer> stack = new Stack<Integer>();
		     int stackSum = arr[i];
		     stack.add(arr[i]);
		     while (!stack.isEmpty()) {
		    	  int curr = stack.pop();
		    	
	    		 for (int j=i+1; j<arr.length;j++) {
	    			if (stackSum+arr[j]<=targetSum) {
	    				stack.push(arr[j]);
	    				stackSum+=arr[j];
	    			} 
	    		 }
		    	 
		     }
		 }
	 }
} 
