package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {//Serves main index.html

	  	@RequestMapping(method = RequestMethod.GET)
	    public String getIndexPage() {
	        return "index";
	    }
	  
	  	
}
