package com.my.jersey.clients;

import org.apache.log4j.Logger;

import com.my.jersey.model.Constants;
import com.sslcontext.manager.ClientSSLContextManager;
import com.sslcontext.manager.FindResourceFilePath;
import com.sun.jersey.api.client.Client;

public class JerseyClientSingleton {
	static Logger Log = Logger.getLogger(JerseyClientSingleton.class);

	private static JerseyClientSingleton handler=null;
	public static JerseyClientSingleton getInstance() {
		if (handler==null) {
			handler = new JerseyClientSingleton() ;
		}
		return handler;
	}
	private JerseyClientSingleton() {
		
	}
	public Client getJerseyClient(String URL) {
		String trustStorePath = FindResourceFilePath.getInstance().getResourceFile(Constants.trustStoreFilename);
		
		Log.info("trustStorePath="+trustStorePath);
		ClientSSLContextManager handler= new ClientSSLContextManager(trustStorePath,Constants.trustStorePassword,null,null);
		
		
		Log.info("URL = "+URL);
		
		return handler.getJerseyClient(URL);
	}
	
	public Client getJerseyClient() {
		String URL = Constants.LOCAL_HOST_SERVICE;
		return getJerseyClient(URL);
	}
}
