package com.code.review.array.string.easy.binarySearch;

public class BinarySearchRunningWay {
	/**
	 * RunningWay problem , there are sorted array could be duplicated, find wing is just larger than 
	 * a running way and less than another wider way or equal a running way 
	 * RunningWay is occupied , then can be use next one, O(logN) time
	 * (1) simplest way, runway is 2 dim array, two rows , row 1 is running width, row 2 is occupied flag 0 is available , 1 is taken
	 * (2) Binary search row1, find or col value < wing <col+1 value, take equal or smaller width 
	 * (3) if duplicated width, always take left side first else go right until find non o
	 * (4) return index of runway
	 * @param args
	 */
	 static int runway[][] = {
					{100,105,115,115, 120,125, 128,128,128,130,140,155,155,160},
					{0,   0,  0,   1 ,  0,  0,   1,  0,  1,  0,  0,  1, 0, 0}
				  // 0	  1   2    3	4   5    6   8   9   10  11  12 13 14 
		};
	public static int findRunway(int wingWidth) {
		if (wingWidth<=0) return -1;
		int cols = runway[0].length;
		int rows = runway.length;
		int low = 0;
		int high = cols-1;
		int prevLow = 0;
		int prevHigh = 0;
		/*
		 *  record previous low 
		 */
		int mid=0;
		while (low<=high) {
		    mid =low + (high-low)/2;
		    System.out.println("mid="+mid);
			if (runway[0][mid] == wingWidth) {
				if (runway[1][mid]==0) {
					return mid;
				}  else {
					int ty=mid--;
					while (ty>0 && runway[1][ty]==1 && wingWidth>=runway[0][ty]) {
						
					}
				}
			} else if (runway[0][mid]>wingWidth) {
				prevLow=high;
				high = mid+1;
			} else {
				prevLow = low;
				low = mid-1;
			}
			
		}
		if (runway[0][prevLow]<wingWidth) {
			if (runway[1][prevLow]==0) {
				return prevLow;
			}
		}
		if (runway[0][prevHigh]>wingWidth) {
			if (runway[1][prevHigh]==0) {
				return prevLow;
			}
		}
		return -1;
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(findRunway(115));
		//System.out.println(findRunway(128));
		//System.out.println(findRunway(130));
	}

}
