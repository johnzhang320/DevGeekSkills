package com.code.review.array.string.hard.game;

public class DuplicatedNumber1ToN {
	/**
	 * 
	 *  LeetCode – Find the Duplicate Number (Java)
 

		Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
		 prove that at least one duplicate number must exist. Assume that there is only one duplicate 
		 number, find the duplicate one.
		
		Note:
		You must not modify the array (assume the array is read only).
		You must use only constant, O(1) extra space.
		Your runtime complexity should be less than O(n2).
		There is only one duplicate number in the array, but it could be repeated more than once.
	 *  
	 *  My approach
	 *  (1) A[i-1] == A[i] , i=0 to N, O(N)
	 *  (2) Binary Search O(logN);
	 */
	/**
	 * 1,2,3,......N, there is one number is duplicated, try to find it
	 * sum = n(n+1)/2, 
	 * we search max data as n
	 * sum all element from 0 to arr[length-1] = sum
	 * sum - max*(max+1)/2 = duplicated number
	 * @param args
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
		int result = sum - (max*(max+1))/2;
		System.out.println("1 duplicated number is "+(result==0? -1:result));
		 
	}
	
	public static int findDuplicate(int[] nums) {
	    int slow = 0;
	    int fast = 0;
	 
	    do{
	        slow = nums[slow];
	        fast = nums[nums[fast]];
	    } while(slow != fast);
	 
	    int find = 0;
	 
	    while(find != slow){
	        slow = nums[slow];
	        find = nums[find];
	    }
	    System.out.println("2 duplicated number is "+find);
	    return find;
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data[] = {1,2,3,4,5,6,7,8,9,10,11,12};
		findDuplicatedNumnber1ToN(data);
		
		// findDuplicate(data);  // if no duplicated , out of array
		
		 
		
	}

}
