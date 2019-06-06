package com.interview.questions.vending.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

	public static final Integer SLOTS_PER_DRINK = 4; 
	public static final Integer DRINKS_PER_SLOT =10 ; 
	/**
	 *  Integer is available number of drinks associated with specific slotl
	 */
	private Map<Slot,Integer> inventoryMap = new HashMap<Slot,Integer>();
 
	public VendingMachine() {
		Integer count=100;
 		for (Integer j=0; j<SLOTS_PER_DRINK;j++) {
			Coke coke = new Coke();
			Slot slotCoke = new Slot("NO"+(new Integer(count++)).toString(),coke);
			inventoryMap.put(slotCoke,DRINKS_PER_SLOT);
 		}
		
		for (Integer j=0; j<SLOTS_PER_DRINK;j++) {
			Pepsi pepsi = new Pepsi();
			Slot slotPepsi = new Slot("NO"+(new Integer(count++)).toString(),pepsi);
			inventoryMap.put(slotPepsi,DRINKS_PER_SLOT);
 		}
		for (Integer j=0; j<SLOTS_PER_DRINK;j++) {
			Soda soda = new Soda();
			Slot slotSoda = new Slot("NO"+(new Integer(count++)).toString(),soda);
			inventoryMap.put(slotSoda,DRINKS_PER_SLOT);
		}
 	}
	
	public List<Coin> processOrder(Order order) {
		List<Slot> slotList = order.getSlotList();
		List<Coin> coinList = order.getCoinList();
		/**
		 *  First of all check if total price <= total coin value;
		 */
		Integer totalPrice=0,totalCoinValue=0;
		for (Slot s: slotList) {
			totalPrice+=s.getDrinks().getPrice();
		}
		System.out.println("You totally price will be charged:"+totalPrice); 
		System.out.println("You totally paid:"); 
		totalCoinValue=this.displayPayStatus(coinList);
		
		if (!Confirm()) {
			System.out.println("You Cancel the payment, we are returning you :");
			this.displayPayStatus(coinList);
			return null;
		}
		
		if (totalPrice>totalCoinValue) {
			System.out.println("You still owe "+(totalPrice-totalCoinValue)+" cents for your order");
			System.out.println("Suggest that you should pay:");
			this.exchange(totalPrice-totalCoinValue);
			return null;
		}
		
		/**
		 *  unpack slots from list<Slot>
		 */
		for (Slot s: slotList) {
			Integer availNumber = findAvailNumber(s);
			if (availNumber>0) { // decrease drink count by 1
				unpackOneDrink(s);
			} else {
				// total charge will be reduced by this drink 
				totalPrice -=s.getDrinks().getPrice();
			}
		}
		
		Integer exchange = totalCoinValue - totalPrice;
		System.out.println("Congratulations, The truncation suceeded.....\nExhange to you: "+exchange+" cents"); 
		return exchange(exchange); // return exchange, pure value;
	}
	
	public List exchange(Integer value) {
		// Accepts coins of 1,5,10,25 Cents i.e. penny, nickel, dime, and quarter.
		List<Coin> coinList = new ArrayList<Coin>();
		
		int q = value/Coin.QUARTER;
		if (q>=1) {			 
			 coinList.add(new Quarter(q));
			 value = value%Coin.QUARTER;
		}
	 
		q = value/Coin.DIME;
		 
		if (q>=1) {			 
			 coinList.add(new Dime(q));
			 value = value%Coin.DIME;
		}
		 
		q = value/Coin.NICKEL;
		if (q>=1) {			 
			 coinList.add(new Nickel(q));
			 value = value%Coin.NICKEL;
		}
		q = value/Coin.PENNY;
		if (q>=1) {			 
			 coinList.add(new Penny(q));
			 value = value%Coin.PENNY;
		}
		//Show Exchange of value
		this.displayPayStatus(coinList);
		return coinList;	
		
	}
	public Integer displayPayStatus(List<Coin> coinList) {
		Integer sum=0;
		for (Coin c: coinList) {
			 if (c instanceof Quarter) {
				 System.out.print("Quarters(25 cents):"+c.getCoinNumber()+",value="+c.getCoinVale());
			 } else if (c instanceof Dime) {
				 System.out.print(",Dime(10 cents):"+c.getCoinNumber()+",value="+c.getCoinVale());
			 } else if (c instanceof Nickel) {
				 System.out.print(",Nickel (5 cents):"+c.getCoinNumber()+",value="+c.getCoinVale());
			 } else if (c instanceof Penny) {
				 System.out.print(",Penny (1 cents):"+c.getCoinNumber()+",value="+c.getCoinVale());
			 } 
			 System.out.println(" ");
			 sum+=c.getCoinVale();
 		}
		System.out.println("Total Value:"+sum);
		return sum;
	}
	
	 
	
	public void displaySlotStatus() {
		System.out.println("Inventory: ");
		Iterator<Slot> itr = inventoryMap.keySet().iterator();
		while (itr.hasNext()) {
			Slot slot = itr.next();
			String prodType = slot.getDrinks().getProduct(); 
			Integer remaind = inventoryMap.get(slot);
			if (Product.COKE.equalsIgnoreCase(prodType)) {
				System.out.println("Slotno:"+slot.getSlotNo()+","+Product.COKE+" remaind :"+remaind);
			}
			if (Product.PEPSI.equalsIgnoreCase(prodType)) {
				System.out.println("Slotno:"+slot.getSlotNo()+","+Product.PEPSI+" remaind :"+remaind);
			}
			if (Product.SODA.equalsIgnoreCase(prodType)) {
				System.out.println("Slotno:"+slot.getSlotNo()+","+Product.SODA+" remaind :"+remaind);
			}
			
		}
		System.out.println(" ");
	}
	public boolean Confirm() {

		System.out.println("Y - Confirm, N - Cancel : ");

	    String input=null;
	 
	    Scanner scanIn = new Scanner(System.in);
	    input = scanIn.nextLine();
	
	    scanIn.close();            
	    System.out.println(input);
	    boolean retVal=true;
	    if (null!=input && "N".equalsIgnoreCase(input)) {
	    	retVal=false;
	    }
	    return retVal;
	    
	         
	}
	public Integer findAvailNumber(Slot slot) {
		Iterator <Slot> itr = inventoryMap.keySet().iterator();
		while (itr.hasNext()) {
			Slot key = itr.next();
 			if (key.getSlotNo().equalsIgnoreCase(slot.getSlotNo())) {
				return inventoryMap.get(key);
			}
		}
		return -1;
	}
	
	public Slot unpackOneDrink(Slot slot) {
		Iterator <Slot> itr = inventoryMap.keySet().iterator();
		while (itr.hasNext()) {
			Slot key = itr.next();
			Product prod = key.getDrinks();
			Integer avail=inventoryMap.get(key);
			if (key.getSlotNo() == slot.getSlotNo() && avail>0) {
				inventoryMap.put(key,(avail-1));
				return key;
			} 
 		}
		return null;
	}
	
	public Map<Slot, Integer> getInventoryMap() {
		return inventoryMap;
	}



	public void setInventoryMap(Map<Slot, Integer> inventoryMap) {
		this.inventoryMap = inventoryMap;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VendingMachine handler = new VendingMachine();
		
		//handler.displaySlotStatus();
		//handler.exchange(131);
		List<Coin> coinList = new ArrayList<Coin>();
		coinList.add(new Quarter(3));
		coinList.add(new Dime(4));
		coinList.add(new Nickel(2));
		coinList.add(new Penny(2));
		List<Slot> slotList = new ArrayList<Slot>();
	 
		slotList.add(new Slot("NO101", new Coke()));
		slotList.add(new Slot("NO105", new Pepsi()));
		slotList.add(new Slot("NO108", new Soda()));
		Order order = new Order(slotList,coinList);
		
		handler.processOrder(order);
		
		 
	}

}
