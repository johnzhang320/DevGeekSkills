package com.code.review.recursion;

public class TailRecursion {
	/**
	 * Non Tail Recursion Call: println part is same order as calling recursion call and reverse order
	 * as recursive stack pop out order 
	 * 
	 * System.out.println result:
	 * 	calling 5* factorial(4)    --  System.out.println("calling "+n+"* factorial("+(n-1)+")");	
		calling 4* factorial(3)
		calling 3* factorial(2)
		calling 2* factorial(1)
		120                        --  System.out.println(factorial(5));	   
	 *  
	 
	 * Calling Stack map, for example calculate factorial(5)
	 *  -----------------
	 *  | factorial(1)  |   Last call factorial(1) = 1  LIFO , return first
	 *  | factorial(2)  |   2*1
	 *  | factorial(3)  |   3*2*1
	 *  | factorial(4)  |   4*3*2*1
	 *  | factorial(5)  |   5*4*3*2*1
     *  | return        |   120                         Finally return	
	 * @param args
	 */
	static int factorial(int n) {
	      if (n <= 1)
	            return 1;
	      else {
	    	System.out.println("calling "+n+"* factorial("+(n-1)+")");     
	        return   n * factorial(n - 1);
 	      }
	      
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(factorial(5));
	}

}
