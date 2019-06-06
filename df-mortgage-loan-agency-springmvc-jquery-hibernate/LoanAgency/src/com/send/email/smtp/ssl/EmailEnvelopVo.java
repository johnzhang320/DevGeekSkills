package com.send.email.smtp.ssl;

import java.util.List;

public class EmailEnvelopVo {
	String FromEmailAddress;
	String mailSubject;
	String greetingWord="Dear ";
	String contentType="text/html";    	
	String emailContent;
	List<RecipientVo> recipients; 
	String senderName;
	String senderCorp;
	String senderPhone;
	String senderAddress;	
	List<String> attachmentPathList;
	
	public List<RecipientVo> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<RecipientVo> recipients) {
		this.recipients = recipients;
	}
	public String getFromEmailAddress() {
		return FromEmailAddress;
	}
	public void setFromEmailAddress(String fromEmailAddress) {
		FromEmailAddress = fromEmailAddress;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getGreetingWord() {
		return greetingWord;
	}
	public void setGreetingWord(String greetingWord) {
		this.greetingWord = greetingWord;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getEmailContent() {
		
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderCorp() {
		return senderCorp;
	}
	public void setSenderCorp(String senderCorp) {
		this.senderCorp = senderCorp;
	}
	public String getSenderPhone() {
		return senderPhone;
	}
	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public List<String> getAttachmentPathList() {
		return attachmentPathList;
	}
	public void setAttachmentPathList(List<String> attachmentPathList) {
		this.attachmentPathList = attachmentPathList;
	}
	
	
}
