package com.loan.agent.mvc.controller;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.loan.agent.calculators.vo.AgentEmailVo;
import com.loan.agent.calculators.vo.AgentIdVo;
import com.loan.agent.calculators.vo.AgentReviewQuoteVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.Niches;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.RateSheetDAO;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.NicheForm;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.loan.agent.services.AgentAdService;
import com.loan.agent.services.impl.AgentAdServiceImpl;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;

public class AgentAdController extends MultiActionController {
	static final Logger LOG = Logger.getLogger(AgentController.class);
	 
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService;	
	
	
	public ModelAndView agentAdPageHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		ModelAndView mode =null;
		
		Utility.setParamAgentId(request);		
		 
		
	 
		Integer agentId = (Integer) request.getSession().getAttribute(Constant.AGENT_ID);
		Agents agents =null;
		if (agentId==null) { 
			agents = LookupDataInitialServiceImpl.getAgent();
		} else {
			agents = quoteDBService.getAgentsDAO().findById(agentId);
		}
		LOG.info("AgentId="+agentId);
		 
			 agents = quoteDBService.getAgentsDAO().findById(agentId);		 
		
			RateSheetDAO rateSheetDao = quoteDBService.getRateSheetDAO();
		    
			List<RateSheet> list = rateSheetDao.findByAgentId(agentId);
			
			 
			if (list==null || list.size()==0) {
				LOG.info("List<RateSheet> list =null");
				 
			} else {
				AgentAdService rHandler = new AgentAdServiceImpl();			
				rHandler.publishRateToSession(request, list);
			}
			 
			request.getSession().setAttribute(Constant.AGENT_PROFILE, agents );
			/**
			 *  Fetch Niche data
			 */
			  NichesDAO nicheDAO = quoteDBService.getNichesDAO();
			    List<Niches> niches = nicheDAO.findByAgentId(agentId);
			    List<NicheForm> nicheList = new ArrayList<NicheForm>();
			    for (Niches n: niches) {
			    	NicheForm nicheForm = new NicheForm();
			    	nicheForm.renderNiche(n);
			    	nicheList.add(nicheForm );
			    }
			    
			    LOG.info("nichesList.size()="+niches.size());
			    
			    request.getSession().setAttribute(Constant.NICHES_LIST, nicheList);	
			    
			    
			mode =new ModelAndView(Constant.AGENT_ADVERTISE_PAGE);
			
		return mode;
	}
	
	

	 
	
}
