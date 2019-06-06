package com.code.review.aaa.amazon.code.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindPairSum {
	/**
	 *  amazon-interview-questions
 

		A = {1,2,4,-6,5,7,9,....}
		B = {3, 6, 3, 4, 0 .......}
		n = 5 -> pairs whose sum is n
		
		Output = (1,4), (5,0)....
		My Approach
		Suppose each array elements are not repeated in itself
		add one element in HashMap(A[i]-x, A[i]), loop another array B
		check map.containsKey(B[i]) return pair.first = map.get(B[i]) , pair.second=B[i]
	 * 
	 */
	public class pair {
		public int first;
		public int second;
		public pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
		
	}
	public List<pair> findPairs(int A[], int B[] , int x) {
		List<pair> list = new ArrayList<pair>();
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		for (int i=0; i<A.length;i++) {
			if (x>=A[i]) {  // critical point: if x>=A[i], x-A[i]=B[j] means x = A[i]+B[j], if x< A[i], no room to add B
				map.put(x-A[i], A[i]);
			}
		}
		for (int i=0; i<B.length;i++) {
			int b = B[i];
			if (map.containsKey(b)) {
				list.add(new pair(map.get(b),b));
			}
		}
		return list;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindPairSum ref = new FindPairSum();
		int A[]={1,2,4,-6,5,7,9};
		int B[]={3, 6, 3, 4, 0};
		int x=5;
		List<pair> list = ref.findPairs(A, B, x);
		list.forEach((pair)->System.out.print("["+pair.first+","+pair.second+"]\n"));
	}

}
