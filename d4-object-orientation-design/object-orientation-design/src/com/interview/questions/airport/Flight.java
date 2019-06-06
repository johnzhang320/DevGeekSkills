package com.interview.questions.airport;

public enum Flight {
	CA101("CA101",Airplane.A380),UA130("UA130",Airplane.BOEING757),UN650("UN650",Airplane.BOEING747),UD402("UD402",Airplane.A380),
	TC1900("TC1900",Airplane.BOEING757),UP122("UP122",Airplane.A380), CQ200("CQ200",Airplane.BOEING757);
	
	
 
	private Flight(String filghtNo, Airplane plane) {
		this.filghtNo = filghtNo;
		this.plane = plane;
	}
	private String filghtNo;
	private Airplane plane;
	public String getFilghtNo() {
		return filghtNo;
	}
	public void setFilghtNo(String filghtNo) {
		this.filghtNo = filghtNo;
	}
	public Airplane getPlane() {
		return plane;
	}
	public void setPlane(Airplane plane) {
		this.plane = plane;
	}
	
	
}
