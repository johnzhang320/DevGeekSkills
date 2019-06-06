package com.code.review.array.string.hard.game;

public class ContainerWater {
	/**
	 * Problem

		Given n non-negative integers a1, a2, ..., an, where each represents a point at 
		coordinate (i, ai). n vertical lines are drawn such that the two endpoints of 
		line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis 
		forms a container, such that the container contains the most water.
		
		Analysis
		
		Initially we can assume the result is 0. Then we scan from both sides. 
		If leftHeight < rightHeight, move right and find a value that is greater than leftHeight.
		 Similarily, if leftHeight > rightHeight, move left and find a value that is greater than 
		 rightHeight. Additionally, keep tracking the max value.
		
		container with most water
	 * @param args
	 */
	public int maxArea(int[] height) {
		if (height == null || height.length < 2) {
			return 0;
		}
	 
		int max = 0;
		int left = 0;
		int right = height.length - 1;
	 
		while (left < right) {
			max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
			if (height[left] < height[right])
				left++;
			else
				right--;
		}
	 
		return max;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
