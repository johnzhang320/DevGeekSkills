package com.code.review.aaa.walmart.code;

public class StringToNumber {
	/**
	 * Without using Integer.parse() or Integer.ValueOf() .
	 * (1) We divide the problem like 
	 * 
	 * toInteger(string s)
	 * toDouble(string s)
	 * (1) toInteger
	 * (2) verify if string contain illegal character 
	 * (3) check if negative, yes, start point i=1 else i=0
	 * (4) each char of s is multiplied by 10, charAt(i) - '0' to added to result
	 * (5) first of all use Long result check if string number digit limit, if overflow, return null else number*=10 and number+=s.charAt(0)-0
	 * @param args
	 */
	private static final String INTEGER_SAMPLE = "-0123456789";
	 
	 
	public static Integer toInteger(String s) {
		if (null==s || s.length()==0) return null;
		Long result=0L;
		Integer number=0;
		int len = s.length();
		boolean negative = false;
		int i=0;
		if (s.charAt(0)=='-') {
			negative=true;
			i=1;
		}
		
		while(i<len) {
			char c = s.charAt(i);
			if (INTEGER_SAMPLE.indexOf(c)==-1) {
				System.out.println("Error: String "+s+" contains illegal character");
				return null;
			}
			result*=10;
			result +=(long) ( s.charAt(i)-'0');
			if (negative) {
 				if ((-1)*result<Integer.MIN_VALUE) {
					System.out.println("Error: String "+s+" is less than minimal integer:"+Integer.MIN_VALUE);
					return null;
				} 
			} else {
				if (result>Integer.MAX_VALUE) {
					System.out.println("Error: String "+s+" is greater than maximum integer:"+Integer.MAX_VALUE);
					return null;
				} 
			}
			number*=10;
			number +=s.charAt(i)-'0';
			i++;
		}
		if (negative) {
			number = -number;
		}
 		return number;
	}
	/**
	 * (1) result=0, len, negative=false, i=0
	 * (2) if s.charAt(0)=='-', negative=true, i=1
	 * (3) dot = location of "." and precision=1;
	 * (4) if dot !=-1, len-=1, produce precision=10*len, remove "." from s
	 * (5) i<len loop check each char of s is illegal char of number
	 * (6) result*=10, result += s.charAt(i++)-'0'
	 * (7) check if str is over limit, note: min double is -Double.MAX_VALUE;
	 * (8) at finish point, check negative , make it negative
	 * (9) return result/precision;
	 * 
	 * @param s
	 * @return
	 */
	public static Double toDouble(String s) {
		if (null==s || s.length()==0) return null;
		 
		Double result=0.0;
		int len = s.length();
		boolean negative = false;
		int i=0;
		if (s.charAt(0)=='-') {
			negative=true;
			i=1;
		}
		int dot =s.indexOf(".");
		int precision = 1;
		if (dot!=-1) {
			len = len-1;
		
			int j=dot;
			while(j<len) {
				precision*=10;
				j++;
			}
 			s = s.replace(".", "");	
		}
		while(i<len) {
			char c = s.charAt(i);
			if (INTEGER_SAMPLE.indexOf(c)==-1) {
				System.out.println("Error: String "+s+" contains illegal character");
				return null;
			}
			result*=10;
			result +=(double) ( s.charAt(i)-'0');
			
			if (negative) {
 				if ((-result/precision)<(-Double.MAX_VALUE)) {
					System.out.println("Error: String "+s+" is less than minimal Double:"+(-Double.MAX_VALUE));
					return null;
				} 
			} else {
				if (result/precision>Double.MAX_VALUE) {
					System.out.println("Error: String "+s+" is greater than maximum Double:"+Double.MAX_VALUE);
					return null;
				} 
			}
			
			i++;
		}
		if (negative) {
			result=-result;
		}
		
 		return result/precision;
	}	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="85322142";
		Integer number = toInteger(str);
		System.out.println("Converted number is "+number);
		str="-35322142";
		number = toInteger(str);
		System.out.println("Converted number is "+number);
		str="55322142777";
		number = toInteger(str);
		System.out.println("Converted number is "+number);
		str="-953221429999";
		number = toInteger(str);
		System.out.println("Converted number is "+number);
		str="-4322142e999";
		number = toInteger(str);
		System.out.println("Converted number is "+number);
		
		str="953221429999.523";
		Double dnumber = toDouble(str);
		System.out.println("Converted number is "+dnumber);
		Double d = -1203.0;
		System.out.println("d is "+d);
		str="-214294242999.523";
		dnumber = toDouble(str);
		System.out.println("Converted number is "+dnumber);
		str="247567767765765232314294242999.5231323";
		dnumber = toDouble(str);
		System.out.println("Converted number is "+dnumber);
		str="24756776776576523231422ty294242999.5231323";
		dnumber = toDouble(str);
		System.out.println("Converted number is "+dnumber);
		
		str="-21429.523";
		dnumber = toDouble(str);
		System.out.println("Converted number is "+dnumber);
		str="-21429523";
		dnumber = toDouble(str);
		System.out.println("Converted number is "+dnumber);
	}

}
