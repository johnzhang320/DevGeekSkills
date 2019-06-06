package com.boot.oauth.server.controllers;


import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
	private static final Logger log = Logger.getLogger(UserController.class);
 

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
    	log.info("login begin");
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid or role is incorrect");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        log.info("login end");
        return "login";
    }

    @GetMapping({"/home"})
    public String homw() {
    	log.info("Home begin");
    	
    	//getUserAndRoles(request,auth);
        //      <!--   login Username is <%=request.getSession().getAttribute("username")%> with Roles: <%=request.getSession().getAttribute("roles")%> </h1>
    	  
    	log.info("Home end");
        return "home";
    }
    @GetMapping("/403")
    public String error403(HttpServletRequest request,Authentication auth) {
     	log.info("403 page now!");
      	getUserAndRoles(request,auth);
        return "/error/403";
    }
    public void getUserAndRoles(HttpServletRequest request,Authentication auth) {
    	
    	 request.getSession().setAttribute("username",auth.getName());
         StringBuffer rolesum =new StringBuffer();
         for (GrantedAuthority s: auth.getAuthorities()) {
         	rolesum.append(s.getAuthority()+" ");
         	log.info("Current Role="+s.getAuthority());
         }
         String sum ="";
         if (rolesum.length()>0) {
         	sum=rolesum.toString().trim().replace(" ", ",");
         	log.info("Roles="+sum);
         	request.getSession().setAttribute("roles",sum);
         }
    }
    
}
