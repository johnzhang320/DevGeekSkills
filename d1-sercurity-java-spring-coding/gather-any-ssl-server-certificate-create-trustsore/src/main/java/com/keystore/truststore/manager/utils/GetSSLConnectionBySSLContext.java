package com.keystore.truststore.manager.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;

import com.sslcontext.manager.SSLContextManager;

public class GetSSLConnectionBySSLContext {
	static Logger Log = Logger.getLogger(GetSSLConnectionBySSLContext.class);
	private String https_url;
	private String clientKeystorePathFile;    // defaul null but Mutual Authenticate need it: if remote server need authenticate client by client certificate, please please
	private String clientKeystorePassword;    // defaul null but Mutual Authenticate need it client keystore (jks) storage password
	private String trustStorePathFile;         // remote server keystore (jks) which must contain the server certificate chain, we already gather in test case
	private String trustStorePassword;
	
	public GetSSLConnectionBySSLContext() {}
	 
	public GetSSLConnectionBySSLContext(
			String https_url, 
			String clientKeystorePathFile, 
			String clientKeystorePassword,
			String trustStorePathFile, 
			String trustStorePassword
			) {
		super();
		this.https_url = https_url;
		this.clientKeystorePathFile = clientKeystorePathFile;
		this.clientKeystorePassword = clientKeystorePassword;
		this.trustStorePathFile = trustStorePathFile;
		this.trustStorePassword = trustStorePassword;
	}
	public HttpsURLConnection getSSLConnection() {
		return  getSSLConnection(
				https_url, 
				"GET",
				"application/json",
				clientKeystorePathFile, 
				clientKeystorePassword,
				trustStorePathFile, 
				trustStorePassword);
	}
	public HttpsURLConnection getSSLConnection(String requestMethod) {
		return  getSSLConnection(
				https_url, 
				requestMethod,
				"application/json",
				clientKeystorePathFile, 
				clientKeystorePassword,
				trustStorePathFile, 
				trustStorePassword);
	}
	public HttpsURLConnection getSSLConnection(
			String https_url,
			String requestMethod,
			String Accept,
			String clientKeystorePathFile,    // defaul null but Mutual Authenticate need it: if remote server need authenticate client by client certificate, please please
			String clientKeystorePassword,    // defaul null but Mutual Authenticate need it client keystore (jks) storage password
			String trustStorePathFile,         // remote server keystore (jks) which must contain the server certificate chain, we already gather in test case
			String trustStorePassword
			) {
		   /**
		    *  truststore.jks should contain the certificate chain of the server be trusted, if unconditionally trust a server
		    *  put null for JKS_TRUSTSTORE_PATH, KEY_PASSWORD , this is bad practice for production but for code testing only
		    */
		   SSLContextManager ssl = new SSLContextManager(
				   clientKeystorePathFile, 
				   clientKeystorePassword,
				   trustStorePathFile,
				   trustStorePassword 
			);
		   SSLContext ssl_ctx = ssl.getSSLContext();
		   URL url;
		   HttpsURLConnection conn=null;
		   try 
		   {
		   HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
	
	 	    url = new URL(https_url);
	 	    conn = (HttpsURLConnection)url.openConnection();
	
	 	    // Guard against "bad hostname" errors during handshake.
	         conn.setHostnameVerifier(new HostnameVerifier() {
	             public boolean verify(String host, SSLSession sess) {
	            	 return true;
	               //  if (host.equals("localhost")) return true;
	               //  else return false;
	             }
	         });
	         
	         
	         conn.setRequestMethod(requestMethod);
	         
	         conn.setRequestProperty("Content-Type", "application/json; charset=\"utf-8\"");
	         conn.setRequestProperty("Accept", Accept);
	         conn.setDoOutput(true);
	 		 conn.setDoInput(true);
	         	 	   
	
	 	 } catch (MalformedURLException e) {
	 		Log.error("MalformedURLException "+e.getMessage());
	 		 
	 		return null;
	 	 } catch (IOException e) {
	 		Log.error("IOException "+e.getMessage() );
	 		return null;
	 	 } 
		        
		 if (conn!=null) {
		   Log.info("Succeed to establish ssl connection with "+https_url); 
		 
		 }
		 return conn;
	}
}
