package com.code.review.array.string.easy.duplicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RemoveMajority {
	/**Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in 
	linear time and in O(1) space.

	implementation
	Using data structure, it will be very simple , create map<arr[i], count>, store max count and arr[i] , return count >= n/3
	*/
		public static List<Integer> findMajority(int arr[]) {
			Map<Integer,Integer> map = new HashMap<Integer,Integer>();
			for (Integer key: arr) {
				if (map.containsKey(key)) {
					map.put(key,map.get(key)+1);
				} else {
					map.put(key,1);
				}
			}
			List<Integer> result = new ArrayList<Integer>();
			Iterator<Integer> itr = map.keySet().iterator();
			while (itr.hasNext()) {
				int key = itr.next();
				int count = map.get(key);
				if (count>=arr.length/3) {
					result.add(key);
				}
			}
			return result;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {2,3,4,5,1,3,3,4,4,4};
		List<Integer> list  =  findMajority(arr);
		 
		System.out.print(list.toString());
	 
	}

}
