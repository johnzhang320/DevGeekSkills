package com.interview.questions.sample.parkinglots;

public interface ParkingLot {
	// Disclosure the APIs for ParkingLot
	public String parking(VechicleLevel vechicle);
	public String parking(Vechicle vechicle);
	public boolean unparking(String token);
	public void display();
	public void reset();
}
