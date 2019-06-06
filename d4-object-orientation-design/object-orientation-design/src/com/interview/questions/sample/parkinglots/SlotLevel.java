package com.interview.questions.sample.parkinglots;

public enum SlotLevel {
	SMALL(0),COMPACT(1),LARGE(2);
	
	private SlotLevel(int slotLevel) {
		this.slotLevel = slotLevel;
	}

	private int slotLevel;

	public int getSlotLevel() {
		return slotLevel;
	}

	public void setSlotLevel(int slotLevel) {
		this.slotLevel = slotLevel;
	}
	
	
}
