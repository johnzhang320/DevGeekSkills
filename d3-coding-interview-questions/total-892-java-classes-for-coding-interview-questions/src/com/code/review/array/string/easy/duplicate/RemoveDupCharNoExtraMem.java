package com.code.review.array.string.easy.duplicate;

import java.util.Arrays;

public class RemoveDupCharNoExtraMem {
	/**
	 * A String contain duplicated character, remove duplicated character
	 * return String with length
	 * Implementation
	 * Using brute force: two level loop. 
	 * external loop, i points to scanned character, design a limit points 
	 * last character in return string. this pointer initialized as 1
	 * inner loop j from 0 to limit, check arr[i] exist in arr[0] ... arr[limit]
	 * if exists , break loop , if not exists in limit, add to result[limit+1]
	 * 
	 * @param args
	 */
	public static char[] removeDupChar(char arr[]) {
		int limit=1;
		boolean found = false;
		for (int i=1; i<arr.length;i++) {
			found = false;
			for (int j=0; j<limit;j++) {
				if (arr[i]==arr[j]) {
					found = true;
					break;
				}
			}
			if (!found) {
				arr[limit] = arr[i];
				limit++;
			}
		}
		return Arrays.copyOf(arr, limit);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "aabcabefgabcd";
		char [] removed = removeDupChar(str.toCharArray());
		for (Character c:removed) {
			System.out.print(c);
		}
	}

}
