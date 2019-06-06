package com.code.review.array.string.easy.sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumIII {
	/**
	 * Design and implement a TwoSum class. It should support the following operations: add and find.

		add - Add the number to an internal data structure.
		find - Find if there exists any pair of numbers which sum is equal to the value.
		For example,

		add(1); 
		add(3); 
		add(5);
		find(4) -> true
		find(7) -> false

		Implementation
		any two number should be target
		Using HashMap, add map by number, find if any number (target - given value exists) in the map
	 * @param args
	 */
	public static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	public static void add(int value) {
		if (map.containsKey(value)) {
			map.put(value, map.get(value)+1);
			
		} else {
			map.put(value, 1);
		}
	}
	public static boolean twoSumIII (int value) {
		for (Integer i: map.keySet()) {   // map.keySet()
			int target = value-i;
			if (map.containsKey(target)) {
				if (i==target && map.get(target)!=2) {
					continue;
				}
				return true;
			}
		}
		return false;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1, 2, 4, 3, 15, 11, 7};
		for (Integer i: arr) {
			add(i);
		}
		int k=18;
		  System.out.println("for array "+Arrays.toString(arr)+" find k = "+k + " two sum is found:");
		  System.out.println(twoSumIII(k)); 
	}

}
