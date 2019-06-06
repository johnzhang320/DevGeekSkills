package com.code.review.facebook.code.lab;

import java.util.ArrayList;
import java.util.List;

public class Plus1 {
	/**
	 * 
	 * Given a non-negative number represented as an array of digits,

		add 1 to the number ( increment the number represented by the digits ).
		
		The digits are stored such that the most significant digit is at the head of the list.
		
		Example:
		
		If the vector has [1, 2, 3]
		
		the returned vector should be [1, 2, 4]
		
		as 123 + 1 = 124.
		
		    NOTE: Certain things are intentionally left unclear in this question which you should practice asking the interviewer.
		    For example, for this problem, following are some good questions to ask :
		
		        Q : Can the input have 0’s before the most significant digit. Or in other words, is 0 1 2 3 a valid input?
		
		        A : For the purpose of this question, YES
		        Q : Can the output have 0’s before the most significant digit? Or in other words, is 0 1 2 4 a valid output?
		        A : For the purpose of this question, NO. Even if the input has zeroes before the most significant digit.

		My Approach
		Reversely add all element of array to ArrayList<Integer> except the most significant digits is zero
		Extremely 999 add 1 must be 1000
	 */
	public static List<Integer> plus1(int A[]) {
		if (null==A || 0==A.length) return null;
		List<Integer> curr= new ArrayList<Integer>();
		int carry=0;
		int n=A.length;
		if (A[n-1]==0 ) {
		   if (n-2>0) {
			   n++;
			} else {
				if (A[n-1]<9) {
					curr.add(A[n-1]+1);
				} else {
					curr.add(10);
				}
				return curr;
			}
		}
		for (int i=n-1;i>=0;i--) {
			if (i==n-1) {
				if (A[i]+1>9) {
					carry++;
					A[i]=0;
				} else {
					A[i]++;
				}
			} else {
				if (A[i]+carry<9) {
					A[i]+=carry;
					carry=0;
				} else {
					A[i]=0;
				}
			}	
		} 
		 
		if (carry==1) {
			curr.add(1);
		}
		for (int i=0;i<n;i++) {
			curr.add(A[i]);
		}
		return curr;
	}
	/**
	 *  
	 *  (1) Most safe convert A[] to a real integer by loc*A[i] ; loc *=10; i = A.length-1 to 0
	 *  (2) convert integer as String, get char -'0' to integer at each digit, add all digit to result
	 *  
	 */
	public static List<Integer> plus1ByInteger(int A[]) {
		if (null==A || 0==A.length) return null;
		int sum = 0;
	 
		int loc=1;
		int i = A.length-1;
		while (i>=0) {			
			sum = sum+ A[i]*loc;
			loc *=10;
			i--;
		}
		sum +=1;
		String s = String.valueOf(sum);
		List<Integer> result = new ArrayList<Integer>();
		for (i = 0; i<s.length();i++) {
			int b =  s.charAt(i) - '0';
			result.add(b);
		}
		return result;
	}
	public ArrayList<Integer> plusOne(ArrayList<Integer> a) {
		   
		if (null==a || 0==a.size()) return null;
		int sum = 0;
	 
		int loc=1;
		int i = a.size()-1;
		while (i>=0) {			
			sum = sum+ a.get(i)*loc;
			loc *=10;
			i--;
		}
		sum +=1;
		String s = String.valueOf(sum);
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (i = 0; i<s.length();i++) {
			int b =  s.charAt(i) - '0';
			result.add(b);
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {9,9,9};
		List<Integer> result =  plus1ByInteger(A);
		for (Integer i: result) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
		int A2[] = {1,2,9};
		result =  plus1ByInteger(A2);
		for (Integer i: result) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
		int A3[] = {1,2,4};
		result =  plus1ByInteger(A3);
		for (Integer i: result) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
		
		int A4[] = {9};
		result =  plus1ByInteger(A4);
		for (Integer i: result) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
		int A5[] = {0,0,1,3,9};
		result =  plus1ByInteger(A5);
		for (Integer i: result) {
			System.out.print(i+" ");
		}
	}

}
