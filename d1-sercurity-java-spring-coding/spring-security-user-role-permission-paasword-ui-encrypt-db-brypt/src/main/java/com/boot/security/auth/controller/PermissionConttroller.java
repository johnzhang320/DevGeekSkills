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
import com.boot.security.auth.model.MatrixForm;
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
import com.boot.security.auth.repository.service.RolePermissionMatrixService;
import com.boot.security.auth.model.MatrixForm;

import org.springframework.ui.ModelMap;
@Controller
@RequestMapping(value="/safebox")
public class PermissionConttroller {
	private static Logger Log = Logger.getLogger(PermissionConttroller.class);

	@Autowired
	RepositoryService  userRepositoryService;
	
	@Autowired
	RolePermissionMatrixService  rolePermissionMatrixService;
	
	@RequestMapping(value="/assign_permissions.html",method=RequestMethod.GET)  
	public ModelAndView getAssignPermission() {
		ModelAndView model = new ModelAndView();
		model.setViewName("permissionmatrix");
		MatrixForm matrixForm = new MatrixForm();
		
		matrixForm.setMatrix(null);
		matrixForm.setMessage("");
		 
	    model.addObject("permMatrix",rolePermissionMatrixService.findPermissionsRolesMatrix());
		model.addObject("matrixForm", matrixForm);
		return model;
	}
 
	
	@RequestMapping(value="/assign_permissions.html",method=RequestMethod.POST)  
	public String submitAssignPermission(
			@ModelAttribute("matrixForm") MatrixForm matrixForm,			
			BindingResult result, SessionStatus status,
			HttpServletRequest request,
			ModelMap model
		)	throws Exception { 
		model.addAttribute("matrixForm", matrixForm);
		if (result.hasErrors()) {
			model.addAttribute("message", "Error Occured!");
			model.addAttribute("permMatrix",rolePermissionMatrixService.findPermissionsRolesMatrix());
			return "permissionmatrix";
		} else {
			rolePermissionMatrixService.diaplayMatrix(matrixForm.getMatrix());
			// update existing role and permission matrix in database
			rolePermissionMatrixService.updateRolePermissionMatrix(matrixForm.getMatrix()); 
			
			StringBuffer message = new StringBuffer();
			
			message.append("You assigned permission to roles:<br/> ");
		
			
			Log.info(message.toString());
			matrixForm.setMessage(message.toString());
			model.addAttribute("permMatrix", rolePermissionMatrixService.findPermissionsRolesMatrix() );    
			model.addAttribute("matrixForm", matrixForm);
		}
		return "permissionmmatrixsuccess";
	}
	
	 
	
}
