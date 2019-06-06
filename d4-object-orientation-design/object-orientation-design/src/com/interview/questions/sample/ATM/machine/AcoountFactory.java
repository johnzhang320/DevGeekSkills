package com.interview.questions.sample.ATM.machine;

public class AcoountFactory {
	public static AccountManager getAccount(String bankbranch) {
		if ("BankOfAmericanSanJose".equalsIgnoreCase(bankbranch)) {
			return new BankOfAmericanSanJose();
		} else if ("WellsfargoSunnyvale".equalsIgnoreCase(bankbranch)) {
			return new WellsfargoSunnyvale();
		}
		return null;
	}
}
