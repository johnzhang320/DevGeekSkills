package com.code.review.algorithm.recursion;

public class BasicRecursionAnalysis {
	/**
	 * Below factorial
	 * Each recursion call has a return n* factorial(n-1) into stack
	 * Stack map, for example calculate factorial(5), call factorial(5) and push it to stack first and finally call factorial(1)
	 * and push it to stack finally and pop out it first because it return really value;
	 * 
	 *  -----------------
	 *  | factorial(1)  |   Last call factorial(1) = 1  LIFO , return first
	 *  | factorial(2)  |   2*1
	 *  | factorial(3)  |   3*2*1
	 *  | factorial(4)  |   4*3*2*1
	 *  | factorial(5)  |   5*4*3*2*1
     *  | return        |   120                         Finally return
	 *  
	 *  Recursive call always need to find when function be able to really return which function can start calculating real value 
	 *  Last one is when we can really calculate the value
	 * @param args
	 */
	static int factorial(int n) {
	      if (n <= 1)
	            return 1;
	      else {
	     	   
	       int result= n * factorial(n - 1);
	       
	       return result;
	      }
	      
	}
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(factorial(5));
	}

}
