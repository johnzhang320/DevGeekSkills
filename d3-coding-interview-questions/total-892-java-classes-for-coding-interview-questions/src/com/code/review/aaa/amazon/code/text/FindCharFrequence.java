package com.code.review.aaa.amazon.code.text;
/**
 * 
 *  amazon-interview-questions
 

	Find the frequency of a number in array in less than bigo n time
	Array 1,2,2,3,4,5,5,5,2
	Input 5
	Output 3
	Array 1,1,1,1,
	Input 1
	Output 4
	
	Keep in mind less than bigo n
	
	Thanks
	My Approach:
	Obviously this is sorted array, we apply firstChar and lastChar subroutine
	and in each sub code , we use binary search , once find data, firstChar find A[mid-1] ! = k
	or k!=A[mid+1]
 *
 */

public class FindCharFrequence {
	public int firstChar(int A[], int k) {
		if (null==A || 0==A.length) return 0;
		int low = 0;
		int high = A.length-1;
		while(low<=high) {
			int mid = low+(high-low)/2;
			if (k<A[mid]) {
				high = mid-1;
			} else if (k>A[mid]) {
				low=mid+1;
			} else { // find k, first A[mid] is k			 
				if (mid>0 && k!=A[mid-1]) {
					return mid;
				}
				high=mid-1;
			}
			
		}
		return 0;
	}
	public int lastChar(int A[], int k) {
		if (null==A || 0==A.length) return 0;
		int low = 0;
		int high = A.length-1;
		while(low<=high) {
			int mid = low+(high-low)/2;
			if (k<A[mid]) {
				high = mid-1;
			} else if (k>A[mid]) {
				low=mid+1;
			} else { // find k, first A[mid] is k
				if (mid+1<A.length && k!=A[mid+1]) {
					return mid;
				}
				low=mid+1;
			}
			
		}
		return 0;
	}
	public int getFrequence(int A[], int k) {
		return lastChar(A,k)-firstChar(A,k)+1;
	}
	
	// Another way , find k first and then go low and go high
	// return lowest and highest to result[2]
	// using iteration way
	public int[]  getRepeatRange(int A[], int k) {
		if (null== A || A.length==0 ) return null;
		int low = 0;
		int high = A.length-1;
		int result[] = new int[2];
		while (low < high) {
			int mid = low + (high-low)/2;
			if (A[mid]>k) {
				high = mid-1;
			} else if (A[mid]<k){
				low = mid+1;
			} else {
				result[0] = mid;
				result[1] = mid;
				int i = mid;
				while (i>=low && A[i]==k) {
					result[0]=i;
					i--;
				}
				i = mid;
				while (i<=high && A[i]==k) {
					result[1] = i;
					i++;
				}
				break;
			}
		}
		return result;
	}
	
	
	public static void main(String args[]) {
		int A[] ={1,2,2,3,4,5,5,5,2};
		FindCharFrequence ref = new FindCharFrequence();
		int k=5;
		
		System.out.println("k="+k+",char frequence="+ref.getFrequence(A, k));
		int result[] = ref.getRepeatRange(A, k);
		System.out.println("k="+k+",char range="+result[0]+","+result[1]);
		
		int B[] = {1,1,1,1};
		
		k=1;
			
		System.out.println("k="+k+",frequence="+ref.getFrequence(A, k));
		 result = ref.getRepeatRange(B, k);
		System.out.println("k="+k+",char range="+result[0]+","+result[1]);
	}
}
