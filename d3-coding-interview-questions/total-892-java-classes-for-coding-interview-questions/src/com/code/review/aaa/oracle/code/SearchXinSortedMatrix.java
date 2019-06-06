package com.code.review.aaa.oracle.code;

public class SearchXinSortedMatrix {
	/**
	 * 
		Search in a row wise and column wise sorted matrix
		
		Given an n x n matrix and a number x, find position of x in the matrix if it 
		is present in it. Else print “Not Found”. In the given matrix, every row and 
		column is sorted in increasing order. The designed algorithm should have linear 
		time complexity.
		
		Example :
		
		Input : mat[4][4] = { {10, 20, 30, 40},
		                      {15, 25, 35, 45},
		                      {27, 29, 37, 48},
		                      {32, 33, 39, 50}};
		              x = 29
		Output : Found at (2, 1)
		
		Input : mat[4][4] = { {10, 20, 30, 40},
		                      {15, 25, 35, 45},
		                      {27, 29, 37, 48},
		                      {32, 33, 39, 50}};
		              x = 100
		Output : Element not found

	 *  (1) from top right check if element e > x, then move left
	 *  (2) if x>e then mean could found x in this column of e, then move down until find it  
	 */
	public static int[] searchXinSortedMatrix(int M[][], int x) {
		int ret[] = new int[2];
		int rows = M.length;
		int cols = M[0].length;
		for (int j=cols-1;j>=0; j--) {
			if (M[0][j]<=x) {
				int i=0;
				while(i<rows) {
					if (M[i][j]==x) {
						ret[0]=i;
						ret[1]=j;
						System.out.println("Found "+x + " at ["+i+"]"+"["+j+"]");
						return ret;
					}
					i++;
				}
			}
		}
		System.out.println("Not Found");
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int M[][]={ {10, 20, 30, 40},
                {15, 25, 35, 45},
                {27, 29, 37, 48},
                {32, 33, 39, 50}};
		int x = 29;
		searchXinSortedMatrix(M,x);
		
		x = 100;
		searchXinSortedMatrix(M,x);
	}

}
