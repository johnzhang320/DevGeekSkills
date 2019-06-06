package com.code.review.array.string.hard.median;
 

public class MedianTwoSortedArrays {
	/**
	 *  Find Median from two sorted arrays
	 *  There are two sorted arrays A and B of size m and n respectively.
	 *  Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
	 *  Analysis
	 *  Suppose A[i] at median i value 
	 *  Suppose B[j] at median j value
	 *  when A[i] > B[j] say A[i] =100, B[j]=50, then we recursively find 50....100 , find median from B[j] to A[i],
	 *  when A[i] < B[j] say A[i] =17, B[j]=30, then we recursively find 17....30 , find median from A[j] to B[i],
	 *  Apply recursive binary search, then O(log(m+n))
	 *  
	 *   median A[i] = 17 
	 */
	public static double MedianSortedArrays(int[] nums1, int[] nums2) {
	    int total = nums1.length+nums2.length;
	    if(total%2==0){
	    	// if a [] = {4,5,8,10,12} b = {3,2,16} merged {2,3,4,5,8,10,12,16}
	    	// median = (5+8)/2.0 = 13/2 = 6.5, that's why total/2+1 and total/2 for even
	        return (findKth(total/2+1, nums1, nums2, 0, 0)+findKth(total/2, nums1, nums2, 0, 0))/2.0;
	    }else{
	    	// if a [] = {4,5,8,10,12} b = {3,2,13,14} merged {2,3,4,5,8,10,12,13,14}
	    	// median = 8 , why total/2 +1 for odd
	        return findKth(total/2+1, nums1, nums2, 0, 0);
	    }
	}
	 
	public static int findKth(int k, int[] nums1, int[] nums2, int s1, int s2){
	    if(s1>=nums1.length)
	        return nums2[s2+k-1];
	 
	    if(s2>=nums2.length)
	        return nums1[s1+k-1];
	 
	    if(k==1)
	        return Math.min(nums1[s1], nums2[s2]);
	 
	    int m1 = s1+k/2-1;
	    int m2 = s2+k/2-1;
	 
	    int mid1 = m1<nums1.length?nums1[m1]:Integer.MAX_VALUE;    
	    int mid2 = m2<nums2.length?nums2[m2]:Integer.MAX_VALUE;
	 
	    if(mid1<mid2){ // from mid1 to s2
	        return findKth(k-k/2, nums1, nums2, m1+1, s2);
	    }else{ // from s1 to mid2
	        return findKth(k-k/2, nums1, nums2, s1, m2+1);
	    }
	}
	public static void testFindMedianSortedArrays() {
		 
		int a[] = {4,5,8,10,12};
		int b[] = {3,2,16};
		double result = MedianSortedArrays(a, b);		 
		 
		System.out.println(result);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testFindMedianSortedArrays();
	}

}
