package com.apple.cert.rest.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class BulkCertRenewForm {
   
    @NotEmpty
  	private String ipAddress;
    @NotEmpty
 	private String authToken;
    @NotEmpty
	private String storePass;
    @NotEmpty
 	private String alias;
 	private String cert_result;
  
	public BulkCertRenewForm() {
		super();
	}
 
	public BulkCertRenewForm( String cert_result, String ipAddress, String authToken,
			String storePass, String alias) {
		super();
	
		this.cert_result = cert_result;
		this.ipAddress = ipAddress;
		this.authToken = authToken;
		this.storePass = storePass;
		this.alias = alias;
	}

	public String getAuthToken() {
		return authToken;
	}



	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

 
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
 
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getStorePass() {
		return storePass;
	}
	public void setStorePass(String storePass) {
		this.storePass = storePass;
	}
	 
 
	public String getCert_result() {
		return cert_result;
	}
 
	public void setCert_result(String cert_result) {
		this.cert_result = cert_result;
	}

	@Override
	public String toString() {
		return "BulkCertRenewForm [ipAddress=" + ipAddress + ", authToken=" + authToken + ", storePass=" + storePass
				+ ", alias=" + alias + ", cert_result=" + cert_result + "]";
	}

	 
	
	
}
