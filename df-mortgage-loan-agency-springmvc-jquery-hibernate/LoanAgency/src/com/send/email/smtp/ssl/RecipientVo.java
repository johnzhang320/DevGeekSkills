package com.send.email.smtp.ssl;

public class RecipientVo {
	private String recipientFullName;
	private String recipientFirstName;
	private String recipientMiddleName="";
	private String recipientLastName;
	private String recipientEmailAddress;
	
	
	
	public String getRecipientMiddleName() {
		return recipientMiddleName;
	}
	public void setRecipientMiddleName(String recipientMiddleName) {
		this.recipientMiddleName = recipientMiddleName;
	}
	public String getRecipientFullName() {
		return recipientFullName;
	}
	public void setRecipientFullName(String recipientFullName) {
		this.recipientFullName = recipientFullName;
	}
	public String getRecipientFirstName() {
		return recipientFirstName;
	}
	public void setRecipientFirstName(String recipientFirstName) {
		this.recipientFirstName = recipientFirstName;
	}
	public String getRecipientLastName() {
		return recipientLastName;
	}
	public void setRecipientLastName(String recipientLastName) {
		this.recipientLastName = recipientLastName;
	}
	public String getRecipientEmailAddress() {
		return recipientEmailAddress;
	}
	public void setRecipientEmailAddress(String recipientEmailAddress) {
		this.recipientEmailAddress = recipientEmailAddress.replace(" ", "");
	}
	
	 
	
}
