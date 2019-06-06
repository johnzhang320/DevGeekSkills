package com.code.review.aaa.vmware.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class FindDifferentElementTwoSortArrays extends TestCase {
	private FindDifferentElementTwoSortArrays ref = null;
    /**
     * Question: Two arrays are sorted , return sub array which element of array1 is not in array2
     *  
     */
	public int [] findA1NotInA2(int A1[], int A2[]) {		 
		 if (null == A2 || 0==A2.length) return A1;
		 int p1=0;
		 for (int i = 0; i<A1.length;i++) {
			  if (!BinSearch(A2,A1[i])) {
				  A1[p1++]= A1[i];
			  }
		 }
		 return Arrays.copyOf(A1,p1);
	}
	public boolean BinSearch(int A[], int x) {
		
		int low = 0;
		int high = A.length-1;
		while (low <= high) {
			int mid = low+(high-low)/2;
			if (x<A[mid]) {
				high = mid-1;
			} else if (x>A[mid]) {
				low = mid+1;
			} else {
				return true;
			}
		}
		return false;
	}
	// After meeting I did correct better solution at 4:14PM Sept 15
	public List<Integer> doBetter(int A1[],int A2[]) {
		List<Integer> result = new ArrayList<Integer>();
		//int p1=0, p2=0;
		int p1 = A1.length-1;
		int p2 = A2.length-1;
		 
		while (p1>=0 && p2>=0) {
			if (A1[p1]>A2[p2]) {
				result.add(A1[p1]);
				p1--;
			} else if (A1[p1]<A2[p2]) {				 
				p2--;
			} else {
				p1--;
				p2--;
			}
			
		}
		while (p1>=0) {
			result.add(A1[p1]);
			p1--;
		}
	   return result;	
	}
	
	// After meeting I did correct better solution at 4:14PM Sept 15
	public List<Integer> doBetter2(int A1[],int A2[]) {
		List<Integer> result = new ArrayList<Integer>();
		//int p1=0, p2=0;
		int p1 =0;
		int len1 = A1.length;
		int p2 = 0; 
		int len2= A2.length;
		 
		while (p1<len1 && p2<len2) {
			if (A1[p1]<A2[p2]) {
				result.add(A1[p1]);
				p1++;
			} else if (A1[p1]>A2[p2]) {				 
				p2++;
			} else {
				p1++;
				p2++;
			}
			
		}
		while (p1<len1) {
			result.add(A1[p1]);
			p1++;
		}
	   return result;	
	}
	
	public void setUp() {
		 ref = new FindDifferentElementTwoSortArrays();
	}
	public void testCase1 () {
		int A1[] = {-1,1,2,3,6,7,11,13};
		int A2[] = {2,4,6,7,8,9,10,12};
		int result [] = ref.findA1NotInA2(A1, A2);
		for (Integer i: result) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
		System.out.println(BinSearch(A2, 6));
		int A11[] = {-1,1,2,3,6,7,11,13};
		int A22[] = {2,4,6,7,8,9,10,12};
		List<Integer> result2 = ref.doBetter(A11,A22);
		
		for (Integer i: result2) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
		int A3[] = {-1,1,2,3,6,7,11,13};
		int A4[] = {2,4,6,7,8,9,10,12};
		List<Integer> result3 = ref.doBetter2(A3,A4);
		
		for (Integer i: result3) {
			System.out.print(i+" ");
		}
	}

}
