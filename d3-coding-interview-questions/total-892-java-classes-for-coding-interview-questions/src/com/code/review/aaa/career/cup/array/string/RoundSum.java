package com.code.review.aaa.career.cup.array.string;

public class RoundSum {
	/**
	 *  * For this problem, we'll round an int value up to the next multiple of 10 if its rightmost 
	 * digit is 5 or more, so 15 rounds up to 20. Alternately, round down to the previous multiple 
	 * of 10 if its rightmost digit is less than 5, so 12 rounds down to 10. Given 3 ints, a b c, 
	 * return the sum of their rounded values. To avoid code repetition, write a separate helper 
	 * "public int round10(int num) {" and call it 3 times. Write the helper entirely below and at 
	 * the same indent level as roundSum().

		roundSum(16, 17, 18) → 60
		roundSum(12, 13, 14) → 30
		roundSum(6, 4, 4) → 10
		My Approach
		(1) create a sub routine to do round calculate
		(2) calculate modulus of numnber by n%10, check if modulus >=5 , modulus is 10, otherwise is 0
		(3) calculate 10 multiple by p=n/10; p*=10
		(4) return p+modulus
	 */
	public static int moduleRound(int num) {
		int mod = num%10;
		int modRound = mod >=5 ? 10: 0;
		int cary = num/10;
		int sum = cary*10+modRound;
		return sum;
 	}
	public static int roundSum(int a, int b, int c) {
		return moduleRound(a)+moduleRound(b)+moduleRound(c);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(roundSum(16, 17, 18));
		System.out.println(roundSum(12, 13, 14));
		System.out.println(roundSum(6, 4, 4));
	}

}
