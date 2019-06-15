 package com.sslcontext.manager;
 
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.spring.rest.ssl.model.Constants;

 
 /* 
 *
 * @author jian.zhang320@gmail.com
 *
 */
public class HttpsClientManager {
	public static Logger Log =Logger.getLogger(HttpsClientManager.class.getName());
	
	private String trustStorePath=null;
	private String trustPassword=null;
	private String keyStorePath=null;
	private String keyPassword=null;
	private SSLContext sslContext;
 	private SSLContextManager sslContextManager;
	
	public HttpsClientManager(SSLContext sslContext) {
		this.sslContext = sslContext;
	}
	
	public HttpsClientManager(String trustStorePath,String trustPassword,String keyStorePath,String keyPassword) {
		this.trustStorePath = trustStorePath;
		this.trustPassword=trustPassword;  
		this.keyStorePath = keyStorePath;
		this.keyPassword = keyPassword;
		if (trustStorePath!=null) {
			this.trustStorePath = FindResourceFilePath.getInstance().getResourceFile(trustStorePath);
		}
		if (keyStorePath!=null) {
			this.keyStorePath = FindResourceFilePath.getInstance().getResourceFile(keyStorePath);
		}
		sslContextManager = new SSLContextManager(
				this.trustStorePath,
				this.trustPassword,
				this.keyStorePath,
				this.keyPassword);
		sslContext = sslContextManager.getSSLContext();
	}
	public HttpClient getHttpsClientConfig() {
		 
		 HttpClient client = HttpClients.custom()
				    .setHostnameVerifier(sslContextManager.getX509HostnameVerifier())
	                .setSSLContext(sslContext)
	                .build();
		 return client;
		
	}
 
}
