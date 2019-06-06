package com.security.app.crypto.service;

import java.io.File;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.logging.log4j.Logger;

import com.mailfrontier.msgcenter.app.log.Logger;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class EmptySSLContextManager {
	public static Logger Log =Logger.getLogger(EmptySSLContextManager.class.getName());
	
	public EmptySSLContextManager() {}
	
	public EmptySSLContextManager(String hostname) {}
	/**
	 * Jersey create Secure Client by create.client(getDefaultClientConfig)
	 * @param sslContext
	 * @return
	 */
	
	public DefaultClientConfig getDefaultClientConfig(SSLContext sslContext) {
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
	public SSLContext getSSLContext() {
		Log.info("Begin");
		
        TrustManager mytm[] = null;
      
        
        try {
        	TrustManager trust = null;
        	 
        	trust = new EmptyX509TrustManager();
          	
            mytm = new TrustManager[]{trust};
            
        } catch (Exception ex) {
            Log.warn("Failed to create Key Store Manager, use null and trust manager only to create SSLContext  ",ex);
            
        }

        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("SSL");            
            ctx.init(null,mytm,new java.security.SecureRandom());
            Log.info("Not Using keyStore Manager to initialize SSLContext !");
            Log.info("Succeed to create SSLContext!");
        } catch (java.security.GeneralSecurityException ex) {
        	Log.error("Failed to create the SSLContext !"+ex);
        }
        Log.info("End");
        return ctx;
    }
}
