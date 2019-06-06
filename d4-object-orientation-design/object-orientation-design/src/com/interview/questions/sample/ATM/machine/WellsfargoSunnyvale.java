package com.interview.questions.sample.ATM.machine;

public class WellsfargoSunnyvale extends AccountManagerImpl {
	public void initialize() {
		this.openAccount("johnzhang320", 100.0, AccountType.CHECKING, "405-23-1234");
		this.openAccount("denniecha", 100.0, AccountType.SAVING, "406-13-5324");
	}
}
