package com.code.review.algorithm.dynamic.program;

import java.util.ArrayList;
import java.util.List;
/*
 * 123. Best Time to Buy and Sell Stock III
		Hard
		
		848
		
		50
		
		Favorite
		
		Share
		Say you have an array for which the ith element is the price of a given stock on day i.
		
		Say you have an array for which the ith element is the price of a given stock on day i.Design an algorithm to find the maximum profit. 
		You may complete at most k transactions.
		
		Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
		
		Example 1:
		
		Input: [3,3,5,0,0,3,1,4]
		Output: 6
		Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
		             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
		Example 2:
		
		Input: [1,2,3,4,5]
		Output: 4
		Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
		             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
		             engaging multiple transactions at the same time. You must sell before buying again.
		Example 3:
		
		Input: [7,6,4,3,1]
		Output: 0
		Explanation: In this case, no transaction is done, i.e. max profit = 0.
		Accepted
		135,716
		Submissions
		414,932
 */
/*
 *  I still check diff=A[i]-A[i-1] , if diff >0 chance = false, then buy = A[i-1] record the buy point, chance =true
 *  mean next time diff > 0 we do not change buy
 *  when diff<0 then if chance =true, record A[i-1]-buy to list 
 *  out of loop , change if chance = true means price always increased, last price munus buy to list
 */
public class BuySellStockIV {
	public static int maxProfit(int[] A,int k) {
        if (null==A || 0==A.length) return 0;
        List<Integer> list = new ArrayList<Integer>();
        int diff=0;
        int n = A.length;
        boolean chance=false;
        int buy=0;
        for (int i=1;i<A.length;i++) {
             diff =A[i]-A[i-1];
             if (diff>0) {
                 if (!chance) {
                     buy = A[i-1];
                 }
                 chance = true;
             } else {
                 if (chance) {
                     diff = A[i-1] - buy;
                     list.add(diff);
                     
                 }
                 chance = false;
             }
        }
        if (chance) {
            list.add(A[n-1]-buy);
        }
        int result=0;
        if (list.size()>k-1) {
            list.sort((o1,o2) -> o2-o1);
            for (int i=0;i<k;i++) {
            	result +=list.get(i);
            }
             
            list.forEach(x->System.out.print(x+" "));
        } else if(list.size()==1) {
            result = list.get(0);
        }
       
        System.out.println("Result="+result);
        return result;
    }
	public static void main(String[] args) {
		int A[] = {1,2,4,2,5,7,2,4,9,0};   // this test case I calculated to be 12 but leetcode is 13 , I don't know why
		maxProfit(A,2);
		int A2[] = {3,3,5,0,0,3,1,4};
		maxProfit(A2,2);
		int A3[] = {1,2,3,4,5};
		maxProfit(A3,2);
		int A4[] = {7,6,4,3,1};
		maxProfit(A4,2);
		int A5[] = {
				1,2,4,2,5,7,2,4,9,0};
		maxProfit(A5,2);
		
		int A6[] = {3,2,6,5,0,3,2,8,1,9};
		maxProfit(A6,3);
	}
}
