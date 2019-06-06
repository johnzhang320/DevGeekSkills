package com.code.review.array.string.easy.number.calculate;

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
		
		        Q : Can the input have 0�s before the most significant digit. Or in other words, is 0 1 2 3 a valid input?
		
		        A : For the purpose of this question, YES
		        Q : Can the output have 0�s before the most significant digit? Or in other words, is 0 1 2 4 a valid output?
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
    (1) int len = A.length 
    (2) A[len-1] +=1;
    (3) i = len-1 to 1; check if A[i]==10 then A[i]=0 and A[i-1]+=1
         else return A[i]
    (4) if A[0]=10 , create result[len+1]
        
        
    */
   public int[] plusOne(int[] A) {
       if (null==A || A.length==0) return A;
       int len = A.length;
       A[len-1] +=1;
       for (int i=len-1;i>0;i--) {
           if (A[i]==10) {
               A[i]=0;
               A[i-1]+=1;
           } else {
               return A;
           }
       }
       if (A[0]==10) {
           int result[] = new int[len+1];
           result[0]=1;
           result[1]=0;
           for (int i=2;i<result.length-1;i++) {
               result[i] = A[i-1];
           }
           return result;
       }  
       return A;
       
       
   }
	
	public static int[] plus1ByInteger2(int A[]) {
		if (null==A || 0==A.length) return null;
		long sum = 0;
		// 
	    StringBuffer buf = new StringBuffer();
	    int len = A.length-1;
	    int leadingZero=0;
		for (int i=0;i<=len;i++) {
			 if (A[i]==0 && leadingZero<=1) {   // continue zero
				 leadingZero=1;
			 } else if (leadingZero==1) {  // A[i]!=0 first time
				 buf.append(A[i]);
				 leadingZero=2;
			 } else {
				 buf.append(A[i]);
			 }
		}
		System.out.println(buf);
		 
		sum = Long.valueOf(buf.toString());
		 
		sum +=1;
		String s = String.valueOf(sum);
		len = s.length();
		int result[] = new int [len];
		 
		for (int i = 0; i<s.length();i++) {
			int b =  s.charAt(i) - '0';
			result[i]=b; 
		}
		return result;
	}
	/**
	 * (1) sum=1 means we will plus one. pos =1
	 * (2) i=a.size()-1 to 0, sum += A[i]*pos,  p*=10
	 * (3) convert back to array or list
	 * @param a
	 * @return
	 */
	public ArrayList<Integer> plusOne(ArrayList<Integer> a) {
		   
		if (null==a || 0==a.size()) return null;
		int sum = 0;
	 
		int loc=1;
		int i = a.size()-1;
		while (i>=0) {			
			sum = sum*loc+ a.get(i);
			loc *=10;
			i--;
		}
		sum+=1; 
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
		//List<Integer> 
		int result[];
		result =  plus1ByInteger2(A);
		for (Integer i: result) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
		int A2[] = {1,2,9};
		result =  plus1ByInteger2(A2);
		for (Integer i: result) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
		int A3[] = {1,2,4};
		result =  plus1ByInteger2(A3);
		for (Integer i: result) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
		
		int A4[] = {9};
		result =  plus1ByInteger2(A4);
		for (Integer i: result) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
		int A5[] = {0,0,9,8,7,6,0,5,4,3,2,1,0};
		result =  plus1ByInteger2(A5);
		for (Integer i: result) {
			System.out.print(i+" ");
		}
		System.out.println("");
		int A52[] = {0,9,9,9,9,9};
		result =  plus1ByInteger2(A52);
		for (Integer i: result) {
			System.out.print(i+" ");
		}
	}

}
