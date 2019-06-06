package com.interview.questions.sample.selling.machine;

public enum Drink implements Product {
	COKE("Coke", 25), PEPSI("Pepsi",35),SODA("Soda",45);
	private String name;
	private int price;
	
	
	private Drink(String name, int price) {
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	 
}
