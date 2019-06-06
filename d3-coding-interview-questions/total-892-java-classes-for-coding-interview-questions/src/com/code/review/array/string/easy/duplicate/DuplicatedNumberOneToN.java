package com.code.review.array.string.easy.duplicate;

public class DuplicatedNumberOneToN {
	/**
	 * 1,2,3,......N, there is one number is duplicated, try to find it
	 * sum = n(n+1)/2, 
	 * we search max data as n
	 * sum all element from 0 to arr[length-1] = sum
	 * sum - max*(max+1)/2 = duplicated number
	 * @param args
	 * O(N)
	 */
	public static void findDuplicatedNumnber1ToN(int[] data) {
		// find maximum number, which could not be last element because repeated data could be allowed 
		// to be added last position
 		int len = data.length; 
 		int max=data[len-1];
 		if (len>2) {
 			if (data[len-1]<data[len-2]) {
 				max = data[len-2];
 			}  
 		}
		int sum = 0;
		for (int i=0; i<data.length; i++) {
			sum +=data[i];
		}
		int result = sum - (max*(max+1))/2;    // Guoss definition 
		System.out.println("duplicated number is "+result);
		 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data[] = {1,2,3,4,5,6,7,11,8,9,10,11,12};
		findDuplicatedNumnber1ToN(data);
	}

}
