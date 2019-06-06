package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

	  @RequestMapping(method = RequestMethod.GET)
	    public String getIndexPage() {
	        return "UserManagement";    // WEB-INF/views/UserManagement.jsp and WEB-INF/views is prefix and '.jsp' is suffix configured in configureViewResolvers() method of HelloWorldConfiguration.java
	    }

}