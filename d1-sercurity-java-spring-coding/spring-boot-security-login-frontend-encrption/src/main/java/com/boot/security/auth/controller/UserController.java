package com.boot.security.auth.controller;

import com.boot.security.auth.model.RolesForm;
import com.boot.security.auth.model.User;
import com.boot.security.auth.service.SecurityService;
import com.boot.security.auth.service.SecurityServiceImpl;
import com.boot.security.auth.service.UserService;
import com.boot.security.auth.validator.UserValidator;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.bouncycastle.asn1.x500.X500Name;
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
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,ModelMap model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

       // securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
       
        model.addAttribute("userForm", userForm);
        model.addAttribute("message", "(1) USER");
        return "registrationseccess";
    }

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
