package com.code.review.middle.matrix;
public class Search2DMatrixII {
/**
 * 
 * LeetCode – Search a 2D Matrix II (Java)
 
	Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
	
	Integers in each row are sorted in ascending from left to right.
	Integers in each column are sorted in ascending from top to bottom.
	
	
	For example, consider the following matrix:
	
	[
	  [1,   4,  7, 11, 15],
	  [2,   5,  8, 12, 19],
	  [3,   6,  9, 16, 22],
	  [10, 13, 14, 17, 24],
	  [18, 21, 23, 26, 30]
	]
	Given target = 5, return true.
 *
 */
/**
 * 
 * In a naive approach, we can use the matrix boundary to reduce the search space. Here is a simple recursive implementation.
 * This is similiar to maze
 *
 */
public boolean searchMatrix(int[][] matrix, int target) {
    int i1=0; 
    int i2=matrix.length-1;
    int j1=0;
    int j2=matrix[0].length-1;
 
    return helper(matrix, i1, i2, j1, j2, target);
}
 
public boolean helper(int[][] matrix, int i1, int i2, int j1, int j2, int target){
 
    if(i1>i2||j1>j2)
        return false;
 
    for(int j=j1;j<=j2;j++){
        if(target < matrix[i1][j]){
            return helper(matrix, i1, i2, j1, j-1, target);
        }else if(target == matrix[i1][j]){
            return true;
        }
    }
 
    for(int i=i1;i<=i2;i++){
        if(target < matrix[i][j1]){
            return helper(matrix, i1, i-1, j1, j2, target);
        }else if(target == matrix[i][j1]){
            return true;
        }
    }
 
    for(int j=j1;j<=j2;j++){
        if(target > matrix[i2][j]){
            return helper(matrix, i1, i2, j+1, j2, target);
        }else if(target == matrix[i2][j]){
            return true;
        }
    }
 
    for(int i=i1;i<=i2;i++){
        if(target > matrix[i][j2]){
            return helper(matrix, i1, i+1, j1, j2, target);
        }else if(target == matrix[i][j2]){
            return true;
        }
    }
 
    return false;
}
/**
 *  Second solution : target is less than matrix[i][j] , search upward less element which it change less from bottom to up rows
 *  if it is greater than element , search right side cause it change greated from left to right
 * @author vn0zq5b
 *
 */
	public static boolean searchMatrixII(int[][] matrix, int target) {
		int cols = matrix[0].length-1;
		int rows = matrix.length-1;
		int row = rows;
		int col = 0;
		// search from bottom and left to right
		while (row>=0 && col<=cols) {
			if (target<matrix[row][col]) {
				row--;  // if target is less than elemen, go upward
			}
			if (target>matrix[row][col]) {
				col++;   //if target > elem , go from left to right
			}
			if (target==matrix[row][col]) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
