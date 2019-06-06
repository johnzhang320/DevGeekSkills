package com.code.review.array.string.easy.duplicate;

public class RemoveDuplicateI {
	/**
	 * Given an array of integers and an integer k, return true if and only if there are two 
	 * distinct indices i and j in the array such that nums[i] = nums[j] and the difference 
	 * between i and j is at most k.
	 * Thought
	 * Create map.put(nums[i],i), preIndex= map.get(nums[j]), 
	 * if i-preIndex<=k return true;.
	 */
    public int removeDuplicates(int[] nums) {
      
         int len =1;
         
 
        if (null==nums || nums.length==0) {
            return 0;
        }
        if (nums.length==1) {
            return 1;
        }
        for (int i=1;i<nums.length;i++) {
            if (nums[i]!=nums[i-1]) {
   
                 
                nums[len] =nums[i];
                
                len++;
                
                 
            }
        }
        return len;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
