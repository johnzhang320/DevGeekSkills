package com.code.review.array.string.hard.game;

public class BullCow {
	 /**
     * LeetCode – Bulls and Cows (Java) 
  
		 You are playing the following Bulls and Cows game with your friend: You write down a
		 number and ask your friend to guess what the number is. Each time your friend makes 
		 a guess, you provide a hint that indicates how many digits in said guess match your
	     secret number exactly in both digit and position (called "bulls") and how many digits
	     match the secret number but locate in the wrong position (called "cows"). Your friend 
	     will use successive guesses and hints to eventually derive the secret number.
		For example:
		Secret number: "1807"
		Friend's guess: "7810"
		Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
		Write a function to return a hint according to the secret number and friend's guess, use 
		A to indicate the bulls and B to indicate the cows. In the above example, your function 
		should return "1A3B".
     */
    public static String FindBullsAndCows(String secret, String guess) {
   	 int bulls=0;
   	 int cows = 0;
   	 for (int i=0; i < secret.length();i++) {
   		 char c1 = secret.charAt(i);
   		 char c2 = guess.charAt(i);
   		 if (c1==c2) {
   			 bulls++;
   		 } else {
   			 cows++;
   		 }
   	 }
   	 return String.valueOf(bulls)+"A"+String.valueOf(cows)+"B";
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String secret = "1807";
		String guess = "7810";
		System.out.println(FindBullsAndCows(secret, guess));
	}

}
