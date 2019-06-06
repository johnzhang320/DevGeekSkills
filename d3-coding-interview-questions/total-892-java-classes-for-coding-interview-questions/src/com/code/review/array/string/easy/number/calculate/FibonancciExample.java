package com.code.review.array.string.easy.number.calculate;

public class FibonancciExample {
	/**
	 *  using recursive
	 *  0, 1, 1, 2, 3, 5, 8, 13, 21, 34,55,89,144,233
	 */
	public static int fibonancci(int number) {
		if (number==1 || number==2) {
			return 1;
		}
		return (fibonancci(number-1)+fibonancci(number-2));
	}
	/**
	 *  using iterator
	 */
	public static int fibonancci2(int number) {
		if (number==1 || number==2) return 1;
		int fib1=1, fib2=1, fibonancci=1;
		for (int i=3; i<=number;i++) {
			fibonancci = fib1+fib2;
			fib1 = fib2;
			fib2 = fibonancci;
		}
		return fibonancci;		
	}
	/**
	 *  Dynamic Program using memorize and reuse
	 *  ensure each previus fibonancci data be calculated only once
	 *  0, 1, 1, 2, 3, 5, 8, 13, 21, 34,55,89,144,233
	 * @param args
	 */
	public static int fibonancciDP(int n) {
		int fib[] = new int[n+1];
	 
		
		for (int i=1; i<=n;i++ ) {
			if (i<=2) {
				fib[i]=1;
			} else {
				fib[i] = fib[i-1]+fib[i-2];
			}
		}
		return fib[n];
	}
	
	public static void main(String args[]) { //input to print Fibonacci series upto how many numbers 

		System.out.println("Enter number upto which Fibonacci series to print: "); 

		int number = 12;

		System.out.println("Recursive Fibonacci series upto " + number +" numbers : "); //printing Fibonacci series upto number 

			for(int i=1; i<=number; i++){ 
			
				System.out.print(fibonancci(i) +" "); 
			} 
			
			System.out.println("\nIteration ");
			for(int i=1; i<=number; i++){ 
				
				System.out.print(fibonancci2(i) +" "); 
			} 
			System.out.println("\nDynamic Program ");
			for(int i=1; i<=number; i++){ 
				
				System.out.print(fibonancciDP(i) +" "); 
			} 
		} 



}
