package com.code.review.array.string.easy.binarySearch;

public class BinarySearchInSortedMatrix {
/**
 *  LeetCode – Search a 2D Matrix (Java)
 

		Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has properties:
		
		1) Integers in each row are sorted from left to right. 2) The first integer of each row is greater than the last integer of the previous row.
		
		For example, consider the following matrix:
		
		[
		  [1,   3,  5,  7],
		  [10, 11, 16, 20],
		  [23, 30, 34, 50]
		]
		
		Given target = 3, return true.
		
		This is a typical problem of binary search.

		You may try to solve this problem by finding the row first and then the column. There is no need 
		to do that. Because of the matrix's special features, the matrix can be considered as a sorted 
		array. Your goal is to find one element in this sorted array by using binary search
 * @param args
 */
	public static boolean SearchInSortedMatrix(int matrix[][],int target) {
		if (null==matrix || matrix.length==0 || matrix[0].length==0) {
			return false;
		}
       
		int maxRow = matrix.length;
		int maxCol = matrix[0].length;
		int low = 0;
		int high = maxRow*maxCol-1;
		//if (target < matrix[0][0] || target>matrix[maxRow-1][maxCol-1]) {
	     //  	return false;
	    //}
		
		while (low<=high) {
			int mid = low+(high-low)/2;
			int midX = mid/maxRow;
			int midY = mid%maxRow;
			System.out.println("mid="+mid+",maxRow="+maxRow+",midX="+midX+",midY="+midY);
			if (target<matrix[midX][midY]) {
				high = mid-1;
				
			} else if (target>matrix[midX][midY]) {
				low = mid+1;
			} else {
				return true;
			}
			
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrix [][] = 
		 {
		  {1,   3,  5,  7},
		  {10, 11, 16, 20},
		  {23, 30, 34, 50}
		 };
		int target = 34;
		System.out.println(SearchInSortedMatrix(matrix,target) ); 
		target = 15;
		System.out.println(SearchInSortedMatrix(matrix,target) );
		
		target = 0;
		System.out.println(SearchInSortedMatrix(matrix,target) );
		
		target = 50;
		System.out.println(SearchInSortedMatrix(matrix,target) );
	}

}
