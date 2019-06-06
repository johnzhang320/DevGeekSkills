package com.code.review.algorithm.recursion;

public class NonTailRecursion {
	/**
	 * Non Tail Recursion Call: working parts are placed after recursive calling, working part is accepting
	 * the data poping out from calling stack, therefore is reverse order from pushing data
	 * working part keep running until return condition met, in other word, working part already run before 
	 * return condition met
	 * 
	 * System.out.println result:
		calling 2* factorial(1)
		calling 3* factorial(2)
		calling 4* factorial(3)
		calling 5* factorial(4)
		120
		120                        --  System.out.println(factorial(5));	   
	 Therefore the Tail Recursive call always same order of recursive calling order
	 (recursive call factorial(5) first and println(factorial(5)) call first
	 
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
	      
	        int result= n * factorial(n - 1);
	        System.out.println("calling "+n+"* factorial("+(n-1)+")");	   // same as function pop out order 
	        return result;
 	         
	      }
	      
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(factorial(5));
	}

}
