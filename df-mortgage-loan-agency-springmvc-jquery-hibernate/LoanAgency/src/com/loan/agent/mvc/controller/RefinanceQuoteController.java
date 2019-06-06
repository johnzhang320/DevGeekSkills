package com.loan.agent.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
 
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.loan.agent.calculators.vo.CompareLoanVo;
import com.loan.agent.calculators.vo.ReplyVoHelp;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.Niches;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.dao.Quote;
import com.loan.agent.dao.QuoteDAO;
import com.loan.agent.dao.Reply;
import com.loan.agent.dao.ReplyId;
import com.loan.agent.dao.State;
import com.loan.agent.dao.Users;
import com.loan.agent.dao.UsersDAO;
import com.loan.agent.mvc.formbean.NicheForm;
import com.loan.agent.mvc.formbean.QuoteForm;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.loan.mvc.validator.LoanQuoteValidator;
import com.send.email.smtp.ssl.SendHTMLAttachEmails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors; 

public class RefinanceQuoteController extends SimpleFormController{
	Logger LOG = Logger.getLogger(PurchaseQuoteController.class);
	
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService;
	
	public RefinanceQuoteController() {
		setCommandName("QuoteForm");
		setCommandClass(QuoteForm.class);
		
	}
	@Override	 
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		QuoteForm quoteForm = new QuoteForm();
		Integer userId =(Integer) request.getSession().getAttribute("LoginUserId");
		if (userId!=null) {
			Users users = quoteDBService.getUsersDAO().findById(userId);
			if (users!=null) {
				quoteForm.renderUser(users);
			}
		}
		/**
		 *  Niche Loan Initialization
		 */
		ui.setRequest(request);		
		Integer agentId =(Integer) request.getSession().getAttribute(Constant.AGENT_ID); 
		
		
		Agents agents =null;
		if (agentId==null) { 
			agents = LookupDataInitialServiceImpl.getAgent();
			agentId = agents.getAgentId();
		} else {		
		 
		    agents = quoteDBService.getAgentsDAO().findById(agentId);
		}
		
