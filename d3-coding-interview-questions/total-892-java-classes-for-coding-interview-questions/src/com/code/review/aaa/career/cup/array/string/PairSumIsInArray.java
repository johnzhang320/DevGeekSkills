package com.code.review.aaa.career.cup.array.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PairSumIsInArray {
	/**
	 * 
	 *  amazon-interview-questions
 
		 Given an array of numbers. Print all the pairs (2) of numbers in the array if the sum
		 of those numbers is also present in the array. Write in C
	 *   
	 *   input {2,4,1,3,5}
	 *   output 2,1 = 3
	 *          1,3 = 4
	 *          4,1 = 5
	 *          2,3 = 5
	 *          
	 *          1,2,3,4,5
	 */
	/**
	 *  (1) Sorted this array first
	 *  (2) i = A.length-1 to 0, 
	 *  (3) high = i-1 and low=0;  
	 *  (4) check if A[i]>A[high]+A[low] then low++
	 *  (5) check if A[i]<A[high]+A[low] then high--;
	 *  (6) check A[i] == A[high]+A[low] then put A[high] and A[low] to pair and add to arraylist result
	 *  (7) low > high break internal while, i--
	 * 
	 */
	public class Pair{
		public int first;
		public int second;
		public int sum;
		public Pair(int f, int s) {
			first = f;
			second =s;
			sum = f+s;
		}
		@Override
		public String toString() {
			return "Pair [first=" + first + ", second=" + second + ", sum=" + sum + "]\n";
		}
		 
		
	}
	public  List<Pair> findPairSumInArray(int A[]) {
		List<Pair> result = new ArrayList<Pair>();
		if (null==A || 0==A.length) return result;
		Arrays.sort(A);
		int low=0;
		int high = A.length-1;
		
		for (int i=A.length-1;i>=0;i--) {
			high = i;
			low =0;
			while(low<high) {
				if (A[i]>A[high]+A[low]) {
					low++;
				} else if (A[i]<A[high]+A[low]) {
					high--;
				} else {
					result.add(new Pair(A[low],A[high]));
					low++;
					high--;
				}
			}			
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {2,4,1,3,5,7};
		PairSumIsInArray ref = new PairSumIsInArray();
		List<Pair> result = ref.findPairSumInArray(A);
		result.forEach((n)->System.out.print(n.toString()+","));
	}

}
