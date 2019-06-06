package com.code.review.array.string.easy.duplicate;

public class RemoveDupCharsWithoutBuffer {
	public static void removeDup(char [] str) {
		int tail = 1;
		int i,j;
		
		for (i=0; i<str.length;i++) {
			boolean found=false;
			 
			for (j=0; j <tail; j++) {
				if (str[i] == str[j]) {
					found=true;
					break;
				}
			}
			if (!found) {
				str[tail] = str[i];
				tail++;
			}
		}
		for (i=0;i<tail;i++) {
			System.out.print(str[i]+" ");
		}
	}
	/**
	 *  Remove duplicated char without java hashtable, set or hashmap
	 *  only use array
	 */
	public static String removeRepeatusingArray(String str) {
		char chars[] = str.toCharArray();
		boolean temp[] = new boolean[256];
		int tail = 0;
		for (int i =0; i< chars.length;i++) {
			int ch =chars[i];
			temp[ch]=true;
		}
		for (int i=0; i<chars.length;i++) {
			int ch = chars[i];
			if (temp[ch]) {
				chars[tail++] = chars[i];
				temp[ch]=false;   // only one time chace to be appended
			}
		}
		return (new String (chars)).substring(0,tail);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "aabbdcegfbhijkgmndaocbeph";
		 
		removeDup(str.toCharArray());
		System.out.println("");
		System.out.println(removeRepeatusingArray(str)); 
	}

}
