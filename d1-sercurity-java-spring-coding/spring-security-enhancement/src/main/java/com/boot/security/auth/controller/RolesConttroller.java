package com.boot.security.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.boot.security.auth.model.PairVO;
import com.boot.security.auth.model.Role;
import com.boot.security.auth.model.RolesForm;

import java.util.ArrayList;
 
import java.util.List;
 

import javax.servlet.http.HttpServletRequest;
 
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.boot.security.auth.model.User;
import com.boot.security.auth.model.UserRole;
import com.boot.security.auth.repository.RoleRepository;
import com.boot.security.auth.repository.UserRepository;
import com.boot.security.auth.repository.service.RepositoryService;

import org.springframework.ui.ModelMap;
@Controller
@RequestMapping(value="/safebox")
public class RolesConttroller {
	private static Logger Log = Logger.getLogger(RolesConttroller.class);

	@Autowired
	RepositoryService  userRepositoryService;
	
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
	
		User user = userRepositoryService.getUserRepository().findByUsername(username);
		 
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
			User user = userRepositoryService.getUserRepository().findByUsername(rolesForm.getUsername());
			List<Role> set = new ArrayList<Role>();
			StringBuffer message = new StringBuffer();
			
			message.append("You assigned user " +user.getUsername()+" with new roles:<br/> ");
			for (String r: rolesForm.getMyRoles()) {
				Role role =userRepositoryService.getRoleRepository().findByName(r);
				set.add(role);
				message.append("("+role.getId()+") "+role.getName()+" <br/>");
			}
			 
			//This is merge change
			userRepositoryService.assignUserNewRoles(rolesForm.getUsername(),set);
			
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
	      for (Role r:userRepositoryService.getRoleRepository().findAll()) {
	    	  roleList.add(new PairVO(r.getName(),"UNCHECKED"));
	      }
	      
	      return roleList;
	}
	public List<PairVO> getUserRoleList(User user) {
	      List<PairVO> roleList = new ArrayList<PairVO>();
	      int count=0;
	      
	      for (Role r:userRepositoryService.getRoleRepository().findAll()) {
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
		
		User u = userRepositoryService.getUserRepository().findByUsername(user.getUsername());
		UserRole userRole = u.getUserRoles().stream().filter(x->x.getUserRolePair().equals(u.getUsername()+"_"+role)).findAny().orElse(null);
		
		return userRole!=null;
	}
 
	@ModelAttribute("userList")
	public List<String> getUserList() {
	      List<String> userList = new ArrayList<String>();
	      
	      for (User u:userRepositoryService.getUserRepository().findAll()) {
	    	  userList.add(u.getUsername());
	      }
	      return userList;
	}
	
}
