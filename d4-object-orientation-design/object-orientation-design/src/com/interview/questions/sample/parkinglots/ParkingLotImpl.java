package com.interview.questions.sample.parkinglots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ParkingLotImpl implements ParkingLot {
 
	 
	
	SlotList slotlist[] = new SlotList[4];
	
	// Slot No is associated a type of vechicle. wbenever drive comes in, slot manager gives them a slot no
	
	Map<String,Slot> parkings = new HashMap<String,Slot>(); 
	
	public ParkingLotImpl() {
		
		initialize();
	}
	
	public abstract void initialize() ;
	
	public String parking(VechicleLevel vechicle) {
		return parking(new Vechicle( vechicle));
	}
	
	public String parking(Vechicle vechicle) {
		Slot slot;
		String parkToken="";
		try { 
			for (int i= vechicle.getVechicleLevel().getCarLevel();i<3; i++) {
				SlotList slots = slotlist[i];
				if ((slot = slots.findEmptySlot())!=null) {
					parkToken = getTokenToPark(slot,vechicle);
					System.out.println("Found slot for "+vechicle.getVechicleLevel().getName()+" on token "+parkToken);
				} else {
					throw new NotFoundSlotException("Not found available slot for dmovNO "+vechicle.getDmvNo()+", please go to other park lot");

				}
  			}  
		} catch (NotFoundSlotException e) {
			System.out.println(e.getMessage());
		} catch (SlotDuplicatedRequestedException e) {
			System.out.println(e.getMessage());
		}
		return parkToken;
	}
	
	/**
	 * insert current vechicle associated slot to parkings map, key is slot hashcode
	 * @param slot
	 * @return return key as park token
	 */
	private String getTokenToPark(Slot slot,Vechicle vechicle) throws SlotDuplicatedRequestedException {
		 
		slot.setOccupied(true);
		
		// the token is combined with SlotNo and Vechicle Type to save time, we should use hashcode()
		String token = slot.getSlotNo()+" for "+vechicle.getDmvNo();
		if (parkings.containsKey(token)) {
			throw new SlotDuplicatedRequestedException("Same Slot "+slot.getSlotNo()+" duplicately requested ");
		}  
		parkings.put(token, slot);
		
		return token;
	}
	/**
	 * Based on parking token, remove parking slot 
	 * @param slot
	 * @param token
	 * @return
	 */
	public boolean unparking(String token) {
		if (!parkings.containsKey(token)) {
			return false;
		} 
		Slot slot = parkings.get(token);
		slot.setOccupied(false);		
		parkings.remove(token);
		return true;
	}
	public void reset() {
		
		slotlist[0].getSlots().clear();
		slotlist[1].getSlots().clear();
		slotlist[2].getSlots().clear();
		parkings.clear();
	}
	
	public void display() {
	
	}
}
