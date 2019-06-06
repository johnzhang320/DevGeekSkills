package com.code.review.aaa.career.cup.array.string;

public class FindMaxProductContiguousSubarray {
	/**
	 * 	Find the contiguous subarray within an array (containing at least one number) which has the 
	 *  largest product.
		Return an integer corresponding to the maximum product possible.
		
		Input : [2, 3, -2, 4]
		Return : 6 
		
		Possible with [2, 3]
		
		my approach:
		if no negative number , all elements must produce max product
		Therefore i keep go further, record all elements product as maximum number
		once find current product is smaller than max product, current product =1
		recalculate again 
		
	 */
	public static int findMaxProduct(int nums[]) {
		if (null==nums || 0==nums.length) return 0;
		int[] max = new int[nums.length];
	    int[] min = new int[nums.length];
	 
	    max[0] = min[0] = nums[0];
	    int result = nums[0];
	 
	    for(int i=1; i<nums.length; i++){
	        if(nums[i]>0){
	            max[i]=Math.max(nums[i], max[i-1]*nums[i]);
	            min[i]=Math.min(nums[i], min[i-1]*nums[i]);
	        }else{
	            max[i]=Math.max(nums[i], min[i-1]*nums[i]);
	            min[i]=Math.min(nums[i], max[i-1]*nums[i]);
	        }
	 
	        result = Math.max(result, max[i]);
	    }
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {2, 3, -2, 4,2};
		System.out.println(findMaxProduct(A));
		int A2[] = {2, 3, -2, 4, 2, -4, 5,2};
		System.out.println(findMaxProduct(A2));
	}
}