		LOG.info("RefinanceQuoteController.formBackingObject() AgentId="+agentId);		
		request.getSession().setAttribute(Constant.LOGIN_AGENT_NAME,agents.getFirstName()+" "+agents.getLastName());
	    NichesDAO nicheDAO = quoteDBService.getNichesDAO();
	    List<Niches> niches = nicheDAO.findByAgentId(agentId);
	    List<NicheForm> nicheList;
	    if (null==niches || 0==niches.size()) {
	    	nicheList = null;
	    } else {
	    	nicheList = new ArrayList<NicheForm>();
	    	for (Niches n: niches) {
	    		NicheForm nicheForm = new NicheForm();
	    		nicheForm.renderNiche(n);
	    		nicheList.add(nicheForm );
	    	}
	    	if (nicheList.size()==0)  {
	    		nicheList=null;
	    	}
	    }	    
	    request.getSession().setAttribute(Constant.NICHES_LIST, nicheList);
	
		
		return quoteForm;
		
	}
	@Override	 
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		QuoteForm quoteForm = (QuoteForm) command;
		/**
		 *  Calculate the monthlyIncome and LTV
		 */
		LOG.info("EmailAddress="+quoteForm.getEmailAddress());
		Utility.setParamAgentId(request);
	    
    	//Convert form bean to dao pojo
		Quote quote = quoteForm.modelQuote();
		
		Users users = quoteForm.modelUser();
		
		
		
		
		quote.setProcessStatus("pending");
		quote.setLoanType("Refinance");
		
		// Manually wired quoteDBService
		// quoteDBService  =(QuoteDBService) SpringFramework.getBean("QuoteDBService");
		
		 
		
		LOG.info("Model Passed, Purchase Price="+quote.getPurchasePrice()+",Injecting quoteDBService="+quoteDBService);
		
		QuoteDAO quoteDAO =  quoteDBService.getQuoteDAO();		
		UsersDAO usersDAO =  quoteDBService.getUsersDAO();
	 	
		LOG.info("Found DAOs....... ");
		
		Users userHandler = quoteDBService.findUserByEmail(users.getEmailAddress());
	 
		//Email is uniqueId 
		if (userHandler!=null) {			 
			users.setUserId(userHandler.getUserId());
			LOG.info("existing users Merging, user="+users.getUserId());
			usersDAO.merge(users);
		} else {
			LOG.info("users Saving....., userId=null");
			usersDAO.save(users);
		}
		
		
		quote.setUsers(users);
		
		Set<Quote> quoteSet = new HashSet<Quote>();
		quoteSet.add(quote);
				
		Integer agentId = (Integer) request.getSession().getAttribute(Constant.AGENT_ID);
		Agents agents =null;
		if (agentId==null) { 
			agents = LookupDataInitialServiceImpl.getAgent();
		} else {
			agents = quoteDBService.getAgentsDAO().findById(agentId);
		}
		if (agents==null) {
	      	LOG.info("Can not fetch current agent!");
	       	ModelAndView mode = new ModelAndView("RefinanceQuote");
	       	return mode;
	    } 
		request.getSession().setAttribute(Constant.AGENT_OBJECT, agents);
		 
		quote.setAgents(agents);
		
		 	 
		 quote = quoteDAO.merge(quote);
		 
		 List<String> loanTerms= quoteForm.getLoanTerms();
			/**
			 *  Max 4 interest rate quote
			 */
		 Integer quoteId =quote.getQuoteId();	
		 LOG.info("New Quote_ID="+quoteId);
		 /**
		  *  No need reply and send email agent 04/19/2014
		  */
				 
	/**	 if (quoteId!=null) {
			 for (int i=0;i<4;i++) {
				 if (i<loanTerms.size()) {		
					 
					    ReplyId id =  new ReplyId();		    
					    id.setLoanId(i);
					    id.setQuoteId(quoteId);
					    Reply persistOne = quoteDBService.getReplyDAO().findById(id);
					   
					    if (persistOne!=null) {
					    	 persistOne.setTerm(loanTerms.get(i));
					    	quoteDBService.getReplyDAO().merge(persistOne);
					    } else {
					    	Reply newReply = new Reply();
					    	newReply.setTerm(loanTerms.get(i));
					    	newReply.setId(id);					    	 
					    	quoteDBService.getReplyDAO().save(newReply);
					    }
					  
				 }
			 } 
		 }
		
		LOG.info("Refinance Quote saving successfully .....");
		   
	       
			 *  Send email to current agent, GET URL to server trigger requestFilter to decode the paramAgentId
		 
			 String encodedAgentId = ui.getEncodedString(agentId.toString());
			 
		     String targetURL = SysPath.getHostName()+"/"+Constant.REFINANCE_QUOTE_EMAIL_HTML+"?quoteId="
		     +quoteId.toString()+"&paramAgentId="+encodedAgentId+"&userId="+users.getUserId().toString();	
         
        	
           
             *  Post Agent Id first and get Response from 
             
        	LOG.info("targetURL ="+targetURL);
        	 String emailContent = ui.getEmailContentFromURL(targetURL); 
		    LOG.info("Email Content = "+ emailContent);
		    
		    SendHTMLAttachEmails handler = new  SendHTMLAttachEmails();
		    
		    boolean ret=  handler.sendSingleEmail(
    		  		"john.zhang320@gmail.com",
    		  		"abc12345!",
    		  		users.getEmailAddress(),
    		  		users.getFirstName()+" " + users.getLastName()+" sent a refinance loan quote",
    		  		agents.getEmailAddress(),
    		  		agents.getFirstName(),
    		  		null,
    		  		emailContent,
    		  		null,
    		  		null,
    		  		null,
    		  		null    		  		
    		    );
		request.getSession().setAttribute(Constant.REFINANCE_QUOTE_EMAIL, null);
		if (ret) {
			request.getSession().setAttribute(Constant.REFINANCE_QUOTE_EMAIL, "yes");
			LOG.info("Quote Email Sent successfully .....");
		}
		
		*/
		//ModelAndView mode = new ModelAndView(new RedirectView("purchaseQuoteSuccess.html"));
		ModelAndView mode = new ModelAndView("RefinanceQuoteSuccess");
		//mode.addObject("quoteForm",quoteForm);
		/**
		 *  Calculate MonthlyIncome and LoanToValue
		 */
		quoteForm.renderQuote(quote);
		
		request.getSession().setAttribute("quoteForm", quoteForm);
		
		
		LOG.info("Redirect to LoanQuoteSuccess .....");
		return mode;
	 
	}
 
	 
	public QuoteDBService getQuoteDBService() {
		return quoteDBService;
	}
	public void setQuoteDBService(QuoteDBService quoteDBService) {
		this.quoteDBService = quoteDBService;
	}
	
	@Override	 
	public Map referenceData(HttpServletRequest request) throws Exception {
		
		Map referenceData = new HashMap(); 
		 
			Map<String,String> stateMap = LookupDataInitialServiceImpl.getStateMap();
		 
			referenceData.put("stateMap", stateMap);		
		 
		
	 	List<String> loanTermList = new ArrayList<String>();
		loanTermList.add("30 years fixed");
		loanTermList.add("20 years fixed");
		loanTermList.add("15 years fixed");
		loanTermList.add("10/1 ARM");
		loanTermList.add("7/1 ARM");
		loanTermList.add("5/1 ARM");
		loanTermList.add("3/ ARM");
		 
		 
		referenceData.put("loanTermList", loanTermList);
		return referenceData;
	}
	
}
