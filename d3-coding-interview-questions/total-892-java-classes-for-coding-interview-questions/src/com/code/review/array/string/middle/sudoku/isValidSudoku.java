package com.code.review.array.string.middle.sudoku;

import java.util.HashSet;

import com.code.review.array.am.testing.solution2;

public class isValidSudoku {

/**
 * Question 1 from google
 

	Determine if a Sudoku is valid. 
	
	Example 2D 9X9 matrix:
*/
	
	public static int[][] sMatrix={
                    {5,3,4,6,7,8,9,1,2},
                    {6,7,2,1,9,5,3,4,8},
                    {1,9,8,3,4,2,5,6,7},
                    {8,5,9,7,6,1,4,2,3},
                    {4,2,6,8,5,3,7,9,1},
                    {7,1,3,9,2,4,8,5,6},
                    {9,6,1,5,3,7,2,8,4},
                    {2,8,7,4,1,9,6,3,5},
                    {3,4,5,2,8,6,1,7,9}
      };
	public boolean isValidSudoku() {
	      
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(!isValid(i,j)){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isValid(int a, int b) {
        HashSet<Integer> mySet = new HashSet<Integer>();
        // cache the occurrence and return false in case of a duplicate
        for (int i = 0; i < 9; i++) {
            if (mySet.contains(sMatrix[a][i])) {
                return false;
            }
            mySet.add(sMatrix[a][i]);
        }
        mySet.clear();
        for (int i = 0; i < 9; i++) {
            if (mySet.contains(sMatrix[i][b])) {
                return false;
            }
            mySet.add(sMatrix[i][b]);
        }
        // Check sub-box sMatrix[a][b] located.
        mySet.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = a / 3 * 3 + i, y = b / 3 * 3 + j;
                if (mySet.contains(sMatrix[x][y])) {
                    return false;
                }
                mySet.add(sMatrix[x][y]);
            }
        }       
        return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solution2 ref = new solution2 ();
		System.out.println(ref.isValidSudoku());
	}
}
