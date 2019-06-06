package com.code.review.array.string.hard.game;

import java.util.HashSet;
import java.util.Set;

public class ReverseVowelsOfString {
	/**
	 *  LeetCode – Reverse Vowels of a String (Java)
 

		Write a function that takes a string as input and reverse only the vowels of a string.
		
		Java Solution
		
		this is a simple problem which can be solved by using two pointers scanning from beginning 
		and end of the array. , vowels a, e,i, o,u
	 * @param args
	 */
	public static String reverseVowelsOfString(String s) {
		Set<Character> set = new HashSet<Character>();
		set.add('a');
		set.add('e');
		set.add('i');
		set.add('o');
		set.add('u');
		set.add('A');
		set.add('E');
		set.add('I');
		set.add('O');
		set.add('U');
		int low = 0;
		int high = s.length()-1;
		char arr[] = s.toCharArray();
		while (low<high) {
			char c1 = arr[low];
			char c2 = arr[high];
			if (!set.contains(c1)) {
				low++;
				continue;
			}
			if (!set.contains(c2)) {
				high--;
				continue;
			}
			char t = arr[low];
			arr[low] = arr[high];
			arr[high] = t;
			low++;
			high--;
			
		}
		return new String(arr);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="How are you, dear Tiffany, have you ever done your divorce?";
		System.out.println(reverseVowelsOfString(str));
	}

}
