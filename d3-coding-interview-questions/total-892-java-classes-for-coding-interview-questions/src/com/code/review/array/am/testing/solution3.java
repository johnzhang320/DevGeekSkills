package com.code.review.array.am.testing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class solution3 {
	/*
	 * 3, 2, 2, 1, 2, 2, 1

	 */
	
	public int findMajority(int arr[]) {
		if (null==arr || 0==arr.length) {
			return -1;
		}
		 
		if (arr.length<=2 && arr.length>=1) return arr[0];
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int half = arr.length/2;
        int count = 0;
        for (int i=0; i < arr.length;i++) {
            int key = arr[i];
            if (map.containsKey(key)) {
                count = map.get(key)+1;
                
            } else {
                count = 1;
            }
            if (count>=half) {
                return key;
            }
            map.put(key,count);    
        }
        return -1;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solution3 ref = new solution3();
		int arr[] = {3, 2, 2, 1, 2, 2, 1};
		System.out.println(ref.findMajority(arr));

	}

}
