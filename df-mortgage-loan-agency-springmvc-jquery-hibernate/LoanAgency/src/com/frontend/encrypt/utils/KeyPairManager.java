package com.frontend.encrypt.utils;

import java.security.KeyPair;

 
import com.frontend.encrypt.utils.JCryptionUtil;
import org.apache.log4j.*;

public class KeyPairManager {
	private static Logger Log = Logger.getLogger(KeyPairManager.class);
	private static KeyPairManager handler=null;
	private KeyPair keyPair=null; 
	private String keyString=null;
	JCryptionUtil jCryptionUtil =null;
	public synchronized static KeyPairManager getInstance() {
		if (null==handler) {
		    handler = new KeyPairManager();
		}
		return handler;
	}
	
	private KeyPairManager() {
		try {
			 jCryptionUtil = new JCryptionUtil();  	       
			 keyPair = jCryptionUtil.generateKeypair();  
			 StringBuffer output = new StringBuffer();  
			 String e = jCryptionUtil.getPublicKeyExponent(keyPair);  
			 String n = jCryptionUtil.getPublicKeyModulus(keyPair);  
			 String md = String.valueOf(jCryptionUtil.getMaxDigits());  		
			 output.append("{\"e\":\"");   // {e,n} is public key , {d,n} is private key, md=(p-1) x (q-1) , n=p x q, p and q must be prime number
			 output.append(e);  
			 output.append("\",\"n\":\"");  
			 output.append(n);  
			 output.append("\",\"maxdigits\":\"");  
			 output.append(md);  
			 output.append("\"}");  
			 output.toString();  
			 keyString = output.toString().replaceAll("\r", "").replaceAll("\n", "").trim();  
		} catch (Exception e) {
			Log.info("Generate Key Failed because of "+e.getMessage());
		}
	}

	public String decrypt(String encrypted) {
		String retVal = null;
		try {
			retVal = jCryptionUtil.decrypt(encrypted, keyPair);
		} catch (Exception e) {
			Log.info("Descryption Failed because of "+e.getMessage());
		}
		return retVal;
	}
	 
	public KeyPair getKeyPair() {
		return keyPair;
	}

	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}

	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	public JCryptionUtil getjCryptionUtil() {
		return jCryptionUtil;
	}

	public void setjCryptionUtil(JCryptionUtil jCryptionUtil) {
		this.jCryptionUtil = jCryptionUtil;
	}
	
}
