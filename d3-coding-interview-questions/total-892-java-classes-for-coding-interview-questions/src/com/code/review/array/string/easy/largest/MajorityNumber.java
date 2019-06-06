package com.code.review.array.string.easy.largest;

import java.util.Map;
import java.util.HashMap;

public class MajorityNumber {
	/**
	 *  LeetCode – Majority Element II (Java)
 

		Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
		The algorithm should run in linear time and in O(1) space.
		
		Java Solution 1 - Using a Counter
		
		Time = O(n) and Space = O(n)
		My Approach 
		add each number to Map<key,count> , if count > n/3 return this number

	 * @param args
	 */
	public static int MajorityElement(int arr[]) {
		if (arr==null || arr.length==0) return -1;
		int len = arr.length;
		int limit = len/3;
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		int count = 0;
		for (Integer key: arr) {
			count=0;
			if (map.containsKey(key)) {
				count = map.get(key)+1;
				if (count>=limit) {
					return key;
				} else {
					map.put(key, count);
				} 
			} else {
				map.put(key, 1);
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1,3,6,7,7,7,7,7,7,7,19,10};
		System.out.println(MajorityElement(arr));
	}

}
