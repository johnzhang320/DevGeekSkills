package com.loan.agent.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

 
import com.loan.agent.calculators.vo.AgentEmailVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AppCheckList;
import com.loan.agent.dao.AppCheckListDAO;
import com.loan.agent.dao.Niches;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.dao.Quote;
import com.loan.agent.dao.QuoteDAO;
import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.RateSheetDAO;
import com.loan.agent.dao.Users;
import com.loan.agent.mvc.formbean.EmailServerForm;
import com.loan.agent.mvc.formbean.NicheForm;
import com.loan.agent.mvc.formbean.QuoteForm;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;

public class GenerateAgentEmailController extends MultiActionController {
	static final Logger LOG = Logger.getLogger(GenerateAgentEmailController.class);
	 
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService;	
	
	public ModelAndView generateAgentEmailHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		 LOG.info(" generateAgentEmailHandler() begin");
		/**
		 *   Parent code call this code by URL redirect, normally session context will be lost 
		 *   So I use parameter passes agentId, then we must save agent id again
		 */
		ui.setRequest(request);
      
        String agentIdStr = request.getParameter(Constant.PARAM_AGENT_ID);
        
        
        
        String paramEmail = request.getParameter(Constant.PARAM_MY_EMAIL);
        
        String NicheProgramId = request.getParameter(Constant.NICHE_PROGRAM_ID);
        
        String ApplicationFormId = request.getParameter(Constant.APPLICATION_FORM_ID);
        
        String InteratRateId = request.getParameter(Constant.INTEREST_RATE_ID);
        
        String LoansAgentLinksId = request.getParameter(Constant.LOANS_AGENT_LINKS_ID);
        
        String AgentPictureId = request.getParameter(Constant.AGENT_PICTURE_ID);
        
        String pullAllAgentData = request.getParameter(Constant.PULL_ALL_AGENT_DATA);
        
        String paraAppCheckListIds = request.getParameter(Constant.PARAM_APP_CHECKLIST_IDS);
        
        List <AppCheckList> chList =null;
        
        if (null!=paraAppCheckListIds) {  
        	
        	chList = new ArrayList<AppCheckList>();
        	
        	AppCheckListDAO dao = quoteDBService.getAppCheckListDAO();
        	
        	List<AppCheckList> aList =(List<AppCheckList>) dao.findAll();
        	LOG.info(" generateAgentEmailHandler() paraAppCheckListIds="+  paraAppCheckListIds);
        	String checkIdArray[] = paraAppCheckListIds.split("\\|");
        	
        	for (int i=0; i<checkIdArray.length;i++) {
        		LOG.info(" generateAgentEmailHandler() checkIdArray[i]="+  checkIdArray[i]);
        		Integer checkId = new Integer(checkIdArray[i]);
        		
        		AppCheckList vo = aList.get(checkId); 
        		
        		chList.add(vo);
         	}
            request.getSession().setAttribute(Constant.SELECTED_APP_CHECKLIST, chList);
        }
        
      /**
       *  Process Quote Form Data   
       */
        String quoteIdStr = request.getParameter(Constant.QUOTE_ID);
        if (null!=quoteIdStr) {
        	Integer quoteId = new Integer(quoteIdStr);
        	QuoteForm qForm =(QuoteForm) request.getSession().getAttribute(Constant.QUOTE_FORM);   
        	QuoteDAO quoteDao = quoteDBService.getQuoteDAO();	 	
        	Quote quote=quoteDao.findById(quoteId);
        	  if (null==qForm) {
  				if (null!=quoteId) {					 
  					qForm  = new QuoteForm();
  					qForm.renderQuote(quote);
  		 		}
  		   }
  		   qForm.setQuoteId(quoteId.toString());
  		   request.getSession().setAttribute(Constant.QUOTE_FORM, qForm);
        	
        }
        
        if (!Utility.isEmpty(pullAllAgentData)) {
        	NicheProgramId="yes";
        	ApplicationFormId="yes";
        	InteratRateId="yes";
        	LoansAgentLinksId="yes";
        	AgentPictureId="yes";
        }
        
