package com.interview.questions.sample.traffic.junction;

public interface Traffic {
	public TrafficSides getSides();
	public void setSides(TrafficSides sides);
	public int getId() ;
	public void setId(int id) ;
	public int getCommingFrequent() ;
	public void setCommingFrequent(int commingFrequent) ;
	public int getPassingSpeed() ;
	public void setPassingSpeed(int passingSpeed) ;
	public String getTrafficType();
	
}
