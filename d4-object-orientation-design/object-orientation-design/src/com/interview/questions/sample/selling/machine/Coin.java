package com.interview.questions.sample.selling.machine;
/**
 * Items or products supported by Vending Machine.
 *  type-safe Enum to represent coins, which is acceptable by vending machine. 
 */
  
public enum Coin {
	PENNY("Penny",1), NICKLE("Nickle",5), DIME("Dime",10), QUARTER("Quarter",25),DOLLAR("Dollar",100);

	private String coinName;
    private int denomination;
   
    
   
    private Coin(String coinName, int denomination) {
		this.coinName = coinName;
		this.denomination = denomination;
	}



	public String getCoinName() {
		return coinName;
	}



	public int getDenomination(){
        return denomination;
    }
}
