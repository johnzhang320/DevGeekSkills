package com.interview.questions.airport;

public class BinarySearchRunningWay {
	/**
	 * RunningWay problem , there are sorted array could be duplicated, find wing is just larger than 
	 * a running way and less than another wider way or equal a running way 
	 * RunningWay is occupied , then can be use next one, O(logN) time
	 * (1) simplest way, runway is 2 dim array, two rows , row 1 is running width, row 2 is occupied flag 0 is available , 1 is taken
	 * (2) Binary search row1, find or col value < wing <col+1 value, take equal or smaller width 
	 * (3) if duplicated width, always take left side first else go right until find non o
	 * (4) return index of runway
	 * 
	 * @param args
	 */
	 static int runway[][] = {
					{100,105,110,115, 120,125, 128,129,135,138,140,151,155,160},
					{0,   0,  0,   0 ,  0,  0,   1,  0,  0,  1,  0,  1,  0,  0}
			      // 0    1   2	   3    4   5    6   7   8   9   10  11  12  13
		};
	/**
	 * Binary Search, find where the wing is fit in an increase array 
	   (1) regular binary search : passed array A[] and target , low and high , A[mid] == target then return index
	   (2) if not found the same width runway, at return plase
	       return A[low]>=target ? low : high;  // A[low]< target we must take high index
	 */
	
	public static int binarySearch(int A[],int target) {
		  if (A == null || A.length == 0) {
	            return 0;
	        }
		  int low =0;
		  int high = A.length-1;
		  while (low+1<high) {
			  int mid = low + (high-low)/2;
			  if (A[mid]==target) {
				  return mid;
			  } else if (A[mid]<target) {
				  low = mid;
			  } else {
				  high = mid;
			  }
		  }
		  return A[low]>=target ? low: high;
	}
	 
	public static int findRunway(int wingWidth) {
		if (wingWidth<=0) return -1;
		 
	 
		return binarySearch(runway[0],wingWidth) ;
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(findRunway(125));
		System.out.println(findRunway(122));
		System.out.println(findRunway(130));
		System.out.println(findRunway(139));
	}

}
