package com.security.app.crypto.service;
import java.io.File;
import java.net.InetAddress;

import javax.net.ssl.HostnameVerifier; 
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.logging.log4j.Logger; 


import com.mailfrontier.msgcenter.app.log.Logger;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.net.ssl.KeyManager;
/**
 * Copyright (c) 2015 Dell,inc. All rights reserved.
 * Created by jzhang
 * Created Date: 3/17/2015
 * Create SSLContext based on trust manager and key store manager
 * Create DefaultClientConfig object based on current SSLContent
 */
public class SSLContextManager {
	public static Logger Log =Logger.getLogger(SSLContextManager.class.getName());
	private String trustStorePath=null;
	private String trustPassword=null;
	private String keyStorePath=null;
	private String keyPassword=null;
	private String keystoreType=null;
	private boolean trustAllhost=false;

	public SSLContextManager() {		
	}
	 
	/**
	 * If both trust store file and key store file , the use this constructor 
	 * to create SSLContext 
	 * @param trustStorePath
	 * @param trustPassword
	 * @param keyStorePath
	 * @param keyPassword
	 */
	public SSLContextManager(String trustStorePath,String trustPassword,String keyStorePath,String keyPassword) {
		this.trustStorePath = trustStorePath;
		this.trustPassword=trustPassword;  
		this.keyStorePath = keyStorePath;
		this.keyPassword = keyPassword;
	}
	/**
	 * if trust store file is not available, use this constructor, create SSLContext based on EmptyX509TrustManager
	 * @param keyStorePath
	 * @param keyPassword
	 */
	public SSLContextManager(String keyStorePath,String keyPassword) {
		this.keyStorePath = keyStorePath;
		this.keyPassword = keyPassword;
	}
	/**
	 * There is no certificate and have to establish the communication without encrypted channel
	 * use this constructor
	 * @param trustAllhost
	 */
	public SSLContextManager(boolean trustAllhost) {
		this.trustAllhost = trustAllhost;
	} 
	public HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                return true;
            }
        };
	}
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
	/**
	 * SSLContext is load trust manager or load keyStore
	 * @return SSLContext
	 */
	public SSLContext getSSLContext() {
		Log.info("Begin");
		
        TrustManager mytm[] = null;
        KeyManager mykm[] = null;
        
        try {
        	TrustManager trust = null;
        	if (null==this.trustStorePath && null ==trustPassword ) {
        		trust = new EmptyX509TrustManager();
        	} else {
        		trust = new MyX509TrustManager(trustStorePath, trustPassword.toCharArray());
        	}
        	
        	
            mytm = new TrustManager[]{trust};
            
            File file = null;
            /**
             *  if ssl host is not local host , keyStorePath must be null
             */
            if (null!=keyStorePath) {
            	file = new File(keyStorePath);
            }
             
        	
            if (null == file || !file.exists()) {
        		Log.warn("Key Store File: does not exist!"+(null==keyStorePath ? " SSL Host is not local host ":""));
        	} else if (!this.trustAllhost) {
        		/**
        		 *  We must use constructor: MyX509KeyManager(File keyStore, char[] password) 
        		 *  to explicitly create key store manager 
        		 */
            	MyX509KeyManager km = new MyX509KeyManager(file, keyPassword.toCharArray(),this.keystoreType);             
            	mykm =km.getKeyManager() ;
            	if (null==mykm) {
            		Log.warn("Failed to create key store manager");
            	} else {
            		Log.info("Succeed to create key store manager!");
            	}
            	
        	}
            
        } catch (Exception ex) {
            Log.warn("Failed to create Key Store Manager, use null and trust manager only to create SSLContext  ",ex);
            
        }

        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("SSL");            
            if (null==this.keyStorePath && null ==this.keyPassword ) {
            	 ctx.init(null,mytm,new java.security.SecureRandom());
            	 Log.info("Not Using keyStore Manager to initialize SSLContext !");
            } else {
            	 ctx.init(mykm, mytm, new java.security.SecureRandom());
            	 Log.info("Using keyStore Manager to initialize SSLContext !");
            }
            Log.info("Succeed to create SSLContext!");
        } catch (java.security.GeneralSecurityException ex) {
        	Log.error("Failed to create the SSLContext !"+ex);
        }
        Log.info("End");
        return ctx;
    }

	public String getTrustStorePath() {
		return trustStorePath;
	}

	public void setTrustStorePath(String trustStorePath) {
		this.trustStorePath = trustStorePath;
	}

	public String getTrustPassword() {
		return trustPassword;
	}

	public void setTrustPassword(String trustPassword) {
		this.trustPassword = trustPassword;
	}

	public String getKeyStorePath() {
		return keyStorePath;
	}

	public void setKeyStorePath(String keyStorePath) {
		this.keyStorePath = keyStorePath;
	}

	public String getKeyPassword() {
		return keyPassword;
	}

	public void setKeyPassword(String keyPassword) {
		this.keyPassword = keyPassword;
	}
	
}
