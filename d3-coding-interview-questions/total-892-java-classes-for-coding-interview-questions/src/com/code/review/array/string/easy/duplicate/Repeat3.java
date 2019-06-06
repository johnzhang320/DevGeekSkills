package com.code.review.array.string.easy.duplicate;

import java.util.HashMap;
import java.util.Map;

public class Repeat3 {
	/**
	 * You’re given a read only array of n integers. Find out if any integer occurs more than n/3 times 
	 * in the array in linear time and constant additional space.

		If so, return the integer. If not, return -1.
		
		If there are multiple solutions, return any one.
		
		Example :
		
		Input : [1 2 3 1 1]
		Output : 1 
		1 occurs 3 times which is more than 5/3 times. 
 	 *  My Approach 
 	 *  very simple: create Map<A[i], count> , count > (double) n/3 ; then return this element
	 */
	//O(n)
	public static int findOneThirdElement (int A[]) {
		if (null==A || 0==A.length) return -1;
		double oneThird =((double) A.length)/3.0;
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (int i=0; i<A.length;i++) {
			int key = A[i];
			if (map.containsKey(key)) {
				int count = map.get(key)+1;
				double countd = (double) count;
				if (countd>=oneThird) {
					return key;
				} else {
					map.put(key,count);
				}
			} else {
				map.put(key,1);
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1, 2, 3, 1, 1};
		System.out.println(findOneThirdElement (A));
	}

}
