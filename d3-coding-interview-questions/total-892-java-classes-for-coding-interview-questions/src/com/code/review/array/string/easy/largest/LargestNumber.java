package com.code.review.array.string.easy.largest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * Given a list of non negative integers, arrange them such that they form the largest number.

   For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330. 
   (Note: The result may be very large, so you need to return a string instead of an integer.)

 *
 */

public class LargestNumber {
	
	public String largestNumber2(List<Integer> A) {
		
		 List<String> strs = A.stream().map(n->String.valueOf(n)).collect(Collectors.toList());
		 strs.sort((s1,s2)->s2.compareTo(s1));
		 System.out.println(strs.toString());
		 StringBuilder sb = new StringBuilder();
		    for(String s: strs){
		        sb.append(s);
		    }
		 
		    if (sb.charAt(0)=='0'&&sb.length()>1)
		        sb.deleteCharAt(0);
		 
		    return sb.toString();
		 
	}
	
	public String largestNumber(int[] nums) {
	    String[] arr = new String[nums.length];
	    for(int i=0; i<nums.length; i++){
	        arr[i]=String.valueOf(nums[i]);
	    }
	 
	    Arrays.sort(arr, new Comparator<String>(){
	        public int compare(String a, String b){
	            return (b+a).compareTo(a+b);
	        }
	    });
	 
	    StringBuilder sb = new StringBuilder();
	    for(String s: arr){
	        sb.append(s);
	    }
	 
	    while(sb.charAt(0)=='0'&&sb.length()>1)
	        sb.deleteCharAt(0);
	 
	    return sb.toString();
	}
	public static void main(String args[])  {
		LargestNumber ln = new LargestNumber();
		List<Integer> A = new ArrayList<Integer>();
		A.add(3);
		A.add(30);
		A.add(34);
		A.add(5);
		A.add(9);
		System.out.println(ln.largestNumber2(A));
	}
}
