package com.code.review.array.string.easy.binarySearch;

public class LinkedInInterviewQuestion {
	/** ['c', 'f', 'j', 'p', 'v'], 'a' => 'c'
	 * ['c', 'f', 'j', 'p', 'v'], 'c' => 'f'
	 * ['c', 'f', 'j', 'p', 'v'], 'k' => 'p'
	 * ['c', 'f', 'j', 'p', 'v'], 'z' => 'c' // The wrap around case
	 
	* ['c', 'f', 'j', 'p', 'v'], 'w' => 'c' // The wrap around case
	* ['c', 'f', 'j', 'p', 'v'], 'x' => 'c' // The wrap around case
	* ['c', 'f', 'j', 'p', 'v'], 'y' => 'c' // The wrap around case
	 
	 * ['c', 'f', 'k'], 'f' => 'k'
	 * ['c', 'f', 'k'], 'c' => 'f'
	 * ['c', 'f', 'k'], 'd' => 'f'
	 */
	// Java
	// Binary Search
	public static char findInsPoint(String sortedString, char x)
	{   
	// implementation goes here.
	    int low =0;    // INT_MAX / 2
	    int high = sortedString.length()-1;   // INT_MAX
	    int mid=0;
	   
	    if (x >= sortedString.charAt(high)) return sortedString.charAt(0);
	    
	    while (low<high) {
	        mid =low+(high-low)/2;
	        char midch = sortedString.charAt(mid);
	        // when i put f to x, mid is j, f < j , high = mid-1 f
	        if (midch>x) {
	            high=mid-1;
	        } else {
	            low = mid+1;
	        }
	    }
	    char midch = sortedString.charAt(mid);
	    System.out.println("x="+x+", Mid="+mid);
	    if (midch<=x) midch = sortedString.charAt(mid+1);
	    return midch;
	}
	public static char findInsertChar(String str, char x) {
		int low = 0;
		int high = str.length()-1;
		if ( x>=str.charAt(high)) {
			return str.charAt(low);
		}
		int mid=0;
		while (low<=high) {
			mid = low+(high-low)/2;
			if (x<str.charAt(mid)) {
				high = mid-1;
			} else if (x>str.charAt(mid)) {
				low=mid+1;
			} else { // char x is one of arr element
				if (mid==str.length()-1) {
					return str.charAt(0);
				} else {
					return str.charAt(mid+1);
				}
				 
			}
		}
		// char is not any elements of array
		
		return str.charAt(low);
			
		
	}
	public static void main(String[] args) {
		String str = "cfjpv";
		char x = 'c';
		System.out.println("x="+x+",selected value is "+ findInsPoint(str, x));
		
		x = 'k';
		System.out.println("x="+x+",selected value is "+findInsPoint(str, x));
		
		x = 'v';
		System.out.println("x="+x+",selected value is "+findInsPoint(str, x));
		
		x = 'w';
		System.out.println("x="+x+",selected value is "+findInsPoint(str, x));
		
		x = 'z';
		System.out.println("x="+x+",selected value is "+findInsPoint(str, x));
		
		x = 'a';
		System.out.println("x="+x+",selected value is "+findInsPoint(str, x));
		
		 
		x = 'p';
		System.out.println("x="+x+",selected value is "+findInsPoint(str, x));

		
		
		System.out.println("-------------findInsertChar---------------");
		
		x = 'c';
		System.out.println("x="+x+",selected value is "+ findInsertChar(str, x));
		
		x = 'k';
		System.out.println("x="+x+",selected value is "+findInsertChar(str, x));
		
		x = 'v';
		System.out.println("x="+x+",selected value is "+findInsertChar(str, x));
		
		x = 'w';
		System.out.println("x="+x+",selected value is "+findInsertChar(str, x));
		
		x = 'z';
		System.out.println("x="+x+",selected value is "+findInsertChar(str, x));
		
		x = 'a';
		System.out.println("x="+x+",selected value is "+findInsertChar(str, x));
		
		 
		x = 'p';
		System.out.println("x="+x+",selected value is "+findInsertChar(str, x));
	}
}