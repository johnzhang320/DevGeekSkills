package com.loan.agent.mvc.controller;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class WebsiteHelpController extends MultiActionController {
	static final Logger LOG = Logger.getLogger(WebsiteHelpController.class);
	 
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService;	
	
	
	public ModelAndView websiteHelpHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		ModelAndView mode =null;
		
		Utility.setParamAgentId(request);		
		 
		
		 String helpTabPointer = request.getParameter(Constant.HELP_TAB_POINTER);
		 
		 LOG.info("WebsiteHelpController: helpTabPointer="+helpTabPointer);
		
		 
		 String currentJspCode = null;
		 
		 if (Utility.isEmpty(helpTabPointer)) {
			 mode =new ModelAndView(Constant.FORWARD_WEBSITE_HELP);
			 LOG.info("WebsiteHelpController:Help Menu");
		 } else {
			 LOG.info("WebsiteHelpController:Help popout");
			if ("tabs-1".equalsIgnoreCase(helpTabPointer)) {
		   		currentJspCode="HelpWebsiteOverview.jsp";
			}
			if ("tabs-2".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpAgentSignupGmail.jsp";
			}
			if ("tabs-3".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpAgentEnterInterestRate.jsp";
			}
			if ("tabs-4".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpAgentEnterNicheProgram.jsp";
			}
			if ("tabs-5".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpTestConfigureGmail.jsp";
			}
			if ("tabs-51".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpWhyGmail.jsp";
			}
			if ("tabs-6".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpEmailMarketingRoadMaps.jsp";
			}
			if ("tabs-7".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpUploadEmailList.jsp";
			}
			if ("tabs-8".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpPullAgentProfileData.jsp";
			}
			if ("tabs-9".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpPullBorrowerQuoteData.jsp";
			}
			if ("tabs-10".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpPullApplicationCheckList.jsp";
			}
			if ("tabs-11".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpEditSaveAttachSendEmail.jsp";
			}
			if ("tabs-12".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpFromEmailToAgentSite.jsp";
			}
			if ("tabs-13".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpBorrowerPurchaseQuote.jsp";
			}
			if ("tabs-14".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpBorrowerRefinanceQuote.jsp";
			}
			if ("tabs-15".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpPurchLoanCompare.jsp";
			}
			if ("tabs-16".equalsIgnoreCase(helpTabPointer)) {
				currentJspCode="HelpRefiLoanCompare.jsp";
			}
			 mode =new ModelAndView(Constant.FORWARD_WEBSITE_HELP_POPOUT);
		 }
		 LOG.info("WebsiteHelpController: currentJspCode="+currentJspCode);
		 request.getSession().setAttribute(Constant.HELP_CURRENT_JSP_CODE,currentJspCode);
		 	
		 return mode;
	}
	
	
     
	
}
