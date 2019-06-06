package com.code.review.array.string.easy.sum;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
	/**
	 * Given an array of integers, find two numbers such that they add up to a specific target number.
	
		The function twoSum should return indices of the two numbers such that they add up to the target, 
		where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
	
			For example:
	
		Input: numbers={2, 7, 11, 15}, target=9
		Output: index1=0, index2=1
 
	 * @param args
	 */
	public static int [] TwoSum(int numbers[], int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] result = new int[2];
	 
		for (int i = 0; i < numbers.length; i++) {
			if (map.containsKey(numbers[i])) {
				int index = map.get(numbers[i]);
				result[0] = index ;
				result[1] = i;
				break;
			} else {
				map.put(target - numbers[i], i);
			}
		}
	 
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   int arr[] = {1, 2, 4, 3, 15, 11, 7};
	   int k = 18;
	   System.out.println("for array "+Arrays.toString(arr)+" find k = "+k + " two sum is ");
	   System.out.println(Arrays.toString(TwoSum(arr,k))); 
	}

}
