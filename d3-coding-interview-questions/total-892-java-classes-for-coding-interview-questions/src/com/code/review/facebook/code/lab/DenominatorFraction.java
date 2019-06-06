package com.code.review.facebook.code.lab;

public class DenominatorFraction {
	/**
	 * Given two integers representing the numerator and denominator of a fraction, 
	 * return the fraction in string format.

		If the fractional part is repeating, enclose the repeating part in parentheses.
		
		Example :
		
		Given numerator = 1, denominator = 2, return "0.5"
		Given numerator = 2, denominator = 1, return "2"
		Given numerator = 2, denominator = 3, return "0.(6)"
		

	 * @param args
	 */
	public static String fraction(int numerator, int denominator) {
		System.out.println("--------------------numerator="+numerator+",denominator="+denominator+"---------------------");
		if (denominator==0) return null;
		if (numerator==0) return "0";
		int divided = numerator%denominator;
		double num = Double.valueOf(numerator);
		double den = Double.valueOf(denominator);
		double fractor = num/den;
		double temp = fractor;
		 
		if (divided==0) {
		   return String.valueOf(numerator/denominator);
		}
		 
		String str = String.valueOf(fractor);
 		int start = str.indexOf(".")+1;
		
		int i=start+1;		
	
		if (start == str.length()-1) {  // only one digit after period "."
			return str;
		}
		boolean repeated=true;
		while (i<str.length()) {
			if (str.charAt(start)!=str.charAt(i)) {
				repeated=false;
				break;
			} 
			i++;	
		}
		if (!repeated) {
			return String.valueOf(temp);
		} else {
			String s = String.valueOf(temp);
			int p = s.indexOf(".")+1;
			String point = Character.toString(s.charAt(p));
			s = "0.("+point+")";
			return s;
		}
		 
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numerator = 1, denominator = 2;
		System.out.println(fraction(numerator, denominator));
		numerator = 2;
		denominator = 1;
		System.out.println(fraction(numerator, denominator));
		numerator = 2;
		denominator = 3;
		System.out.println(fraction(numerator, denominator));
		numerator = 9;
		denominator = 20;
		System.out.println(fraction(numerator, denominator));
	}

}
