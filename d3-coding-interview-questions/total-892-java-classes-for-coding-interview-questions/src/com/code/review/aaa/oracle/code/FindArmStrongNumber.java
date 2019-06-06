package com.code.review.aaa.oracle.code;



import java.util.Scanner;

public class FindArmStrongNumber {

	/**
	 * An Armstrong number is a 3 digit number for which sum of cube of its digit is equal to
	 *  the number. Example of Armstrong number is 153 as 153= 1+ 125+27 which 1^3+5^3+3^3. 
	 *  Another Armstrong number is 371. In this Java program example we will see complete code 
	 *  example of Java program to check if any 3 digit number is Armstrong number or not.
	 * @param args
	 */
	public static boolean isArmStrong(int number) {
		int orig = number;
		int result=0;
		while(number!=0) {
			int remainder = number%10;
			result = result + remainder*remainder*remainder;
			number = number/10;
		}
		if (result==orig) {
			return true;
			
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  //input number to check if its Armstrong number
        System.out.println("Please enter a 3 digit number to find if its an Armstrong number:");
        int number = new Scanner(System.in).nextInt();
     
        //printing result
        if(isArmStrong(number)){
            System.out.println("Number : " + number + " is an Armstrong number");
        }else{
            System.out.println("Number : " + number + " is not an Armstrong number");
        }
	}

}

