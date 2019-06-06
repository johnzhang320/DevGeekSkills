package com.interview.questions.airport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Iterator;

public class UsingRunway {
	
	private Set<Runway> runwayList = new TreeSet<Runway>();
	/**
	 *  Long mean airplane flight chars summary
	 */
	private Map<Airplane, Runway> runwayMap = new HashMap<Airplane, Runway>();
	
	public UsingRunway(Runway runwayArray[]) {
	//	runwayList = Arrays.asList(runwayArray); add O(n)
	//	Collections.sort(runwayList);   //  sorted by O(n^2) worse case
		for (Runway r: runwayArray) {
			runwayList.add(r);  // add by sorted O(1) to add , O(n) to sorted
		}
 	}
	
	public Runway TakeRunway(Airplane airplane) {
		
		Runway runway = findSuitableRunway(airplane);
		if (runway!=null) {
			runway = getRunwayToUsing(runway, airplane);
		}
		return runway;
	}
	
	public Runway findSuitableRunway(Airplane airplane) {
		int airWidth = airplane.getWidth();
		for (Runway r : runwayList) {
			if (airWidth<r.getWidth() && !r.isOccupied()) {
				return r;
			}
		}
		return null;
	}
	public Runway getRunwayToUsing(Runway r, Airplane airplane) {
		//int hashCode = airplane.hashCode()*43;
		if (runwayMap.containsKey(airplane)) {
			return null;
		}
		r.setOccupied(true);
		runwayMap.put( airplane,r);
		return r;
 	}
	public boolean releaseRunway(Airplane airplane) {
		//int hashCode = airplane.hashCode()*43;
		if (!runwayMap.containsKey(airplane)) {
			return false;
		}
		Runway runway = runwayMap.get(airplane);
		runway.setOccupied(false);
		runwayMap.remove(airplane);
		System.out.println("Airplane given up runway:"+airplane.toString());
		return true;
	}
	
	public void displayStatus() {
		Iterator<Airplane> itr = runwayMap.keySet().iterator();
		while (itr.hasNext()) {
			Airplane key  = itr.next();
			Runway r = runwayMap.get(key);
		//	System.out.println("Currently Flight:"+key.getFlight()+",Model:"+key.getName()+",width:"+key.getWidth()+",Occupied Runway"+r.getRunwayNo()+",runway width:"+r.getWidth());
		}
	}
	
	public void addRunway(Runway runway) {
		runwayList.add(runway);
	}
	/*public void sortRunway() {
		
		Collections.sort(runwayList);
	}
	
	*/
	public Set<Runway> getRunwayList() {
		return runwayList;
	}

	public void setRunwayList(Set<Runway> runwayList) {
		this.runwayList = runwayList;
	}

 

	
	
	public Map<Airplane, Runway> getRunwayMap() {
		return runwayMap;
	}

	public void setRunwayMap(Map<Airplane, Runway> runwayMap) {
		this.runwayMap = runwayMap;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/**		Runway runwayArr[] = {		
 		 new Runway("Run201",180,false),
		 new Runway("Run200",160,false),
		 new Runway("Run150",200,false),
		 new Runway("Run140",105,false),
		 new Runway("Run250",145,false),
		 new Runway("Run260",120,false),
		 new Runway("Run450",90,false),
		}; 
		System.out.println("Sorted Runway:");
		UsingRunway handler = new UsingRunway(runwayArr);
		for (Runway r: handler.runwayList) {
			System.out.println(r.toString());
		}
		System.out.println("Take Runways by following airplane");
 	Airplane Boeing747 = new Airplane("CA794", "Boeing747", 100);
	//	Airplane Boeing747_2 = new Airplane("UA793", "Boeing747", 100);
	//	Airplane AirBus800 = new Airplane("UN793", "A800", 135);
	//	Airplane AirBus800_2 = new Airplane("UN799", "A800", 135);
		 
		 runwayArr[0] = handler.TakeRunway(Boeing747);
		 runwayArr[1] = handler.TakeRunway(Boeing747_2);
		 runwayArr[2] = handler.TakeRunway(AirBus800);
		 runwayArr[3] = handler.TakeRunway(AirBus800_2);
		for (int i=0; i<runwayArr.length;i++) {
			System.out.println(runwayArr[i].toString());
		}
		System.out.println("Airplane occupied which runway");
		handler.displayStatus();
		
		System.out.println("Some airplane left the runway");
		handler.releaseRunway(AirBus800_2);
		
		System.out.println("Airplane occupied which runway");
		handler.displayStatus();*/
	}

}
