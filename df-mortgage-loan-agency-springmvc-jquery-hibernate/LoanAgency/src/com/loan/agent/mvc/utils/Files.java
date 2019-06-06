package com.loan.agent.mvc.utils;

import java.util.List;

public class Files {
    private int id;
    private String filename;
    private Long filesize;
    private String notes;
    private String type;
    private String fileExt; 
    private byte[] file;
    private String assciContent;
    private boolean emailListFile;
    private List<String> wrongEmails;  
    
    
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public Long getFilesize() {
		return filesize;
	}
	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}
	public List<String> getWrongEmails() {
		return wrongEmails;
	}
	public void setWrongEmails(List<String> wrongEmails) {
		this.wrongEmails = wrongEmails;
	}
	public boolean isEmailListFile() {
		return emailListFile;
	}
	public void setEmailListFile(boolean emailListFile) {
		this.emailListFile = emailListFile;
	}
	public String getAssciContent() {
		return assciContent;
	}
	public void setAssciContent(String assciContent) {
		this.assciContent = assciContent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
 
    // please make setter and getter
    
}