        ui.setSessionAttribute(Constant.NICHE_PROGRAM_ID, NicheProgramId);
        ui.setSessionAttribute(Constant.APPLICATION_FORM_ID, ApplicationFormId);
        ui.setSessionAttribute(Constant.INTEREST_RATE_ID, InteratRateId);
        ui.setSessionAttribute(Constant.LOANS_AGENT_LINKS_ID, LoansAgentLinksId);
        ui.setSessionAttribute(Constant.AGENT_PICTURE_ID, AgentPictureId);
     
        //ServletRequestUtils.getStringParameter(request,"currentAgentId");
       // LOG.info(" generateAgentEmailHandler() from parameter currentAgentId agentIdstr="+agentIdStr+",my email address="+paramEmail) ; 
       
        if (agentIdStr==null) {
			LOG.info(" generateAgentEmailHandler() agentIdStr==null") ;
			return null;
		}
        agentIdStr = ui.getDecodedString(agentIdStr);
        
        Integer agentId = new Integer(agentIdStr) ; 
        
        if (!Utility.isEmpty(NicheProgramId)) {
        	
    		if (agentId==null) {
    			LOG.info(" generateAgentEmailHandler() agentId==null") ;
    			return null;
    		}
    		 
    		String nicheIntro = quoteDBService.findNicheIntroByAgentId(agentId);
    		if (null==nicheIntro) {
    			nicheIntro = " ";
    		}
    		ui.setSessionAttribute(Constant.NICHES_INTRO, nicheIntro);
    		LOG.info("generateAgentEmailHandler() nicheIntro="+nicheIntro); 
    		
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
    	   
        }
       
       
		if (agentId==null) {
			LOG.info(" generateAgentEmailHandler() agentId==null") ;
			return null;
		}
	
		//Integer agentId = (Integer) request.getSession().getAttribute(Constant.AGENT_ID);
		LOG.info(" generateAgentEmailHandler() from session AGENT_ID agentId="+agentId) ;
		 
		request.getSession().setAttribute(Constant.PARAM_AGENT_ID, agentId);
		
		 
		LOG.info("AgentId="+agentId);
		 
		Agents agents = quoteDBService.getAgentsDAO().findById(agentId);		 
		
		if (paramEmail!=null && paramEmail.trim().length()>0) {
			agents.setEmailAddress(paramEmail);
		}
		
		RateSheetDAO rateSheetDao = quoteDBService.getRateSheetDAO();
		    
		List<RateSheet> list = rateSheetDao.findByAgentId(agentId);			
		
		ui.setRequest(request);
			 
		if (list==null || list.size()==0) {
			LOG.info("List<RateSheet> list =null");
				 
		} else {
			ui.setSessionAttributeObject(Constant.INTEREST_RATE_LIST, list);
			 
	   }
			 
   		 
   	    AgentEmailVo agentEmailVo = new AgentEmailVo();
   	   	
   	   	
   	    agentEmailVo.setLinks(agents);
   	    
      	ui.setRequest(request);     
      
		
   	    ui.setSessionAttributeObject(Constant.AGENT_EMAIL_LINKS, agentEmailVo);
   		
   	    ui.setSessionAttributeObject(Constant.HOST_NAME,SysPath.getHostName());
   	    
   	  
   	    String paramAgentIdStr = ui.getEncodedString(agentId.toString());
   	   
   	   
   	    String imageUrlString = SysPath.getHostName()+"/previewPicture.html?paramAgentId="+paramAgentIdStr;
   	   
   	    LOG.info("GenerateAgentEmailController imageUrlString="+imageUrlString);
   	    
   		ui.setSessionAttribute(Constant.AGENT_EMAIL_PICTURE_URL, imageUrlString);
   		
   		ui.setSessionAttributeObject(Constant.AGENT_PROFILE, agents);
   		/**
   		 *  First Name and Last Name will be fetched from PurchaseQuoteSuccess.jsp and RefinanceQuoteSucess.jsp
   		 */
   		ui.setSessionAttributeObject(Constant.AGENT_OBJECT, agents);
   		
