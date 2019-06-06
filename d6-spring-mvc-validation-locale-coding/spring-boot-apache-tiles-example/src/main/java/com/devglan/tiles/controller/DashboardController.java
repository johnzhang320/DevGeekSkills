package com.devglan.tiles.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.devglan.tiles.model.User;

@Controller
public class DashboardController {
	static Logger Log = Logger.getLogger(DashboardController.class);
	 @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	    public ModelAndView dashboard() {
	    	ModelAndView model = new ModelAndView();
	    	model.addObject("users", getUsers());
	    	model.setViewName("dashboard");
	    	Log.info("Call Dashboard Controller now!");
	    	 Log.info("Encrypted Password File ="+WorkHome.ENCRYPTED_USER_CREDENTIAL_FILE);
	    	    
    	    File file = new File(WorkHome.ENCRYPTED_USER_CREDENTIAL_FILE);
    	    
    	    if (file.exists()) {
	    	    //String content=Utils.readFileExactly(WorkHome.ENCRYPTED_USER_CREDENTIAL_FILE);
	    	    Log.info("password File exists "+file.getAbsolutePath());
	    	    String content=Utils.readFileExactly(WorkHome.ENCRYPTED_USER_CREDENTIAL_FILE);
	    	    Log.info(content);
     	} else {
     		Log.info("Password File does not exist: "+WorkHome.ENCRYPTED_USER_CREDENTIAL_FILE);
       		
     	}
	    	return model;
	    }

		private List<User> getUsers() {
			User user = new User();
			user.setEmail("johndoe123@gmail.com");
			user.setName("John Doe");
			user.setAddress("Bangalore, Karnataka");
			User user1 = new User();
			user1.setEmail("amitsingh@yahoo.com");
			user1.setName("Amit Singh");
			user1.setAddress("Chennai, Tamilnadu");
			User user2 = new User();
			user2.setEmail("bipulkumar@gmail.com");
			user2.setName("Bipul Kumar");
			user2.setAddress("Bangalore, Karnataka");
			User user3 = new User();
			user3.setEmail("prakashranjan@gmail.com");
			user3.setName("Prakash Ranjan");
			user3.setAddress("Chennai, Tamilnadu");
			return Arrays.asList(user, user1, user2, user3);
		}


}
