package com.code.review.middle.matrix;

public class Search2DMatrix {
/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has properties:
	
	1) Integers in each row are sorted from left to right. 
	2) The first integer of each row is greater than the last integer of the previous row. 10>7 , 23 >20
	
	
	For example, consider the following matrix:
	
	[
	  [1,   3,  5,  7],
	  [10, 11, 16, 20],
	  [23, 30, 34, 50]
	]
	target =3 return true
	
	This is typical binary search matrix, we consider all elements as an array, 
	initially low =0 and high = rows x cols -1;
	mid = low+(high-low)/2
	midx = mid/cols
	midy = mid%cols
	check matrix[midx][midy] == > < target 
	
 */
	 /**
     *  This is typical binary search matrix, we consider all elements as an array, 
        initially low =0 and high = rows x cols -1;
        mid = low+(high-low)/2
        midx = mid/cols
        midy = mid%cols
        check matrix[midx][midy] == > < target 
     */
    public boolean searchMatrix(int[][] matrix, int target) {
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0;
        int end = row * col - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start)/2;
            int num = matrix[mid/col][mid%col];
            if (target == num) {
                return true;
            } else if (num < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return (matrix[start/col][start%col] == target || matrix[end/col][end%col] == target);
    }
    public boolean searchMatrix2(int matrix[][], int target) {
    	if (null == matrix || matrix.length==0 || matrix[0].length==0) return false;
    	int row = matrix.length;
    	int col = matrix[0].length;
    	int low = 0;
    	int high = row * col -1;
    	int mid;
    	while (low+1<high) {
    		mid = low+(high-low)/2;
    		int num = matrix[mid/col][mid%col];
    		if (num==target) {
    			return true;
    		} else if (num<target) {
    			low = mid;
    		} else {
    			high = mid;
    		}
    	}
    	return (matrix[low/col][low%col]==target || matrix[high/col][high%col]==target);
    }
}
