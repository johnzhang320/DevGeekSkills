package com.code.review.array.string.easy.reverse;

public class ReverseWordNoDataStructure {
	/**
	 * 
	 *  samsung-interview-questions
 

		We have a char array and we need to reverse it. say Char Array is �Northern California USA�, 
		need to print �USA California Northern�. Can�t use any other data structures or buffer. Can 
		only use a char temp.
		my approach
		This is char array (not String), we can create reverse sub routine
		First pass: we reverse entire char array
		Second pass:we reverse each word split by space
		in case spaces could be double or triple ...
		each time check prev!=A[i] then check if  prev!=' ' and A[i]==' '
		then we can reverse A with start and i-1 
	 */
	
	public static char[] reverse(char A[], int low, int high) {
		if (null==A || 0==A.length) return null;
		 
		while (low<high) {
			char temp = A[low];
			A[low] = A[high];
			A[high] = temp;
			low++;
			high--;
		}
		return A;
		
	}
	public static char[] reverseWords(char A[]) {
		if (null==A || 0==A.length) return null;
		reverse(A,0,A.length-1);
		int start=0;
		char prev = A[0];
		int i=1;
		while (i<A.length) {
			if (prev!=A[i]) {
				if (prev!=' ' && A[i]==' ') {					
					reverse(A,start, i-1);					
				}
				if (prev==' ' && A[i]!=' ') {
					start = i;
				}
			}
			prev = A[i];
			i++;
		}
		if (start<i) {
			reverse(A,start,i-1);
		}
		return A;
	}
	public static String reverseWordsBySplit(char A[]) {
		String str = new String(A);
		String s[] = str.split(" ");
		StringBuffer buf = new StringBuffer();
		for (int i=s.length-1; i>=0; i--) {
			buf.append(s[i]+" ");
		}
		return buf.toString();
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "USA California Northern  Hello World";
		//System.out.println(reverse(s.toCharArray(),0,s.length()-1));
		System.out.println(reverseWords(s.toCharArray()));
		
		System.out.println(reverseWordsBySplit(s.toCharArray()));
	}

}
