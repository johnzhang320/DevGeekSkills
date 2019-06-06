package com.loan.agent.mvc.utils;

import java.sql.Blob;

public class FileDocument {
	private String fileName;
	private String contentType;
	private Blob content;
	public String getFileName() {
		return fileName;
	}
	public FileDocument(){}
	public FileDocument(String fileName, String contentType, Blob content) {
		super();
		this.fileName = fileName;
		this.contentType = contentType;
		this.content = content;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Blob getContent() {
		return content;
	}
	public void setContent(Blob content) {
		this.content = content;
	}
	
}
