package com.code.review.aaa.career.cup.array.string;

public class MakeBricks {
	/**
	 * Apple
	 * 
		We want to make a row of bricks that is goal inches long. We have a number of small bricks 
		(1 inch each) and big bricks (5 inches each). Return true if it is possible to make the goal 
		by choosing from the given bricks. This is a little harder than it looks and can be done 
		without any loops. See also: Introduction to MakeBricks
		
		makeBricks(3, 1, 7) → true
		makeBricks(3, 1, 8) → true
		makeBricks(3, 1, 9) → false
		makeBricks(3, 2, 10) → true
		makeBricks(3, 2, 12) → true
		makeBricks(1, 3, 17) → false
		makeBricks(2, 3, 12) → true
		makeBricks(8, 2, 12) → true
		
	 * 
	 */
	public static boolean findBricks(int small, int big, int target) {
		int bigBricks = big*5;
		if ((small+bigBricks)<target) {
			return false;
		} else if ((small+bigBricks) > target) {
			if (bigBricks>target) {				 		 
				int needbigbricks = target/5;
				int mod = target - needbigbricks*5;
				if (mod<=small) {
					return true;
				} else {
					return false;
				}
			} else if (bigBricks<target) {
				int mod = target-bigBricks;
				if (mod<=small) {
					return true;
				} else {
					return false;
				}
			} else { // bigBricks = target
				return true;
			}
		} else {
			return true;
		}
	}
	public static void main(String[] args) {
		/**
		 * 		makeBricks(3, 1, 7) → true
		makeBricks(3, 1, 8) → true
		makeBricks(3, 1, 9) → false
		makeBricks(3, 2, 10) → true
		makeBricks(3, 2, 12) → true
		makeBricks(1, 3, 17) → false
		makeBricks(2, 3, 12) → true
		makeBricks(8, 2, 12) → true
	
		
	 * 
		 */
		System.out.println(findBricks(3, 1, 7));
		System.out.println(findBricks(3, 1, 9));
		System.out.println(findBricks(3, 1, 7));
		System.out.println(findBricks(3, 2, 10));
		System.out.println(findBricks(3, 2, 12));
		System.out.println(findBricks(1, 3, 17));
		System.out.println(findBricks(2, 3, 12));
		System.out.println(findBricks(8, 2, 12));
		System.out.println(findBricks(3, 11, 7));
		
	}

}
