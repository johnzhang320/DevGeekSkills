package com.interview.questions.vending.machine;

public class Dime extends Coin {
	
	public Dime(Integer coinNumber) {
		super(coinNumber);
	}
	
	@Override
	public Integer getCoinVale() {
		// TODO Auto-generated method stub
		return Coin.DIME*coinNumber;
	}

}
