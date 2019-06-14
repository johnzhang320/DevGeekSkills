package com.frontend.encrypt.utils;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
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
	 
   
}
