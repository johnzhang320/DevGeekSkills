package com.code.review.aaa.career.cup.array.string;

import java.util.Arrays;

public class StringCompressInCount {
	/**
	 *  amazon-interview-questions
 
		Write function compress(char* strSource)
		it should do the following .
		repeating chars in sequence should be replaced with char & count, in case count is 1 no 
		need to add any integer.
		Example - AAAABBBCXYZEEEEPPPPPKKABC
		should be A4B3CXYZE4P5K2ABC.
		you are supposed to iterate the array only once, and modify the same input parameter, do not 
		create any new string.
	 *  
	 *  Java code, using char[] str
	 *  (1) parameter is char A[]
	 *  (2) create index p which pointer result section in A[]
	 *  (3) create char variable prev which points previous char
	 *  (4) create count = i-p, i = 1 to n-1
	 *  (5) check if prev != current char (curr), A[p] = prev; p++ ; 
	 *  (6) check if count>1  A[p] = count; p++ 
	 *  (7) always prev=A[i]
	 *  (8) return Arrays.copyOf(A,p);  (equiavlent C lauguage *p='\n')
	 *  
	 */
	public static String compressStringWithCount(char A[]) {
		if (null==A || 0==A.length) return null;
		int p = 0;
		char prev=A[0];
		int count=1;
		for (int i=1; i<A.length;i++) {			 
			if (prev != A[i]) {
				A[p] = prev;
				p++;
				if (count>1) {
					A[p] =(char)(count+'0');
					p++;
					
				}
				count=1;
			} else {
				count++;
			}
			prev = A[i];
		}
		return new String (Arrays.copyOf(A,p));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "AAAABBBCXYZEEEEPPPPPKKABC";
		System.out.println(compressStringWithCount(s.toCharArray()) );
	}

}
