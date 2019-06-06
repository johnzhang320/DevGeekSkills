package com.interview.questions.vending.machine;

public abstract class Coin implements Comparable<Coin>{
	
	public static Integer PENNY=1;
	public static Integer NICKEL=5;
	public static Integer DIME=10;
	public static Integer QUARTER=25;
	public static Integer DOLLAR=100;
	
	protected Integer coinNumber =0;
	
	
	public Coin(Integer coinNumber) {
		super();
		this.coinNumber = coinNumber;
	}

	public abstract Integer getCoinVale();
	
	@Override
	public int compareTo(Coin obj) {
		int o1 = this.getCoinVale();
		int o2 = obj.getCoinVale();
		if (o1<o2) {
			return -1;
		} else if (o1>o2) {
			return 1;
		}
		return 0;
 	}

	public Integer getCoinNumber() {
		return coinNumber;
	}

	public void setCoinNumber(Integer coinNumber) {
		this.coinNumber = coinNumber;
	}	
	 
 }
