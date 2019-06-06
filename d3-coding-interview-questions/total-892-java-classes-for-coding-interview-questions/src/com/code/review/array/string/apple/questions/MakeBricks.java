package com.code.review.array.string.apple.questions;

public class MakeBricks {
/**
 * Apple
 * 
	We want to make a row of bricks that is goal inches long. We have a number of small bricks 
	(1 inch each) and big bricks (5 inches each). Return true if it is possible to make the goal 
	by choosing from the given bricks. This is a little harder than it looks and can be done 
	without any loops. See also: Introduction to MakeBricks
	
	makeBricks(3, 1, 8) → true
	makeBricks(3, 1, 9) → false
	makeBricks(3, 2, 10) → true
 * @param args
 */public static boolean makeBricks(int small, int big, int goal) {
	    // too large
	    if (goal> (big*5+small)) {
	      return false;
	    }
	    // smaller than current bricks combination
	    // combine big brick first
	    int balance = goal;
	    int bCount = big;
	    while (balance>0) {
	        if (bCount>=1) {
	           balance -= 5;
	           bCount--;
	        } else {
	          break;
	        }
	        
	    }
	    if (balance==0) return true;
	    if (balance<0) balance +=5;
	    int sCount = small;
	     while (balance>0) {
	        if (sCount>=1) {
	           balance --;
	           sCount--;
	        } else {
	           break;
	        }
	    }
	     if (balance==0) return true;
	     return false;
	   
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		System.out.println("makeBricks(3, 1, 8)="+makeBricks(3, 1, 8));
		System.out.println("makeBricks(3, 1, 9)="+makeBricks(3, 1, 9));
		System.out.println("makeBricks(3, 2, 10)="+makeBricks(3, 2, 10));
	}

}
