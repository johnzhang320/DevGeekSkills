package com.code.review.array.string.easy.number.calculate;

public class ReverseInteger {
	public static int reverseInteger(int num) {
		if (num==0 || num>=Integer.MAX_VALUE) return 0;
		int sum =0;
		while (num>0) {
			int digit = num%10;
			sum = sum*10+digit;
			num/=10;
		}
		return sum;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(reverseInteger(0));
		System.out.println(reverseInteger(Integer.MAX_VALUE));
		
		System.out.println(reverseInteger(12345));
	}

}
