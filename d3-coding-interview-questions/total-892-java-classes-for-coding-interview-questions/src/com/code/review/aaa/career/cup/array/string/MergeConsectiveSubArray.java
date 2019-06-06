package com.code.review.aaa.career.cup.array.string;

import java.util.ArrayList;
import java.util.List;

public class MergeConsectiveSubArray {
	/**
	 * Given a sorted integer array without duplicates, return the summary of 
	 * its ranges for consecutive numbers.

	 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
	 * given {1,2,3,5,6,7,9,10,11} return {"1->3", "5->7","9->11"}
	 * 
	 * implementation:
	 * (1) assign two variables: prev=A[0] and one variable start=A[0]; ArrayList<String>=>result
	 * (2) check if prev+1 == A[i], prev=A[i], else if not match, add start+"->"+prev to result; start=A[i]; prev = A[i]
	 * (3) check if start==prev, only put start to result
	 * (4) return result
	 */
	public static List<String> consectiveSubArray(int A[]) {
		List<String> result = new ArrayList<String>();
		if (null==A || 0==A.length) return result;
		int start = A[0];
		int prev = A[0];
		for (int i=1; i< A.length;i++) {
			if (prev+1!=A[i]) {
				if (start==prev) {
					result.add(String.valueOf(start));
				} else {
					result.add(String.valueOf(start)+"->"+String.valueOf(prev));
				}
				start = A[i];
			}
			prev = A[i];
		}
		if (start==prev) {
			result.add(String.valueOf(start));
		} else {
			result.add(String.valueOf(start)+"->"+String.valueOf(prev));
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1,2,3,5,6,7,9,10,11,15};
		List<String> result = consectiveSubArray(A);
		result.forEach((n)->System.out.print(n+","));
	}

}
