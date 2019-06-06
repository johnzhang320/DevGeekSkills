package com.loan.agent.mvc.controller;

import java.io.File;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.loan.agent.calculators.vo.SelectedStateVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;

import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;

import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;

public class AgentSignupController extends SimpleFormController {
 static final Logger LOG = Logger.getLogger(AgentSignupController.class);
	
 	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService; 
	@Autowired
	@Resource(name="AgentReviewService")
	private AgentReviewService agentReviewService;
		
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
	
		AgentForm agentForm = new AgentForm();
		Integer agentId = (Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
		 request.getSession().setAttribute(Constant.EMAIL_SERVER_CONNECTION_STATUS, null);
		//LOG.info("formBackingObject agendId="+agentId);
		if (agentId!=null) {
			Agents agents = quoteDBService.getAgentsDAO().findById(agentId);
			if (agents!=null) {
				agentForm.renderAgents(agents);
				/**
				 *  get eligible states string and merge to List<SelectedStateVo> list
				 */
				List<SelectedStateVo> list = ui.mergeSelectedState(agents.getLicenseEligibleState());
				request.getSession().setAttribute(Constant.STATE_SELECTED_LIST, list);
			}
		} else {
			List<SelectedStateVo> list = ui.mergeSelectedState(" ");
			request.getSession().setAttribute(Constant.STATE_SELECTED_LIST, list);
		}
	 	if (agentId==null) {
	 		return agentForm;
	 	}
		String paremAgentIdStr = ui.getEncodedString(agentId.toString());	   	   
	   	   
	    String imageUrlString = SysPath.getHostName()+"/previewPicture.html?paramAgentId="+paremAgentIdStr;
	    ui.setRequest(request);
		ui.setSessionAttribute(Constant.AGENT_EMAIL_PICTURE_URL, imageUrlString);
		
	 
		return agentForm;
		
	} 
	
	@Override
	public Map referenceData(HttpServletRequest request) {
		Map referenceData = new HashMap(); 
		 
		Map<String,String> stateMap = LookupDataInitialServiceImpl.getStateMap();
		
		referenceData.put("stateMap", stateMap);		
        
		
		Utility.setParamAgentId(request); 
		return referenceData;
	}
	
	
	public AgentSignupController() {
		setCommandName("agentForm");
		setCommandClass(AgentForm.class);
	}
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 
		LOG.info("AgentSignupController OnSubmit() begin");
		
		AgentController ac = new AgentController();
		ModelAndView mode = ac.agentSignupProcessHandler(request, response);
		
		/**
		
		AgentForm agentForm = (AgentForm) command;
		
		Agents agents = agentForm.modelAgents();
		
		LOG.info("agents DRE_NO ="+agents.getDreNo()+",NMLS_NO="+agents.getNmlsNo());
		
	    AgentsDAO agentsDAO = quoteDBService.getAgentsDAO();	
		
		LOG.info("OnSubmitted...........");
			Utility.setRequest(request,response);
	
	
		   
		  
		    Integer agentId = (Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
		    
		    Agents agentObj = null;
		    
		    if (agentId==null || agentId==0) { 
		    	 
		    	LOG.info("agentId==null");				
				
		    	agentObj = quoteDBService.findAgentByEmail(agents.getEmailAddress());
		    } else {
		    	 
		    	LOG.info("agentId="+agentId);	
		    	agentObj =quoteDBService.getAgentsDAO().findById(agentId);
		    }
		 		
		 
			 
			//Email is uniqueId 
			if (agentObj!=null) {					  
				agents.setAgentId(agentObj.getAgentId());
			
				LOG.info("agents Merging....., agentId="+agents.getAgentId());
				LOG.info("agents merge DRE_NO ="+agents.getDreNo()+",NMLS_NO="+agents.getNmlsNo());
				 
				 
				agentsDAO.merge(agents);
			
			} else {
				LOG.info("agents Save...., agent.getpictureContent()="+agents.getPictureContent());
				agentsDAO.save(agents);
			}
			agentId = agents.getAgentId();
			
			request.getSession().setAttribute("agentId", agentId);
		 
			LOG.info(" agents ID="+agents.getAgentId());			
	   */
			
		LOG.info("AgentSignupController OnSubmit() end");
	 		
	 
		return mode; 
		 
	}
	public QuoteDBService getQuoteDBService() {
		return quoteDBService;
	}
	public void setQuoteDBService(QuoteDBService quoteDBService) {
		this.quoteDBService = quoteDBService;
	}
	public AgentReviewService getAgentReviewService() {
		return agentReviewService;
	}
	public void setAgentReviewService(AgentReviewService agentReviewService) {
		this.agentReviewService = agentReviewService;
	}
	
} 

