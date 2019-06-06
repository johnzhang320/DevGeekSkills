package com.interview.questions.sample.selling.machine;

public class Order {
	/**
	 *  Be able to accept muliple coins to purchase multiple drinks
	 */
	Inventory<Coin> depositCoin = new Inventory<Coin>();
	Inventory<Drink> selectedItem = new Inventory<Drink>();
	
	public Order(Inventory<Coin> depositCoin, Inventory<Drink> selectedItem) {
		super();
		this.depositCoin = depositCoin;
		this.selectedItem = selectedItem;
	}
	public Inventory<Coin> getDepositCoin() {
		return depositCoin;
	}
	public void setDepositCoin(Inventory<Coin> depositCoin) {
		this.depositCoin = depositCoin;
	}
	public Inventory<Drink> getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(Inventory<Drink> selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	
}
