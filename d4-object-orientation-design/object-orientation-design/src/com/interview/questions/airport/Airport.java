package com.interview.questions.airport;

import java.util.Map;
import java.util.Set;

public interface Airport {
	public Runway TakeRunway(Flight airplane);
	public boolean releaseRunway(Flight airplane);
	public void displayStatus();
	public void addRunway(Runway runway);
	public Set<Runway> getRunwayList();
	public void setRunwayList(Set<Runway> runwayList);
	public Map<Flight, Runway> getRunwayMap();

}
