package com.code.review.array.string.easy.merge;

import java.util.Arrays;

public class MergeTwoSortedArrays {
	 /**
	  * 
	  *  @param argsYou may assume that A has enough space to hold additional elements from B. 
	  *  The number of elements initialized in A and B are m and n respectively.
	 	
	  *	Analysis
	 	
	  *	The key to solve this problem is moving element of A and B backwards. If B has some
	  *	 elements left after A is done, also need to handle that case.
	 	
	  *	The take away message from this problem is that the loop condition. 
	  *	This kind of condition is also used for merging two sorted linked list. 
	  */
	
	public static int [] MergeTwoSortedArrays(int A[],int m, int B[], int n) {
		 if (null==A || A.length==0) return null;
		 if (A.length==1 && m==1) return A;
		 if (A.length==2 && m==1 && n==1 ) {
			 if (A[0] > B[0]) {
				 A[1] = A[0];
				 A[0] = B[0];
				 return A;
			 } else {
				 A[1] = B[0];
				 return A;
			 }
		 }
		 while (m>0 && n>0) {
			 if (A[m-1] > B[n-1]) {
				 A[m+n-1] = A[m-1];
				 m--;
			 } else {
				 A[m+n-1] = B[n-1];
				 n--;
			 }
		 }
		 while (n>0) {
			 A[n+m-1] = B[n-1];
			 n--;
		 }
		 return A;
 	
	}
	/**
	 * A.length is long enough to contain A and B
	 * merge A and B element which are not zero together to A
	 * @param A
	 * @param B
	 * @return A
	 */
	public static int[] merge2SortedArray(int A[],int B[]) {
		if (null==A || A.length==0) return null;
		 
	    int alen=A.length-1;
	    while (A[alen]==0) {
	    		alen--;
	    }
	    alen++;
		int blen = B.length;
		
		System.out.println("alen="+alen+",blen="+blen);
		
		while (alen>0 && blen>0) {
			if (A[alen-1]>B[blen-1]) {
				A[alen+blen-1] = A[alen-1];
				alen--;
			} else {
				A[alen+blen-1] = B[blen-1];
				blen--;
			}
		}
		while (blen>0) {
			A[alen+blen-1]=B[blen-1];
			blen--;
		}
		return A;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int a[]={2,4,8,9,10,12,15,16,30,33,35,0,0,0,0,0,0,0,0,0,0};		
		 int b[]={0,2,3,4,5,6,8,10,20,31};
		 int result[] = MergeTwoSortedArrays(a,11, b,10);		 
		 
		 System.out.println(Arrays.toString(result));
		 int a1[]={2,4,8,9,10,12,15,16,30,33,35,0,0,0,0,0,0,0,0,0,0};		
		 int b1[]={0,2,3,4,5,6,8,10,20,31}; 
		 result = merge2SortedArray(a1,b1);	
		 System.out.println(Arrays.toString(result));
	}

}
