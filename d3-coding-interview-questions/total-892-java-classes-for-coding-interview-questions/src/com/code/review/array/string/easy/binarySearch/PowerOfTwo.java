package com.code.review.array.string.easy.binarySearch;

public class PowerOfTwo {
	   /* 
     *  keep dividing n by 2, if n%2!=0 or n/2==0, mean is not 2 power
        otherwise is 2 power
     */
    
    public boolean isPowerOfTwo(int n) {
        if (n==0) return false;
        
        while (n!=1) {
            if (n%2!=0 || n/2==0) {
                return false;
            }
            n/=2;
        }
        return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
