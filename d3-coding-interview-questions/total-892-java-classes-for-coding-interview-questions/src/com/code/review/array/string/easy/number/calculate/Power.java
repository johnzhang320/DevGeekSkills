package com.code.review.array.string.easy.number.calculate;

import java.math.BigInteger;

public class Power {
	/**
	 * 

		
		Calculate x ^ y in O(log n) [closed]
		up vote
		4
		down vote
		favorite
		3
			
		
		Interview question: "Calculate x ^ y in O(log n)"
		
		There are different answers like Use the Indian Power algorithm
		
		or
		
		double power(double x, int y) {
		    if(y == 0) return 1;
		
		    double d = power(x, y/2);
		
		    if(y%2 == 0) return d*d;
		    else return x*d*d;
		}

	 *  
	 */
	public static double power(double x, int y) {
	    if(y == 0) return 1;
	
	    double d = power(x, y/2);
	
	    if(y%2 == 0) return d*d;
	    else return x*d*d;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(power(2, 64));
	}

}
