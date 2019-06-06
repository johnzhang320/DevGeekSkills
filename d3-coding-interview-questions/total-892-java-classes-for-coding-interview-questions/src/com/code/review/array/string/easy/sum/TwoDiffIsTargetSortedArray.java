package com.code.review.array.string.easy.sum;

import java.util.ArrayList;
import java.util.List;

public class TwoDiffIsTargetSortedArray {
/**
 * Given an array of positive, unique, increasingly sorted array, such as 
 * A={1,2,3,5,6,8,9,11,12,13} given a k =3
 * Output all pairs in A that differ exactly by K
 * 2,5
 * 3,6
 * 5,8
 * 6,9
 * 8,11
 * 9,12
 * Solution:
 * points i=0, j=1, when arr[j]-arr[i] = diff, if diff<k, j++ and if diff>k i++, else add pair to
 * list 
 */
	class Pair {
		public int a;
		public int b;
		public Pair(int x, int y) {
			a = x;
			b = y;
		}
		@Override
		public String toString() {
			return "Pair [a=" + a + ", b=" + b + "]";
		}
		
	}
	public List<Pair> PairDiffIsTarget(int arr[], int target) {
		if (null==arr || arr.length==0 || arr.length==1) return null;
		List <Pair> result = new ArrayList<Pair>();
    	int diff=0;	
		int i=0, j =1;
		while (i<arr.length && j< arr.length) {
			diff = arr[j]-arr[i];
			if (diff<target) {
				j++;
			} else if (diff>target) {
				i++;
			} else {
				result.add(new Pair(arr[i],arr[j]));
				j++;
				i++;
			}
 		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoDiffIsTargetSortedArray ref = new TwoDiffIsTargetSortedArray();
		int arr[] ={1,2,3,5,6,8,9,11,12,13};
		int target = 3;
		List<Pair> list = ref.PairDiffIsTarget(arr, target);
		for (Pair p: list) {
			System.out.println(p.toString());
		}
	}

}
