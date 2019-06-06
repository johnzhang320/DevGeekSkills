package com.code.review.array.string.easy.duplicate;


import java.util.HashSet;

public class RemoveDuplicatedWords {
	/**
	 *  Remove duplicated words in a string array
	 *  Simple algorithm is using an hashSet, use containkeys to check if repeated
	 *  
	 */
	public static String [] removeDuplicateWords(String str[]) {
		HashSet<String> hashset = new HashSet<String>();
		int j=0;
		for (int i=0;i<str.length;i++) {
			if (!hashset.contains(str[i])) {
				hashset.add(str[i]);
				j++;
			}
		}
		String str1[] = new String[j+1];
		j=0;
		for (String s:hashset) {
			str1[j++]=s;
		}
		return str1;
		
	}
	/** 
	 *  remove duplicate words without any extra data stucture 
	 *  algorithm : use tail pointers and i pointers, if 0~ tail , no
	 *  duplicated word equals word i, move word i to word tail then move tail / i
	 *  next , otherwise do nothing but move i ahead
	 */
	public static String [] removeDuplicateNoExtraStructure(String str[]) {
		int tail =1;
		for (int i=1;i<str.length;i++) {
			int j=0;
			for (j=0;j<tail;j++) {
				if (str[i].equalsIgnoreCase(str[j])) break;
			}
			if (j==tail) {
				str[tail] = str[i];
				tail++;
			}
		}
		String str1[] = new String[tail];
		for(int i=0; i<tail; i++) {
			str1[i] = str[i];
			//System.out.println(str[i]);
		}
		return str1;
	}

	public static void display(String str[]) {
		for(int i=0; i<str.length; i++) {
		 
			System.out.println(str[i]);
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1[]={"Hello","World","this","is","an","Crazy","World"};
		str1 = removeDuplicateWords(str1);
		//display(str1); 
		String str2[]={"Hello","World","this","is","an","Crazy","World","Hello"};
		str2=removeDuplicateNoExtraStructure(str2);
		display(str2); 
	}

}
