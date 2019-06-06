package com.code.review.array.string.easy.binarySearch;

public class PowerBinarySearch {
	   /**
     * Using binary Search and make it O(log(n))
     * Such as 2 ^4 = (2*2) * (2*2) = 4 * 4 means 
       first time calculated result as 2*2 => value,
       second time value * value  
       if n is odd, value*value*x 
       each time n/2 till is is 0 
       check if n < 0 
       check even and odd 
       
     */
    public double helper(double x, int n) {
        // recursive boundary condition
        if (n==0) return 1.0;  
         // recursive check condition
        if (n==1) return x;
        int mid = n/2;
        double value = helper(x,mid);
        if (n%2==0) { // even
            return value* value;
        } else {
            return value*value*x;
        }
    } 
    public double myPow(double x, int n) {
        double result =0;
        if (n<0) {
            result = 1/helper(Math.abs(x), Math.abs(n)); 
        } else {
            result = helper(x,n);
        }
        return result;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PowerBinarySearch  ref = new PowerBinarySearch ();
		double x =2.0;
		int n=10;
		System.out.println(x+"^"+n+" is "+ref.myPow(x, n));
		
		x =2.0;
		n=-10;
		System.out.println(x+"^"+n+" is "+ref.myPow(x, n));
	}

}
