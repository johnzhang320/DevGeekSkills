package com.code.utils.LinkedList;

import java.util.Arrays;
import java.util.List;

public class Utils {
	public static void print(String str) {
		System.out.println(str);
	}
	public static void print(Integer str) {
		System.out.println(str);
	}
	public static void print(String method,Object str) {
		System.out.println(method+":"+str);
	}
	 
	public static void print(int [] arr) {
		 
		for (Integer i:arr) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
	}
	
	public static int [] print(List<Integer> arr) {
		int result[] = new int[arr.size()];
	 
		for (int i=0; i<arr.size();i++) {
			System.out.print(arr.get(i)+(i<arr.size()-1 ? ",":""));
			result[i] = arr.get(i);
		}
		System.out.println(" ");
		return result;
	}
	public static String[] printStr(List<String> arr) {
		String result[] = new String[arr.size()]; 
		for (int i=0; i<arr.size();i++) {
			
			System.out.print(arr.get(i)+(i<arr.size()-1 ? ",":""));
			result[i] = arr.get(i);
		}
		System.out.println(" ");
		return result;
	} 
    public static void print(Integer arr[]) {
		 
		for (Integer i:arr) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
	}
	public static void print(char arr[]) {
	 
		for (Character i:arr) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
	}
	public static void print(String arr[]) {
		 
		for (String i:arr) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
	}
	
	public static void main(String args[]) {
	   int arr [] = {2, 4, 8, 10};
	   
	   print(arr);
	}
}
