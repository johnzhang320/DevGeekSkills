package com.interview.questions.sample.parkinglots;

public enum VechicleLevel {
	MOTORCYCLE("Motorcycle",0),CAR("Car",1),BUS("Bus",2);
	private String name;
	private int carLevel;

	

	private VechicleLevel(String name, int carLevel) {
		this.name = name;
		this.carLevel = carLevel;
	}

	public int getCarLevel() {
		return carLevel;
	}

	public void setCarLevel(int carLevel) {
		this.carLevel = carLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	  
 
	
	 
	
}
