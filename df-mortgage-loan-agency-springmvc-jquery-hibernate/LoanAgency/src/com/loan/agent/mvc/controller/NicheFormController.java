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
import com.loan.agent.dao.Niches;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.mvc.formbean.NicheForm;
import com.loan.agent.mvc.formbean.NicheForm;
 
 
import com.loan.agent.mvc.utils.Files;
import com.loan.agent.mvc.utils.ProcessDownloadFile;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
 
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.send.email.smtp.ssl.RecipientVo;
import com.send.email.smtp.ssl.UpdateEnvelop;
 

 
public class NicheFormController extends SimpleFormController {
Logger LOG = Logger.getLogger( NicheFormController.class);
@Autowired
@Resource(name="QuoteDBService")
private QuoteDBService quoteDBService;

	public NicheFormController() {
		setCommandName("nicheForm");
		setCommandClass(NicheForm.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		LOG.info("NicheController formBackingObject begin!");
		
		ui.setRequest(request);
		
		String actionType=ServletRequestUtils.getStringParameter(request,Constant.ACTION_TYPE);
		
		String nicheIdStr = ServletRequestUtils.getStringParameter(request,Constant.PARAM_NICHE_ID);
		
		ui.setSessionAttributeObject(Constant.ACTION_TYPE,actionType);
		
	 
		/** 
		 *  Get initialized Form
		 */
		request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,null);
		/**
		 *  NichesListView.jsp define the ADD, EDIT, Delete instead of NichesForm itself.
		 */
		NicheForm nicheForm =null;
		 
		nicheForm = new NicheForm();	
		//LOG.info("actionType="+actionType+",nicheIdStr="+nicheIdStr+",step 1") ;
		if (Constant.ACTION_NICHE_EDIT.equalsIgnoreCase(actionType) || Constant.ACTION_NICHE_DELETE.equalsIgnoreCase(actionType)) {						 
			Integer nicheId=null;			
			if (nicheIdStr!=null) {	 // save or delete in NichesForm, there is no nicheIdStr
				nicheId = new Integer(nicheIdStr);
				NichesDAO nichesDAO = quoteDBService.getNichesDAO();					
				Niches niches = nichesDAO.findById(nicheId);	
				nicheForm.renderNiche(niches);			
				//LOG.info("actionType="+actionType+",nicheIdStr="+nicheIdStr+",step 2"+",nicheForm.nicheTitle="+nicheForm.getNicheTitle()) ;
			}
			
		}
		nicheForm.setActionType(actionType);
			   
		LOG.info("NichesController formBackingObject end!");
		return nicheForm;
		
	}  
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		
		LOG.info("NichesController onSubmit() begin!");
		
		ui.setRequest(request);
		
		NicheForm form =  (NicheForm) command;
		
		Integer agentId = (Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
		
		ModelAndView mode = new ModelAndView(Constant.FORWARD_NICHES_FORM);
		
		if (null==agentId) {
			LOG.info("NichesController onSubmit() Login Agent id not exists");
			request.setAttribute(Constant.SUCCESS_MESSAGE, "No Agent Login, Login as Agent, please. ");
			return mode; 
		}
		
		form.setAgentId(agentId);
		
		request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,null);
		
		String actionType = form.getActionType();
		
		LOG.info("NichesController onSubmit() actionType="+actionType);

	
		
		NichesDAO nichessDAO = quoteDBService.getNichesDAO();	
		Niches niches = form.modelNiches();	
		
		if (Constant.ACTION_NICHE_ADD.equalsIgnoreCase(actionType)) {        	
			 
			LOG.info("Niche Add begin");						
			 
			nichessDAO.save(niches);		
			
			request.setAttribute(Constant.SUCCESS_MESSAGE, "Successfully add a new niche program:"+niches.getNicheTitle());
			
			LOG.info("Niche Add end");		 
			
	   }
	   if (Constant.ACTION_NICHE_EDIT.equalsIgnoreCase(actionType)) {        	
			 
			LOG.info("Niche Edit begin");
			
			String nicheIdstr = form.getNicheId();
			
			Integer nicheId = new Integer(nicheIdstr); 
			 LOG.info("nicheIdStr="+nicheIdstr+",nicheId="+nicheId); 
			if (null!=nicheIdstr) {
 			      Niches nicheObj =nichessDAO.findById(nicheId);
 			      if (null!=nicheObj) {
 			    	 LOG.info("Niche Edit Merge a record hehehe on nicheId="+nicheIdstr); 
 			    	 /**
 			    	  *  We must set NicheId, only when Edit and object existed , NicheId exists!
 			    	  */
 			    	 niches.setNicheId(nicheId);
 			    	 
 				     nichessDAO.merge(niches);
 				     
 			      } else {
 			    	 LOG.info("Niche Edit Add a record on on nicheId="+nicheIdstr);  
 			    	 nichessDAO.save(niches);
 			      }
 			 	request.setAttribute(Constant.SUCCESS_MESSAGE, "Successfully save the niche program:"+niches.getNicheTitle());
 			     
			}
			
			
			LOG.info("Niche Edit end");		 
			
	   }
	   if (Constant.ACTION_NICHE_DELETE.equalsIgnoreCase(actionType)) {        	
			 
			LOG.info("Niche Delete begin");
			
			String nicheIdstr = form.getNicheId();
		 
			if (null!=nicheIdstr) {
			      Niches nicheObj =nichessDAO.findById(new Integer(nicheIdstr));
			      if (null!=nicheObj) {
			    	  /**
			    	   *  Hibernate delete by object not directly by id
			    	   */
				     nichessDAO.delete(nicheObj);
					 request.setAttribute(Constant.SUCCESS_MESSAGE, "Successfully delete the niche program:"+niches.getNicheTitle());
					 	 
			      } 
			     
			}
			
			
			LOG.info("Niche Delete end");		 
			
	   }
	    if (null!=niches.getNicheId()) {
	    	   if (Constant.ACTION_NICHE_DELETE.equalsIgnoreCase(actionType)) {   
	    		   request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,"Successfully delete a niche: "+niches.getNicheTitle());
	    	   } else {
	    		   request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,"Successfully save a niche: "+niches.getNicheTitle());
	    	   }
	    }
		ui.setSessionAttributeObject(Constant.NICHE_FORM, form); 
		
		mode.addObject(Constant.NICHE_FORM, form);
		 	 
		return mode; 
		
	}
	 
	 
}
