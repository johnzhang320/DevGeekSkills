package com.code.review.aaa.career.cup.array.string;

import java.util.ArrayList;
import java.util.List;

public class DistributeRoughlyEqaulWeighBuckets {
	/**
	 * 
	 *  amazon-interview-questions
 
		Given a sorted array with "n" elements, distributed them into "k" nearly equally weighing buckets.
		
		Space is not constraint.
		
		Ex: [1,3,6,9,10]
		bucket size: 3
		
		output:[10],[9,1],[3,6]
		
		Ex [1,3,4,6,8,9,10,12]
		bucket size: 4
		[12], [1,10] , [3,9], [4,8]
		Analysis
		(1) Sort array if it is not
		(2) from i=len-1; low=0; high=i-1 , A[low]+A[high] most likely nearly equal A[i]
		My Approach
		This is sorted array, mostly possible last element is nearly equal arr[0] + arr[n-2], arr[1]+ a[n-3]
		That is pick up last element as one of buckets, pick up high (from n-2) and low (from 0)
	 */
	public static List<List<Integer>> findNearlyEqualWeighBucket(int A[], int x) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int n = A.length-1;
		List<Integer> list = new ArrayList<Integer>();
		list.add(A[n]);
		result.add(list);
		int low = 0;
		int high = n-1;
		int count=0;
		while (low<high && count<x) {
			List<Integer> l = new ArrayList<Integer>();
			l.add(A[low]);
			l.add(A[high]);
			result.add(l);
			low++;
			high--;
			count++;
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1,3,4,6,8,9,10,12};
		int x= 4;
		
		List<List<Integer>> result = findNearlyEqualWeighBucket(A,  x);
		for (List<Integer> line: result) {
			System.out.print("[ ");
			line.forEach((i)->System.out.print(i+" "));
			System.out.print("]\n");
		}
	}

}
