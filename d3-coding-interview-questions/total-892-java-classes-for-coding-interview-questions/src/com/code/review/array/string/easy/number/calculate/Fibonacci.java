package com.code.review.array.string.easy.number.calculate;

public class Fibonacci {
	/**
	 * Fibonacci numbers are the integer sequence 0, 1, 1, 2, 3, 5, 8, 13, 21, 34,55,89,144,233
	 * Rule : Current number (nth) is sum of previous (n-1)th and previous previous number (n-2)th
	 *         | 0        n=0
	 * F[n] = <  1        n=0
	 *         | F[n-1]+F[n-2];
	 * @param n
	 * @return
	 */
	/**
	 *  Recursion way , n-1,n-n2 is recursively reduce
	 * @param n
	 * @return
	 */
	public static int fibRecursion(int n) {
        if (n < 2) {
           return n;
        }
        else {
        	return fibRecursion(n-1)+fibRecursion(n-2);
        }
	}
	/**
	 *  Iteration, accumulate the sum until current F[n]
	 *  
	 */
	public static int fibIterate(int n) {
		int prev1=0,prev2=1;
		for (int i=0;i<n;i++) {
			int prev_sum = prev1;
			prev1 = prev2;
			prev2 = prev_sum+ prev1;
		}
		return prev1;
	}
	public static int fibiterate(int n) {
		int prev1=0, prev2=1;
		for (int i=0; i<n; i++) {
			int prev_sum = prev1;
			prev1 = prev2;
			prev2 = prev_sum+prev1;
		}
		return prev1;
	}
	/**
	 * fibonacci is current element is the sum of previous two element
	 * @param data
	 */
	
	public static void printFibSerie(int n) {
		if (n==0) {
			System.out.println("0");
			return;
		}
		if (n==1) {
			System.out.println("0 1");
			return;
		}
		int fib[] = new int[n];
		fib[0]=0;
		fib[1]=1;
		for (int i=2;i<n;i++) {
			fib[i]=fib[i-2]+fib[i-1];
		}
		for (int i=0; i<n; i++) {
			System.out.print(fib[i]+" ");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Resursion n=11
		System.out.println("Recursively Fibonacci(11)="+fibRecursion(11));
		System.out.println("Iterately Fibonacci(11)="+fibIterate(11));
		
		System.out.println("Recursively Fibonacci(11)="+fibRecursion(11));
		System.out.println("Iterately Fibonacci(11)="+fibIterate(11));
		 printFibSerie(15);
		
	}

}
