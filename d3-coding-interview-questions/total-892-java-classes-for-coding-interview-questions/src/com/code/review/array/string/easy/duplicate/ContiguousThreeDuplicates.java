package com.code.review.array.string.easy.duplicate;

public class ContiguousThreeDuplicates {
	/**
	 *  Accentra Rakash question:
	 *  Make contiguous characters only repeated 3 times
	 *  intput:
	 *  bbaaaaadddaadddddbb
	 *  output:
	 *  bbaaadddaadddbb
	 *  My Approach:
	 *  (1) define start=0 ,prev = s.charAt(0) ,count=0; and stringbuffer result 
	 *  (2) i = 1 to s.length()-1, count = i-start; curr = s.charAt(i);
	 *  (3) if (prev==curr) then check if count<3 then result.append(curr) 
	 *  (4) (3) no, result.append(curr) prev = curr; i=start; 
	 *  prev= s[0]
	 *  i = i to s.length
	 *  count = i-start
	 *  if (prev==s[i]) {
	 *     if (count<3) {
	 *        result.append(s[i])
	 *     }
	 *   } else {
	 *      result.append(s[i])
	 *   }
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
