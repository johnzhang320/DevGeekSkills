package com.loan.agent.mvc.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.loan.agent.calculators.Calculators;
import com.loan.agent.calculators.vo.AffordableLineVo;
import com.loan.agent.calculators.vo.UserReviewQuoteVo;
import com.loan.agent.calculators.vo.CompareLoanVo;
import com.loan.agent.calculators.vo.QuoteReplyVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.Quote;
import com.loan.agent.dao.Reply;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.LoginForm;
import com.loan.agent.mvc.formbean.QuoteForm;
 
import com.loan.agent.mvc.utils.FileDocument;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.LookupDataInitialService;
import com.loan.agent.services.QuoteDBService;
import com.loan.agent.services.UserReviewService;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;
import com.sun.org.apache.bcel.internal.Constants;
 

public class UserController extends MultiActionController {
	
	static final Logger LOG = Logger.getLogger(UserController.class);
	
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService;
	
	@Autowired
	@Resource(name="UserReviewService")
	private UserReviewService userReviewService;
	
	
	@Autowired
	@Resource(name="LookupDataInitialService")
	private LookupDataInitialService lookupDataInitialService;
	 
	
	public UserReviewService getUserReviewService() {
		return userReviewService;
	}
	public void setUserReviewService(UserReviewService userReviewService) {
		this.userReviewService = userReviewService;
	}
	public LookupDataInitialService getLookupDataInitialService() {
		return lookupDataInitialService;
	}
	public void setLookupDataInitialService(
			LookupDataInitialService lookupDataInitialService) {
		this.lookupDataInitialService = lookupDataInitialService;
	}
	@Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
	throws ServletException { 
	// Convert multipart object to byte[]
	binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
 
    }
	public ModelAndView userReviewQuoteHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		ModelAndView mode =null;
		
			Utility.setRequest(request,response);
		Integer userId =(Integer) request.getSession().getAttribute("LoginUserId");
		
		
		if (userId==null) { 
			LoginForm loginForm = new LoginForm();
			mode = new ModelAndView("UserLogin");
			LOG.info("redirect to UserLogin");
			mode.addObject("loginForm", loginForm);
			return mode;
		}
		  
		 	 
		String userName=null;
		if (userId!=null) {
			 		// fetch list from Database
			List<UserReviewQuoteVo> list = userReviewService.getUserQuoteInquire(userId);
			UserReviewQuoteVo currentVo  = list.get(0);
			userName = currentVo.getFirst_name()+" "+ currentVo.getLast_name();  
			
		 
			request.getSession().setAttribute("QuoteList", list);
			
			for (UserReviewQuoteVo vo:list) vo=null;
			
		   mode =new ModelAndView("UserReviewQuote");
		 
		   
		}
		
