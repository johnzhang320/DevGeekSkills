package com.interview.questions.parkinglots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
	public static final int SMALL_SLOTS=20;
	public static final int COMPACT_SLOTS=15;
	public static final int LARGE_SLOTS=10;
	List<Slot> smallSlots = new ArrayList<Slot>();
	List<Slot> compactSlots = new ArrayList<Slot>();
	List<Slot> largeSlots = new ArrayList<Slot>();
	
	Map<Long,Slot> parkings = new HashMap<Long,Slot>(); 
	
	public ParkingLot() {
		for (int i=0; i<SMALL_SLOTS;i++) {
			smallSlots.add(new SmallSlot(i));
		}
		for (int i=0; i<COMPACT_SLOTS;i++) {
			compactSlots.add(new CompactSlot(i));
		}
		for (int i=0; i<LARGE_SLOTS;i++) {
			largeSlots.add(new LargeSlot(i));
		}
	}
	public Long parking(Vechicle vechicle) {
		Slot slot;
		Long parkToken=-1L;
		int vechicleType = vechicle.getCarType();
		if (Vechicle.MOTORCYCLES == vechicleType) {
			if ((slot = findEmptySlot(smallSlots))!=null) {
				parkToken = getTokenToPark(slot,vechicle);
			} else if ((slot = findEmptySlot(compactSlots))!=null) {
				parkToken = getTokenToPark(slot,vechicle);
			} else if ((slot = findEmptySlot(largeSlots))!=null) {
				parkToken = getTokenToPark(slot,vechicle);
			}
		} else if (Vechicle.CARS == vechicleType) {
			if ((slot = findEmptySlot(compactSlots))!=null) {
				parkToken = getTokenToPark(slot,vechicle);
			} else if ((slot = findEmptySlot(largeSlots))!=null) {
				parkToken = getTokenToPark(slot,vechicle);
			} 
		} else if (Vechicle.BUSES == vechicleType) {
			if ((slot = findEmptySlot(largeSlots))!=null) {
				parkToken = getTokenToPark(slot,vechicle);
			} 
		}
		return parkToken;
	}
	/**
	 * find empty slot from current slot list
	 * @param slots
	 * @return empty slot
	 */
	public Slot findEmptySlot(List<Slot> slots) {
		Slot emptySlot=null; 
		for (Slot slot:slots) {
			if (!slot.isOccupied()) {
				emptySlot = slot;				 
				break;
			}
		}
		return emptySlot;
	}
	/**
	 * insert current vechicle associated slot to parkings map, key is slot hashcode
	 * @param slot
	 * @return return key as park token
	 */
	public long getTokenToPark(Slot slot,Vechicle vechicle) {
		long key = vechicle.hashCode()*53;
		slot.setOccupied(true);
		if (parkings.containsKey(key)) {
			return -1;
		}  
		parkings.put(key, slot);
		return key;
	}
	/**
	 * Based on parking token, remove parking slot 
	 * @param slot
	 * @param token
	 * @return
	 */
	public boolean unparking(long token) {
		if (!parkings.containsKey(token)) {
			return false;
		} 
		
		Slot slot = parkings.get(token);
		slot.setOccupied(false);		
		parkings.remove(token);
		return true;
	}
}
