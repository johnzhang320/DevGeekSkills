package com.code.review.aaa.career.cup.array.string;

public class ContinuousArraySumIsMax {
	/**
	 *  bloomberg-lp-interview-questions
 
		You are given an array of integers both negative and positive.
		Print the maximum continuous sum of the array and if all the elements are negative, print
        the smallest of them.
		Ex : [-20, -5, -2, -9] :> -2(-2)
		Ex : [20, -19, 6, 9, 4] :-> 20(20)
		Ex : [10, -3, 4, -2, -1, 10] -> 18 (10, -3, 4, -2, -1, 10)
		Ex : [20, -25, 6, 9, 8] :-> 23(6,9,8)
		Ex : [-2, -5, 6, -2, -3, 1, 5, -6] : -> -14 (-2,-3,-4,-5)
		
		Three solutions:
		Solution one naive O(N^2)
			Two Level loop
			Loop one i to len, Loop two j=i to len, count max
		
		Solution two divide conquer
			we can find the maximum subarray sum in O(nLogn) time. Following is the Divide and Conquer algorithm.
	
			1) Divide the given array in two halves
			2) Return the maximum of following three
			….a) Maximum subarray sum in left half (Make a recursive call)
			….b) Maximum subarray sum in right half (Make a recursive call)
			….c) Maximum subarray sum such that the subarray crosses the midpoint 
			The lines 2.a and 2.b are simple recursive calls. How to find maximum 
			subarray sum such that the subarray crosses the midpoint? We can easily 
			find the crossing sum in linear time. The idea is simple, find the maximum 
			sum starting from mid point and ending at some point on left of mid, then
			find the maximum sum starting from mid + 1 and ending with sum point on right 
			of mid + 1. Finally, combine the two and return.  
	
	 *  
	 *  Solution three 
	 *  Kadane’s Algorithm: O(n)
	 *  
	 *  Initialize:
	    max_so_far = 0
	    max_ending_here = 0
	
		Loop for each element of the array
		  (a) max_ending_here = max_ending_here + a[i]
		  (b) if(max_ending_here < 0)
		            max_ending_here = 0
		  (c) if(max_so_far < max_ending_here)
		            max_so_far = max_ending_here
		return max_so_far
	 *  Explanation:
		Simple idea of the Kadane's algorithm is to look for all positive contiguous segments of the array 
		(max_ending_here is used for this). And keep track of maximum sum contiguous segment among all positive 
		segments (max_so_far is used for this). Each time we get a positive sum compare it with max_so_far 
		and update max_so_far if it is greater than max_so_far
		
		 Lets take the example:
    {-2, -3, 4, -1, -2, 1, 5, -3}

    max_so_far = max_ending_here = 0

    for i=0,  a[0] =  -2
    max_ending_here = max_ending_here + (-2)
    Set max_ending_here = 0 because max_ending_here < 0

    for i=1,  a[1] =  -3
    max_ending_here = max_ending_here + (-3)
    Set max_ending_here = 0 because max_ending_here < 0

    for i=2,  a[2] =  4
    max_ending_here = max_ending_here + (4)
    max_ending_here = 4
    max_so_far is updated to 4 because max_ending_here greater 
    than max_so_far which was 0 till now

    for i=3,  a[3] =  -1
    max_ending_here = max_ending_here + (-1)
    max_ending_here = 3

    for i=4,  a[4] =  -2
    max_ending_here = max_ending_here + (-2)
    max_ending_here = 1

    for i=5,  a[5] =  1
    max_ending_here = max_ending_here + (1)
    max_ending_here = 2

    for i=6,  a[6] =  5
    max_ending_here = max_ending_here + (5)
    max_ending_here = 7
    max_so_far is updated to 7 because max_ending_here is 
    greater than max_so_far

    for i=7,  a[7] =  -3
    max_ending_here = max_ending_here + (-3)
    max_ending_here = 4
	 */
	
	
	public static int dumbSolution(int A[]) {
		int max = Integer.MIN_VALUE;
		int len = A.length;
		
		for (int i=0; i<len; i++) {
			int sum=0;
			for (int j=i;j<len;j++) {
			   sum += A[j];
			   if (sum>max) {
				   max = sum;
			   }
			}
		}
		return max;
	}
	//O(NlogN)  Divided and conquer 
	// calculate cross middle point (from high to mid and mid+1 to low) then sum left and right
	public int crossMiddle(int A[], int low, int mid, int high) {
		int sum = 0;
		int leftMax=-Integer.MIN_VALUE;
		int rightMax=-Integer.MIN_VALUE;
		
		// from mid to low
		for (int i=mid ; i>=low; i--) {
			sum +=A[i];
			if (sum>leftMax) {
				leftMax = sum;
			}
		}
		// from mid to low
		sum=0;
		for (int i=mid+1 ; i<=high;i++) {
			sum +=A[i];
			if (sum>rightMax) {
				rightMax = sum;
			}
		}
		return leftMax+rightMax;
	}
	// return max from A[]
	public int calculateMax(int A[], int low, int high) {
		if (low==high) {
			return A[low];
		}
		int mid = low+(high-low)/2;
		/* Return maximum of following three possible cases
	      a) Maximum subarray sum in left half
	      b) Maximum subarray sum in right half
	      c) Maximum subarray sum such that the subarray crosses the midpoint */
		return myMax(
				calculateMax(A, low, mid),
				calculateMax(A, mid+1, high),
				crossMiddle(A, low, mid, high)
				);
	}
	public int myMax(int x, int y, int z) {
		return Math.max(Math.max(x, y), z);
	}
	public int divideConquer(int A[]) {
		return calculateMax(A,0,A.length-1);
	}
	//  Kadane’s Algorithm: O(n)
	public int kadaneAlgorithm(int A[]) {
		// initialize variable max_end_here and max_so_far
		int max_end_here =0;
		int max_so_far = 0;
		for (int i=0; i<A.length;i++) {
			max_end_here+=A[i];
			if (max_end_here<0) {
				max_end_here=0;
			} else {
				if (max_end_here>max_so_far) {
					max_so_far = max_end_here;
				}
			}
 		}
		return max_so_far;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A1[] = {20, -19, 6, 9, 4};
		int A2[] = {10, -3, 4, -2, -1, 10};
		int A3[] = {20, -25, 6, 9, 8};
		int A4[] = {-2, -5, 6, -2, -3, 1, 5, -6};
		System.out.println("--------------dumbSolution(O(n^2))----------------");
		System.out.println(dumbSolution(A1));
		System.out.println(dumbSolution(A2));
		System.out.println(dumbSolution(A3));
		System.out.println(dumbSolution(A4));
		
		System.out.println("--------------Divide and conquer(O(nlog(n)----------------");
		ContinuousArraySumIsMax ref = new ContinuousArraySumIsMax();
		System.out.println(ref.divideConquer(A1));
		System.out.println(ref.divideConquer(A2));
		System.out.println(ref.divideConquer(A3));
		System.out.println(ref.divideConquer(A4));
		
		System.out.println("--------------Kadane Algorithm(O(n))----------------");
		
		int A5[] = {-2, -3, 4, -1, -2, 1, 5, -13, 23};
		
		System.out.println(ref.kadaneAlgorithm(A1));
		System.out.println(ref.kadaneAlgorithm(A2));
		System.out.println(ref.kadaneAlgorithm(A3));
		System.out.println(ref.kadaneAlgorithm(A4));
		System.out.println(ref.kadaneAlgorithm(A5));
		
		
	}

}
