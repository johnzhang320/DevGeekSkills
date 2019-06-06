package com.boot.security.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.boot.security.auth.model.PairVO;
import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.RolesForm;
import com.boot.security.auth.repository.RoleRepository;
import com.boot.security.auth.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.boot.security.auth.model.User; 
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping(value="/safebox")
public class RolesConttroller {
	private static Logger Log = Logger.getLogger(RolesConttroller.class);
	@Autowired
	RoleRepository  roleRepository;
	@Autowired
	UserRepository  userRepository;
	
	@RequestMapping(value="/assign_roles.html",method=RequestMethod.GET)  
	public ModelAndView getAssignRoles() {
		ModelAndView model = new ModelAndView();
		model.setViewName("assignroles");
		RolesForm rolesForm = new RolesForm();
		
		rolesForm.setUsername("");
		rolesForm.setMessage("");
		 
	    model.addObject("roleList",getInitRoleList());
		model.addObject("rolesForm", rolesForm);
		return model;
	}
	
	@RequestMapping(value="/find_roles.html",method=RequestMethod.POST)  
	public String findRoles(
			@ModelAttribute("rolesForm") RolesForm rolesForm,
			ModelMap model,
			BindingResult result, SessionStatus status
			) {
		Log.info("findRoles begin");
		String username = rolesForm.getUsername();
		if (username == null || username.trim().length()==0) {
			model.addAttribute("message", "Username is required !");
			Log.info("findRoles error");
			model.addAttribute("roleList",getInitRoleList());
			return "assignroles";
		}
	
		User user = userRepository.findByUsername(username);
		 
		if (user==null || result.hasErrors()) {
			model.addAttribute("message", "User not found !");
			Log.info("findRoles error");
			model.addAttribute("roleList",getInitRoleList());
			return "assignroles";
		}
		model.addAttribute("rolesForm", rolesForm);
 		model.addAttribute("roleList", getUserRoleList(user) );     
 		Log.info("findRoles end");
		return "assignroles";
	}
	@RequestMapping(value="/assign_roles.html",method=RequestMethod.POST)  
	public String submitAssignRoles(
			@ModelAttribute("rolesForm") RolesForm rolesForm,			
			BindingResult result, SessionStatus status,
			HttpServletRequest request,
			ModelMap model
		)	throws Exception { 
		model.addAttribute("rolesForm", rolesForm);
		if (result.hasErrors()) {
			model.addAttribute("message", "Error Occured!");
			model.addAttribute("roleList",getInitRoleList());
			return "assignroles";
		} else {
			String username = rolesForm.getUsername();
			if (username == null || username.trim().length()==0) {
				model.addAttribute("message", "Username is required !");
				Log.info("findRoles error");
				model.addAttribute("roleList",getInitRoleList());
				return "assignroles";
			}
			// update existing user 	
			User user = userRepository.findByUsername(rolesForm.getUsername());
			List<Role> set = new ArrayList<Role>();
			StringBuffer message = new StringBuffer();
			
			message.append("You assigned user " +user.getUsername()+" with new roles:<br/> ");
			for (String r: rolesForm.getMyRoles()) {
				Role role =roleRepository.findByName(r);
				set.add(role);
				message.append("("+role.getId()+") "+role.getName()+" <br/>");
			}
			user.setRoles(set);
			userRepository.save(user);
			Log.info(message.toString());
			rolesForm.setMessage(message.toString());
			model.addAttribute("roleList", getUserRoleList(user) );    
			model.addAttribute("rolesForm", rolesForm);
		}
		return "assignsuccess";
	}
	
	//@ModelAttribute("roleList")
	public List<PairVO> getInitRoleList() {
	      List<PairVO> roleList = new ArrayList<PairVO>();
	      for (Role r:roleRepository.findAll()) {
	    	  roleList.add(new PairVO(r.getName(),"UNCHECKED"));
	      }
	      
	      return roleList;
	}
	public List<PairVO> getUserRoleList(User user) {
	      List<PairVO> roleList = new ArrayList<PairVO>();
	      int count=0;
	      
	      for (Role r:roleRepository.findAll()) {
		  		if (hasRole(r.getName(), user)) {
		  			roleList.add(new PairVO(r.getName(),"CHECKED"));
		  		} else {
		  			roleList.add(new PairVO(r.getName(),"UNCHECKED"));
		  		}
		  	   Log.debug(roleList.get(count++).toString());	
	  	  }
	      return roleList;
	}
	public boolean hasRole(String role, User user) {
		for (Role rr:user.getRoles()) {
	    	  Log.debug("user="+user.getUsername()+", role has "+rr.getName());
	    	  if (rr.getName().equalsIgnoreCase(role)) {
	    		  Log.info("Matched "+rr.getName());
	    		  return true;
	    	  }
	      }
		return false;
	}
 
	@ModelAttribute("userList")
	public List<String> getUserList() {
	      List<String> userList = new ArrayList<String>();
	      
	      for (User u:userRepository.findAll()) {
	    	  userList.add(u.getUsername());
	      }
	      return userList;
	}
	
}
