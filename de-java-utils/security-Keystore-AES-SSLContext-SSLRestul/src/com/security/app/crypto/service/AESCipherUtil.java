package com.security.app.crypto.service;

import javax.crypto.spec.SecretKeySpec;

public class AESCipherUtil {
	private final static int MULTIPLE = 16;	// Block is 128 bits and 16 bytes		
    public final static String CHAR_SET = "ISO-8859-1";
	public final static String KEY_STRING="456f!@#!@#dgdg5756771bcbvhe!@@#!3442112123";
	public static AESCipherUtil handler =null;
	private static SecretKeySpec skey;
	/**
	 * For Client create instance
	 * @return
	 */
	public static  AESCipherUtil getInstance() {
		if (null==handler) {
			handler = new AESCipherUtil();
		}
		return handler;
	}
	
	 
	
	private AESCipherUtil() {		
		 byte[] key = AESCipherUtil.stardardizeKey(AESCipherUtil.KEY_STRING).getBytes();		 
		 skey = new SecretKeySpec(key, "AES");
	}
	
	
	public static SecretKeySpec getSkey() {
		return skey;
	}
 

	public static String bytesToHex(byte[] bytes) {
	    StringBuffer result = new StringBuffer();
	    for (byte byt : bytes) result.append(Integer.toString((byt &0xff) + 0x100, 16).substring(1));
	    return result.toString();
	 } 

	public static String stardardizeKey(String key) {
		if (null==key || key.length()==0) {
			return null;
		}
		String retVal = key;
		if (key.length()>MULTIPLE) {
			retVal = key.substring(0, MULTIPLE);
					
		} else if(key.length()<MULTIPLE) {
			int mypad= MULTIPLE-key.length();
			retVal = padding(key.toCharArray(), mypad); 
		}
		
		return retVal;
	}
	public static String padding(char[] chars, int paddLen) {
		int len = chars.length+paddLen;
		char[] newc = new char [len];
		for (int i=0; i<len; i++) {
			if (i<chars.length) {
				newc[i] = chars[i];				
			} else {
				newc[i]='p';
			}
		}
		return new String(newc);
	}
}
