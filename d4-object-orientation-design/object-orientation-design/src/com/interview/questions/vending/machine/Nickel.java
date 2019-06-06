package com.interview.questions.vending.machine;

public class Nickel extends Coin {

	public Nickel(Integer coinNumber) {
		super(coinNumber);
	}
	@Override
	public Integer getCoinVale() {
		// TODO Auto-generated method stub
		return Coin.NICKEL * coinNumber;
	}

}
