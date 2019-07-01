package com.frontend.encrypt.utils;
/*
 *   Copyright 2018, 2019 John Zhang (Or Jian Zhang)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
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

}
