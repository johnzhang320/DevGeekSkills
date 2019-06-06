package com.interview.questions.sample.parkinglots;

public class Vechicle {
	private String dmvNo;
	private VechicleLevel vechicleLevel;
 	
	public Vechicle(VechicleLevel vechicleLevel) {
		super();
		this.dmvNo = "Vech"+String.valueOf(IdGenerator.getRandomNumber());
		this.vechicleLevel = vechicleLevel;
	}
	public String getDmvNo() {
		return dmvNo;
	}
	public void setDmvNo(String dmvNo) {
		this.dmvNo = dmvNo;
	}
	public VechicleLevel getVechicleLevel() {
		return vechicleLevel;
	}
	public void setVechicleLevel(VechicleLevel vechicleLevel) {
		this.vechicleLevel = vechicleLevel;
	}
	
}
