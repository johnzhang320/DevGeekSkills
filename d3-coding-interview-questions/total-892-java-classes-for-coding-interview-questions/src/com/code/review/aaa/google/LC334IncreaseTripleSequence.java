package com.code.review.aaa.google;

public class LC334IncreaseTripleSequence {
	/**
	 * 
	 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

		Formally the function should:
		
		Return true if there exists i, j, k 
		such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
		Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
		
		Example 1:
		
		Input: [1,2,3,4,5]
		Output: true
		Example 2:
		
		Input: [5,4,3,2,1]
		Output: false
		
		Analysis
		set m1 and m2 max integer,check if m1 is greater than current , m1 = current, else if m1 is smaller than current
		check if m2 is greater than current , m2 = current, if m2 < current, means m1 < m2 <current , return true
		这个思路是使用两个指针m1和m2，初始化为整型最大值，我们遍历数组，如果m1大于等于当前数字，则将当前数字赋给m1；
		如果m1小于当前数字且m2大于等于当前数字，那么将当前数字赋给m2，一旦m2被更新了，说明一定会有一个数小于m2，
		那么我们就成功的组成了一个长度为2的递增子序列，所以我们一旦遍历到比m2还大的数，我们直接返回ture。
		如果我们遇到比m1小的数，还是要更新m1，有可能的话也要更新m2为更小的值，毕竟m2的值越小，能组成长度为3的递增序列的可能性越
	 */
	public boolean increasingTriplet(int[] nums) {
		int x = Integer.MAX_VALUE;
		int y = Integer.MAX_VALUE;
	 
		for (int i = 0; i < nums.length; i++) {
			int z = nums[i];
	 
			if (x >= z) {
				x = z;// update x to be a smaller value  , first time x>=z, x=z, else x<z check if y<z   
			} else if (y >= z) { //only when x is smaller than z and y >=z , get chance to update y = z, means x<y
				y = z; // update y to be a smaller value x<z and y<z, check x 
			} else {  // mean z>x and z>y, get chance x<y<z return true
				return true;
			}
		}
	 
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
