package com.boot.oauth.server.controllers;
 

import org.apache.log4j.Logger;

 
import org.springframework.stereotype.Controller;
 
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
	private static final Logger log = Logger.getLogger(UserController.class);
 

   

    @GetMapping({"/home"})
    public String home() {
    	log.info("Home begin");
    	
    	//getUserAndRoles(request,auth);
        //      <!--   login Username is <%=request.getSession().getAttribute("username")%> with Roles: <%=request.getSession().getAttribute("roles")%> </h1>
    	  
    	log.info("Home end");
        return "home";
    }
  
   
    
    @GetMapping({"/homeWebAdvancedApp"})
    public String homeWebAdvancedApp() {
    	log.info("homeWebAdvancedApp begin");
    	
    	//getUserAndRoles(request,auth);
        //      <!--   login Username is <%=request.getSession().getAttribute("username")%> with Roles: <%=request.getSession().getAttribute("roles")%> </h1>
    	  
    	log.info("homeWebAdvancedApp end");
        return "homeWebAdvancedApp";
    }
}
