package com.interview.questions.sample.traffic.junction;

public enum TrafficSides {
	EAST("East"), WEST("West"),NORTH("North"), SOUTH("South");
	private String side;

	private TrafficSides(String side) {
		this.side = side;
	}

	public String getSide() {
		return side;
	}
	
}
