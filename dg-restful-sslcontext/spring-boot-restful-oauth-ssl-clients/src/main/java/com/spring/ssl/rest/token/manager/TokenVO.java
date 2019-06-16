package com.spring.ssl.rest.token.manager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenVO {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String expire_in;
	private String scope;
	
	public TokenVO() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public TokenVO(String access_token, String token_type, String refresh_token, String expire_in, String scope) {
		super();
		this.access_token = access_token;
		this.token_type = token_type;
		this.refresh_token = refresh_token;
		this.expire_in = expire_in;
		this.scope = scope;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	 

	public String getExpire_in() {
		return expire_in;
	}

	public void setExpire_in(String expire_in) {
		this.expire_in = expire_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "TokenVO [\naccess_token=\"" + access_token + "\",\n token_type=\"" + token_type + "\",\n refresh_token=\"" + refresh_token
				+ "\",\n expire_in=" + expire_in + ",\n scope=\"" + scope + "\"\n]";
	}
	
	
	
}
