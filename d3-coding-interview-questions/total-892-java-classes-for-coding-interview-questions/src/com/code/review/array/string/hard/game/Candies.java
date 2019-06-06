package com.code.review.array.string.hard.game;

import com.code.review.utils.Utils;

public class Candies {
/**
 *  LeetCode – Candy (Java)
 

		There are N children standing in a line. Each child is assigned a rating value. You are giving 
		candies to these children subjected to the following requirements:
		
		1. Each child must have at least one candy.
		2. Children with a higher rating get more candies than their neighbors.
		
		What is the minimum candies you must give?
		
		Analysis
		
		This problem can be solved in O(n) time.
		
		We can always assign a neighbor with 1 more if the neighbor has higher a rating value. 
		However, to get the minimum total number, we should always start adding 1s in the ascending 
		order. We can solve this problem by scanning the array from both sides. First, scan the 
		array from left to right, and assign values for all the ascending pairs. Then scan from right 
		to left and assign values to descending pairs.
		
		This problem is similar to Trapping Rain Water.
 * 
 */
	/**
	 * @param rating
	 * @return
	 */
	public static int candy(int rating[]) {
		if (rating==null || rating.length==0) return 0;
		int len = rating.length;
		int candies[] = new int[len];
		candies[0] = 1;
		// scan from left to right for ascended
		for (int i=1; i<len;i++) {
			if (rating[i-1]<rating[i]) {  // ascended, assign one more candies
				candies[i] = candies[i-1]+1;				
			} else {
				// not ascended , assign one candies
				candies[i]=1;
			}
		}
		int result = candies[len-1];
		// scan from right to left for descend
		
		for (int i=len-2; i>=0; i--) {
			int cur = 1;
			if (rating[i]>rating[i+1]) {
				cur = candies[i+1]+1;
			}
			result += Math.max(candies[i], cur);
			
			candies[i] = cur;  // ascend plus 1
		}
		Utils.print(candies);
		System.out.println("");
		return result;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int rating[] = {1,1,2,3,1,4,1,1,2,3,1};
		System.out.println(candy(rating));
	}

}
