package com.code.review.array.string.easy.duplicate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Design an algorithm and write code to remove the duplicate 
 * characters in a string without using any additional buffer.
 * NOTE: One or two additional variables are fine. An extra copy of 
 * the array is not.
 *  FOLLOW UP
 *  Write the test cases for this method.
 *  Algorithm: outer loop i = 1 ~ n (n = length of string), external loop
 *  defined a tail pointer = 1, internal loop defined a j=0~tail, if 0 ~ tail
 *  there is no str[j] equals to str[i] , then str[tail]<=strd[i], tail++, i++ means
 *  move i char to tail char and tail plus one, if there is str[j]==str[i], do nothing
 *  but i++
 *  Algorithm is , move char i to char tail and i pointer and tail pointer move ahead if 
 *  different chars at i and tail position, if duplicated char at char i and char tail
 *  only move i to next and hold tail 
 *  O[n^2]
 */
public class RemoveDuplicateChar {
	
	public static String removeDuplicatedChar(char str[]) {
		int tail=1;
		for (int i=1;i<str.length;i++) {
			int j;
			for( j=0;j<tail;j++) {
				if (str[i]==str[j]) break; // chars at i and j same, do nothing but i++
			}
			if (j==tail) { // 0~tail, no duplicate char with str[i], move str[i] to str[tail]
				           // tail ++, i++
				str[tail]=str[i];
				tail++;
			}
		}
		StringBuffer string = new StringBuffer();
		for (int i=0;i<tail;i++) {
			string.append(str[i]);
		}
		 
		return string.toString();
	}
	/**
	 *  Using data structure to avoid to count pointer which is very easy 
	 *  make me stick 
	 *  using set<Character>, if char exist in set,  ignore
	 */
	public static String removeDuplicateChar(String str) {
		Set<Character> set = new HashSet<Character>();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i <str.length(); i++) {
			char key = str.charAt(i);
			if (!set.contains(key)) {
				buf.append(key);
			}
		}
		return buf.toString();
	}
	/**
	 * Better solution with O[n^2] with constant size of additional memory
	 * @param str
	 * @return removed string
	 */
	public static String removeDuplicatedCharMem(char str[]) {
		if (str==null) return null;
		int len = str.length;
		if (len<2) return null;
		int hit[] = new int[256];  // initialized zero when int is instantiated
		hit[str[0]]=1;
		int tail=1;
		for (int i=1;i<len;i++) {
			if (hit[str[i]]==0) {
				str[tail] = str[i];
				tail++;
				hit[str[i]]=1;
			}
		}
		 
	   return (new String(str)).substring(0,tail);
 	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "aabdcegfbhijkgmnoph";
		char strb[] = str.toCharArray();
		str = removeDuplicatedChar(str.toCharArray());
		System.out.println("final str="+str);
		
		String ret = removeDuplicatedCharMem(strb);
		System.out.print("Using constant size of additional memory, result:\n");
		 System.out.print(ret);
	}

}
