package com.loan.agent.mvc.formbean;

 

import org.apache.log4j.Logger;
 
import java.io.Serializable;
import java.sql.Blob;
 
public class EmailForm implements Serializable {
	 private static final long serialVersionUID = 1L;
	public static final Logger LOG = Logger.getLogger(EmailForm.class);		 
 
	private String emailAddress=null;
	
	private String subject=null;
	 
	private String emailContent=null;

	private String actionType=null;
	
	private String emailList = null;
	
	private String connectionStatus = null;	
	 

	public String getEmailList() {
		return emailList;
	}

	public void setEmailList(String emailList) {
		this.emailList = emailList;
	}

	public String getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(String connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	} 
	
	  
	
	
}
