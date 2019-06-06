package com.interview.questions.sample.ATM.machine;

public enum AccountType {
	CHECKING("Check"), SAVING("Saving"),BROKERAGE("Brokerage");
	private String name;

	private AccountType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
