package com.code.review.middle.number;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class CombineMaxNumber {
	 /**
     * LeetCode � Largest Number (Java) 
� 
		Given a list of non negative integers, arrange them such that they 
		form the largest number.
		For example, given [3, 30,4,2, 34, 5, 9], the largest formed number is 9534330. 
		(Note: The result may be very large, so you need to return a string instead 
		of an integer.)
		Analysis
		This problem can be solve by simply sorting strings, not sorting integer. 
		Define a comparator to compare strings by concat() right-to-left or left-to-right.
		Java Solution
		Simple solution, do not need compare function of objects
		(1) Sort array by Arrays.sort(str[]) in ascend
		(2) from end of A.length-1 to 0 , reversely append to stringbuffer 
     */
    public String CombineMaxNumber(int arr[]) {
	   	 String str [] = new String[arr.length];
	   	 for (int i=0;i<arr.length;i++) {
	   		 str[i] = String.valueOf(arr[i]);
	   	 }
	   	/* Arrays.sort(str, new Comparator<String>() {
	   		 public int compare(String a, String b) {
	   			 return (b+a).compareTo(a+b);
	   		 }
	   	 });*/
	   	 Arrays.sort(str);
	   	 for (String s: str) {
	   		 System.out.print(s+" ");
	   	 }
	   	 System.out.println(" ");
	   	 StringBuffer buf = new StringBuffer();
	   	 for (int i=str.length-1;i>=0;i--){
	   		 buf.append(str[i]);
	   	 }
	   	 return buf.toString();
    }
    
    public String CombineMaxNumber2 (int arr[]) {
	   	 String str [] = new String[arr.length];
	   	 for (int i=0;i<arr.length;i++) {
	   		 str[i] = String.valueOf(arr[i]);
	   	 }
	   	/* Arrays.sort(str, new Comparator<String>() {
	   		 public int compare(String a, String b) {
	   			 return (b+a).compareTo(a+b);
	   		 }
	   	 });*/
	   	 //Arrays.sort(str);
	   	 //Collections.sort(strs, (s1, s2) -> s2.compareTo(s1));
	    
	   	 for (String s: str) {
	   		 System.out.print(s+" ");
	   	 }
	   	 System.out.println(" ");
	   	 StringBuffer buf = new StringBuffer();
	   	 for (int i=str.length-1;i>=0;i--){
	   		 buf.append(str[i]);
	   	 }
	   	 return buf.toString();
   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {3, 30,4,2, 34, 5, 9};
		CombineMaxNumber ref = new CombineMaxNumber();
		System.out.println(ref.CombineMaxNumber(arr));
	}

}
