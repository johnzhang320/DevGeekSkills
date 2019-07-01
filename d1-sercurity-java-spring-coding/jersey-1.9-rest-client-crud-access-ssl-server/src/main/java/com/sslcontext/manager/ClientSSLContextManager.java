package com.sslcontext.manager;
 
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
/**
 * 
 * In order to run SSL connection channel when Restful is running on HTTPS,
 * Configure com.sun.jersey.api.client.config.DefaultClientConfig by using javax.net.ssl.SSLContext
 * SSLContext object will be created by customized SSLContextManager
 * 
 * @author jian.zhang320@gmail.com
 *
 */
public class ClientSSLContextManager {
	public static Logger Log =Logger.getLogger(ClientSSLContextManager.class.getName());
	
	private String trustStorePath=null;
	private String trustPassword=null;
	private String keyStorePath=null;
	private String keyPassword=null;
	SSLContext sslContext;
	
	public ClientSSLContextManager(SSLContext sslContext) {
		this.sslContext = sslContext;
	}
	
	public ClientSSLContextManager(String trustStorePath,String trustPassword,String keyStorePath,String keyPassword) {
		this.trustStorePath = trustStorePath;
		this.trustPassword=trustPassword;  
		this.keyStorePath = keyStorePath;
		this.keyPassword = keyPassword;
		
		SSLContextManager handler = new SSLContextManager(trustStorePath,trustPassword,keyStorePath,keyPassword);
		sslContext = handler.getSSLContext();
	}
	 
	
	/**
	 * Jersey create Secure Client by create.client(getDefaultClientConfig)
	 * @param sslContext
	 * @return
	 */
	
	public DefaultClientConfig getDefaultClientConfig() {
		 
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getProperties().put(
				com.sun.jersey.client.urlconnection.HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
				new com.sun.jersey.client.urlconnection.HTTPSProperties(
						getHostnameVerifier(), sslContext));		 
		return defaultClientConfig;
		
	}
	public HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                return true;
            }
        };
	 
	 
    }
	public Client getJerseyClient(String URL) {
		
		 Client client = null;
		 String protocol = URL.substring(0,6).toLowerCase(); 
		 if (protocol.indexOf("https")!=-1) {
				 
			 ClientSSLContextManager ssl = new ClientSSLContextManager(sslContext);
					
			 ClientConfig clientConfig = ssl.getDefaultClientConfig();
				 
			 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				 
			 client = Client.create(clientConfig);
				 
			 Log.info("Succeed to create HTTPS Client!");
		 } else {
				 
			 ClientConfig clientConfig = new DefaultClientConfig();
				 
			 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				 
			 client = Client.create(clientConfig);
				 
			 Log.info("Succeed to create HTTP Client!");
		 }
		 return client;	
	}
}
