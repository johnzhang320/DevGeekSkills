package com.frontend.encrypt.utils;

import java.security.MessageDigest;

public class ui {
	   public static String convertToSHA256(String password) {
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
			 // LOG.error("Message Digest (SH256) Failed");
			  
		  }
		   return null;
	   }
	   
}
