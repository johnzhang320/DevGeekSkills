package com.interview.questions.sample.parkinglots;

public class Runch99ParkingLot extends ParkingLotImpl {
	
	public void initialize() {
		slotlist[0] = new SlotList();
		for (int i=0; i<5; i++)
		slotlist[0].getSlots().add(new Slot(SlotLevel.SMALL));
		
		slotlist[1] = new SlotList();
		for (int i=0; i<5; i++)
		slotlist[1].getSlots().add(new Slot(SlotLevel.COMPACT));
		
		slotlist[2] = new SlotList();
		for (int i=0; i<5; i++)
		slotlist[2].getSlots().add(new Slot(SlotLevel.LARGE));
		
	}
	
}
