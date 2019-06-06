package com.security.app.crypto.service;

public class RandomBufferVO {
	 private  String ccTimestamp;  // sent by CC MesaggeAuthVO
	 private  String receivedTimestamp;
	 private  String requestCC;
	 
	 public RandomBufferVO(String ccTimestamp, String receivedTimestamp,
			String requestCC) {
		super();
		this.ccTimestamp = ccTimestamp;
		this.receivedTimestamp = receivedTimestamp;
		this.requestCC = requestCC;
	}
	public RandomBufferVO(String ccTimestamp, String receivedTimestamp) {
		super();
		this.ccTimestamp = ccTimestamp;
		this.receivedTimestamp = receivedTimestamp;
	}
	public String getCcTimestamp() {
	   return ccTimestamp;
	  }
	  public void setCcTimestamp(String ccTimestamp) {
		   this.ccTimestamp = ccTimestamp;
	  }
	  public String getReceivedTimestamp() {
	   return receivedTimestamp;
	  }
	  public void setReceivedTimestamp(String receivedTimestamp) {
		this.receivedTimestamp = receivedTimestamp;
	  }
	public String getRequestCC() {
		return requestCC;
	}
	public void setRequestCC(String requestCC) {
		this.requestCC = requestCC;
	}
	  
	  
	  
}
