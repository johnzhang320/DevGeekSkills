package com.code.review.matrix.middle.dynamic.program;

public class AboutDynamicProgram {
	/**
	 * 
	 *  Dynamic Programming is an algorithmic paradigm that solves a given complex problem by 
	 *  breaking it into subproblems and stores the results of subproblems to avoid computing 
	 *  the same results again. Following are the two main properties of a problem that suggest 
	 *  that the given problem can be solved using Dynamic programming.

		In this post, we will discuss first property (Overlapping Subproblems) in detail. The 
		second property of Dynamic programming is discussed in next post i.e. Set 2.
		
		1) Overlapping Subproblems
		2) Optimal Substructure
		
		1) Overlapping Subproblems:
		Like Divide and Conquer, Dynamic Programming combines solutions to sub-problems. 
		Dynamic Programming is mainly used when solutions of same subproblems are needed again and 
		again. In dynamic programming, computed solutions to subproblems are stored in a table so 
		that these don’t have to recomputed. So Dynamic Programming is not useful when there are 
		no common (overlapping) subproblems because there is no point storing the solutions if 
		they are not needed again. For example, Binary Search doesn’t have common subproblems.
		If we take example of following recursive program for Fibonacci Numbers, there are many 
		subproblems which are solved again and again.
		  simple recursive program for Fibonacci numbers  
		int fib(int n)
		{
		   if ( n <= 1 )
		      return n;
		   return fib(n-1) + fib(n-2);
		}
		
		Recursion tree for execution of fib(5)
		
		                              
		                         fib(5)
		                     /             \
		               fib(4)                fib(3)
		             /      \                /     \
		         fib(3)      fib(2)         fib(2)    fib(1)
		        /     \        /    \       /    \
		  fib(2)   fib(1)  fib(1) fib(0) fib(1) fib(0)
		  /    \
		fib(1) fib(0)
		
		We can see that the function f(3) is being called 2 times. If we would have stored the value of f(3), 
		then instead of computing it again, we could have reused the old stored value. There are following
		 two different ways to store the values so that these values can be reused:
		a) Memoization (Top Down)
		b) Tabulation (Bottom Up)
		
		a) Memoization (Top Down): The memoized program for a problem is similar to the recursive version 
		with a small modification that it looks into a lookup table before computing solutions. We initialize
		 a lookup array with all initial values as NIL. Whenever we need solution to a subproblem, we first 
		 look into the lookup table. If the precomputed value is there then we return that value, otherwise 
		 we calculate the value and put the result in lookup table so that it can be reused later.
		
	 */
	// Memoization (Top Down)
	private final static int MAX=800;
	private final static int NIL = -1;
	long lookup[]= new long[MAX];
	public AboutDynamicProgram() {
		initialize();
	}
	public void initialize() {
		for (int i=0;i<lookup.length;i++) lookup[i]=NIL;
	}
	public long fib(int n) {
		if (lookup[n]==NIL) {
			if (n<=1) {
				lookup[n] = n;
			} else {
				lookup[n] = fib(n-2)+fib(n-1);
			}
		}
		return lookup[n];
	}
	/**
	 *  b) Tabulation (Bottom Up): The tabulated program for a given problem builds a table in bottom 
	 *  up fashion and returns the last entry from table. For example, for the same Fibonacci number, 
	 *  we first calculate fib(0) then fib(1) then fib(2) then fib(3) and so on. So literally, we are 
	 *  building the solutions of subproblems bottom-up. Normally Tabulation (Buttom up) is used as iteration

		Following is the tabulated version for nth Fibonacci Number.
	 *  
	 */
	public long fib2(int n)
	{
	  long f[] = new long[n];
	  int i;
	  f[0] = 0;   f[1] = 1; 
	  for (i = 2; i <= n; i++)
	      f[i] = f[i-1] + f[i-2];
	 
	  return f[n];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AboutDynamicProgram ref = new AboutDynamicProgram();
	   System.out.println(	ref.fib(169));
	}

}
