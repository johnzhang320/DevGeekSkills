package com.interview.questions.airport;

public class SanFranciscoAirport extends AirportImpl {

	 public void initialize() {
		Runway runwayArr[] = {		
 		 new Runway("Run201",180,false),
		 new Runway("Run200",160,false),
		 new Runway("Run150",200,false),
		 new Runway("Run140",105,false),
		 new Runway("Run250",145,false),
		 new Runway("Run260",120,false),
		 new Runway("Run450",90,false),
		}; 
		for (Runway r: runwayArr) {
			runwayList.add(r);  // add by sorted O(1) to add , O(n) to sorted
		}
			 
	 }

}
