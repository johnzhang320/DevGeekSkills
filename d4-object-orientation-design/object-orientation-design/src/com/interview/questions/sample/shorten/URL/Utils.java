package com.interview.questions.sample.shorten.URL;

public class Utils {
	/**
	 * encode shorten URL
	 * @param id
	 * @return
	 */
	public static String encode(Long id) {
		String str = Long.toHexString(id);
		int len = str.length();
		if (len>2) {
			str = str.substring(0, len-2)+"."+str.substring(len-2);
		}  
		return str;	
	}
	public static Long decode(String shortenURL) {
		String str = shortenURL.replace(".", "");
		Long decimal = 0L;
		try {
			decimal = Long.parseLong(str, 16);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}	
		return decimal; 
	}
	 
}
