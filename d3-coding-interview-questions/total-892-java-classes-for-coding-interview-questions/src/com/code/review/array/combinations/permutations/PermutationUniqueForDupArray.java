package com.code.review.array.combinations.permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PermutationUniqueForDupArray {
/**
 *  Given a collection of numbers that might contain duplicates, return all 
 *  possible unique permutations.

	For example, [1,1,2] have the following unique permutations:
	[1,1,2], [1,2,1], and [2,1,1].

 * 
 */
	public static ArrayList<ArrayList<Integer>> permute(int A[]) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		// add a empty list
		result.add(new ArrayList<Integer>());
		// iteration of A array, add A[i] to previous permutation
		for (int i=0; i< A.length; i++) {
			// create current permutation list current as HashSet to avoid duplication
			Set<ArrayList<Integer>> current = new HashSet<ArrayList<Integer>>();
			for (ArrayList<Integer> re : result) {
				// # of location to insert to result will be re.size()+1
				for (int j=0;j<re.size()+1;j++) {
					re.add(j,A[i]);
					// create new object tmp as current re object to be inserted to current  
					ArrayList<Integer> tmp = new ArrayList<Integer>(re);
					current.add(tmp);  // HashSet avoid duplication
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
	 *  For each number in the array, swap it with every element after it. To avoid duplicate, 
	 *  we need to check the existing sequence first. 
	 * @param args
	 */
	public ArrayList<ArrayList<Integer>> permuteRecursive(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		permute(num, 0, result);
		return result;
	}
	 
	void permute(int[] num, int start, ArrayList<ArrayList<Integer>> result) {
	 
		if (start >= num.length) {
			ArrayList<Integer> item = convertArrayToList(num);
			result.add(item);
		}
	 
		for (int j = start; j <= num.length - 1; j++) {
			// we check if duplication , avoid it
			if (containsDuplicate(num,start,j)) {
				swap(num, start, j);  // Before recursive swap
				permute(num, start + 1, result);
				swap(num, start, j);  // After recursive swap
			}
		}
	}
	 
	private boolean containsDuplicate(int[] arr, int start, int end) {
		for (int i = start; i <= end-1; i++) {
			if (arr[i] == arr[end]) {
				return false;
			}
		}
		return true;
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
		int A[]={1,1,2};
		ArrayList<ArrayList<Integer>> result = permute(A);
		for (ArrayList<Integer> re: result ) {
			System.out.print("\n{");
			for (Integer i: re) {
				System.out.print(i+" ");
			}
			System.out.println("}");
		}
		System.out.println("\n-------------------------Recursive---------------------");
		Permutations ref = new Permutations();
		result = ref.permuteRecursive(A);            // recursive is not preventing duplicated
		for (ArrayList<Integer> re: result ) {
			System.out.print("\n[");
			for (Integer i: re) {
				System.out.print(i+" ");
			}
			System.out.println("]");
		}
	}

}
