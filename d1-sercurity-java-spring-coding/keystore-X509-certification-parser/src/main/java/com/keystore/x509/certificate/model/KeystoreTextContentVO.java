package com.keystore.x509.certificate.model;

public class KeystoreTextContentVO {
	private String filename;
	private byte keystoreContent[];
	
	
	
	
	public KeystoreTextContentVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KeystoreTextContentVO(String filename, byte[] keystoreContent) {
		super();
		this.filename = filename;
		this.keystoreContent = keystoreContent;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getKeystoreContent() {
		return keystoreContent;
	}
	public void setKeystoreContent(byte[] keystoreContent) {
		this.keystoreContent = keystoreContent;
	}
	
}
