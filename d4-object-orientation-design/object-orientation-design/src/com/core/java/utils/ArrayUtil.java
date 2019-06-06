package com.core.java.utils;

import java.util.Arrays;

public class ArrayUtil {
	public static <T> T[] append(T[] arr, T element) {
	    final int N = arr.length;
	    arr = Arrays.copyOf(arr, N + 1);
	    arr[N] = element;
	    return arr;
	}
	public static void display(int arr[]) {
		System.out.println(" ");;
		for (Integer i:arr) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
	}
	public static void display(Integer arr[]) {
		System.out.println(" ");;
		for (Integer i:arr) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
	}
	public static void display(char arr[]) {
		System.out.println(" ");;
		for (Character i:arr) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
	}
	public static void display(String arr[]) {
		System.out.println(" ");;
		for (String i:arr) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
	}
}
