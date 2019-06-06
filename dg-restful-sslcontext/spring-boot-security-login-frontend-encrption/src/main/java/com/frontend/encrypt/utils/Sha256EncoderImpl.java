package com.frontend.encrypt.utils;

import java.security.MessageDigest;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;


public class Sha256EncoderImpl implements Sha256Encoder {
	private static Logger Log = Logger.getLogger(Sha256EncoderImpl.class);
	
	@Override
	public String hashEncoder(String password) {
	   StringBuffer hexString =null;
	   try { 
		   MessageDigest md = MessageDigest.getInstance("SHA-256");
		   md.update(password.getBytes());

		   byte byteData[] = md.digest();

		   //convert the byte to hex format method 1
		   StringBuffer sb = new StringBuffer();
		   for (int i = 0; i < byteData.length; i++) {
			   sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		   }
		   //convert the byte to hex format method 2
		    hexString = new StringBuffer();
   			for (int i=0;i<byteData.length;i++) {
   			String hex=Integer.toHexString(0xff & byteData[i]);
  	     	if(hex.length()==1) hexString.append('0');
  	     	hexString.append(hex);
  	     	
   			}
   			return hexString.toString();
	  } catch (Exception e) {
		  Log.error("Message Digest (SH256) Failed");
		  throw new
          BadCredentialsException("Found wrong password or failed to find roles!");
	  }
	   
   }
	/*public static void main(String args[]) {
		
		String p[] = {"password","new=778899","helloworld"};
		for (String pw : p) {
			Log.info("password="+pw+", hashcode="+hashEncoder(pw) );
		}
		
	}*/
}
