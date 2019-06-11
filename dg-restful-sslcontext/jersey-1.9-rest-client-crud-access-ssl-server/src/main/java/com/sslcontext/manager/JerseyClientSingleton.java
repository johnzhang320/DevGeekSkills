package com.sslcontext.manager;

import org.apache.log4j.Logger;

import com.my.jersey.model.Constants;
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
	/**
	 * * if you run you code only in src/main/java folder please place  
	 * Truststore and keystore into src/main/resource folder, pass file name only without path
	 * 
	 * if you run you code only in src/test/java folder please place  
	 * Truststore and keystore into src/test/resource folder, pass file name only without path
	 * 
	 * uni-direction authentication, pass ssl server truststore only
	 * @param URL
	 * @param truststoreName
	 * @param trustorePassword
	 * @return
	 */
	public Client getJerseyClient(String URL,
			String truststoreName,
			String trustorePassword) {
		String trustStorePath = FindResourceFilePath.getInstance().getResourceFile(truststoreName);
		
		Log.info("trustStorePath="+trustStorePath);
		ClientSSLContextManager handler= new ClientSSLContextManager(trustStorePath,trustorePassword,null,null);
		
		
		Log.info("URL = "+URL);
		
		return handler.getJerseyClient(URL);
	}
	/**
	 * 
	 * if you run you code only in src/main/java folder please place  
	 * Truststore and keystore into src/main/resource folder, pass file name only without path
	 * 
	 * if you run you code only in src/test/java folder please place  
	 * Truststore and keystore into src/test/resource folder, pass file name only without path
	 * 
	 * uni-direction authentication, pass ssl server truststore only
	 * 
	 * bi-direction mutual authenticate , pass local keystore and ssl server trustore
	 * 
	 * From my another project to gather truststore of ssl server
	 * 
	 * @param URL                   
	 * @param truststoreName             
	 * @param trustorePassword
	 * @param keystoreName
	 * @param KeystorePassword
	 * @return Jersey Client object
	 */
	public Client getJerseyClientMutureAuthenticate(
			String URL,
			String truststoreName,
			String trustorePassword,
			String keystoreName,
			String KeystorePassword
			
			) {
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
