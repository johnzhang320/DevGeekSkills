package com.code.review.aaa.facebook.code.lab;

import com.code.review.utils.Utils;

public class FindMinDiff3SortedArrays {
	/**
	 * You are given 3 arrays A, B and C. All 3 of the arrays are sorted.

		Find i, j, k such that :
		max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is minimized.
		Return the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i]))
		
		**abs(x) is absolute value of x and is implemented in the following manner : **
		
		      if (x < 0) return -x;
		      else return x;
		
		Example :
		
		Input : 
		        A : [1, 4, 10]
		        B : [2, 15, 20]
		        C : [10, 12]
		
		Output : 5 
		         With 10 from A, 15 from B and 10 from C. 


	 * Binary Search:
	 * 1) Iterate over A all elements of A[]
	 *    a) BinarySearch for element just smaller than or equal to B[] or C[] and note difference
	 * 2) Repeat step 1 for B[] and C[]
	 * 3) Return overall minimum   
	 * Time complexity of this solution is O(nLogn)

		Efficient Solution Let ‘p’ be size of A[], ‘q’ be size of B[] and ‘r’ be size of C[]
		
		1)   Start with i=0, j=0 and k=0 (Three index variables for A,
		                                  B and C respectively)
		
		//  p, q and r are sizes of A[], B[] and C[] respectively.
		2)   Do following while i < p and j < q and k < r
		    a) Find min and maximum of A[i], B[j] and C[k]
		    b) Compute diff = max(X, Y, Z) - min(A[i], B[j], C[k]).
		    c) If new result is less than current result, change 
		       it to the new result.
		    d) Increment the pointer of the array which contains 
		       the minimum.

		Note that we increment the pointer of the array which has the minimum, because our 
		goal is to decrease the difference. Increasing the maximum pointer increases the 
		difference. Increase the second maximum pointer can potentially increase the difference.
	 */
	public static int [] findClost(int A[], int B[], int C[]) {
		int alen = A.length;
		int blen = B.length;
		int clen = C.length;
		int i=0, j=0, k= 0;
		int diff = Integer.MAX_VALUE;
		int result[] = new int[3];
		int res_i=0, res_j=0, res_k=0;
		while (i < alen && j < blen && k<clen) {
			// Find minimum and maximum of current three elements
			int min = Math.min(A[i], Math.min(B[j],C[k]));
			int max = Math.max(A[i], Math.max(B[j],C[k]));
			 // Update result if current diff is less than the min
	        // diff so far
			if (max-min < diff) {
				diff = max - min;
				res_i = A[i];
				res_j = B[j];
				res_k = C[k];
			}
			 // Increment index of array with smallest value
			if (diff==0) break;
			if (A[i]==min) i++;
			else if (B[j]==min) j++;
			else k++;
		}
		result[0] = res_i;
		result[1] = res_j;
		result[2] = res_k;
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			int A[] ={1, 4, 10};
			int B[] ={2, 15, 20};
			int C[]={10, 12};
			int result[] = findClost(A, B, C);
			System.out.println(result[0]+" in A[]");
			System.out.println(result[1]+" in B[]");
			System.out.println(result[2]+" in C[]");
	}

}
