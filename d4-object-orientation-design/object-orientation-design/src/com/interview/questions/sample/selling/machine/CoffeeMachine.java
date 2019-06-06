package com.interview.questions.sample.selling.machine;

import java.util.Collections;
import java.util.List;

public class CoffeeMachine extends SellingMachineImpl {
 	
	public void initialize() {
	   //initialize machine with 5 coins of each denomination
       //and 5 cans of each Item 
	   for (Coin c: Coin.values()) {
		   cashInventory.put(c,5);
	   }
	   for (Coffee i: Coffee.values()) {
		   itemInventory.put(i, 5);
	   }

	}
	 
}
