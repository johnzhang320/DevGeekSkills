package com.interview.questions.sample.call.employee;

public enum EmpRank {
	FRESHER("Fresher",0), TL("Team Leader", 1),PM("Project Manager",2);
	String name;
	int rank;
	
	
	private EmpRank(String name, int rank) {
		this.name = name;
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}
	
}
