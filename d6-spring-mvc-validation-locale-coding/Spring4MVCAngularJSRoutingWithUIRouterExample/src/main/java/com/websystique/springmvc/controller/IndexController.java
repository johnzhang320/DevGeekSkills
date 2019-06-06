package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 
 * Deploy & Run

		Now build the war (either by eclipse as was mentioned in previous tutorials) or via maven
		 command line( mvn clean install). Deploy the war to a Servlet 3.0 container . Since here
		  i am using Tomcat, i will simply put this war file into tomcat webapps folder and click 
		  on startup.bat inside tomcat/bin directory.
		
		Open browser and browse at localhost:8080/Spring4MVCAngularJSRoutingWithUIRouterExample/. 
		it will be redirected to /category [look at $urlRouterProvider.otherwise(“/category”)
		 part in $stateProvider setup]. Check the URL in browser.
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {//Serves main index.html

	  	@RequestMapping(method = RequestMethod.GET)
	    public String getIndexPage() {
	        return "index";
	    }
}
