package com.code.review.aaa.facebook.code.lab;

public class PrintDiagonalSquareMatrix2D {
	/**
	 * Give a N*N square matrix, return an array of its anti-diagonals. Look at the example for more details.
		Example:
				
		Input: 	
		
		1 2 3
		4 5 6
		7 8 9
		
		Return the following :
		
		[ 
		  [1],
		  [2, 4],
		  [3, 5, 7],
		  [6, 8],
		  [9]
		]
		
		
		Input : 
		1 2
		3 4
		
		Return the following  : 
		
		[
		  [1],
		  [2, 3],
		  [4]
		]


	 *  Two points , 
	 *  from left to right
	 *  currRow start with row and decrease, currCol starts with 0 and increase
	 *  then A[currRow][currCol] will be diagonal elements
	 *  from left to right
	 *  currRow start with 1 and increase, currCol starts with cols-1 and decrease
	 *  then A[currRow][currCol] will be diagonal elements
	 *  Create two loop make this happen 
	 */
	public static void printDiagonalSquare2DMatrix(int A[][]) {
		if (null==A || 0==A.length) return;
	    int cols = A.length;
	    int rows = A[0].length;
	    // 
	    System.out.println("[");
	    //  from left to right
		//  currRow start with row and decrease, currCol starts with 0 and increase

	    for (int row = 0 ; row<rows; row++) {
	    	int currRow = row;
	    	int currCol = 0;
	    	System.out.print("\n[");
	    	while (currRow>=0 && currCol>=0 && currRow<rows && currCol<cols) {
	    		System.out.print(A[currRow][currCol]+" ");
	    		currRow--;
	    		currCol++;
	    	}
	    	System.out.print("]\n");
	    }
		//  currRow start with 1 and increase, currCol starts with cols-1 and decrease
	    for (int row = 1 ; row<rows; row++) {
	    	int currRow = row;
	    	int currCol = cols-1;
	    	System.out.print("\n[");
	    	while (currRow>=0 && currCol>=0 && currRow<rows && currCol<cols) {
	    		System.out.print(A[currRow][currCol]+" ");
	    		currRow++;
	    		currCol--;
	    	}
	    	System.out.print("]\n");
	    }
	    System.out.println("]");
		 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[][] = {
				{1, 2, 3},		
				{4, 5, 6},
				{7, 8, 9}
		};
		printDiagonalSquare2DMatrix(A);
		
		int A2[][] = {
				{1, 2, 3, 9, 10},		
				{4, 5, 6, 6, 11},
				{7, 8, 9, 7, 12},
				{5, 1, 4, 9, 13},
				{9, 28, 22, 7, 16},
		};
		printDiagonalSquare2DMatrix(A2);
	}

}
