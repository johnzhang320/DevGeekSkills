package com.code.review.algorithm.recursion;

public class TailRecursion {
	/**
	 * Tail Recursion Call: working part is placing before recursive calling, which is pushing data to 
	 * calling stack, it is proceeding order as pushing data to calling stack
	 * 
	 * when return condition met , working part start to run, in other word working part do not run until
	 * working condition met
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
