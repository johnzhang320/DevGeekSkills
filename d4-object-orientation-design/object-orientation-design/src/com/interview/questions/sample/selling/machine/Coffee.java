package com.interview.questions.sample.selling.machine;

public enum Coffee implements Product {
	
	MOCHA("Mocha", 75), CUPPUCCINO("Cuppuccino",125),LATTE("Latte",100);
	private String name;
	private int price;
	
	
	private Coffee(String name, int price) {
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
