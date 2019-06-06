package com.code.review.array.string.hard.game;

import java.util.Stack;

public class Histogram {
	/**
	 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
		
		largest-rectangle-in-histogram1
		
		Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
		
		largest-rectangle-in-histogram2
		The largest rectangle is shown in the shaded area, which has area = 10 unit.
		
		For example, given height = [2,1,5,6,2,3], return 10.
		
		Analysis
		
		The key to solve this problem is to maintain a stack to store bars' indexes. The stack only keeps the increasing bars. 
	 * @param args
	 */
	public static int largestRectangleArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
	 
		Stack<Integer> stack = new Stack<Integer>();
	 
		int max = 0;
		int i = 0;
	 
		while (i < height.length) {
			//push index to stack when the current height is larger than the previous one
			if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
				stack.push(i);
				i++;
			} else {
			//calculate max value when the current height is less than the previous one
				int p = stack.pop();
				int h = height[p];
				
				int w = stack.isEmpty() ? i : i - stack.peek() - 1;
				System.out.println("1 height="+h+", w="+w);
				max = Math.max(h * w, max);
			}
	 
		}
	 
		while (!stack.isEmpty()) {
			int p = stack.pop();			
			int h = height[p];
		 
			int w = stack.isEmpty() ? i : i - stack.peek() - 1;
			System.out.println("2 height="+h+", w="+w);
			max = Math.max(h * w, max);
		}
	 
		return max;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int height[] = {2,1,5,6,2,3};
		//System.out.println(largestRectangleArea(height));
		// result 10 , it is correct
		int height2[] = {2,1,4,5,6,2,3};
		System.out.println(largestRectangleArea(height2));
		// result is 12, it is correct because it only consider two elements using stack solution
	}

}
