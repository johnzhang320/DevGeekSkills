package com.oauth.client.model;

public class AuthCodeForm {
	private String response_type;
	private String client_id;
	private String client_secret;
	private String redirect_uri;
	private String resource_uri;
	private String authorize_uri;
	
	private String scope;
	
	
	 

	public AuthCodeForm(String response_type, String client_id, String client_secret, String redirect_uri,
			String resource_uri, String authorize_uri, String scope) {
		super();
		this.response_type = response_type;
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.redirect_uri = redirect_uri;
		this.resource_uri = resource_uri;
		this.authorize_uri = authorize_uri;
		this.scope = scope;
	}

	public String getAuthorize_uri() {
		return authorize_uri;
	}

	public void setAuthorize_uri(String authorize_uri) {
		this.authorize_uri = authorize_uri;
	}

	public AuthCodeForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	public String getResource_uri() {
		return resource_uri;
	}

	public void setResource_uri(String resource_uri) {
		this.resource_uri = resource_uri;
	}

	public String getResponse_type() {
		return response_type;
	}
	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getClient_secret() {
		return client_secret;
	}
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
	public String getRedirect_uri() {
		return redirect_uri;
	}
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
	
}