   		ModelAndView mode =new ModelAndView(Constant.GENERATE_AGENT_EMAIL);
   	    LOG.info(" generateAgentEmailHandler() end");
		return mode;
	}
	/**
	 * purchaseQuoteEmailHandler, when use GET to fetch a web page as source inputStream, the main page session
	 * would not be transfer to child page, therefore  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView purchaseQuoteEmailHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		 LOG.info(" purchaseQuoteEmailHandler() begin");
		 
		 String quoteIdStr = request.getParameter(Constant.QUOTE_ID);
		 String agentIdStr = request.getParameter(Constant.PARAM_AGENT_ID);
		 String userIdStr = request.getParameter(Constant.USER_ID);
		 
		 LOG.info(" purchaseQuoteEmailHandler() quoteIdStr ="+quoteIdStr+",agentIdStr="+agentIdStr+",userIdStr="+userIdStr) ;
		 if (quoteIdStr==null) {
				LOG.info(" purchaseQuoteEmailHandler() quoteIdStr ==null") ;
				return null;
		 }
		 
		 Integer quoteId = new Integer(quoteIdStr);
		 
		 agentIdStr = ui.getDecodedString(agentIdStr);
		 
		 Integer agentId = new Integer(agentIdStr);
		 
		 Integer userId = new Integer(userIdStr);
		 
		 
		 
		 LOG.info(" purchaseQuoteEmailHandler() quoteId ="+quoteId+",agentId="+agentId+",userId="+userId) ;
		 
		 Quote quote = quoteDBService.getQuoteDAO().findById(quoteId);		 
		 
		 Users users = quoteDBService.getUsersDAO().findById(userId);		 
			 
		 Agents agents = quoteDBService.getAgentsDAO().findById(agentId);		 
		 
		 /**
		  *  We can not use quote and users to JSP because JSP only read String data type !!!!!
		  */
		 QuoteForm quoteForm = new QuoteForm();
		 
		 quoteForm.renderQuote(quote);
		 
		 quoteForm.renderUser(users);
		 
		 LOG.info(" purchaseQuoteEmailHandler() quote ="+quote+",agents="+agents+",users="+users) ;
		 
		 request.getSession().setAttribute("quoteForm", quoteForm);
		 
		 request.getSession().setAttribute("agentObject", agents);
		 
		 
		 ModelAndView mode =new ModelAndView(Constant.PURCHASE_QUOTE_EMAIL);
	   	 LOG.info(" purchaseQuoteEmailHandler() end");
		 return mode;
	}
	/**
	 * refinanceQuoteEmailHandler, when use GET to fetch a web page as source inputStream, the main page session
	 * would not be transfer to child page, therefore  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView refinanceQuoteEmailHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		 LOG.info(" refinanceQuoteEmailHandler() begin");
		 String quoteIdStr = request.getParameter(Constant.QUOTE_ID);
		 String agentIdStr = request.getParameter(Constant.PARAM_AGENT_ID);
		 String userIdStr = request.getParameter(Constant.USER_ID);
		 if (quoteIdStr==null) {
				LOG.info(" refinanceQuoteEmailHandler() quoteIdStr ==null") ;
				return null;
		 }
		 Integer quoteId = new Integer(quoteIdStr);
		 agentIdStr = ui.getDecodedString(agentIdStr);
		 Integer agentId = new Integer(agentIdStr);
		 Integer userId = new Integer(userIdStr);
		 
		 LOG.info(" purchaseQuoteEmailHandler() quoteId ="+quoteId+",agentId="+agentId+",userId="+userId) ;
		 
		 Quote quote = quoteDBService.getQuoteDAO().findById(quoteId);		 
		 
		 Users users = quoteDBService.getUsersDAO().findById(userId);		 
			 
		 Agents agents = quoteDBService.getAgentsDAO().findById(agentId);		 
	     
		 QuoteForm quoteForm = new QuoteForm();
	     
	     quoteForm.renderQuote(quote);
		 
		 quoteForm.renderUser(users);
		 
		 request.getSession().setAttribute("quoteForm", quoteForm);		 
				 
		 request.getSession().setAttribute("agentObject", agents);
		 
		 ModelAndView mode =new ModelAndView(Constant.REFINANCE_QUOTE_EMAIL);
	   	 LOG.info(" refinanceQuoteEmailHandler() end");
		 return mode;
	}
	public ModelAndView saveEmailSubjectHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		 LOG.info("saveEmailSubjectHandler() begin");
		 String subject = (String) request.getParameter(Constant.EMAIL_SUBJECT);
		 LOG.info("saveEmailSubjectHandler() saving subject="+subject);
		 request.getSession().setAttribute(Constant.EMAIL_SUBJECT, subject);
		 LOG.info("saveEmailSubjectHandler() end");
		 
		 return null;
	}
}
