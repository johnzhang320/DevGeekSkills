package com.code.review.aaa.google;

public class LC774MinimizeMaxDistanceToGasStations {
	/**
	 * On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.

		Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
		
		Return the smallest possible value of D.
		
		Example:
		
		Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
		Output: 0.500000
		Note:
		
		stations.length will be an integer in range [10, 2000].
		stations[i] will be an integer in range [0, 10^8].
		K will be an integer in range [1, 10^6].
		Answers within 10^-6 of the true value will be accepted as correct.

	 */
	/**
     *  Guess Distance D from 0 - 10^8 , if meet the requirement, 
        then distance between two adjacent stations is divided this D, 
        if result<=k
        return result.
        we use binary search find result
     */ 
    public double minmaxGasDist(int[] stations, int K) {
        if (null==stations || 0==stations.length) {
            return 0.0;
        }
	        double low = 0;
	        double high = 1e8;
	        while (high-low>1e-6) {
			double mid = low +(high-low)/2.0;
			int count=0;
			for (int i=0; i<stations.length-1; i++) {
					count+=(int) ((stations[i+1]-stations[i])/mid);
				}
				if (count<=K) {  // calculate to le6
					high=mid;
				} else {
					low = mid;
				}
	        }
	        return low;
    }

}
