package com.security.app.crypto.service;
public class KeyStoreVO {
	private String keyStorePath;
	private String keyStorePass;
	private String keyStoreType;
	private String Protocol;
	private String Cipher;
	private String SSLProtocol;
	private String SSLProtocols;
	private String SSLSchema;
	private String Secure;
	private String SSLEnabled;
	private String SSLPort;
	private String clientAuth;
	private String maxThreads;
	private String allowTrace;
	public String getKeyStorePath() {
		return keyStorePath;
	}
	public void setKeyStorePath(String keyStorePath) {
		this.keyStorePath = keyStorePath;
	}
	public String getKeyStorePass() {
		return keyStorePass;
	}
	public void setKeyStorePass(String keyStorePass) {
		this.keyStorePass = keyStorePass;
	}
	public String getKeyStoreType() {
		return keyStoreType;
	}
	public void setKeyStoreType(String keyStoreType) {
		this.keyStoreType = keyStoreType;
	}
	public String getProtocol() {
		return Protocol;
	}
	public void setProtocol(String protocol) {
		Protocol = protocol;
	}
	public String getCipher() {
		return Cipher;
	}
	public void setCipher(String cipher) {
		Cipher = cipher;
	}
	public String getSSLProtocol() {
		return SSLProtocol;
	}
	public void setSSLProtocol(String sSLProtocol) {
		SSLProtocol = sSLProtocol;
	}
	public String getSSLProtocols() {
		return SSLProtocols;
	}
	public void setSSLProtocols(String sSLProtocols) {
		SSLProtocols = sSLProtocols;
	}
	public String getSSLSchema() {
		return SSLSchema;
	}
	public void setSSLSchema(String sSLSchema) {
		SSLSchema = sSLSchema;
	}
	public String getSecure() {
		return Secure;
	}
	public void setSecure(String secure) {
		Secure = secure;
	}
	public String getSSLEnabled() {
		return SSLEnabled;
	}
	public void setSSLEnabled(String sSLEnabled) {
		SSLEnabled = sSLEnabled;
	}
	public String getSSLPort() {
		return SSLPort;
	}
	public void setSSLPort(String sSLPort) {
		SSLPort = sSLPort;
	}
	public String getClientAuth() {
		return clientAuth;
	}
	public void setClientAuth(String clientAuth) {
		this.clientAuth = clientAuth;
	}
	public String getMaxThreads() {
		return maxThreads;
	}
	public void setMaxThreads(String maxThreads) {
		this.maxThreads = maxThreads;
	}
	public String getAllowTrace() {
		return allowTrace;
	}
	public void setAllowTrace(String allowTrace) {
		this.allowTrace = allowTrace;
	}
	
}
