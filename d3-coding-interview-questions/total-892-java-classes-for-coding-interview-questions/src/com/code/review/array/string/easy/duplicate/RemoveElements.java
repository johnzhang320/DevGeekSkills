package com.code.review.array.string.easy.duplicate;
/**
 * Given an array and a value, remove all instances of that value in place and return
 *  the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.
 * My Approach:
 * (1) create two pointers, p1, p2, if nums[i] == val, p1=i; else p2++;
 * (2) i = p1+1, to len, if nums[i]!=val , nums[p1++] = nums[i] p2++ else 
 *
 */
public class RemoveElements {
 public static int removeElement(int[] nums, int val) {
        int p1=0,p2=0;
        if (null==nums || nums.length==0) {
            return 0;
        }
        if (nums.length==1) {
            if (nums[0] == val) {
                return 0;
            } else {
                return 1;
            }
        }
        boolean find=false;
        for (int i=0; i<nums.length;i++) {
	        if (!find) {
	            if (nums[i] == val) {
	                p1 = i;
	                find = true;		              
	            } else {
	                p2++;
	            }
        	} else {
        		if (nums[i] !=val) {
                    nums[p1++] = nums[i];
                    p2++;
                }
        	}
            
        }
       if (p1==nums.length-1 || !find) {
            return p2;
        }
        
        return p2;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[] = {3,2,2,3,4,3,5,3};
		System.out.println(removeElement(nums, 3));
	 
	}

}
