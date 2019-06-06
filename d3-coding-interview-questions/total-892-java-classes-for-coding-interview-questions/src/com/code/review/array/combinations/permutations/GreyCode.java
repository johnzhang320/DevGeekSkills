package com.code.review.array.combinations.permutations;

import java.util.ArrayList;
import java.util.List;

import com.code.utils.LinkedList.Utils;

public class GreyCode {
	/**
	 * The gray code is a binary numeral system where two successive values differ in only
	 *  one bit.

		Given a non-negative integer n representing the total number of bits in the code, 
		print the sequence of gray code. A gray code sequence must begin with 0.
		
		For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
		
		00 - 0
		01 - 1
		11 - 3
		10 - 2
		
		There might be multiple gray code sequences possible for a given n.
		Return any such sequence.
		My Approach
		This is binary add problem
		n is number digits, each number has different sequence by one bit from another
		different from normal binary add is when highest carry , it will not add to n+1th bit just nth
		 
	 * @param args
	 */
	 public static List<String> grayCode(int n) {
		 if (n==0) return null;
		 List<String> result = new ArrayList<String>();		 
		 char greyCode[] =new char[n]; 
		 for (int i=0;i<n;i++) {
			 greyCode[i]='0';
		 }
		 String s = String.valueOf(greyCode);
		 result.add(s);
		 
		 for (int i=n-1; i>=0; i--) {
			 if (greyCode[i]=='0') {
				 greyCode[i]='1';
			 } 
			 s = String.valueOf(greyCode);
			 result.add(s);
		 }
		 for (int i=n-1; i>0; i--) {  // 0000 exists so 1000
			 if (greyCode[i]=='1') {
				 greyCode[i]='0';
			 } 
			 s = String.valueOf(greyCode);
			 result.add(s);
		 }
		 return result;
	}
	 public static List<Integer> grayCode2(int n) {
		 if (n==0) return null;
		 List<Integer> result = new ArrayList<Integer>();		 
		 char greyCode[] =new char[n]; 
		 for (int i=0;i<n;i++) {
			 greyCode[i]='0';
		 }
		 String s = String.valueOf(greyCode);
		 result.add(Integer.valueOf(s));
		 
		 for (int i=n-1; i>=0; i--) {
			 if (greyCode[i]=='0') {
				 greyCode[i]='1';
			 } 
			 s = String.valueOf(greyCode);
			 result.add(Integer.valueOf(s));
		 }
		 for (int i=n-1; i>0; i--) {  // 0000 exists so 1000
			 if (greyCode[i]=='1') {
				 greyCode[i]='0';
			 } 
			 s = String.valueOf(greyCode);
			 result.add(Integer.valueOf(s));
		 }
		 return result;
	}
	  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list= grayCode2(4);
		
		 	
		for (Integer s: list) {
			System.out.println(s);
		}
	}

}
