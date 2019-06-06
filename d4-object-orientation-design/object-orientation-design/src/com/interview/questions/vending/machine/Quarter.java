package com.interview.questions.vending.machine;

public class Quarter extends Coin {
	public Quarter(Integer coinNumber) {
		super(coinNumber);
	}
	@Override
	public Integer getCoinVale() {
		// TODO Auto-generated method stub
		return Coin.QUARTER*coinNumber;
	}

}
