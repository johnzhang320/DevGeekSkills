package com.interview.questions.sample.traffic.junction;

public class Pedestrain implements Traffic {
	private TrafficSides sides;
	private int id;
	private int commingFrequent=2000;
	private int passingSpeed=600;
	
	public Pedestrain(TrafficSides sides) {
		super();
		this.sides = sides;
		this.id = IdGenerator.getRandomNumber();
	}

	public Pedestrain(TrafficSides sides, int commingFrequent, int passingSpeed) {
		super();
		this.sides = sides;
		this.commingFrequent = commingFrequent;
		this.passingSpeed = passingSpeed;
		this.id = IdGenerator.getRandomNumber();
	}
	public String getTrafficType() {
		return "Pedestrain:"+id;
	}
	public TrafficSides getSides() {
		return sides;
	}
	public void setSides(TrafficSides sides) {
		this.sides = sides;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCommingFrequent() {
		return commingFrequent;
	}
	public void setCommingFrequent(int commingFrequent) {
		this.commingFrequent = commingFrequent;
	}
	public int getPassingSpeed() {
		return passingSpeed;
	}
	public void setPassingSpeed(int passingSpeed) {
		this.passingSpeed = passingSpeed;
	}
	
}
