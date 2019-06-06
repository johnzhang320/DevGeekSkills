package com.code.review.array.combinations.permutations;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
	/**
	 *  LeetCode � Permutations (Java)
 

		Given a collection of numbers, return all possible permutations.
		
		For example,
		[1,2,3] have the following permutations:
		[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
		
		Java Solution 1
		
		We can get all permutations by the following steps:
		
		[1]
		[2, 1] -------------|  add 3 in different locations of [2,1]   
		                    |
		[1, 2] -------------|--            
		                    | |   
		[3, 2, 1] <-----|   | |  
		[2, 3, 1] <-----|<--| |          
		[2, 1, 3] <-----|     | 
		                      | add 3 in different locations of [1,2]   
 		[3, 1, 2] <-----|     |   
		[1, 3, 2] <-----|<----|
		[1, 2, 3] <-----|
		
		Approach one -- iterations
		
		Loop through the array, in each iteration, a new number is added to different locations 
		of results of previous iteration. Start from an empty List.
	 *  
	 */
	public static ArrayList<ArrayList<Integer>> permute(int A[]) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		// add a empty list
		result.add(new ArrayList<Integer>());
		// iteration of A array, add A[i] to previous permutation
		for (int i=0; i< A.length; i++) {
			// create current permutation list current
			ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> re : result) {
				// # of location to insert to result will be re.size()+1
				for (int j=0;j<re.size()+1;j++) {
					re.add(j,A[i]);
					// create new object tmp as current re object to be inserted to current  
					ArrayList<Integer> tmp = new ArrayList<Integer>(re);
					current.add(tmp);
					re.remove(j);  // delete current A[i] , keep original previous permutation for next adding
					
				}
			}
			// create new current object
			result = new ArrayList<ArrayList<Integer>>(current);
		}
		return result;
	}
	/**
	 *  We can also recursively solve this problem. Swap each element with each element after it.
	 *  (1) similar to combination sum , different is swap A[]  and place current swapped A[] to result list
	 * @param args
	 */
	public ArrayList<ArrayList<Integer>> permuteRecursive(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		permute2(num, 0, result);
		return result;
	}
	 
	void permute(int[] num, int start, ArrayList<ArrayList<Integer>> result) {
	 
		if (start >= num.length) {
			ArrayList<Integer> item = convertArrayToList(num);
			result.add(item);
		}
	 
		for (int j = start; j <= num.length - 1; j++) {
			swap(num, start, j);  // Before recursive swap
			permute(num, start + 1, result);
			swap(num, start, j);  // After recursive swap
		}
	}
	 
	void permute2(int A[] , int start, ArrayList<ArrayList<Integer>> result) {
		int len = A.length;
		ArrayList<Integer> item = new ArrayList<Integer>();
		if (start>=len) {
			for (int i=0;i<len;i++) {
				item.add(A[i]);
			}
			result.add(item);
			return;
		}
		for (int i=start; i<len;i++) {
			swap(A,start,i);
			permute2(A ,start+1, result) ;
			swap(A,start,i);
		}
	}
	private ArrayList<Integer> convertArrayToList(int[] num) {
		ArrayList<Integer> item = new ArrayList<Integer>();
		for (int h = 0; h < num.length; h++) {
			item.add(num[h]);
		}
		return item;
	}
	 
	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("-------------------------Iteration---------------------");
		int A[]={1,2,3};
		ArrayList<ArrayList<Integer>> result = permute(A);
		for (ArrayList<Integer> re: result ) {
			System.out.print("{");
			for (Integer i: re) {
				System.out.print(i+" ");
			}
			System.out.println("}");
		}
		System.out.println("\n-------------------------Recursive---------------------");
		Permutations ref = new Permutations();
		result = ref.permuteRecursive(A);
		for (ArrayList<Integer> re: result ) {
			System.out.print("[");
			for (Integer i: re) {
				System.out.print(i+" ");
			}
			System.out.println("]");
		}
	}

}
