package com.interview.questions.parkinglots;

public abstract class Slot {
	private int slotNumber;
	private boolean occupied;
	
	Slot(int slotNumber) {
        occupied = false;
        this.slotNumber = slotNumber;    
    }
	
	@Override
	public boolean equals(Object obj) {
		return ((Slot) obj).getSlotNumber() == this.slotNumber;
	}
	@Override
	public int hashCode() {
		int hash = 5;
		return 53*hash+this.slotNumber;
	}
	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
}
