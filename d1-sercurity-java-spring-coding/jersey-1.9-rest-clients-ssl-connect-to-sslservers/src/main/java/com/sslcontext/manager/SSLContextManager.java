package com.sslcontext.manager;
import java.io.File;
import java.net.InetAddress;

import javax.net.ssl.HostnameVerifier; 
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.log4j.Logger;

import com.keystore.truststore.manager.EmptyX509TrustManager;
import com.keystore.truststore.manager.MyX509KeyManager;
import com.keystore.truststore.manager.MyX509TrustManager;

import javax.net.ssl.KeyManager;
/**
 *  Modified by john.zhang320@gmail.com
 *  Modified Date: 06/06/2019
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
	private String sslHostName=null;
	private static String localHost = null; 
 
	public SSLContextManager() {		
	}
	
	/**
	 * If both trust store file and key store file , the use this constructor 
	 *  For mutual authenticate, below keystore contains 
	 * client cert chain and truststore is server cert chain

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
	 * for authenticate client keystore is client cert and truststore is EmptyX509TrustManager means unconditional trust server:
	 * if trust store file is not available, use this constructor, create SSLContext based on EmptyX509TrustManager
	 * 
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
        		Log.debug("truststore file="+trustStorePath);
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
	        		Log.debug("keystore file="+file.getAbsolutePath());
	            	MyX509KeyManager km = new MyX509KeyManager(file, keyPassword.toCharArray(),this.keystoreType);             
	            	mykm =km.getKeyManager() ;
	            	if (null==mykm) {
	            		Log.warn("Failed to create key store manager");
	            	} else {
	            		Log.debug("Succeed to create key store manager!");
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
            	 Log.debug("Using keyStore Manager to initialize SSLContext !");
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
