package com.interview.questions.sample.selling.machine;

import java.util.List;

public interface SellingMachine {
	/**
	 * Obtain current item price based user selected item
	 * @param item
	 * @return item price
	 */
	public long getItemPrice(Product item);
	/**
	 * 
	 * @return the changes list of selected item
	 */
	 
	public Bucket<Product, List<Coin>> collectItemChange();
	
	 
	
	/**
	 * Insert coin to purchase one drink item
	 * @param coin
	 */
	public void insertCoin(Coin coin);
	
	/**
	 *  Refund all coins if user cancel order
	 */
	public void refund();
	
	/**
	 *  Clear up vendor machine
	 */
	public void reset();
	
	/**
	 *  Get Total value from changes list
	 */
	public int getTotalChange(List<Coin> changes);
}
