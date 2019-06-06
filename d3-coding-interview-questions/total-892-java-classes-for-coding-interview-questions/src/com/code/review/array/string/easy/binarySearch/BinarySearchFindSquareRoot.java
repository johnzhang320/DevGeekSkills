package com.code.review.array.string.easy.binarySearch;

public class BinarySearchFindSquareRoot {
/**1. Initialize, start = 0, end = number, mid = (start+end)/2.

2. Set prevMid = 0, as the previous mid value.

3. Find diff = absolute difference between prevMid and mid.

4. While mid is not the square root of number (i.e. mid*mid == number) or 
   difference diff is greater than 0.0005, 

repeat the following steps:

   a. If mid*mid > number, then the square root will be less than mid. So, set end = mid.

   b. Else, the square root will be greater than mid. So, set start = mid.

   c. Set prevMid = mid

   d. Re-evaluate mid  = (start+end)/2.

   e. Re-evaluate diff from prevMid and mid.

Below gif illustrates how this method works:

 * 
 * @param args
 */
	private final static double FIX_ERROR=0.00001;
	public static double findSquareRootBinarySearch(double data) {
		double low=0.0;
		double mid=0;
		double high=data;   // a sufficiently big number
 		 
		while (high-low>FIX_ERROR) {
			mid = low + (high - low)/2;
			if (mid*mid>data) {
				high = mid;
			} else {
				low = mid;
			}
		}
		
		return mid;
	}
	/**
	 * Find Integer square root
	 * @param data
	 * @return
	 */
	public static Integer findIntegerSquareRoot(Integer data) {
		Double low=0.0;
		Double mid=0.0;
		Double high=Double.valueOf(data);   // a sufficiently big number
 		 
		while ((high-low)>0.000001) {
			mid = low + (high - low)/2;
			if (mid*mid>data) {
				high = mid;
			} else {
				low = mid;
			}
		}
		Integer iMid = mid.intValue();
		if (iMid*iMid!=data) {
			return -1;
		}
		return mid.intValue();
	}
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double square = 4096.0;
		System.out.println("Square Root of number "+square+" ="+findSquareRootBinarySearch(square));
		Integer IntSquare= 37;
		System.out.println("Square Root of number "+IntSquare+" ="+findIntegerSquareRoot(IntSquare));
	}

}
