package com.interview.questions.sample.call.employee;

public class Employee {
	private EmpRank rank;
	private Boolean free;
	 
	 
	public Employee(EmpRank rank, Boolean free) {
		super();
		this.rank = rank;
		this.free = free;
	 
	}
	public EmpRank getRank() {
		return rank;
	}
	public void setRank(EmpRank rank) {
		this.rank = rank;
	}
	public Boolean getFree() {
		return free;
	}
	public void setFree(Boolean free) {
		this.free = free;
	}
	@Override
	public String toString() {
		return "Employee [rank=" + rank + ", free=" + free + "]";
	}
	 
	
}
