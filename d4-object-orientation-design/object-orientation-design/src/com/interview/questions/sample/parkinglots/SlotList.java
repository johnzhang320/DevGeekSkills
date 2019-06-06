package com.interview.questions.sample.parkinglots;

import java.util.ArrayList;
import java.util.List;

public class SlotList {
	
	private List<Slot> slots = new ArrayList<Slot>();
	
	public SlotList() {
		super();
	}

	public Slot findEmptySlot() throws NotFoundSlotException {
		 
		for (int i=0; i<slots.size();i++){
			if (!slots.get(i).isOccupied()) {		
				slots.get(i).setOccupied(true); // change object in list
				 
				return slots.get(i);
			}
		}
		return null;
		//throw new NotFoundSlotException("Not found available slot, please go to other park lot");
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}
	
}
