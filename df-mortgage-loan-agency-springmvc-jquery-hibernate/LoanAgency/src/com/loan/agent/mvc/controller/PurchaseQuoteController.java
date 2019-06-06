package com.loan.agent.mvc.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.Niches;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.dao.Quote;
import com.loan.agent.dao.QuoteDAO;
import com.loan.agent.dao.Reply;
import com.loan.agent.dao.ReplyId;
 
import com.loan.agent.dao.Users;
import com.loan.agent.dao.UsersDAO;
import com.loan.agent.mvc.formbean.NicheForm;
import com.loan.agent.mvc.formbean.QuoteForm;
 
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;
import com.loan.agent.services.Constant;
import com.loan.agent.services.LookupDataInitialService;
import com.loan.agent.services.QuoteDBService;
import com.loan.mvc.validator.LoanQuoteValidator;
import com.send.email.smtp.ssl.SendHTMLAttachEmails;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.validation.BindException;
import org.springframework.validation.Errors; 
import org.apache.log4j.*;

public class PurchaseQuoteController extends SimpleFormController{
	
	Logger LOG = Logger.getLogger(PurchaseQuoteController.class);
	//Auto Wired for quoteDBService, in ApplicationContext, we must define the bean quoteDBService
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService;
	 
	public PurchaseQuoteController() {
		setCommandName("QuoteForm");
		setCommandClass(QuoteForm.class);
	}
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Utility.setParamAgentId(request);
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
		
		LOG.info("PurchaseQuoteController.formBackingObject() AgentId="+agentId);		
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
		LOG.info("EmailAddress="+quoteForm.getEmailAddress());
		
	    
    	//Convert form bean to dao pojo
		Quote quote = quoteForm.modelQuote();
		Users users = quoteForm.modelUser();
		LOG.info("First_Name="+quote.getFirstName()+",lastName="+quote.getLastName()+",email="+quote.getEmailAddress()+",phone="+quote.getPhoneNo());
	
		
		quote.setProcessStatus("pending");
		quote.setLoanType("Purchase");
		
				 
		
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
		/**
		 *  get current agent_id and fetch the agent object
		 */
		Integer agentId = (Integer) request.getSession().getAttribute(Constant.AGENT_ID);
		Agents agents =null;
		if (agentId==null) { 
			agents = LookupDataInitialServiceImpl.getAgent();
		} else {
			agents = quoteDBService.getAgentsDAO().findById(agentId);
		}
		
        if (agents==null) {
        	 LOG.info("Can not fetch current agent!");
        	ModelAndView mode = new ModelAndView("PurchaseQuote");
        	return mode;
        }
		request.getSession().setAttribute(Constant.AGENT_OBJECT, agents);
	 		 
		quote.setAgents(agents);
		
		 LOG.info("Quote Save or Merging.....Quote.County="+quote.getPropertyCounty()+",quoteDAO="+quoteDAO); 
		 
		 quote = quoteDAO.merge(quote);
		 
		 List<String> loanTerms= quoteForm.getLoanTerms();
			/**
			 *  Max 4 interest rate quote
			 */
		 Integer quoteId =quote.getQuoteId();	
		 /**
		  *  No need reply and send email agent 04/19/2014
		  */
		 /** 
		 LOG.info("New Quote_ID="+quoteId);
		 if (quoteId!=null) {
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
		
		 *  Send email to current agent, GET URL to server trigger requestFilter to decode the paramAgentId
		 
		 String encodedAgentId = ui.getEncodedString(agentId.toString());
		 
	     String targetURL =SysPath.getHostName()+"/"+Constant.PURCHASE_QUOTE_EMAIL_HTML+"?quoteId="
	     +quoteId.toString()+"&paramAgentId="+encodedAgentId+"&userId="+users.getUserId().toString();

        	
         
        	
            
        	LOG.info("targetURL ="+targetURL);
        	 String emailContent = ui.getEmailContentFromURL(targetURL); 
		    LOG.info("Email Content = "+ emailContent);
		    
		    SendHTMLAttachEmails handler = new  SendHTMLAttachEmails();
		    
		    boolean ret=  handler.sendSingleEmail(
    		  		"john.zhang320@gmail.com",
    		  		"abc12345!",
    		  		users.getEmailAddress(),
    		  		users.getFirstName()+" " + users.getLastName()+" sent a purchase loan quote",
    		  		agents.getEmailAddress(),
    		  		agents.getFirstName(),
    		  		null,
    		  		emailContent,
    		  		null,
    		  		null,
    		  		null,
    		  		null    		  		
    		    );
		request.getSession().setAttribute(Constant.PURCHASE_QUOTE_EMAIL, null);
		if (ret) {
			request.getSession().setAttribute(Constant.PURCHASE_QUOTE_EMAIL, "yes");
			LOG.info("Quote Email Sent successfully .....");
		}*/
		
		LOG.info("Quote saving successfully .....");
		
		
		//ModelAndView mode = new ModelAndView(new RedirectView("purchaseQuoteSuccess.html"));
		ModelAndView mode = new ModelAndView("PurchaseQuoteSuccess");
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
		loanTermList.add("3/1 ARM");
		 
		
		referenceData.put("loanTermList", loanTermList);
		 
		return referenceData;
	}
	
}
