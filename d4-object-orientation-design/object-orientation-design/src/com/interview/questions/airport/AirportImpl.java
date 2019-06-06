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

public abstract class AirportImpl implements Airport {
	
	protected Set<Runway> runwayList = new TreeSet<Runway>();
	/**
	 *  Long mean airplane flight chars summary
	 */
	protected Map<Flight, Runway> runwayMap = new HashMap<Flight, Runway>();
	
	public AirportImpl() {
		initialize();
	}
	public abstract void initialize();
	
 
	
	public Runway TakeRunway(Flight airplane) {
		Runway runway =null;
		try {
			runway = findSuitableRunway(airplane);
			if (runway!=null) {
				runway = getRunwayToUsing(runway, airplane);
			}
			System.out.println("Runway found "+runway.getRunwayNo() +" for flight "+airplane.getFilghtNo());	
			
		} catch (RunwayFullException e) {
			System.out.println(e.getMessage());
			 
		} catch (DuplicatedFlightRequestException e) {
			System.out.println(e.getMessage());
		}
		return runway;
	}
	
	private Runway findSuitableRunway(Flight airplane) throws RunwayFullException {
		int airWidth = airplane.getPlane().getWidth();
		for (Runway r : runwayList) {
			if (airWidth<=r.getWidth() && !r.isOccupied()) {
				return r;
			}
		}
		throw new RunwayFullException(" All Runways of this airport are occupied, please waiting ....");
	}
	private Runway getRunwayToUsing(Runway r, Flight airplane) throws DuplicatedFlightRequestException {
		//int hashCode = airplane.hashCode()*43;
		if (runwayMap.containsKey(airplane)) {
			throw new DuplicatedFlightRequestException(airplane.getFilghtNo()+" Duplicated Flight try to request runway !");
			 
		}
		r.setOccupied(true);
		runwayMap.put( airplane,r);
		return r;
 	}
	public boolean releaseRunway(Flight airplane) {
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
		Iterator<Flight> itr = runwayMap.keySet().iterator();
		while (itr.hasNext()) {
			Flight key  = itr.next();
			Runway r = runwayMap.get(key);
		}
	}
	
	public void addRunway(Runway runway) {
		runwayList.add(runway);
	}

	public Set<Runway> getRunwayList() {
		return runwayList;
	}

	public void setRunwayList(Set<Runway> runwayList) {
		this.runwayList = runwayList;
	}
 	
	public Map<Flight, Runway> getRunwayMap() {
		return runwayMap;
	}

	public void setRunwayMap(Map<Flight, Runway> runwayMap) {
		this.runwayMap = runwayMap;
	}

	public static void main(String[] args) {
		 
		System.out.println("Take Runways by following airplane");
 
	/*	 runwayArr[0] = handler.TakeRunway(Flight.CA101);
		 runwayArr[1] = handler.TakeRunway(Flight.UA130);
		 runwayArr[2] = handler.TakeRunway(Flight.UD402);
		 runwayArr[3] = handler.TakeRunway(Flight.CA101);
		 runwayArr[4] = handler.TakeRunway(Flight.UD402);
		 runwayArr[5] = handler.TakeRunway(Flight.UN650);
		 runwayArr[6] = handler.TakeRunway(Flight.UD402);
		for (int i=0; i<runwayArr.length;i++) {
			System.out.println(runwayArr[i].toString());
		}
		System.out.println("Airplane occupied which runway");
		handler.displayStatus();
		
		System.out.println("Some airplane left the runway");
		handler.releaseRunway(Flight.UN650);
		handler.TakeRunway(Flight.UN650);
		System.out.println("Airplane occupied which runway");
		handler.displayStatus();*/
	}

}
