package com.interview.questions.vending.machine;

public class Penny extends Coin {
	
	public Penny(Integer coinNumber) {
		super(coinNumber);
	}
	
	@Override
	public Integer getCoinVale() {
		return Coin.PENNY*coinNumber;
	}
}
