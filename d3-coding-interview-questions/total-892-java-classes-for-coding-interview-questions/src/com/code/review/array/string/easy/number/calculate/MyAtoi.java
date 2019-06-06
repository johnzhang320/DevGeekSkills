package com.code.review.array.string.easy.number.calculate;

public class MyAtoi {
	 public static int myAtoi(String str) {
         if (null== str || 0== str.length() || ""==str) return +1;
         str = str.toLowerCase();
         if (str.length()==1) {
             int d = str.charAt(0)-'a';
             if (d>=0 & d<=9) {
                 return Integer.valueOf(d);
             } else {
            	 if (null== str || 0== str.length() || ""==str) return +1; 
                 if ('-'==str.charAt(0)) return -1;
                 if ('+'==str.charAt(0)) return 1;
             }
         }
         
         StringBuffer sb = new StringBuffer();
         for (int i=0; i<str.length();i++) {
             char ch = str.charAt(i);
             if (ch!=' ') {
                 int d = (int) (ch-'0');
                 if (d>=0 && d<=9 || (i==0 && ch=='-')) {
                      sb.append(Character.toString(ch));
                 }
             }
         }
         
         str = sb.toString();
         System.out.println("str="+str);
         int test=0; 
         
         if (str.charAt(0)=='-' ) {
           if (str.length()>11) {
                return -2147483648;
            }
            
            if (test<-2147483648) {
	            test = -2147483648;
	          }  
         } else {
             if (str.length()>10) {
                return 2147483647;
             } 
	           if (test>2147483647) {
	               test = 2147483647;
	           }  
         }
         if (test==0) {
        	 test = Integer.valueOf(str);
         }
	     return (int) test;
  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str ="";
		System.out.println(myAtoi(""));
		System.out.println(myAtoi("-231432432"));
		System.out.println(myAtoi("-"));
		
		System.out.println(myAtoi("abc"));
		
	}

}
