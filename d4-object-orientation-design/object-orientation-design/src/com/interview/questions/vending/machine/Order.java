package com.interview.questions.vending.machine;

import java.util.List;

public class Order {
	private List<Slot> slotList;
	private List<Coin> coinList;
	public Order(List<Slot> slotList, List<Coin> coinList) {
		super();
		this.slotList = slotList;
		this.coinList = coinList;
	}
	public List<Slot> getSlotList() {
		return slotList;
	}
	public void setSlotList(List<Slot> slotList) {
		this.slotList = slotList;
	}
	public List<Coin> getCoinList() {
		return coinList;
	}
	public void setCoinList(List<Coin> coinList) {
		this.coinList = coinList;
	}
	
}
