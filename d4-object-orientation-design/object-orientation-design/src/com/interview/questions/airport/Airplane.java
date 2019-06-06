package com.interview.questions.airport;

public enum Airplane {
	BOEING747("Boeing747",120), A380("A380",130), BOEING757("Boeing757",150), MD200("MD200",140);
	
	private Airplane(String name, int width) {
		this.name = name;
		this.width = width;
	}
	private String name;
	private int width;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
}
