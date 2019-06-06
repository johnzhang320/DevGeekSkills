package com.interview.questions.sample.traffic.junction;

public class Vechicle implements Traffic {
	private TrafficSides sides;
	private int id;
	private int commingFrequent=3000;
	private int passingSpeed=2000;
	
	public Vechicle(TrafficSides sides) {
		super();
		this.sides = sides;
		this.id = IdGenerator.getRandomNumber();
	}

	public Vechicle(TrafficSides sides, int commingFrequent, int passingSpeed) {
		super();
		this.sides = sides;
		this.commingFrequent = commingFrequent;
		this.passingSpeed = passingSpeed;
		this.id = IdGenerator.getRandomNumber();
	}
	public String getTrafficType() {
		return "Vechicle:"+id;
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
