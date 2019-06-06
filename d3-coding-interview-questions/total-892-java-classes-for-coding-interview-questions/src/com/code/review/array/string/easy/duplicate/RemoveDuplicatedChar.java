package com.code.review.array.string.easy.duplicate;

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
public class RemoveDuplicatedChar {
	
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
	 * Better solution with O[n^2] with constant size of additional memory
	 * @param str
	 * @return tail -- removed string length
	 */
	public static int removeDuplicatedCharMem(char str[]) {
		if (str==null) return -1;
		int len = str.length;
		if (len<2) return -1;
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
		 
	   return tail;
			
		 
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
		
		int tail = removeDuplicatedCharMem(strb);
		System.out.print("Using constant size of additional memory, result:\n");
		for (int i=0;i<tail;i++) System.out.print(strb[i]);
	}

}
