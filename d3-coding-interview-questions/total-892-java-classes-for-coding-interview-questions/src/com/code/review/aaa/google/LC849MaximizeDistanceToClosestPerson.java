package com.code.review.aaa.google;
/**
 * 
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty. 

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 

Return that maximum distance to closest person.

Example 1:

Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
Example 2:

Input: [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
Note:

1 <= seats.length <= 20000
seats contains only 0s or 1s, at least one 0, and at least one 1
 *
 */
public class LC849MaximizeDistanceToClosestPerson {
	/**
     *   
     *. if seat[0] ==0 , start = -1 else start =1
     *. end = 1 to seats.length-1
        if (seats[end]==1) 
        if start==-1 means first seat is empty , dist = end, 
        if start!=1 means first seat is not empty, we find middle between end and start
           dist = max(dist, (end-start)/2);
        start = end; calculate next one   
        if seats[length-1] ==0 , find length-1-start
     */
    public int maxDistToClosest(int[] seats) {
        if (null==seats || 0==seats.length) {
            return 0;
        }
        int start=1;
        if (seats[0]==0) {
            start = -1;   // flag for empty at seats[0]
        }
        int n = seats.length;
        int dist = Integer.MIN_VALUE;
        for (int end = 1; end<n;end++) {
            if (seats[end]==1) {
                if (start==-1) {
                    dist = end;
                } else { // find middle
                    dist = Math.max(dist,(end-start)/2);
                }
            }
            start = end;
        }
        // end round
       if (seats[n-1]==0) dist = Math.max(dist,(n-1-start));
        return dist;
    }
    public int maxDistToClosest2(int[] seats) {
        if (null==seats || 0==seats.length) {
            return -1;
        }
       
        int n = seats.length, dist = 0;
        int start = seats[0] == 1 ? 0 : -1; // check start bound: -1, imaginary boundary
        
        for (int end = 1; end < n; end++) {
            if (seats[end] == 1) {
                if (start == -1) dist = end;
                else dist = Math.max(dist, (end - start) / 2);
                start = end;
            } 
        }
        
        // end bound
        if (seats[n - 1] == 0) dist = Math.max(dist, n - 1 - start);
        return dist;
    }
}
