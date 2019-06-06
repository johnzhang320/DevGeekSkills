package com.code.review.aaa.ge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.code.review.array.string.easy.merge.MergeIntervalsII.Interval;

public class TrainsPlatforms {
	/**
     *  There are many trains arriving and departure platform, how many platform needed to
     *  make trains have no waiting
     *  Trains       Arriving            Departure
     *  train1       9:45  (945)         10:50 (1050)
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
	public class Interval implements Comparable {
		  String name;
	      int start;
	      int end;
	      
	      
		  public Interval(String name, int start, int end) {
			super();
			this.name = name;
			this.start = start;
			this.end = end;
		}
		public int compareTo(Object o) {
			  Interval o2 = (Interval) o;
			  Interval o1 = this;
			  // Ascend order
			  if (o1.start != o2.start) {
				  return o1.start - o2.start;
			  } else {
				  return o1.end - o2.end;
			  }
		  }
		@Override
		public String toString() {
			return "[" + start + "," + end + "]";
		}
		  
	}
	public  int CalculatePlatform(List<Interval> trains) {
		if (null==trains || 0==trains.size()) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		Collections.sort(trains);
		
		for (int i =0 ; i<trains.size();i++) {
			Interval curr = trains.get(i);
			int count=1;
			int j = 0; 
			Interval other = trains.get(j);
			while (curr.start>other.end && j<trains.size()) {
				if (j!=i) {
					count++;
					other = trains.get(j++);
				} else {
					j++;
					other = trains.get(j);
				}
			}
			max = Math.max(max , count);
		}
		return max;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Interval> list = new ArrayList<Interval>();
		/*   *  train1       9:45  (945)         10:50 (1050)
		     *  train2       9:30                9:55
		     *  train3       11:00               11:20
		     *  train4       11:30               12:10
		     *  train5       10:30               11:00
		     *  train6       11:40               12:00*/
		TrainsPlatforms ref = new TrainsPlatforms();
		list.add(ref.new Interval("train1",945,1050));
		list.add(ref.new Interval("train2",1100,1120));
		list.add(ref.new Interval("train3",1130,1210));
		list.add(ref.new Interval("train4",1030,1100));
		list.add(ref.new Interval("train5",930,955));
		list.add(ref.new Interval("train6",1140,1200));
		System.out.println(ref.CalculatePlatform(list));
		
		     
	}

}
