package com.concurrency.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FasterFibonacci {    
	private static Map<Long, Long> previousValuesHolder;
 
	static {
	    previousValuesHolder = new HashMap<Long, Long>();
	    previousValuesHolder.put(Long.valueOf(0), Long.valueOf(0));
	    previousValuesHolder.put(Long.valueOf(1), Long.valueOf(1));
	}
	public static long getFibonacciOf(long n) {
	    if (n== 0) {
	
	        return 0;
	    } else if (n == 1) {
	        return 1;
	    } else {
	        if (previousValuesHolder.containsKey(Long.valueOf(n))) {
	            return previousValuesHolder.get(n);
	        }  
	
	        long newValue = getFibonacciOf(n-2) + getFibonacciOf(n-1);
	        previousValuesHolder.put(Long.valueOf(n),     Long.valueOf(newValue));
	        return newValue;
	         
	
	    }
	}
	public static long fibRecursion(long n) {
	    if (n < 2) {
	       return n;
	    }
	    else {
	    	return fibRecursion(n-1)+fibRecursion(n-2);
	    }
	}
	
	public static long fibonancci2(long number) {
		if (number==1 || number==2) return 1;
		int fib1=1, fib2=1, fibonancci=1;
		for (int i=3; i<=number;i++) {
			fibonancci = fib1+fib2;
			fib1 = fib2;
			fib2 = fibonancci;
		}
		return fibonancci;		
	}
	public static void main(String[] args) {
	    Scanner scanner = new Scanner (System.in);
	         
	       
	    long n = 35L;
	    System.out.println("Enter n :"+n+"\n");
	    if (n >= 0) {
	    	//--------------------Fast Way ---------------
	        long beginTime = System.currentTimeMillis();
	        long fibo = getFibonacciOf(n);
	        long endTime = System.currentTimeMillis();
	
	        long delta = endTime - beginTime;
	
	        System.out.println("Fast Memory way F(" + n + ") = " + fibo + " ... computed     in " + delta + " milliseconds\n");
	
	      //--------------------Traditional Recursive Way ---------------
	        
	        beginTime = System.currentTimeMillis();
	        fibo = fibRecursion(n);
	        endTime = System.currentTimeMillis();
	
	        delta = endTime - beginTime;
	
	        System.out.println("Traditional Recursive way F(" + n + ") = " + fibo + " ... computed     in " + delta + " milliseconds\n");
	
	        //--------------------Iterate Way ---------------
	                    
	        
	        beginTime = System.currentTimeMillis();
	        fibo = fibonancci2(n);
	        endTime = System.currentTimeMillis();
	
	        delta = endTime - beginTime;
	
	        System.out.println("Traditional Iterate way F(" + n + ") = " + fibo + " ... computed     in " + delta + " milliseconds\n");
	
	    }
	        
	 }
}