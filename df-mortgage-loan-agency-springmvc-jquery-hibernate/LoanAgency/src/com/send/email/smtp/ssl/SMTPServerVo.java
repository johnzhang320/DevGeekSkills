package com.send.email.smtp.ssl;

public class SMTPServerVo {
	private String smtpHostName="smtp.gmail.com";
	private String smtpPort="465";
	private String authEmailAddress;
	private String authEmailPassword;
	private String debugMode="false";
	private String sslFactory="javax.net.ssl.SSLSocketFactory";
	private String fromEmailAddress="support.staff@loans-agent.com";   // default from header email
	
	public String getSslFactory() {
		return sslFactory;
	}
	public void setSslFactory(String sslFactory) {
		this.sslFactory = sslFactory;
	}
	public String getSmtpHostName() {
		return smtpHostName;
	}
	public void setSmtpHostName(String smtpHostName) {
		this.smtpHostName = smtpHostName;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getAuthEmailAddress() {
		return authEmailAddress;
	}
	public void setAuthEmailAddress(String authEmailAddress) {
		this.authEmailAddress = authEmailAddress;
	}
	public String getAuthEmailPassword() {
		return authEmailPassword;
	}
	public void setAuthEmailPassword(String authEmailPassword) {
		this.authEmailPassword = authEmailPassword;
	}
	public String getFromEmailAddress() {
		return fromEmailAddress;
	}
	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}
	public String getDebugMode() {
		return debugMode;
	}
	public void setDebugMode(String debugMode) {
		this.debugMode = debugMode;
	}
	
}
