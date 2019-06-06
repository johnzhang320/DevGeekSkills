package com.code.review.aaa.career.cup.array.string;

public class ContiguousThreeDuplicates {
	/**
	 *  Accentra Rakash question:
	 *  Make contiguous characters only repeated 3 times
	 *  intput:
	 *  bbaaaaadddaadddddbb
	 *  output:
	 *  bbaaadddaaddbb
	 *  My Approach:
	 *  track i-start<3 and prev == curr
	 *  (1) set start =0, prev = A[0]
	 *  (2) iterated i =1 to len,  count = i-start, check if prev == current char, 
	 *  (3) if (2) is yes,   check count<3, if yes add to result, 
	 *  (4) if (3) is no , do not add, 
	 *  (5) if (2) is no (prev !=current char), add curr char to result, prev <= current char, start <= i; 
	 * @param args
	 */
	public static String contiguousThreeTime(String str) {
		if (null==str || 0==str.length()) return str;
		int start =0;
		char prev = str.charAt(0);
		StringBuffer result = new StringBuffer();
		result.append(prev);
		int count =0;
		for (int i=1;i<str.length();i++) {
			count = i - start;
			char curr = str.charAt(i);
			if (prev == curr) {
				if (count<3) {
					result.append(curr);
				}
			} else {
				result.append(curr);
				prev = curr;
				start = i;   // new start, keep Q(N)
			}
		}
		return result.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "bbaaaaadddaaccccccbbbbbb";
		System.out.println(contiguousThreeTime(str));
	}

}
