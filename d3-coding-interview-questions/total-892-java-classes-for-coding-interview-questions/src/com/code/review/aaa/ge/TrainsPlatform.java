package com.code.review.aaa.ge;

public class TrainsPlatform {
	/**
	 *  There are many trains arriving and departure platform, how many platform needed to 
	 *  make trains have no waiting
	 *  Trains       Arriving            Departure
	 *  train1       9:45                10:50
	 *  train2       9:30                9:55
	 *  train3       11:20               11:55
	 *  train4       11:30               12:10
	 *  train5       10:30               11:00
	 *  train6       11:40               12:00
	 *  Analysis
	 *  check how many arriving times are greater than departure times, the max count is platforms number
	 *  implementation
	 *  (1) create train object , name, arrive(9.45,11.50 ,etc) and departure, implement compare
	 *  (2) sort objects
	 *  (3) in loop , check if overlap (curr.arrive > prev.departure) count++ , while the next
	 *  
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
