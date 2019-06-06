package com.code.review.aaa.walmart.code;

public class ShuffleCards {
	/**
	 *  Given cards numbers: A[]={1,2,3,4,5,6,7} 
	 *  Shuffle the cards , output numbers are not ordered
	 *  (1) in loop using a rand function to produce the r<A.length
	 *  (2) check i!=r then Swap A[i] with A[r]
	 */
	public static Integer[] shuffleCards(Integer A[]) {
		int tmp;
		int r;
		int len = A.length;
		for (int i=0;i<len;i++) {
			r = (int)(Math.random()*len);
			if (r!=i) {
				tmp=A[i];
				A[i]=A[r];
				A[r]=tmp;
			}
		}
		return A;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer  A[]={1,2,3,4,5,6,7,8,9,10};  
	     shuffleCards(A);
		 for (int i:A) System.out.println(i);
		
	}

}
