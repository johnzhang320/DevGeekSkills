package com.code.review.aaa.apple.questions;



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
	public int roundSum(int a, int b, int c) {
	
		    return cell(a)+cell(b)+cell(c);
	}
	public int cell(int n) {    
	    int result = 0;
	    int d = n%10;
	    int b =  n /10;
	    int init = b * 10;    
	    if (d>=5) {
	       result = init + 10;
	} else {
	       result = init ;
	    }
	    return result;
	}
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RoundSum ref = new RoundSum();
		System.out.println(ref.roundSum(16, 17, 18));
		System.out.println(ref.roundSum(12, 13, 14));
		System.out.println(ref.roundSum(6, 4, 4));
	}

}
