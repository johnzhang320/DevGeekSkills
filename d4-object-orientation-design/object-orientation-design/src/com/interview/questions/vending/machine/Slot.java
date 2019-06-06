package com.interview.questions.vending.machine;


public class Slot {
	private String slotNo;  // A102, B401
	private Product drinks;

	public Slot(String slotNo, Product drinks) {
		super();
		this.slotNo = slotNo;
		this.drinks = drinks;
	}
	@Override
	public boolean equals(Object obj) {
		return ((Slot) obj).slotNo == this.slotNo;
	}
	@Override
	public int hashCode() {
		int sum=0;
		char slotChar[] = slotNo.toCharArray();
		for (int i=0; i<slotChar.length;i++) {
			sum+=slotChar[i];
		}
		return sum*53;
	}
	public String getSlotNo() {
		return slotNo;
	}
	public void setSlotNo(String slotNo) {
		this.slotNo = slotNo;
	}
	public Product getDrinks() {
		return drinks;
	}
	public void setDrinks(Product drinks) {
		this.drinks = drinks;
	}
	 
	
}
