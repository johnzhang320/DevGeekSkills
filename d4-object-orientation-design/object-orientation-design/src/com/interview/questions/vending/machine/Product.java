package com.interview.questions.vending.machine;

public interface Product {
	//products Coke(25), Pepsi(35), Soda(45)
	public static String COKE="Coke";
	public static String PEPSI="Pepsi";
	public static String SODA="SODA";
	public static Integer COKE_PRICE=25;
	public static Integer PEPSI_PRICE=35;
	public static Integer SODA_PRICE=45;
	public String getProduct();
	public Integer getPrice();
	
	
}
