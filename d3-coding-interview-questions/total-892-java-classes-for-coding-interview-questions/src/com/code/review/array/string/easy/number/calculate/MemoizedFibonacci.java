package com.code.review.array.string.easy.number.calculate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MemoizedFibonacci {
	private static final List<BigInteger> FIBONACCI_LIST = new ArrayList<BigInteger>();
    static {
        FIBONACCI_LIST.add(BigInteger.ZERO);
        FIBONACCI_LIST.add(BigInteger.ONE);
    }

    public static BigInteger fibonacci(final int number) {
        if (number < 0){
            throw new IllegalArgumentException("negative number: " + number);
        }
        if (isInFibonacciList(number)) {
            return FIBONACCI_LIST.get(number);
        }
        BigInteger fibonacci = fibonacci(number - 1).add(fibonacci(number - 2));
        FIBONACCI_LIST.add(fibonacci);
        return fibonacci;
    }

    private static boolean isInFibonacciList(final int number) {
        return (number <= FIBONACCI_LIST.size() - 1);
    }
    public static void main(String args[]) {
    	System.out.println(fibonacci(100));
    }
}
