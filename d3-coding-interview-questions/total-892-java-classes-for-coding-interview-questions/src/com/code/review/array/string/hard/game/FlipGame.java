package com.code.review.array.string.hard.game;

import java.util.ArrayList;
import java.util.List;

public class FlipGame {
	/**
	 *  LeetCode – Flip Game (Java)
 

		You are playing the following Flip Game with your friend: Given a string that contains 
		only these two characters: + and -, you and your friend take turns to flip two 
		consecutive "++" into "--". The game ends when a person can no longer make a move and
		therefore the other person will be the winner.
		
		Write a function to compute all possible states of the string after one valid move.
		
		Java Solution
	 * @param args
	 */
	public static List<String> generatePossibleNextMoves(String s) {
	    List<String> result = new ArrayList<String>();
	 
	    if(s==null)
	        return result;
	 
	    char[] arr = s.toCharArray();
	    for(int i=0; i<arr.length-1; i++){
	        if(arr[i]==arr[i+1] && arr[i]=='+'){
	            arr[i]='-';
	            arr[i+1]='-';
	            result.add(new String(arr));
	            arr[i]='+';
	            arr[i+1]='+';
	        }
	    }
	 
	    return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="----++++-+-+-+-";
		System.out.println(generatePossibleNextMoves(s));
	}

}
