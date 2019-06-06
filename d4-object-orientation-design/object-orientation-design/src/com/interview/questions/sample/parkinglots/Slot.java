package com.interview.questions.sample.parkinglots;

 

public class Slot {
	private SlotLevel slotLevel;
	private boolean occupied;
	private String slotNo;
	
	public Slot(SlotLevel slotLevel) {
		super();
		this.slotLevel = slotLevel;		
		this.occupied = false;
		this.slotNo = "slot"+String.valueOf(IdGenerator.getRandomNumber());
	}
	public Slot(SlotLevel lottype, boolean occupied, String slotNo) {
		super();
		this.slotLevel = lottype;
		this.occupied = occupied;
		this.slotNo = slotNo;
	}
	 
	public SlotLevel getSlotLevel() {
		return slotLevel;
	}
	public void setSlotLevel(SlotLevel slotLevel) {
		this.slotLevel = slotLevel;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public String getSlotNo() {
		return slotNo;
	}
	public void setSlotNo(String slotNo) {
		this.slotNo = slotNo;
	}
	@Override
	public boolean equals(Object obj) {
		return ((Slot) obj).getSlotNo().equals(this.getSlotNo());
	}
	@Override
	public int hashCode() {
		int hash = 5;
		int sum = 0;
		for (int i=0; i<slotNo.length();i++) {
			sum+= slotNo.charAt(i);
		}
		return 53*hash+sum;
	} 
	
}
