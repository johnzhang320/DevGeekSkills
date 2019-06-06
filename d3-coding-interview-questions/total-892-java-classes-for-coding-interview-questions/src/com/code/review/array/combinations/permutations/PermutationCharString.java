package com.code.review.array.combinations.permutations;

import java.util.ArrayList;
import java.util.List;

public class PermutationCharString {
	/**
	 *  TCS questions
 
		input "abcd";
		output 
		abcd
		abdc
		acbd
		acdb
		adcb
		adbc
		bacd
		badc
		bcad
		bcda
		bdca
		bdac
		cbad
		cbda
		cabd
		cadb
		cdab
		cdba
		dbca
		dbac
		dcba
		dcab
		dacb
		dabc
        
	 
	 *  
	 */
	 
	/**
	 *  We can also recursively solve this problem. Swap each element with each element after it.
	 * @param args
	 */
	public ArrayList<String> permuteRecursive(char[] num) {
		ArrayList<String> result = new ArrayList<String>();
		permute(num, 0, result);
		return result;
	}
	 
	void permute(char[] A, int start, ArrayList<String> result) {
	 
		if (start >= A.length) {
			 
			result.add(new String(A));  // char [] A to String
		}
	 
		for (int j = start; j <= A.length - 1; j++) {
			swap(A, start, j);  // Before recursive swap
			permute(A, start + 1, result);
			swap(A, start, j);  // After recursive swap
		}
	}
	 
	private ArrayList<Integer> convertArrayToList(int[] num) {
		ArrayList<Integer> item = new ArrayList<Integer>();
		for (int h = 0; h < num.length; h++) {
			item.add(num[h]);
		}
		return item;
	}
	 
	private void swap(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		System.out.println("\n-------------------------Recursive---------------------");
		PermutationCharString ref = new PermutationCharString();
		String str = "abcd";
		char num[] = str.toCharArray();
		List<String> result1 = ref.permuteRecursive(num);
	    result1.forEach((v)->System.out.println(v));	 
	}

}
