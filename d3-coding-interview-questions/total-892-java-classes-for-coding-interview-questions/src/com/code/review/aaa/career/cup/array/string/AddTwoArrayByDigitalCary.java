package com.code.review.aaa.career.cup.array.string;

public class AddTwoArrayByDigitalCary {
	/**
	 * 
	 *  google-interview-questions
 

		write a method that takes in 2 int arrays of any size and returns an array that calculates the
		sum of both.
		
		for example, [1,2,3] and [2,3,4] will return [3,5,7]
		
		Or [1,2,3] and [2,3,5,5] will return [2,4,7,8]
		
		however, if it's like [9,9,2] and [0,1,3] you need to carry the sum so it returns as [1,0,0,5]
		
		** SINGLE DIGIT ONLY
	 * 
	 */
	/**
	 *  (1) create indexes p1= A.length-1, p2 = B.length-1, carry = 0; int C[] = new int[max(p1,p2)+1]; p3 = C.length-1;
	 *  (2) create carry = A[p1] + A[p2] + carry  
	 *  (3) if carry >9 , digit = carry %10, carry = carry/10;
	 *  (4) if carry <=9 , digit = carry; carry=0;
	 *  (5) C[p3--] = digit, p1--; p2--;
	 *  (6) after iteration, if carry>0; C[p3]=carry;
	 *   
	 */
	public static int[] addTwoArrays(int A[], int B[]) {
		if (null==A || null==B || 0==A.length || 0==B.length) return null;
		int p1 = A.length-1;
		int p2 = B.length-1;
		int clen =Math.max(A.length,B.length)+1;
		
		int C[] = new int[clen];
		int p3 = C.length-1;
		int carry = 0;		 
		
		while(p1>=0 || p2>=0) {
			if (p1>=0) {
				carry +=A[p1--];
			}
			if (p2>=0) {
				carry +=B[p2--];
			}
			if (carry>9) {
				C[p3--] = carry%10;
				carry = carry/10;
			} else if (carry<=9) {
				C[p3--] = carry;
				carry = 0;
			}
		}
		if (carry>0) {
			C[p3]=carry;
		}
		return C;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A1[] = {1,2,3};
		int B1[] = {2,3,4};
		int A2[] = {1,2,3};
		int B2[] = {2,3,5,5};
		int A3[] = {9,9,2};
		int B3[] = {0,1,3};
		int C1[] = addTwoArrays(A1, B1);
		int C2[] = addTwoArrays(A2, B2);
		int C3[] = addTwoArrays(A3, B3);
		for (Integer i:C1)
		System.out.print(i+",");
		System.out.println(" ");
		
		for (Integer i:C2)
		System.out.print(i+",");
		System.out.println(" ");
		
		for (Integer i:C3)
		System.out.print(i+",");
		System.out.println(" "); 		

	}

}
