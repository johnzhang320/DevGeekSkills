package com.loan.agent.mvc.controller;

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
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.RateSheetDAO;

import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.RateSheetForm;
import com.loan.agent.mvc.utils.Utility;

import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.Constants;
import com.loan.agent.services.QuoteDBService;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;

public class RateSheetController extends SimpleFormController {
 static final Logger LOG = Logger.getLogger(RateSheetController.class);
	
 	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService; 
 
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Utility.setParamAgentId(request);
		RateSheetForm rateForm = new RateSheetForm();
		RateSheetDAO rateDao = quoteDBService.getRateSheetDAO(); 
		Integer agentId =(Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
		if (agentId!=null) {
			
			List<RateSheet> list =  rateDao.findByAgentId(agentId);
			rateForm.renderRateSheet(list);
			Agents agents =quoteDBService.getAgentsDAO().findById(agentId);
			request.getSession().setAttribute(Constant.AGENT_FULL_NAME,agents.getFirstName()+" "+agents.getLastName());
			
			 
		} else {
			Log.info("you must login as an agent");
			
		}
		   
		
		return rateForm;
		
	} 
	
	
	
	
	public RateSheetController() {
		setCommandName("rateSheetForm");
		setCommandClass(RateSheetForm.class);
	}
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 
		LOG.info("OnSubmit() begin........");
		Utility.setParamAgentId(request);
		/**
		 *  Command must be form bean
		 */
		RateSheetForm rateForm = (RateSheetForm) command;
		/**
		 *  Modeling the form to a List<RateSheet>
		 */
		Integer agentId =(Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
		
		if (agentId==null) {
		    LOG.info("You must login as an agent to enter rate sheet");
		    throw new Exception("You must login as an agent to enter rate sheet");
			 
		}
	    		
		LOG.info("Current agentId="+agentId+", submit is going on!");
		
		List<RateSheet> formList= rateForm.modelRateSheet(agentId);
		
		LOG.info("Current Form List size="+formList.size());
		
		
		
	    /**
	     *  Delete all records in rate sheet table per current agentId 
	     *  Save all records in rate form
	     */
		
		RateSheetDAO rateDao = quoteDBService.getRateSheetDAO(); 
		
		List<RateSheet> existList = rateDao.findByAgentId(agentId);
		
		if (existList==null && existList.size()>0) {
			LOG.info("Agent Id "+agentId+" Not Exist in Rate Sheet table");
			/**
			 *  Save anyway
			 */
			persistRate(rateDao,formList,Constants.DB_SAVE);
		} else {
			/**
			 *  Delete exist list and save form list
			 */
			LOG.info("Agent Id "+agentId+" exists in Rate Sheet table, delete first ");
			persistRate(rateDao,existList,Constants.DB_DELETE);
			LOG.info("Agent Id "+agentId+" exists in Rate Sheet table, then insert ");
			persistRate(rateDao,formList,Constants.DB_SAVE);
			LOG.info("The insert, OK! ");
		}
		
	 		
	 
		ModelAndView mode = new ModelAndView(Constant.RATE_SHEET_SAVE_SUCCESS); 		
		 
		
		//mode.addObject("rateSheetForm", rateForm);
		
		//rateForm.publishRateToSession(request,formList);
		
		LOG.info("OnSubmit() end........");
		 
		return mode; 
		 
	}
	
	private void persistRate(RateSheetDAO rateDao,List<RateSheet> list,String type) {
		if (list==null) {
			return;
		}
		for (RateSheet rs : list) {
			if (Constants.DB_SAVE.equalsIgnoreCase(type)) {
				rateDao.save(rs);
			}
			
			if (Constants.DB_MERGE.equalsIgnoreCase(type)) {
				rateDao.merge(rs);
			}
			if (Constants.DB_DELETE.equalsIgnoreCase(type)) {
				rateDao.delete(rs);
			}
		}
	}
	
	public QuoteDBService getQuoteDBService() {
		return quoteDBService;
	}
	public void setQuoteDBService(QuoteDBService quoteDBService) {
		this.quoteDBService = quoteDBService;
	}
	
	
} 

