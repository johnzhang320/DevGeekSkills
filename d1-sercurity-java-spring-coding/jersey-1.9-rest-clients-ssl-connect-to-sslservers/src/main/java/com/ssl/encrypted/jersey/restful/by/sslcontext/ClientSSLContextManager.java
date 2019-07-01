package com.ssl.encrypted.jersey.restful.by.sslcontext;
 
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import org.apache.log4j.Logger; 
import com.sun.jersey.api.client.config.DefaultClientConfig;
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
	
	SSLContext sslContext;
	
	public ClientSSLContextManager(SSLContext sslContext) {
		this.sslContext = sslContext;
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
}
