package com.loan.agent.mvc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;

 


import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.AppCheckList;
import com.loan.agent.dao.AppCheckListDAO;
import com.loan.agent.mvc.formbean.AppCheckListForm;
import com.loan.agent.mvc.formbean.AppCheckListForm;
 
 
import com.loan.agent.mvc.utils.Files;
import com.loan.agent.mvc.utils.ProcessDownloadFile;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
 
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.send.email.smtp.ssl.RecipientVo;
import com.send.email.smtp.ssl.UpdateEnvelop;
 

 
public class AppCheckListFormController extends SimpleFormController {
Logger LOG = Logger.getLogger( AppCheckListFormController.class);
@Autowired
@Resource(name="QuoteDBService")
private QuoteDBService quoteDBService;

	public AppCheckListFormController() {
		setCommandName("appCheckListForm");
		setCommandClass(AppCheckListForm.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		LOG.info("appCheckListsController formBackingObject begin!");
		
		ui.setRequest(request);
		
		String actionType=ServletRequestUtils.getStringParameter(request,Constant.ACTION_TYPE);
		
		String checkIdStr = ServletRequestUtils.getStringParameter(request,Constant.PARAM_CHECK_ID);
		
		ui.setSessionAttributeObject(Constant.ACTION_TYPE,actionType);
		
 
		/** 
		 *  Get initialized Form
		 */
		request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,null);
		/**
		 *  AppCheckListListView.jsp define the ADD, EDIT, Delete instead of AppCheckListForm itself.
		 */
	 
		 
		AppCheckListForm appCheckListForm = new AppCheckListForm();	
		
	 
		
		if (Constant.ACTION_CHECK_LIST_EDIT.equalsIgnoreCase(actionType) || Constant.ACTION_CHECK_LIST_DELETE.equalsIgnoreCase(actionType)) {						 
			Integer checkId=null;			
			if (checkIdStr!=null) {	 // save or delete in AppCheckListForm, there is no checkIdStr
				checkId  = new Integer(checkIdStr);
				AppCheckListDAO appCheckListDAO = quoteDBService.getAppCheckListDAO();					
				AppCheckList appCheckList = appCheckListDAO.findById(checkId);	
				appCheckListForm.render(appCheckList);			
  		        //LOG.info("actionType="+actionType+",checkIdStr="+checkIdStr+",step 2"+",appCheckListForm.appCheckListTitle="+appCheckListForm.getTitle()) ;
			}
			
		}
		
		appCheckListForm.setActionType(actionType);
			   
		LOG.info("AppCheckListController formBackingObject end!");
		return appCheckListForm;
		
	}  
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		
		LOG.info("AppCheckListController onSubmit() begin!");
		
		 
		
		ui.setRequest(request);
		
		AppCheckListForm form =  (AppCheckListForm) command;		
	 
		
		ModelAndView mode = new ModelAndView(Constant.FORWARD_CHECK_LIST_FORM);	 		
	 
		
		request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,null);		
		
	    String actionType = form.getActionType();
		
	 	
		LOG.info("AppCheckListController onSubmit() actionType="+actionType);
	
		
		AppCheckListDAO appCheckListDAO = quoteDBService.getAppCheckListDAO();	
		
		AppCheckList appCheckList = form.model();	
		
		if (Constant.ACTION_CHECK_LIST_ADD.equalsIgnoreCase(actionType)) {        	
			 
			LOG.info("AppCheckList Add begin");						
			 
			appCheckListDAO.save(appCheckList);		
			
			request.setAttribute(Constant.SUCCESS_MESSAGE, "Successfully add a new Check List Item:"+appCheckList.getTitle());
			
			LOG.info("AppCheckList Add end");		 
			
	   }
	   if (Constant.ACTION_CHECK_LIST_EDIT.equalsIgnoreCase(actionType)) {        	
			 
			LOG.info("AppCheckList Edit begin");
			
			String checkIdstr = form.getCheckId();
			
			Integer checkId = new Integer(checkIdstr); 
			 LOG.info("checkIdStr="+checkIdstr+",checkId="+checkId); 
			if (null!=checkIdstr) {
 			      AppCheckList appCheckListObj =appCheckListDAO.findById(checkId);
 			      if (null!=appCheckListObj) {
 			    	 LOG.info("AppCheckList Edit Merge a record on checkId="+checkIdstr); 
 			    	 /**
 			    	  *  We must set AppCheckListId, only when Edit and object existed , AppCheckListId exists!
 			    	  */
 			    	 appCheckList.setCheckId(checkId);
 			    	 
 				     appCheckListDAO.merge(appCheckList);
 				     
 			      } else {
 			    	 LOG.info("AppCheckList Edit Add a record on on checkId="+checkIdstr);  
 			    	 appCheckListDAO.save(appCheckList);
 			      }
 			 	request.setAttribute(Constant.SUCCESS_MESSAGE, "Successfully save the Check List Item:"+appCheckList.getTitle());
 			     
			}
			
			
			LOG.info("AppCheckList Edit end");		 
			
	   }
	   if (Constant.ACTION_CHECK_LIST_DELETE.equalsIgnoreCase(actionType)) {        	
			 
			LOG.info("AppCheckList Delete begin");
			
			String checkIdstr = form.getCheckId();
		 
			if (null!=checkIdstr) {
			      AppCheckList appCheckListObj =appCheckListDAO.findById(new Integer(checkIdstr));
			      if (null!=appCheckListObj) {
			    	  /**
			    	   *  Hibernate delete by object not directly by id
			    	   */
				     appCheckListDAO.delete(appCheckListObj);
					 request.setAttribute(Constant.SUCCESS_MESSAGE, "Successfully delete the Check List Item:"+appCheckList.getTitle());
					 	 
			      } 
			     
			}
			
			
			LOG.info("AppCheckList Delete end");		 
			
	   }
	    if (null!=appCheckList.getCheckId()) {
	    	
	    	   if (Constant.ACTION_CHECK_LIST_DELETE.equalsIgnoreCase(actionType)) {   
	    		   request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,"Successfully delete a Check List Item: "+appCheckList.getTitle());
	    	   } else {
	    		   request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,"Successfully save a Check List Item: "+appCheckList.getTitle());
	    	   }
	    }
		ui.setSessionAttributeObject(Constant.CHECK_LIST_FORM, form); 
		
		mode.addObject(Constant.CHECK_LIST_FORM, form);
		 	 
		return mode; 
		
	}
	 
	 
}
