package com.sslcontext.manager;

public class SSLContextBuilder {
	private String trustStorePath;
	private String trustPassword;  
	private String keyStorePath;
	private String keyPassword;
	
	public static class Builder{
		private String trustStorePath=null;
		private String trustPassword=null;  
		private String keyStorePath=null;
		private String keyPassword=null;
		public Builder() {}
		public Builder keyStorePath(String keyStorePath1,String keyPassword1) {
			keyStorePath = keyStorePath1;
			keyPassword = keyPassword1;
			return this;
		}
		public Builder trustStorePath(String trustStorePath1,String trustPassword1) {
			keyStorePath = trustStorePath1;
			trustPassword = trustPassword1;
			return this;
		}
		public SSLContextBuilder build() {
			return new SSLContextBuilder(this);
		}
	}
	public SSLContextBuilder(Builder builder) {
		this.keyStorePath = builder.keyStorePath;
		this.keyPassword = builder.keyPassword;
		this.trustStorePath = builder.trustStorePath;
		this.trustPassword = builder.trustPassword;
	}
	public String getTrustStorePath() {
		return trustStorePath;
	}
 
	public String getTrustPassword() {
		return trustPassword;
	}
 
	public String getKeyStorePath() {
		return keyStorePath;
	}
	 
	public String getKeyPassword() {
		return keyPassword;
	}
 
	
}
