package com.code.review.facebook.code.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.code.utils.BST.Utils;

public class RemoveDuplicateII {
	/**
	 * Remove Duplicates from Sorted Array

		Given a sorted array, remove the duplicates in place such that each element can appear 
		atmost twice and return the new length.
		
		Do not allocate extra space for another array, you must do this in place with constant 
		memory.
		
		Note that even though we want you to return the new length, make sure to change the 
		original array as well in place
		
		For example,
		Given input array A = [1,1,1,2],
		
		Your function should return length = 3, and A is now [1,1,2].
		
		Create two points curr = 2 and prev = 1
		check if A[curr] == A[prev] && A[curr] == A[prev-1]
	 * @param args
	 */
	
	public static int removeDupII(int A[]) {
		if (null==A || A.length==0) return 0;
		if (A.length<=2) return A.length;
		int curr = 2;
		int prev = 1;
		while (curr<A.length) {
			if (A[curr]==A[prev] && A[curr]==A[prev-1]) {
				curr++;
			} else {
				A[++prev] = A[curr];
				curr++;
			}
			
		}
		A = Arrays.copyOf(A, prev+1);
		System.out.println("--------------RemoveDupII-------------");
		Utils.print(A);
		return prev+1;
	}
	
	 
	public static int removeDupTwoMap(int A[]) {
		if (null == A || A.length==0) return 0;
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (Integer key: A) {
			if (map.containsKey(key)) {
				int count = map.get(key);
				if (count<2) {
					map.put(key, map.get(key)+1);
				}
			} else {
				map.put(key, 1);
			}
		}
		List<Integer> list = new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
			if (entry.getValue()==2) {
				list.add(entry.getKey());
				list.add(entry.getKey());				
			} else {
				list.add(entry.getKey());
			}
		}
		 
		for (int i=0; i< list.size();i++) {
			A[i] = list.get(i);
		}
		A = Arrays.copyOf(A, list.size());
		System.out.println("----------RemoveDupMap------------");
		Utils.print(A);
		return list.size();
	}
	
	public static int removeDuplicates(int[] A) {
		if (A == null || A.length == 0)
			return 0;
 
		int pre = A[0];
		boolean flag = false;
		int count = 0;
 
		// index for updating
		int o = 1;
 
		for (int i = 1; i < A.length; i++) {
			int curr = A[i];
 
			if (curr == pre) {
				if (!flag) {
					flag = true;
					A[o++] = curr;
 
					continue;
				} else {
					count++;
				}
			} else {
				pre = curr;
				A[o++] = curr;
				flag = false;
			}
		}
		
		return A.length - count;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1,1,1,2,3,3,3,4,5,5,6};
		int result = removeDupTwoMap(A);
		System.out.println(result);
		 
		result = removeDupII(A);
		
		System.out.println(result);
		
		 
	}

}
