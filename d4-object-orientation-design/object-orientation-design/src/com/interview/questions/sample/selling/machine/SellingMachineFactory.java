package com.interview.questions.sample.selling.machine;

public class SellingMachineFactory {
	public static SellingMachine getSellingMachineFactory(String machineType) {
		if ("vendorMachine".equals(machineType)) {
			return new VendorMachine();
		} else if ("coffeeMachine".equals(machineType)) {
			return new CoffeeMachine();
		}
		return null;
	}
}
