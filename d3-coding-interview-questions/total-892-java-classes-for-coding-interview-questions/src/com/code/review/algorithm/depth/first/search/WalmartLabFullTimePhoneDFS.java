package com.code.review.algorithm.depth.first.search;

import java.util.ArrayList;
import java.util.List;

public class WalmartLabFullTimePhoneDFS {

	/*
	Given an array of numbers A = [x1, x2, ..., xn] and T = Round(x1+x2+... +xn). 
	We want to find a way to round each element in A such that after rounding we get a new array B = [y1, y2, ...., yn] such that y1+y2+...+yn = T 
	where yi = Floor(xi) or Ceil(xi)

	We also want to minimize sum|x_i - y_i| where i=1 to n

	4.5 = floor 4 & ceil 5

	Example1:
	A => 30.9, 2.4, 3.9
	T => round (30.9 + 2.4 + 3.9) => round(37.1999996)= 37
	Expected Answer:
	31, 2, 4 
	Explanation => (31-30.9)+(2.4-2)+(4-3.9) = 0.6

	Other candidates:
	Candidate 1: 30, 3, 4  => (30+3+4 = 37)
	(30-30.9)+(3-2.4)+(4-3.9) = 1.59

	Candiidate2: 31,3,3 => (31+3+3 = 37)
	(30-30.9)+(3-2.4)+(4-3.9) = 1.60


	Example2:
	A => 1.2, 2.3, 3.4
	T => round(1.2+2.3+3.4) => round(6.9)=7
	Expected Answer:
	1,2,4 
	Explanation=> (1.2-1)+(2.3-2)+(4-3.4) = 1.09

	Other candidates:
	Candidate 1: 1, 3, 3  => (1+3+3 = 7)
	(1.2-1)+(2.3-3)+(3-3.4) = 1.3

	Candidate 2: 2, 2, 3  => (2+2+3 = 7)
	(1.2-2)+(2.3-2)+(3-3.4) = 1.49

	my approach:

	 Main idea:
	 use floor or ceil to figure out elements of B 
	 (1) Round A to get target
	 (2) DFS floor or ceil the elemewnt
	 (3) check if sum = target, 

	 */
	 

	    int rand = 1;
	    public int myApproach( double A[]) {
	        ArrayList<List<Integer>> combines = new ArrayList<List<Integer>>();
	        List<Integer> list =new ArrayList<Integer> ();
	        double sum1=0;
	        for (int i=0; i<A.length;i++) {
	            sum1+=A[i];
	        }
	        int sum =(int) Math.round(sum1);
	        System.out.println("sum="+sum);
	        
	        dfs( A , sum, 0,  combines,list);
	        System.out.println("combines.size()="+combines.size());
	        int min = Integer.MAX_VALUE;
	        
	        for (List<Integer> lst: combines) {
	            double s=0;
	            System.out.println(lst.toString());
	           
	            for (int i=0;i<A.length;i++) {
	            	int d = (int) lst.get(i);
	                if (d>A[i]) {
	                    s+=d-A[i];
	                } else {    
	                    s+=A[i]-d;
	                }
	            }
	            min = Math.min(min,(int) s);
	        }
	        
	        return min;
	    }
	    public void dfs(double A[] , int sum, int start, ArrayList<List<Integer>> combines, List<Integer> list) {
	        if (sum==0 && list.size()==A.length ) {
	        	List<Integer> temp = new ArrayList<Integer>(list);
	            combines.add(temp);
	            return;
	        }
	        if (sum<0) {
	            return ;
	        }
	        for (int i=0;i<A.length;i++) {
	            
	            System.out.println(rand);
	            int p =(int) (rand==1? Math.floor (A[i]):Math.ceil(A[i]));
	            rand = rand ==1 ? 0:1;
	            list.add(p);
	            dfs(A,sum-p, i,combines,list);
	            list.remove(list.size()-1);
	        }
	    }
	    public static void main(String args[] ) throws Exception {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
	    	double A[] = {30.9, 2.4, 3.9};
	    	WalmartLabFullTimePhoneDFS ref = new WalmartLabFullTimePhoneDFS();
	    	System.out.println(ref.myApproach(A));
	    }
	
}
