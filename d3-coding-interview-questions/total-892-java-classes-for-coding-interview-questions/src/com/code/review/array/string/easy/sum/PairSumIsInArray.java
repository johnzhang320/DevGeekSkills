package com.code.review.array.string.easy.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PairSumIsInArray {
	/**
	 * 
	 *  amazon-interview-questions
 
		 Given an array of numbers. Print all the pairs (2) of numbers in the array if the sum
		 of those numbers is also present in the array. Write in C
	 *   
	 *   Given an array , find all pair of elements which sum is also in array
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
	/**
	 * Using Binary Search find pair of element in sorted array is A[i]
	 * Therefore 
	 * (1) sorted array
	 * (2) loop from A.length-1, A[i]
	 * (3) binary search to find A[i] == A[low]+A[high] 
	 * @param args
	 */
	public List<List<Integer>> findAllPairInArray(int A[]) {
		if (null==A || 0==A.length) return null;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(A);
		int low,high;
		for (int i=A.length-1;i>=0;i--) {
			low = 0;
			high = i;
			// now binary search without mid value;
			while (low<high) {
				if (A[i]>A[low]+A[high]) {
					low++;
				} else if (A[i]<A[low]+A[high]) {
					high--;
				} else {
					List<Integer> curr = new ArrayList<Integer>();
					//System.out.println("Pair ["+A[low]+" "+A[high]+"]");
					curr.add(A[low]);
					curr.add(A[high]);
					result.add(curr);
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
		//List<Pair> result = ref.findPairSumInArray(A);
		//result.forEach((n)->System.out.print(n.toString()+","));
		List<List<Integer>> resultSet = ref.findAllPairInArray(A);
		for (List<Integer> s:resultSet) {
			System.out.print("\n[");
			s.forEach(x->System.out.print(x+" "));
			System.out.print("]");
		}
		 
	}

}
