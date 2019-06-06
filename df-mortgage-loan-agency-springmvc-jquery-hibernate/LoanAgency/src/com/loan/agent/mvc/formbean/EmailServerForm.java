package com.loan.agent.mvc.formbean;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.email.list.reader.SimpleEmailLineVO;
import com.loan.agent.mvc.utils.ui;
 
public class EmailServerForm implements Serializable {
	 private static final long serialVersionUID = 1L;
	public static final Logger LOG = Logger.getLogger(EmailServerForm.class);		
	 
	private String emailAddress=null; 
	private String password=null;
	private String esConnectionStatus=null;
	private String emailList=null;
	private String actionType=null;
	private String firstNamePtr=null;
	private String emailPtr =null;
	private List<SimpleEmailLineVO> simpleEmailList=null;
	
	
	private String fromEmailAddress;  
	private String toEmailAddress;  
	private String subject=null;	 
	private String emailContent=null;	 
	private String connectionStatus = null;		
	
	private String attachment1=null;
	private String attachment2=null;
	private String attachment3=null;
	private String attachment4=null;
	private String attachment5=null;
	
	private String filename=null;
	private String filesize=null;
	
	private String sendEmailTo=null;
	
	 
	public String getFirstNamePtr() {
		return firstNamePtr;
	}
	public void setFirstNamePtr(String firstNamePtr) {
		this.firstNamePtr = firstNamePtr;
	}
	public String getEmailPtr() {
		return emailPtr;
	}
	public void setEmailPtr(String emailPtr) {
		this.emailPtr = emailPtr;
	}
	public List<SimpleEmailLineVO> getSimpleEmailList() {
		return simpleEmailList;
	}
	public void setSimpleEmailList(List<SimpleEmailLineVO> simpleEmailList) {
		this.simpleEmailList = simpleEmailList;
	}
	public String getSendEmailTo() {
		return sendEmailTo;
	}
	public void setSendEmailTo(String sendEmailTo) {
		this.sendEmailTo = sendEmailTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmailContent() {
		return ui.deCodeHTML(emailContent);
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	public String getConnectionStatus() {
		return connectionStatus;
	}
	public void setConnectionStatus(String connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	public String getFromEmailAddress() {
		return fromEmailAddress;
	}
	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public String getAttachment1() {
		return attachment1;
	}
	public void setAttachment1(String attachment1) {
		this.attachment1 = attachment1;
	}
	public String getAttachment2() {
		return attachment2;
	}
	public void setAttachment2(String attachment2) {
		this.attachment2 = attachment2;
	}
	public String getAttachment3() {
		return attachment3;
	}
	public void setAttachment3(String attachment3) {
		this.attachment3 = attachment3;
	}
	public String getAttachment4() {
		return attachment4;
	}
	public void setAttachment4(String attachment4) {
		this.attachment4 = attachment4;
	}
	public String getAttachment5() {
		return attachment5;
	}
	public void setAttachment5(String attachment5) {
		this.attachment5 = attachment5;
	}
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
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEsConnectionStatus() {
		return esConnectionStatus;
	}
	public void setEsConnectionStatus(String esConnectionStatus) {
		this.esConnectionStatus = esConnectionStatus;
	}
	public String getToEmailAddress() {
		return toEmailAddress;
	}
	public void setToEmailAddress(String toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}
	
	
}
