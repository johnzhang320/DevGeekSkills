package com.boot.security.auth.controllers;


import java.security.KeyPair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.frontend.encrypt.utils.Constants;
import com.frontend.encrypt.utils.KeyPairManager;

@RestController
@RequestMapping("/")
public class KeypairRestController {
	static Logger Log = Logger.getLogger(KeypairRestController.class);
	
	@RequestMapping(value="/getKeyPair.html", method=RequestMethod.POST)
	public void getKeypair(HttpServletRequest request,HttpServletResponse response) {
		String keyString ="";
		try {
			Log.info("doPost() Begin:");
			KeyPair keys = KeyPairManager.getInstance().getKeyPair();
		    request.getSession().setAttribute(Constants.KEY_PAIR, keys);  		    
		    keyString =  KeyPairManager.getInstance().getKeyString();		   
		    response.getOutputStream().print(keyString);  
		    
		} catch (Exception e) {
				Log.info("Generate Key Failed because of "+e.getMessage());
				keyString="Generate Key Failed because of "
				+e.getMessage();
		}
		 Log.info("doPost() End:");
		 return ;
	}
	
	@RequestMapping(value="/getKeyPair.html", method=RequestMethod.GET)
	public void getKeypairGet(HttpServletRequest request,HttpServletResponse response) {
		String keyString ="";
		try {
			Log.info("doGet() Begin:");
			KeyPair keys = KeyPairManager.getInstance().getKeyPair();
		    request.getSession().setAttribute(Constants.KEY_PAIR, keys);  		    
		    keyString =  KeyPairManager.getInstance().getKeyString();		   
		    response.getOutputStream().print(keyString);  
		    
		} catch (Exception e) {
				Log.info("Generate Key Failed because of "+e.getMessage());
				keyString="Generate Key Failed because of "
				+e.getMessage();
		}
		 Log.info("doGet() End:");
		 return ;
	}
}
