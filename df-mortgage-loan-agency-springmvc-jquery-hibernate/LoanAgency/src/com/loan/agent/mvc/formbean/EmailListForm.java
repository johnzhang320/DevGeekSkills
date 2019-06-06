package com.loan.agent.mvc.formbean;

import java.io.Serializable;
import java.sql.Blob;

import org.apache.log4j.Logger;
 
public class EmailListForm implements Serializable {
	 private static final long serialVersionUID = 1L;
	public static final Logger LOG = Logger.getLogger(EmailListForm.class);		
	
	private String actionType=null;
	
	private String emailList=null;
	 
	private Blob fileContent;

	
	
	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getEmailList() {
		return emailList;
	}

	public void setEmailList(String emailList) {
		this.emailList = emailList;
	}

	public Blob getFileContent() {
		return fileContent;
	}

	public void setFileContent(Blob fileContent) {
		this.fileContent = fileContent;
	}

	 
	
	
	
	
}
