package com.code.review.array.string.hard.game;

import java.util.ArrayList;

public class NumberAndOperatorReachTarget {
	 
	//  Get Target Number Using Number List and Arithmetic Operations
 

	//	Given a list of numbers and a target number, write a program to determine whether the target
	//  number can be calculated by applying "+-*/" operations to the number list? You can assume ()
	//  is automatically added when necessary.
		
	//	For example, given {1,2,3,4} and 21, return true. Because (1+2)*(3+4)=21
		
	/**	Analysis
		
		This is a partition problem which can be solved by using depth first search. 
		divided all elements into two parts, apply add, subtract, multiply, divide from multiple elements 
		pair to single element pair, record to result, check if any result equals to target 
	 *  
	 */
	public static boolean isReachable(ArrayList<Integer> list, int target) {
		if (list==null || list.size()==0) return false;
		int low = 0;
		int high = list.size()-1;
		ArrayList<Integer> result = getResult(list,low,high,target);
		for (Integer re: result) {
			if (re==target) {
				return true;
			}
		}
		return false;
	}
	public static ArrayList<Integer> getResult(ArrayList<Integer> list, int low, int high, int target) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (low>high) {
			return result;
		} else if (low==high) {
			result.add(list.get(low));
			return result;
		}
		for (int i = low; i< high; i++) {
			ArrayList<Integer> result1 =getResult(list, low, i, target);
			ArrayList<Integer> result2 =getResult(list, i+1, high, target);
			for (Integer x: result1) {
				for (Integer y:result2) {
					result.add(x+y);
					result.add(x-y);
					result.add(x*y);
					if (y!=0)
					   result.add(x/y);
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1,2,3,4}; 
		 
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		int target =21;
		System.out.println(isReachable(list, target));
		
		list = new ArrayList<Integer>();
		list.add(3);
		list.add(3);
		list.add(3);
		 
		target =6;
		System.out.println(isReachable(list, target));
	}

}
