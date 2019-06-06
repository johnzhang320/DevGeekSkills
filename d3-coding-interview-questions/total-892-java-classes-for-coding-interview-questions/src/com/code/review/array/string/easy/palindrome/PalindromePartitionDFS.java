package com.code.review.array.string.easy.palindrome;

import java.util.ArrayList;

public class PalindromePartitionDFS {
	/**
	 *  LeetCode – Palindrome Partitioning (Java)
 

		Problem
		
		    Given a string s, partition s such that every substring of the partition is a palindrome.
		
		Return all possible palindrome partitioning of s.
		
		For example, given s = "aab",
		Return

		  [
		    ["aa","b"],
		    ["a","a","b"]
		  ]

	 * @param args
	 */
	//  Depth-first Search solution, very like combination except check sub combination string is condition to
	// further divide
	public ArrayList<ArrayList<String>> partitionPalindrome(String s) {		
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		if (s == null || s.length() == 0) {
			return result;
		}
		ArrayList<String> partition = new ArrayList<String>();
		addPalindrome(s, 0,result, partition);
		return result;
	}
	public void addPalindrome(String s, int start,ArrayList<ArrayList<String>> result, ArrayList<String> partition) {
		if (start==s.length()) {
			ArrayList<String> tmp = new ArrayList<String>(partition);
			result.add(tmp);
			return;
		}
		for (int i=start+1; i<=s.length();i++) {
			String str = s.substring(start,i);
			if (isPalindrome(str)) {
				partition.add(str);
				addPalindrome(s,i,result,partition);
				partition.remove(partition.size()-1);
			}
		}
	}
	public boolean isPalindrome(String s) {
		int low=0;
		int high=s.length()-1;
		while (low<high) {
			if (s.charAt(low)!=s.charAt(high)) {
				return false;
			}
			low++;
			high--;
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aacbbcaa";
		PalindromePartitionDFS ref = new PalindromePartitionDFS();
		ArrayList<ArrayList<String>> result = ref.partitionPalindrome(s);
		for (ArrayList<String> list: result) {
			System.out.print("\n[");
			for (String sc: list) {
				System.out.print("\""+sc+"\""+",");
			}
			System.out.print("}\n");
			
		}
		
	}

}
