package com.interview.questions.sample.ATM.machine;

public class BankOfAmericanSanJose extends AccountManagerImpl {
	public void initialize() {
		this.openAccount("goergeLin", 100.0, AccountType.CHECKING, "405-44-3234");
		this.openAccount("scottenk", 200.0, AccountType.BROKERAGE, "416-66-5824");
	}
}