		request.getSession().setAttribute("LoginUserId", userId);
		request.getSession().setAttribute("UserName", userName);
		
		
		return mode;
	}
	
	public ModelAndView getRefiRemainBalanceHandler(  HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
				PrintWriter out =Utility.SetPostResponseContent(response);
		      
		        UserReviewQuoteVo quoteVo =(UserReviewQuoteVo) request.getSession().getAttribute("SessionQuoteVo");
		        
		  				// using ServletRequestUtils to obtain form data which never use binding command class
		  			Utility.setRequest(request,response);
		  		Double loan_amt =Utility.getDouble(quoteVo.getFirst_loan_balance());	
		  		
		  		 
		  		Double term =Utility.DescriptionToDoubleTerm(quoteVo.getFirst_loan_term());
		  			 
			  		
		  		Double int_rate = Utility.getDouble(quoteVo.getFirst_loan_rate());
		  		
		  		Integer first_mm =0;         //YPMT_mm));
				Integer first_yyyy =0;
		  		if (quoteVo.getFirst_date() !=null) {
		  			first_mm =Utility.getMonth(quoteVo.getFirst_date());         //YPMT_mm));
		  			first_yyyy =Utility.getYear(quoteVo.getFirst_date()); 
		  		} else {
		  			first_mm =Utility.getMonth(Utility.getHalfYearBeforeToday());         //YPMT_mm));
		  			first_yyyy =Utility.getYear(Utility.getHalfYearBeforeToday()); 
		  		}
				
				Integer paid_num=Calculators.uptoday_months(first_yyyy,first_mm);
				 LOG.info("Loan Amt="+loan_amt+",term="+term+",int_rate="+int_rate+",first_mm ="+first_mm +",first_yyyy="+first_yyyy); 
				Double remain_balance = Calculators.uptonow_remain_balance(loan_amt,paid_num,term,int_rate);
				
				Gson gson = new Gson();
			    JsonObject myObj = new JsonObject();
			    
				CompareLoanVo curLoan = new CompareLoanVo();
		       
		        curLoan.setUnpainLoanAmt(remain_balance);       
		        JsonElement curLoanElement = gson.toJsonTree(curLoan);
		        myObj.add("curLoan", curLoanElement);
		        
		        out.println(myObj.toString());        
		        out.close();
		        
		        LOG.info("Remain Balance="+remain_balance);
		        return null;
	}
	public ModelAndView userLogoutHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		request.getSession().setAttribute("LoginUserId", null);
		request.getSession().setAttribute("UserName", null);
		ModelAndView mode = new ModelAndView("PurchasePageOneQuote");
		QuoteForm quoteForm = new QuoteForm();
		mode.addObject("QuoteForm", quoteForm);
		return mode;
	}
	
	/**
	 *  Call Agent Process Quote Detail Information 
	 *   
	 */
	public ModelAndView userProcessQuoteHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		
		ModelAndView mode =new ModelAndView("UserProcessQuote");
			Utility.setRequest(request,response);
		
		Integer index = Utility.getIntegerParameter(Constant.CHOSEN_INDEX);
		/**
		 *  QuoteList was generated by UserReviewQuote, and Agent click review icon to get 
		 *  index of QuoteList 
		 */
		List list =(List) request.getSession().getAttribute("QuoteList");
		if (list==null) {
			mode.addObject("QuoteVo",null);
			return mode;
		}
		UserReviewQuoteVo quoteVo =(UserReviewQuoteVo) list.get(index);
		
		Double HomePrice;
		
		Double loanAmount = Utility.getDouble(quoteVo.getLoan_amount());
		
		Double monthDebt=Utility.getDouble(quoteVo.getMonthly_debt());
		
		if (monthDebt==null) monthDebt=0.0;
		
		Double income=Utility.getDouble(quoteVo.getNone_rental_income());
		
		Double rental = Utility.getDouble(quoteVo.getRental_income());
		
		if (rental==null) rental=0.0;
		if ("Purchase".equals(quoteVo.getLoan_type())) {
			HomePrice=Utility.getDouble(quoteVo.getPurchase_price());
		} else {
			HomePrice=Utility.getDouble(quoteVo.getEstimate_home_value());
		}
		Double DTI = 0.0;
        if ((income+rental)>0.0 )
		     DTI = (monthDebt/(income+rental))*100.00;
        Double LTV=((loanAmount/HomePrice)*100.00);
		
        mode.addObject("DTI", Utility.renderRate(DTI));
        mode.addObject("LTV",Utility.renderRate(LTV));
           
		LOG.info("quoteVo.loan_amount="+quoteVo.getLoan_amount());
		
		if ("replied".equals(quoteVo.getProcess_status()))  {
		
		Reply curReply = quoteDBService.findReplyByPrimaryKey(quoteVo.getQuote_id(),Constant.CURRENT_LOAN);
		Reply reply_loan1 = quoteDBService.findReplyByPrimaryKey(quoteVo.getQuote_id(),Constant.REPLY_LOAN1);
		Reply reply_loan2 = quoteDBService.findReplyByPrimaryKey(quoteVo.getQuote_id(),Constant.REPLY_LOAN2);
		Reply reply_loan3 = quoteDBService.findReplyByPrimaryKey(quoteVo.getQuote_id(),Constant.REPLY_LOAN3);
		Reply reply_loan4 = quoteDBService.findReplyByPrimaryKey(quoteVo.getQuote_id(),Constant.REPLY_LOAN4);
		
	
		mode.addObject("currentLoan", curReply);
		mode.addObject("replyLoan1", reply_loan1);
		mode.addObject("replyLoan2", reply_loan2);
		mode.addObject("replyLoan3", reply_loan3);
		mode.addObject("replyLoan4", reply_loan4);
		
	   LOG.info("CurrentLoan.PMT="+curReply.getMonthPayment()
	    		+",currentLoan Term="+curReply.getTerm() 
	    		+",currentLoan Int Rate="+curReply.getIntRateVo()); 
		LOG.info("ReplyLoan1.PMT="+reply_loan1.getMonthPayment()
				+",reply_loan1 Term="+reply_loan1.getTerm() 
				+",reply_loan1 Int Rate="+reply_loan1.getIntRateVo());  
		} 
		mode.addObject("QuoteVo", quoteVo);	
		
		String agentName = quoteVo.getAfirst_name()+" "+ quoteVo.getAlast_name();  
		mode.addObject("agentName", agentName);	
		request.getSession().setAttribute("SessionQuoteVo", quoteVo);
		return mode;
	}
	
	
 
	public ModelAndView getAgentPictureHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
			Utility.setRequest(request,response);
		
		/**
		 *  get Agant On Duty Picture and Information 
		 */
		byte[] blobAsBytes = LookupDataInitialServiceImpl.getBlobAsBytes();
		Agents agent = LookupDataInitialServiceImpl.getAgent();
		
		response.setContentType(agent.getPictureType());
	    response.setHeader("Cache-control", "no-cache, no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Expires", "-1");
	    response.setStatus(HttpServletResponse.SC_OK);
	    ServletOutputStream out = response.getOutputStream();
	    out.write(blobAsBytes);
	    blobAsBytes=null;
	    out.flush();
	    out.close();
	    
	    LOG.info("Write "+agent.getFirstName()+"'s picture!"+",picture Type is "+agent.getPictureType());
		return null;
	}
	
	public ModelAndView getAgentInfoHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		    response.setContentType("text/html");
	        response.setHeader("Cache-control", "no-cache, no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Expires", "-1");
	 
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.setHeader("Access-Control-Max-Age", "86400");
			Agents agent = LookupDataInitialServiceImpl.getAgent();
 
	    	 
	    	//agent.setQuotes(null);
	    	Gson gson = new Gson();
		    JsonObject myObj = new JsonObject();
		    JsonElement agentElement = gson.toJsonTree(agent);
	        myObj.add("agent", agentElement);
	    	PrintWriter out = response.getWriter();
	        out.println(myObj.toString());   
	        
	        out.flush();
	        out.close();
	    	 
	        return null;
	}
	
	public ModelAndView agentSignupHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		AgentForm agentForm = new AgentForm();
		Map<String,String> stateMap = LookupDataInitialServiceImpl.getStateMap();
		
		ModelAndView mode =new ModelAndView("AgentSignup");
		mode.addObject("agentForm",agentForm);
		mode.addObject("stateMap", stateMap);
		
		return mode;
	}
	
	public ModelAndView agentLoginHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		
		AgentForm agentForm = new AgentForm();		
 		
		ModelAndView mode =new ModelAndView("UserLogin");		
		
		mode.addObject("agentForm",agentForm);
		 
		return mode;
	} 
	
	public ModelAndView agentPasswordForgetHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		
		AgentForm agentForm = new AgentForm();		
	 	
		ModelAndView mode =new ModelAndView("AgentPasswordForget");		 
		
		mode.addObject("agentForm",agentForm);
		 
		return mode;
	} 
	
	public ModelAndView agentSignupProcessHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		// get form data
		Agents agents = modelAgents(request); 			
	
		
		//quoteDBService =(QuoteDBService) SpringFramework.getBean("QuoteDBService");
		
		AgentsDAO agentsDAO = quoteDBService.getAgentsDAO();	
		
		LOG.info("OnSubmitted...........");
		
	 	String agentAction = "signup";
		
		LOG.info("Email="+agents.getEmailAddress()+", Blob="+agents.getPictureContent().length()+",userAction="+agentAction);
	
		Integer userId =null;
		 		
			Agents agentObj = quoteDBService.findAgentByEmail(agents.getEmailAddress());
			 
			//Email is uniqueId 
			if (agentObj!=null) {					  
				agents.setAgentId(agentObj.getAgentId());
				LOG.info("exist agents Merging, agent="+agents.getAgentId());				
			
				LOG.info("agents Merging....., userId="+agents.getAgentId());
			
				agentsDAO.merge(agents);
			
			} else {
				LOG.info("agents Merging....., agent.getpictureContent()="+agents.getPictureContent());
				agentsDAO.save(agents);
			}
			userId = agents.getAgentId();
			
			request.getSession().setAttribute("userId", userId);
			
			LOG.info(" agents ID="+agents.getAgentId());			
		
		 
	
		ModelAndView mode = new ModelAndView("AgentSuccess"); 		
		// release memory
		agents.setPictureContent(null);
		
		mode.addObject("agentForm", agents);
		
		mode.addObject("agentAction", "signup");
		 
		return mode; 
		 
	}

	
	
	public ModelAndView agentLoginProcessHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 		
			Utility.setRequest(request,response);
		String email = Utility.getStringParameter(Constant.EMAIL_ADDRESS);
		String password = Utility.getStringParameter(Constant.PASSWORD);
		ModelAndView mode=null;
		Integer userId = userReviewService.findUserIdByEmailPassword(email, password);
		 
		String userName=null;
		if (userId!=null) {
			 		// fetch list from Database
			List<UserReviewQuoteVo> list = userReviewService.getUserQuoteInquire(userId);
			UserReviewQuoteVo currentVo  = list.get(0);
			userName = currentVo.getAfirst_name()+" "+ currentVo.getAlast_name();  
			//mode.addObject("QuoteList", list);
			request.getSession().setAttribute("QuoteList", list);
			
			for (UserReviewQuoteVo vo:list) vo=null;
			
		   mode =new ModelAndView("UserReviewQuote");
		} else if (userId==null) {
		
		   mode =new ModelAndView("UserLogin");
		   
		}
		
		request.getSession().setAttribute("LoginUserId", userId);
		request.getSession().setAttribute("UserName", userName);
		return mode;
		
	}

	
	
	public ModelAndView agentPasswordForgetProcessHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 		
			
		ModelAndView mode =new ModelAndView("AgentSucess");
		mode.addObject("agentAction", "login");
		
		return mode;
		
	}
	
	public QuoteDBService getQuoteDBService() {
		return quoteDBService;
	}

	public void setQuoteDBService(QuoteDBService quoteDBService) {
		this.quoteDBService = quoteDBService;
	}
	
	public static Agents modelAgents(HttpServletRequest request) {
		Agents agent = new Agents();
		try {
	     agent.setFirstName(ServletRequestUtils.getStringParameter(request,"firstName"));			
	     agent.setLastName(	ServletRequestUtils.getStringParameter(request,"lastName" ));			
	     agent.setEmailAddress(	ServletRequestUtils.getStringParameter(request,"emailAddress"));			
	     String digestPassword = ServletRequestUtils.getStringParameter(request,"password");
	     if (digestPassword!=null && digestPassword.trim().length()>0) {
	    	 digestPassword = ui.convertToSHA256(digestPassword);
	    	 agent.setPassword(	digestPassword);	
	     }
	     				
	     agent.setCompanyName(	ServletRequestUtils.getStringParameter(request,"companyName"));			
	     agent.setWorkPhone(	ServletRequestUtils.getStringParameter(request,"workPhone"));			
	     agent.setCellPhone(	ServletRequestUtils.getStringParameter(request,"cellPhone"));			
	     agent.setAddress(ServletRequestUtils.getStringParameter(request,"address("));				
	     agent.setCity(		ServletRequestUtils.getStringParameter(request,"city"));		
	     agent.setState(	ServletRequestUtils.getStringParameter(request,"state"));		
	     agent.setZipCode(	ServletRequestUtils.getStringParameter(request,"zipCode"));		
	     agent.setDescription(ServletRequestUtils.getStringParameter(request,"description"));			
	     agent.setLicenseNo(ServletRequestUtils.getStringParameter(request,"licenseNo"));			
	     agent.setLicenseEligibleState(	ServletRequestUtils.getStringParameter(request,"licenseEligibleState"));
	     agent.setModifiedDate(Utility.getCurrentTimeStamp());
	     
	     MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	     MultipartFile multipartFile = multipartRequest.getFile(Constant.HTML_CONTENT_PICTURE);
	     LOG.info("pictureContent FileName="+multipartFile.getOriginalFilename()+",multipartFile.getBytes().size()="+multipartFile.getBytes().length);
	    // FileDocument doc =Utility.uploadFile(multipartFile);	  
	     
	     
			agent.setPictureType((multipartFile.getContentType()));	
			
		    Blob  content =Hibernate.createBlob(multipartFile.getInputStream());
			LOG.info("Blob content size="+content.length());
			agent.setPictureContent(content);
			agent.setPictureFilename(multipartFile.getOriginalFilename());
		   
	     
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
		 
		
		return agent;
	}
}
