package com.interview.questions.sample.call.employee;

public class Call {
	private EmpRank rank;
	private boolean handling=false;
	private boolean disconnected=true;
	private String caller;
	private EmpRank receiver;
	
	
	 
	public Call(EmpRank rank, String caller) {
		super();
		this.rank = rank;
		this.caller = caller;
	}
	public Call(EmpRank rank, boolean handling, boolean disconnected, String caller, EmpRank receiver) {
		super();
		this.rank = rank;
		this.handling = handling;
		this.disconnected = disconnected;
		this.caller = caller;
		this.receiver = receiver;
	}
	public EmpRank getRank() {
		return rank;
	}
	public void setRank(EmpRank rank) {
		this.rank = rank;
	}
	public boolean isHandling() {
		return handling;
	}
	public void setHandling(boolean handling) {
		this.handling = handling;
	}
	public boolean isDisconnected() {
		return disconnected;
	}
	public void setDisconnected(boolean disconnected) {
		this.disconnected = disconnected;
	}
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	public EmpRank getReceiver() {
		return receiver;
	}
	public void setReceiver(EmpRank receiver) {
		this.receiver = receiver;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caller == null) ? 0 : caller.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Call other = (Call) obj;
		if (caller == null) {
			if (other.caller != null)
				return false;
		} else if (!caller.equals(other.caller))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Call [rank=" + rank + ", handling=" + handling + ", disconnected=" + disconnected + ", caller=" + caller
				+ ", receiver=" + receiver + "]";
	}
	
	
}
