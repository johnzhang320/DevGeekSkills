package com.code.review.array.am.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.PriorityQueue;
public class solution1 {


 

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

	    public static void main(String[] args) {
	      // TODO Auto-generated method stub
	      List<String> list= grayCode(4);

	      for (String s: list) {
	        System.out.println(s);
	      }
	    }
	 

}
