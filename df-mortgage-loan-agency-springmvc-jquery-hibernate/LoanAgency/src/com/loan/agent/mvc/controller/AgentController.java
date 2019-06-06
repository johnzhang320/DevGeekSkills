package com.loan.agent.mvc.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.hibernate.Session;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
 
 
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
 
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.frontend.encrypt.utils.KeyPairManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.loan.agent.calculators.Calculators;
import com.loan.agent.calculators.LoanBuilder;
import com.loan.agent.calculators.LoanDirector;
import com.loan.agent.calculators.LoanParam;
import com.loan.agent.calculators.LoanProduct;
import com.loan.agent.calculators.PurchaseCurrentLoanBuilder;
import com.loan.agent.calculators.PurchaseNewLoanBuilder;
import com.loan.agent.calculators.RefinanceCurrentLoanBuilder;
import com.loan.agent.calculators.RefinanceNewLoanBuilder;
import com.loan.agent.calculators.vo.AffordableLineVo;
import com.loan.agent.calculators.vo.AgentIdVo;
import com.loan.agent.calculators.vo.AgentReviewQuoteVo;
import com.loan.agent.calculators.vo.CompareLoanVo;
import com.loan.agent.calculators.vo.QuoteReplyVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.Niches;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.dao.Quote;
import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.RateSheetDAO;
import com.loan.agent.dao.Reply;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.NicheForm;
import com.loan.agent.mvc.formbean.QuoteForm;
import com.loan.agent.mvc.formbean.RateSheetForm;
 
import com.loan.agent.mvc.utils.FileDocument;
import com.loan.agent.mvc.utils.Files;
import com.loan.agent.mvc.utils.GenCertPicCode;
import com.loan.agent.mvc.utils.ProcessDownloadFile;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.AgentAdService;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.LookupDataInitialService;
import com.loan.agent.services.QuoteDBService;
import com.loan.agent.services.impl.AgentAdServiceImpl;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;
import com.loan.mvc.validator.AgentValidator;
import com.sun.org.apache.bcel.internal.Constants;
 

public class AgentController extends MultiActionController  {
	
	static final Logger LOG = Logger.getLogger(AgentController.class);
	 
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService;
	
	@Autowired
	@Resource(name="AgentReviewService")
	private AgentReviewService agentReviewService;
	
	
	@Autowired
	@Resource(name="LookupDataInitialService")
	private LookupDataInitialService lookupDataInitialService;
	
	
	
	
	public AgentReviewService getAgentReviewService() {
		return agentReviewService;
	}
	public void setAgentReviewService(AgentReviewService agentReviewService) {
		this.agentReviewService = agentReviewService;
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
	public ModelAndView agentReviewQuoteHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		ModelAndView mode =null;
		LOG.info("agentReviewQuoteHandler begin!");
		Utility.setRequest(request,response);
		Integer agentId =(Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID); 
		
		request.getSession().setAttribute(Constant.AGENT_LOGIN_FOR, Constant.AGENT_REVIEW_QUOTE);
		
		LOG.info("AgentIdStr="+agentId);
		if (agentId==null) {
			AgentForm agentForm = new AgentForm();
			mode = new ModelAndView("AgentLogin");
			mode.addObject("agentForm", agentForm);
			return mode;
			
		}
		
			 	 
		String agentName=null;
		if (agentId!=null) {
			// fetch list from Database
			List<AgentReviewQuoteVo> list = agentReviewService.getAgentQuoteInquire(agentId);
			if (list==null || list.size()==0) {
				LOG.info("List<AgentReviewQuoteVo> list =null");
				return null;
			}
			
			AgentReviewQuoteVo currentVo  = list.get(0);
			agentName = currentVo.getAfirst_name()+" "+ currentVo.getAlast_name();  
			//mode.addObject("QuoteList", list);
			request.getSession().setAttribute("QuoteList", list);
			
			for (AgentReviewQuoteVo vo:list) vo=null;
			
		   mode =new ModelAndView("AgentReviewQuote");
		 
		   
		}
		
		    
		request.getSession().setAttribute(Constant.LOGIN_AGENT_ID, agentId);
		request.getSession().setAttribute("AgentName", agentName);
		
		LOG.info("agentReviewQuoteHandler End!");
		return mode;
	}
	
	public ModelAndView agentEnterRateHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		ModelAndView mode =null;
		
			Utility.setRequest(request,response);		
		
		Integer agentId =(Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID); 
		
		request.getSession().setAttribute(Constant.AGENT_LOGIN_FOR, Constant.AGENT_ENTER_RATE_SHEET);
		
		LOG.info("Current Login AgentId="+agentId);
		
		if (agentId==null) {
			AgentForm agentForm = new AgentForm();
			mode = new ModelAndView("AgentLogin");
			mode.addObject("agentForm", agentForm);
			return mode;
			
		}

		String agentName=null;
		if (agentId!=null) {
			 		// fetch list from Database	 
		   RateSheetForm rateForm = new RateSheetForm();
		   RateSheetDAO rateSheetDao= quoteDBService.getRateSheetDAO();
		   List<RateSheet> rateSheetList = rateSheetDao.findByAgentId(agentId);
		   if (rateSheetList!=null && rateSheetList.size()>0) {
			   rateForm.renderRateSheet(rateSheetList);
		   }
		   /**
		    *   Must be redirect to rate sheet form instead of forward to ratesheetform
		    *   If forward to rate form, when we submit this rate form, still submit to parent form
		    */
		   mode =new ModelAndView(Constant.AGENT_ENTER_RATE_SHEET);		 
		   mode.addObject(Constant.RATE_SHEET_COMMAND_NAME, rateForm);
		}
		
		request.getSession().setAttribute(Constant.LOGIN_AGENT_ID, agentId);
		 
		
		
		return mode;
	}
   
	public ModelAndView agentEmailMarketingHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		ModelAndView mode =null;
		
			Utility.setRequest(request,response);		
		
		Integer agentId =(Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID); 
		
		request.getSession().setAttribute(Constant.AGENT_LOGIN_FOR, Constant.AGENT_EMAIL_MARKETING);
		
		LOG.info("AgentController.agentEmailMarketingHandler() Current Login AgentId="+agentId);
		
		if (agentId==null) {
			AgentForm agentForm = new AgentForm();
			mode = new ModelAndView("AgentLogin");
			mode.addObject("agentForm", agentForm);
			return mode;
			
		} 
       /**
        *  Reset login agent id
        */
		request.getSession().setAttribute(Constant.LOGIN_AGENT_ID,agentId);
		 
	 
	 
			 
		LOG.info("Forward to emailMarketing !");
		    /*   Must be redirect to agent email market page
		    *   
		    */
		 
		 mode = new ModelAndView(Constant.AGENT_EMAIL_MARKETING_REDIRECT); 
		
		 mode.addObject(Constant.LOGIN_AGENT_ID,agentId);
		 
		return mode;
	}
	public ModelAndView agentRelpyQuoteLoginHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		ModelAndView mode =null;
		
			Utility.setRequest(request,response);		
		
		Integer agentId =(Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID); 
		
		request.getSession().setAttribute(Constant.AGENT_LOGIN_FOR, Constant.AGENT_RELY_QUOTE_LOGIN);
		
		LOG.info("AgentController.agentRelpyQuoteLoginHandler() Current Login AgentId="+agentId);
		
		if (agentId==null) {
			AgentForm agentForm = new AgentForm();
			mode = new ModelAndView("AgentLogin");
			mode.addObject("agentForm", agentForm);
			return mode;
			
		} 
       /**
        *  Reset login agent id
        */
		request.getSession().setAttribute(Constant.LOGIN_AGENT_ID,agentId);
		 
	 
	 
			 
		LOG.info("Forward to agentRelpyQuoteLogin !");
		    /*   Must be redirect to agent email market page
		    *   
		    */
		 
		 mode = new ModelAndView(Constant.AGENT_RELY_QUOTE_LOGIN_REDIRECT); 
		
		 mode.addObject(Constant.LOGIN_AGENT_ID,agentId);
		 
		return mode;
	}
	
	public ModelAndView agentNicheProgramHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		ModelAndView mode =null;
		
			Utility.setRequest(request,response);		
		
		Integer agentId =(Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID); 
		
		request.getSession().setAttribute(Constant.AGENT_LOGIN_FOR, Constant.AGENT_NICHE_PROGRAM);
		
		LOG.info("AgentController.agentNicheProgramHandler() Current Login AgentId="+agentId);
		
		if (agentId==null) {
			AgentForm agentForm = new AgentForm();
			mode = new ModelAndView("AgentLogin");
			mode.addObject("agentForm", agentForm);
			return mode;
			
		} 
       /**
        *  Reset login agent id
        */
		request.getSession().setAttribute(Constant.LOGIN_AGENT_ID,agentId);		 
	 
	 
			 
		LOG.info("Forward to emailMarketing !");
		    /*   Must be redirect to agent email market page
		    *   
		    */
		 
		 mode = new ModelAndView(Constant.AGENT_NICHE_PROGRAM_REDIRECT); 
		
		 mode.addObject(Constant.LOGIN_AGENT_ID,agentId);
		 
		return mode;
	}	
	public ModelAndView rateSaveSuccessHandler(  HttpServletRequest request,
			HttpServletResponse response) throws Exception { 
		    
		    ModelAndView mode =new ModelAndView(Constant.RATE_SHEET_SAVE_SUCCESS);		 
	        return mode; 
	}
	public ModelAndView downLoadAppFormHandler(  HttpServletRequest request,
			HttpServletResponse response) throws Exception { 
			Integer agentId = (Integer) request.getSession().getAttribute(Constant.AGENT_ID);
			if (agentId==null) {
				return null;
			}
			//Integer agentId = 10007;  // testing
			Agents agents = quoteDBService.getAgentsDAO().findById(agentId); 
			
			String appParent = SysPath.getInstance().getLoanApplicationFormsPath();
			 
			String fileExt =Utility.getFileExtention(agents.getApplicationFormFilename());
			
			String orgPathFile =SysPath.getInstance().getLoanApplicationFormsPath()+Constant.PREFIX_APP_FORM_FILE+agentId.toString()+fileExt;					
			
			File file = new File(orgPathFile);
			
			if (!file.exists()) {
				LOG.info("Not Found File :"+orgPathFile);
			}
			
		    ProcessDownloadFile.downloadFile(response, agents.getAppFormType(), orgPathFile, agents.getApplicationFormFilename());    
		    
		    ModelAndView mode =new ModelAndView(Constant.RATE_SHEET_SAVE_SUCCESS);		 
	        return null; 
	}
	
	
	
	public ModelAndView getRefiRemainBalanceHandler(  HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		        PrintWriter out= Utility.SetPostResponseContent(response);		  
		        
		        AgentReviewQuoteVo quoteVo =(AgentReviewQuoteVo) request.getSession().getAttribute("SessionQuoteVo");
		        
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
	/**
	 *  Call Agent Process Quote Detail Information 
	 *   
	 */
	public ModelAndView agentProcessQuoteHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		
		ModelAndView mode =new ModelAndView("AgentProcessQuote");
			Utility.setRequest(request,response);
		
		Integer index = Utility.getIntegerParameter(Constant.CHOSEN_INDEX);
		/**
		 *  QuoteList was generated by AgentReviewQuote, and Agent click review icon to get 
		 *  index of QuoteList 
		 */
		List list =(List) request.getSession().getAttribute("QuoteList");
		if (list==null) {
			mode.addObject("QuoteVo",null);
			return mode;
		}
		AgentReviewQuoteVo quoteVo =(AgentReviewQuoteVo) list.get(index);
		
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
		
		quoteVo.setAdvice_note(
"                Loan Agent Professional Notes: \n" +
"(1) Loan Approval Financial Conditions\n"	+	
"Regular Lender Loan Approval Information LTV: Loan to Home Value (%) "+
"\nDTI: Debt To Income (%). "+
"\nYour DTI is : 32.836%    "+
"\nConfirming Loan Information:	"+
"\nUp to $417,000.00, Approval DTI Up to 50%, LTV:80% "+
"\nHigh Balance Loan:	"+
"\n$418,000.00 to $625,000.00, Approval DTI Up to 45%,LTV: Fixed:80% ,ARM:70%  "+
"\nJumbor Loan:"+
"\nOver $625,000.00, Approval DTI:35% or 40% ,LTV:70%  \n"+				
"\n(2) Financial documents you should prepare for loan approval and process\n" +
"\n In order to provide a speedy and successful funding, please complete the "+
"enclosed loan package and provide the following documents: "+								
"\n * Current 2 years W-2 form "+ 					
"\n * Current 1 month pay stub"+ 					
"\n * Current 2 months bank/brokerage statements (all pages) "+		
"\n * Current 2 years federal tax returns (all pages)"+ 			
"\n   (self-employed borrower or borrower with more than 2 properties) "+	
"\n * Year-to-date profit and loss statement (self-employed borrower) "+		
"\n * Rental agreement 	"+					
"\n * Green Card (front and back)"+ 					
"\n * Current mortgage statement "+					
"\n * Current homeowner's insurance policy "+				
"\n * Landlord's name, address and telephone for rental rating "+		
"\n * Gift letter, donor's bank statement, copy of donor's check or wire and deposit receipt"+
"\n") ;
		
		mode.addObject("QuoteVo", quoteVo);	
		request.getSession().setAttribute("SessionQuoteVo", quoteVo);
		return mode;
	}
 
	public ModelAndView agentProcessQuoteRefiOnLineHandler(  HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		        PrintWriter out= Utility.SetPostResponseContent(response);
				 
		        AgentReviewQuoteVo quoteVo =(AgentReviewQuoteVo) request.getSession().getAttribute("SessionQuoteVo");
		        LOG.info("quoteVo="+quoteVo);
		        if (quoteVo==null) {
		        	LOG.error("SessionQuoteVo Session expired");
		        	return null;
		        }
				// using ServletRequestUtils to obtain form data which never use binding command class
					Utility.setRequest(request,response);
				Double loan_amt =Utility.getDouble(quoteVo.getFirst_loan_balance());	
				
				Double [] new_loan_amt = new Double[4];
				new_loan_amt[0] = Utility.getDoubleParameter("new_loan_amt1");
				new_loan_amt[1] = Utility.getDoubleParameter("new_loan_amt2");
				new_loan_amt[2] = Utility.getDoubleParameter("new_loan_amt3");
				new_loan_amt[3] = Utility.getDoubleParameter("new_loan_amt4");
				
				 
				Double term =Utility.DescriptionToDoubleTerm(quoteVo.getFirst_loan_term());
				
				Double [] new_term = new Double[4]; 
				new_term[0] = Utility.getDoubleParameter("term1");
				new_term[1] = Utility.getDoubleParameter("term2");
				new_term[2] = Utility.getDoubleParameter("term3");
				new_term[3] = Utility.getDoubleParameter("term4");
				
				Integer int_term =Utility.DescriptionToIntegerTerm(quoteVo.getFirst_loan_term());
					
				Integer[] new_int_term = new Integer[4];
				new_int_term[0] = Utility.getIntegerParameter("term1");
				new_int_term[1]= Utility.getIntegerParameter("term2");
				new_int_term[2]= Utility.getIntegerParameter("term3");
				new_int_term[3]= Utility.getIntegerParameter("term4");

				Double int_rate = Utility.getDouble(quoteVo.getFirst_loan_rate());
				Double [] new_int_rate = new Double[4]; 
				
				new_int_rate[0] = Utility.getDoubleParameter("int_rate1");
				new_int_rate[1] = Utility.getDoubleParameter("int_rate2");
				new_int_rate[2] = Utility.getDoubleParameter("int_rate3"); 
				new_int_rate[3] = Utility.getDoubleParameter("int_rate4"); 

				 
				Double [] new_closing_cost  = new Double[4]; 
				new_closing_cost[0] = Utility.getDoubleParameter("closing_cost1");
				new_closing_cost[1] = Utility.getDoubleParameter("closing_cost2");
				new_closing_cost[2] = Utility.getDoubleParameter("closing_cost3"); 
				new_closing_cost[3] = Utility.getDoubleParameter("closing_cost4"); 
			
				
				Double [] new_point  = new Double[4]; 
				new_point[0] = Utility.getDoubleParameter("point1");
				new_point[1] = Utility.getDoubleParameter("point2");
				new_point[2] = Utility.getDoubleParameter("point3"); 
				new_point[3] = Utility.getDoubleParameter("point4"); 
 				
				
				Integer first_mm =0;         //YPMT_mm));
				Integer first_yyyy =0; 
				
				if (quoteVo.getFirst_date() !=null) {
		  			first_mm =Utility.getMonth(quoteVo.getFirst_date());         //YPMT_mm));
		  			first_yyyy =Utility.getYear(quoteVo.getFirst_date()); 
		  		} else {
		  			first_mm =Utility.getMonth(Utility.getHalfYearBeforeToday());         //YPMT_mm));
		  			first_yyyy =Utility.getYear(Utility.getHalfYearBeforeToday()); 
		  		}
				
				Integer quoteId = quoteVo.getQuote_id(); 
				Integer agentId = quoteVo.getAgent_id(); 
				Integer userId = quoteVo.getUser_id(); 

				/**
				 *  Builder Json Object my builder design pattern
				 */
				Gson gson = new Gson();
			    JsonObject myObj = new JsonObject();
			    
				LoanParam curPM = new LoanParam(
						loan_amt, 
						int_rate, 
						term,
						int_term,
						0.0,    // closing_fee
						0.0,    // point
						first_mm, 
						first_yyyy, 
						null,   //LoanProduct currentLoan
						null    //LoanParam currentParam
				);
				LoanBuilder curBuilder = new RefinanceCurrentLoanBuilder(curPM);
				LoanProduct curLoanProd = LoanDirector.getInstance().buildProduct(curBuilder);				
				JsonElement curLoanElement = gson.toJsonTree(curLoanProd.getCompareLoanVo());
			    myObj.add("curLoan", curLoanElement);
			    String adviceNote = Utility.getStringParameter(Constant.ADVICE_NOTE);
			    LOG.info("adviceNote ="+adviceNote);
			        /**
			         *  Save Current Loan as a Reply
			         */
			    quoteDBService.SaveReply(quoteId, 
			    		Constant.CURRENT_LOAN, 
			    		curLoanProd.getCompareLoanVo(), 
			    		loan_amt, 
			    		adviceNote,
			    		agentId, 
			    		userId
			    	);
 
			    LoanParam pm =null;
			    for(int i=0;i<Constant.COMP_LOAN_NUM;i++) {
			    	    pm = new LoanParam(
						new_loan_amt[i], 
						new_int_rate[i], 
						new_term[i],
						new_int_term[i],
						new_closing_cost[i],    // closing_fee
						new_point[i],     // point
						first_mm,  
						first_yyyy, 
						curLoanProd,    //LoanProduct currentLoan
						curPM      	    //LoanParam currentParam
			    	);
			    	LoanBuilder newBuilder = new RefinanceNewLoanBuilder(pm);
			    	LoanProduct newLoanProd = LoanDirector.getInstance().buildProduct(newBuilder);
			    	JsonElement newLoanElement = gson.toJsonTree(newLoanProd.getCompareLoanVo());
			    	String loanName = "Loan"+(new Integer(i+1)).toString();
				    myObj.add(loanName, newLoanElement);
				    /**
				    *  Save new Loan as an reply
			         */
			        quoteDBService.SaveReply(quoteId, i+1, newLoanProd.getCompareLoanVo());
				   
			    }		 
	 		        
		        myObj.addProperty("remain_balance", Utility.renderDollar(curLoanProd.getRemain_balance()));
		        
		       
		        /**
		         *  Merge Quote Table with process_status = replied
		         */
		        
		        Quote quote = quoteDBService.getQuoteDAO().findById(quoteId);
		        
		        quote.setProcessStatus("replied");
		        quoteDBService.getQuoteDAO().merge(quote);
		        
		        
		        LOG.info("Loan Quote was saved ! ");
		        
		        out.println(myObj.toString());        
		        out.close();
		        
				return null;
				
			}
	public ModelAndView agentProcessQuotePurchOnLineHandler(  HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
	         	PrintWriter out= Utility.SetPostResponseContent(response);
		        
		        AgentReviewQuoteVo quoteVo =(AgentReviewQuoteVo) request.getSession().getAttribute("SessionQuoteVo");
					 
				// using ServletRequestUtils to obtain form data which never use binding command class
					Utility.setRequest(request,response);
				Double loan_amt =Utility.getDouble(quoteVo.getLoan_amount());	
				Double [] new_loan_amt = new Double[4];
				new_loan_amt[0] = Utility.getDoubleParameter("new_loan_amt1");
				new_loan_amt[1] = Utility.getDoubleParameter("new_loan_amt2");
				new_loan_amt[2] = Utility.getDoubleParameter("new_loan_amt3");
				new_loan_amt[3] = Utility.getDoubleParameter("new_loan_amt4");
					
	Double term =Utility.DescriptionToDoubleTerm(quoteVo.getFirst_loan_term());
				
				Double [] new_term = new Double[4]; 
				new_term[0] = Utility.getDoubleParameter("term1");
				new_term[1] = Utility.getDoubleParameter("term2");
				new_term[2] = Utility.getDoubleParameter("term3");
				new_term[3] = Utility.getDoubleParameter("term4");
				
				Integer int_term =Utility.DescriptionToIntegerTerm(quoteVo.getFirst_loan_term());
					
				Integer[] new_int_term = new Integer[4];
				new_int_term[0] = Utility.getIntegerParameter("term1");
				new_int_term[1]= Utility.getIntegerParameter("term2");
				new_int_term[2]= Utility.getIntegerParameter("term3");
				new_int_term[3]= Utility.getIntegerParameter("term4");

				Double int_rate = Utility.getDouble(quoteVo.getFirst_loan_rate());
				Double [] new_int_rate = new Double[4]; 
				
				new_int_rate[0] = Utility.getDoubleParameter("int_rate1");
				new_int_rate[1] = Utility.getDoubleParameter("int_rate2");
				new_int_rate[2] = Utility.getDoubleParameter("int_rate3"); 
				new_int_rate[3] = Utility.getDoubleParameter("int_rate4"); 

				 
				Double [] new_closing_cost  = new Double[4]; 
				new_closing_cost[0] = Utility.getDoubleParameter("closing_cost1");
				new_closing_cost[1] = Utility.getDoubleParameter("closing_cost2");
				new_closing_cost[2] = Utility.getDoubleParameter("closing_cost3"); 
				new_closing_cost[3] = Utility.getDoubleParameter("closing_cost4"); 
			
				
				Double [] new_point  = new Double[4]; 
				new_point[0] = Utility.getDoubleParameter("point1");
				new_point[1] = Utility.getDoubleParameter("point2");
				new_point[2] = Utility.getDoubleParameter("point3"); 
				new_point[3] = Utility.getDoubleParameter("point4"); 
				/**
				 *  set 2 month after today as purchase pay date (one is closing month, another is fund month)
				 */
				Calendar date = Calendar.getInstance();
		  		Calendar cldr;
		  		SimpleDateFormat dateformatter = new SimpleDateFormat ("MM-dd-yyyy");

				 cldr = (Calendar) date.clone();
		         cldr.add(Calendar.MONTH, + 2);
		         
		         String firstDate = dateformatter.format(cldr.getTime());
		      
				
				
				Integer first_mm =Utility.getMonth(firstDate);         //YPMT_mm));
				Integer first_yyyy =Utility.getYear(firstDate);         //YPMT_yyyy));
				
				Integer quoteId = quoteVo.getQuote_id(); 
				Integer agentId = quoteVo.getAgent_id(); 
				Integer userId = quoteVo.getUser_id(); 
 		
				
				/**
				 *  Builder Json Object my builder design pattern
				 */
				Gson gson = new Gson();
			    JsonObject myObj = new JsonObject();
			    
			    CompareLoanVo curLoan = new CompareLoanVo();
		        curLoan.setMonthPayment(0.0);
		        curLoan.setTimesAlreadyPaid(0);
		        curLoan.setRemainBalance(0.0);
		        curLoan.setRemainTimes(0);
		        curLoan.setPaidInterest(0.0);
		        curLoan.setUnpaidInterets(0.0);
		        curLoan.setUnpainLoanAmt(0.0);       
		        JsonElement curLoanElement0 = gson.toJsonTree(curLoan);
		        myObj.add("curLoan", curLoanElement0);
			    
			    String adviceNote = Utility.getStringParameter(Constant.ADVICE_NOTE);
			        /**
			         *  Save Current Loan as a Reply
			         */
			    quoteDBService.SaveReply(quoteId, 
			    		Constant.CURRENT_LOAN, 
			    		curLoan, 
			    		loan_amt, 
			    		adviceNote,
			    		agentId, 
			    		userId
			    	);
 
				LoanParam curPM = new LoanParam(
						new_loan_amt[0], 
						new_int_rate[0], 
						new_term[0],
						new_int_term[0],
						new_closing_cost[0],    // closing_fee
						new_point[0],    // point
						first_mm, 
						first_yyyy, 
						null,   //LoanProduct currentLoan
						null    //LoanParam currentParam
				);
				LoanBuilder curBuilder = new PurchaseCurrentLoanBuilder(curPM);
				LoanProduct curLoanProd = LoanDirector.getInstance().buildProduct(curBuilder);				
				JsonElement curLoanElement = gson.toJsonTree(curLoanProd.getCompareLoanVo());
			    myObj.add("Loan1", curLoanElement);
			  
			        /**
			         *  Save Current Loan as a Reply
			         */
			    quoteDBService.SaveReply(quoteId, 
			    		Constant.CURRENT_LOAN, 
			    		curLoanProd.getCompareLoanVo(), 
			    		loan_amt, 
			    		adviceNote,
			    		agentId, 
			    		userId
			    	);
 
			    LoanParam pm =null;
			    for(int i=0;i<Constant.COMP_LOAN_NUM-1;i++) {
			    	    pm = new LoanParam(
						new_loan_amt[i], 
						new_int_rate[i], 
						new_term[i],
						new_int_term[i],
						new_closing_cost[i],    // closing_fee
						new_point[i],     // point
						first_mm,  
						first_yyyy, 
						curLoanProd,    //LoanProduct currentLoan
						curPM      	    //LoanParam currentParam
			    	);
			    	LoanBuilder newBuilder = new PurchaseNewLoanBuilder(pm);
			    	LoanProduct newLoanProd = LoanDirector.getInstance().buildProduct(newBuilder);
			    	JsonElement newLoanElement = gson.toJsonTree(newLoanProd.getCompareLoanVo());
			    	String loanName = "Loan"+(new Integer(i+1)).toString();
				    myObj.add(loanName, newLoanElement);
				    /**
				    *  Save new Loan as an reply
			         */
			        quoteDBService.SaveReply(quoteId, i+1, newLoanProd.getCompareLoanVo());
				   
			    }		 
	 		        
		        myObj.addProperty("remain_balance", Utility.renderDollar(curLoanProd.getRemain_balance()));	
		 
			       
		        /**
		         *  Merge Quote Table with process_status = replied
		         */
		        Quote quote = quoteDBService.getQuoteDAO().findById(quoteId);
		        
		        quote.setProcessStatus("replied");
		        quoteDBService.getQuoteDAO().merge(quote);
		        
		        LOG.info("Loan Quote was saved ! reply.");
		        
		        out.println(myObj.toString());        
		        out.close();
		        
				return null;
				
			}
	

	public ModelAndView getAgentPictureHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		
			
		Log.info("getAgentPictureHandler() begin");
		
		Utility.getInstance().AgentIdProcessor(request);
		/**
		 *  get Agant On Duty Picture and Information 
		 */
	
		Integer agentId =(Integer) request.getSession().getAttribute(Constant.AGENT_ID); 
		LOG.info("AgentController.getAgentPictureHandler() AgentId="+agentId);
		
		Agents agents =null;
		if (agentId==null) { 
			agents = LookupDataInitialServiceImpl.getAgent();
			agentId = agents.getAgentId();
		} else {		
		 
		    agents = quoteDBService.getAgentsDAO().findById(agentId);
		}
		 
		String agentPath = SysPath.getInstance().getAgentPicturePath();
		
		byte[] blobAsBytes = ProcessUploadFile.getFileByteArrayByAgent(agentId, agents.getPictureFilename(), Constant.PREFIX_AGENT_PICTURE_FILE, agentPath);
				
		if (null==blobAsBytes) {
			LOG.info("blobAsBytes=null");
			return null;
		} else {
			LOG.info("blobAsBytes.length="+blobAsBytes.length);
		}
		response.setContentType(agents.getPictureType());
	    response.setHeader("Cache-control", "no-cache, no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Expires", "-1");
	    response.setStatus(HttpServletResponse.SC_OK);
	    ServletOutputStream out = response.getOutputStream();
	    out.write(blobAsBytes);
	    blobAsBytes=null;
	    out.flush();
	    out.close();
	    Log.info("getAgentPictureHandler() end");
	 //   LOG.info("Write "+agent.getFirstName()+"'s picture!"+",picture Type is "+agent.getPictureType());
		
		return null;
	}
	
	 
	public ModelAndView getLoginAgentPictureHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
			Utility.setRequest(request,response);
			/**
			 *  get Agant On Duty Picture and Information 
			 */
		 //   LOG.info("getLoginAgentPictureHandler() begin");
			Integer agentId =(Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID); 
		 
			Agents agents =null;
			if (agentId==null) { 
				agentId =(Integer) request.getSession().getAttribute(Constant.PARAM_AGENT_ID); 
				if (agentId==null) {
					return null;
				}
			}  
			
			agents = quoteDBService.getAgentsDAO().findById(agentId); 
			
			String agentPath = SysPath.getInstance().getAgentPicturePath();
			
			byte[] blobAsBytes = ProcessUploadFile.getFileByteArrayByAgent(agentId, agents.getPictureFilename(), Constant.PREFIX_AGENT_PICTURE_FILE, agentPath);

			if (blobAsBytes==null) {
			//	LOG.info("blobAsBytes=null");
				return null;
			}  
			response.setContentType(agents.getPictureType());
		    response.setHeader("Cache-control", "no-cache, no-store");
		    response.setHeader("Pragma", "no-cache");
		    response.setHeader("Expires", "-1");
		    response.setStatus(HttpServletResponse.SC_OK);
		    ServletOutputStream out = response.getOutputStream();
		    out.write(blobAsBytes);
		    blobAsBytes=null;
		    out.flush();
		    out.close();
		    
		 //   LOG.info("Write "+agent.getFirstName()+"'s picture!"+",picture Type is "+agent.getPictureType());
		   // LOG.info("getLoginAgentPictureHandler() end");
			return null;		
		}
	public ModelAndView getApplicationFormByteHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		
			
		//Log.info("getApplicationFormByteHandler() begin");
		
		Utility.getInstance().AgentIdProcessor(request);
		/**
		 *  get Agant On Duty Picture and Information 
		 */
	
		Integer agentId =(Integer) request.getSession().getAttribute(Constant.AGENT_ID); 
		//LOG.info("AgentController.getApplicationFormByteHandler() AgentId="+agentId);
		
		Agents agents =null;
		if (agentId==null) { 
			agents = LookupDataInitialServiceImpl.getAgent();
			agentId = agents.getAgentId();
		} else {		
		 
		    agents = quoteDBService.getAgentsDAO().findById(agentId);
		}
		String appParent = SysPath.getInstance().getLoanApplicationFormsPath();
		 
		String fileExt =Utility.getFileExtention(agents.getApplicationFormFilename());
		
		String orgPathFile =SysPath.getInstance().getLoanApplicationFormsPath()+Constant.PREFIX_APP_FORM_FILE+agentId.toString()+fileExt;					
		
		File file = new File(orgPathFile);
		request.getSession().setAttribute(Constant.APPLICATION_FORM_PATH,null);
		
		if (!file.exists()) {
			LOG.info("Not Found File :"+orgPathFile);
			request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,"Application Form not found!");
			return null;
		} else {
			LOG.info("Application Form Path="+orgPathFile);
			request.getSession().setAttribute(Constant.APPLICATION_FORM_PATH,orgPathFile);
		}
		 
		
		byte[] blobAsBytes = ProcessUploadFile.getImageByteArrayByFile(orgPathFile);
				
		if (null==blobAsBytes) {
			LOG.info("blobAsBytes=null");
			return null;
		} else {
			LOG.info("blobAsBytes.length="+blobAsBytes.length);
		}
		response.setContentType("application/pdf");
	    response.setHeader("Cache-control", "no-cache, no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Expires", "-1");
	    response.setStatus(HttpServletResponse.SC_OK);
	    ServletOutputStream out = response.getOutputStream();
	    out.write(blobAsBytes);
	    blobAsBytes=null;
	    out.flush();
	    out.close();
	    Log.info("getApplicationFormByteHandler() end");
	 //   LOG.info("Write "+agent.getFirstName()+"'s picture!"+",picture Type is "+agent.getPictureType());
		
		return null;
	}
	
	public ModelAndView getAgentInfoHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		    PrintWriter out= Utility.SetPostResponseContent(response);
		    
		    Integer agentId = (Integer) request.getSession().getAttribute(Constant.AGENT_ID);
			Agents agent =null;
			if (agentId==null) { 
				agent = LookupDataInitialServiceImpl.getAgent();
			} else {
				agent = quoteDBService.getAgentsDAO().findById(agentId);
				
			}
		    
			//LOG.info("getAgentInfoHandler: agentId ="+agent.getAgentId());
           
	    	 
	    	//agent.setQuotes(null);
	    	Gson gson = new Gson();
		    JsonObject myObj = new JsonObject();
		    JsonElement agentElement = gson.toJsonTree(agent);
	        myObj.add("agent", agentElement);
	    	//PrintWriter out = response.getWriter();
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
	
	public String writePicture(HttpServletRequest request,String destFile) throws Exception{
		 
		String resultFile=null;
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	     
		MultipartFile multipartFile = multipartRequest.getFile(Constant.HTML_CONTENT_PICTURE);
		     
		LOG.info("pictureContent FileName="+multipartFile.getOriginalFilename()+",multipartFile.getBytes().size()="+multipartFile.getBytes().length);
		if (multipartFile==null) {
		   	 LOG.info("multipartFile==null exist from writePicture....");
		   	 throw new Exception("multipartFile==null exist from writePicture....");
		}
		String origName = multipartFile.getOriginalFilename();		 
		String ext[] = origName.split("\\.");
		
		if (ext[1]==null) {
			LOG.info("uploading file doesn't have extension ");
			throw new Exception("ploading file doesn't have extension ....");
		}
		
		resultFile = destFile + "."+ext[1];
		File file = new File(resultFile);
		     
		LOG.info("Save picture begin .....");
		  try {   
		     multipartFile.transferTo(file);
		  } catch (Exception e) {
			  LOG.info("Error to save picture "+e.getMessage());
			  
		  }
	    LOG.info("Save picture end .....");
			 
		return resultFile;
		  
	}
	
	public ModelAndView previewPictureHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		LOG.info("previewPictureHandler begin");
		
	    	Utility.setRequest(request,response);
		
	    Integer agentId =(Integer) request.getSession().getAttribute(Constant.AGENT_ID); 
		
		LOG.info("AgentId="+agentId);
		
		if (agentId==null) {
			    String agentIdStr = request.getParameter("paramAgentId");   
			    LOG.info(" previewPictureHandler() parameter agentIdStr="+agentIdStr) ;
			    if (agentIdStr==null) {
			    	LOG.info(" previewPictureHandler() parameter agentIdStr==null") ;
					return null;
			    }
			    /**
			     *  decode the agentId
			     */
			    agentIdStr = ui.getDecodedString(agentIdStr);
			    
		        LOG.info("previewPictureHandler() from parameter agentIdstr="+agentIdStr) ; 
		       
		        if (agentIdStr==null) {
					LOG.info(" previewPictureHandler() agentId==null") ;
					return null;
				}
		       
		    agentId = new Integer(agentIdStr) ; 
	    }
	 
		//Integer agentId = 10007;  // testing
		Agents agents = quoteDBService.getAgentsDAO().findById(agentId); 
		
		String agentPath = SysPath.getInstance().getAgentPicturePath();
		
		byte[] blobAsBytes = ProcessUploadFile.getFileByteArrayByAgent(agentId, agents.getPictureFilename(), Constant.PREFIX_AGENT_PICTURE_FILE, agentPath);
		 
		
		if (blobAsBytes==null) {
			LOG.info("blobAsBytes=null");
			return null;
		} else {
			LOG.info("blobAsBytes.length="+blobAsBytes.length);
		}
		
		String contentType = "image/jpg";//ui.getSessionAttribute(Constant.IMAGE_FILE_TYPE);
		
		response.setContentType(contentType);
	    response.setHeader("Cache-control", "no-cache, no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Expires", "-1");
	    response.setStatus(HttpServletResponse.SC_OK);
	    ServletOutputStream out = response.getOutputStream();
	    out.write(blobAsBytes);
	    blobAsBytes=null;
	    out.flush();
	    out.close();			
		
	 
		return null;
	}
	
	public ModelAndView viewEmailSendStatusHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		LOG.info("viewEmailSendStatusHandler begin");
		
		ui.setRequest(request);
		
		String contentType = "text/html"; 
		
		response.setContentType(contentType);
	    response.setHeader("Cache-control", "no-cache, no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Expires", "-1");
	    response.setStatus(HttpServletResponse.SC_OK);
	    ServletOutputStream out = response.getOutputStream();
	    
	    String status = ui.getSessionAttribute(Constant.EMAIL_SEND_STATUS);
	    
	    out.println("<span style=\"color:rgb(140,0,46);font-size:12px;\">"+status+"</span>");
	    
	    out.flush();
	    out.close();			
		
	    LOG.info("viewEmailSendStatusHandler end");
		return null;
	}
	
	public byte[] getImageByteArrayByFile(String origfile) {
		LOG.info("getImageByteArray begin");
		 
		byte[] bytes=null;
		
		
		File file= null;
		 
		
		file= new File(origfile);
		if (!file.exists()) {
		     LOG.info("Can not find the picture file! ");
		     return null;
	    }
		 
		 
		 FileInputStream fis=null;
		 ByteArrayOutputStream bos=null;
		 LOG.info("read image begin ");
		 try {
			   bos = new ByteArrayOutputStream();
		       byte[] buf = new byte[1024];
		       
		       fis= new FileInputStream(file);
		       
		       for (int readNum; (readNum = fis.read(buf)) != -1;) {
		          //Writes to this byte array output stream
		           bos.write(buf, 0, readNum); 
		       }
		       bytes = bos.toByteArray();       
		       LOG.info("read image OK!  bytes size="+ bytes.length);
		 } catch (Exception e) {
			 LOG.info("Error read image byte! "+e.getMessage());
		 } finally {
			 if (fis!=null)
			 try {
				 fis.close();
				 bos.close();
			 } catch (Exception e)
			 {}
		 
		 }
		// file.delete();
		 LOG.info("getImageByteArray begin");
		 return bytes;
	}
	
	public ModelAndView agentLoginHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		Utility.setParamAgentId(request);
		AgentForm agentForm = new AgentForm();		
 		
		ModelAndView mode =new ModelAndView("AgentLogin");		
		
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
	/**
	 * Spring 2.5 MVC not support multipart/form-data form submit
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView agentSignupProcessHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		// get form data
		LOG.info("AgentControl.agentSignupProcessHandler() begin");
		
		Agents agents = modelAgents(request); 	
	 			
		Utility.setParamAgentId(request);
		
		//quoteDBService =(QuoteDBService) SpringFramework.getBean("QuoteDBService");
		
		AgentsDAO agentsDAO = quoteDBService.getAgentsDAO();	
		
		LOG.info("On Submitted...........");
		
		/** 
		  *  Must check if current login agent id
		  */
		  
		 Integer agentId = (Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
		    
		 Agents agentObj = null;
		    
		 if (agentId==null || agentId==0) { 
		 	/**
		 	 *  Not login yet , check if email exists or not, if not agentObj must be null
	    	 */
	    	agentObj = quoteDBService.findAgentByEmail(agents.getEmailAddress());
		 } else {
	    	/**
		   	 *  Logined , agentObj contain id already
		   	 */
		   	agentObj =quoteDBService.getAgentsDAO().findById(agentId);
		    	 
	    	LOG.info("Current agent obj exists for agentId "+agentId); 
	     }
 		//Email is uniqueId 
		if (agentObj!=null) {					  
			agents.setAgentId(agentObj.getAgentId());
			if (Utility.isEmpty(agents.getPassword())) {
				agents.setPassword(agentObj.getPassword());
			}
			/**
			 *  merge current object which include picture type , picture filename , application form filename and type to 
			 *  current agents, nicheIntro never enter here , agent.getNicheIntro must be null, we must rewrite it to agents
			 */
				
			agents.setNicheIntro(agentObj.getNicheIntro());
			 		
			if (Utility.isEmpty(agents.getPictureFilename()) && Utility.isEmpty(agents.getPictureType())) {
				 agents.setPictureFilename(agentObj.getPictureFilename());
				 agents.setPictureType(agentObj.getPictureType());  
			 }
	 		
			if (Utility.isEmpty(agents.getApplicationFormFilename()) && Utility.isEmpty(agents.getAppFormType())) {
				 agents.setApplicationFormFilename(agentObj.getApplicationFormFilename());
				 agents.setAppFormType(agentObj.getAppFormType());  
			 }
						 
			agentsDAO.merge(agents);
			LOG.info("agents Merging.....end, agentId="+agents.getAgentId());
			
		}	else {
			LOG.info("agents save..... DRE_NO ="+agents.getDreNo()+",NMLS_NO="+agents.getNmlsNo()); 
			LOG.info("Agents save begin.....");
			 agentsDAO.save(agents);
			LOG.info("Agents save end.....");
		}
		agentId = agents.getAgentId();
			
		/**
		 *  Save Picture to SysPath
		 */
		 boolean fileUploaded = false;
		 Files file= ProcessUploadFile.uploadCodedFile(
				 agentId.toString(), 
				 Constant.HTML_CONTENT_PICTURE,
		    	 Constant.PREFIX_AGENT_PICTURE_FILE,
				 SysPath.getInstance().getAgentPicturePath(),
				 request );
			 
		 if (null!=file) {
			 if (!Utility.isEmpty(file.getFilename()) && !Utility.isEmpty(file.getType())) {
				 LOG.info("AgentController.modelAgents() now save agent File Type="+file.getType()+",fileName="+file.getFilename());
 				 agents.setPictureFilename(file.getFilename());
				 agents.setPictureType(file.getType());				
				 fileUploaded = true;
			 }  
		 }
		  /**
		   *  Save Application Form to SysPath
		   */
		 file= ProcessUploadFile.uploadCodedFile(
				 agentId.toString(), 
				 Constant.HTML_CONTENT_APP_FORM,
				 Constant.PREFIX_APP_FORM_FILE,
				 SysPath.getInstance().getLoanApplicationFormsPath(),
				 request );
			 
		 if (null!=file) {
			 if (!Utility.isEmpty(file.getFilename()) && !Utility.isEmpty(file.getType())) {
				 LOG.info("AgentController.modelAgents() now save App Form File Type="+file.getType()+",fileName="+file.getFilename());
				 agents.setApplicationFormFilename(file.getFilename());
				 agents.setAppFormType(file.getType());
				 fileUploaded = true;
					  
			 } else {
				 LOG.info("AgentController.modelAgents() now Not save App form File Type");
					 
			 }
		 }
		 /**
		  *  Save filenames to database
		  */
		 if (fileUploaded) {
			 agentsDAO.merge(agents);
		 }
				 
		request.getSession().setAttribute("agentId", agentId);
	
		ModelAndView mode = new ModelAndView("AgentSignupSuccess"); 		
		// release memory
		agents.setPictureContent(null);
		
		mode.addObject("agentForm", agents);
		
		mode.addObject("agentAction", "signup");
		request.getSession().setAttribute(Constant.UPLOAD_WEB_FILE,null);
		LOG.info("AgentControl.agentSignupProcessHandler() end");
		return mode; 
		 
	}
	/**
	 * Verify Certificated Graphic Characters before submit AgentSignup form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView generateCertPicCodeHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		LOG.info("generateCertPicCodeHandler begin");		
	   
		String digitCountStr = (String) request.getParameter(Constant.CERT_PIC_CODE_DIGITS);
		String contentType = "image/jpg";
		
		response.setContentType(contentType);
	    response.setHeader("Cache-control", "no-cache, no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Expires", "-1");
	    response.setStatus(HttpServletResponse.SC_OK);
	    ServletOutputStream out = response.getOutputStream();
	    int text_digits=6;
	    if (null!=digitCountStr) {
	        text_digits = new Integer(digitCountStr);	
	    }
	    
	    String certPicCode = GenCertPicCode.genCertPicCode(text_digits, out);
	    
	    request.getSession().setAttribute(Constant.CERT_PIC_CODE_STRING,certPicCode);		
	    LOG.info("generateCertPicCodeHandler certPicCode="+certPicCode);  
	    out.flush();
	    out.close();			
		
	    LOG.info("generateCertPicCodeHandler end"); 
		return null;
	}
	 
	public ModelAndView fetchCertPicCodeHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		LOG.info("fetchCertPicCodeHandler begin");
		PrintWriter out= Utility.SetPostResponseContent(response);		  
		Gson gson = new Gson();
	    JsonObject myObj = new JsonObject();
	    
		String currentCertPicCode =(String)  request.getSession().getAttribute(Constant.CERT_PIC_CODE_STRING);        
		LOG.info("fetchCertPicCodeHandler currentCertPicCode ="+currentCertPicCode ); 
		 
        JsonElement curElement = gson.toJsonTree(currentCertPicCode);
        myObj.add("currentCertPicCode", curElement);        
        out.println(myObj.toString());        
        out.close();
        
		LOG.info("fetchCertPicCodeHandler end");
		return null;
		
	}
 
	public ModelAndView agentLoginProcessHandler(HttpServletRequest request,	
			HttpServletResponse response, AgentForm agentForm, BindException errors ) throws Exception { 	
		
		Utility.setParamAgentId(request);
		
		String email = agentForm.getEmailAddress();
		
		String password= agentForm.getPassword();
		
		LOG.info("email="+email+",password="+password);
		
	     request.getSession().setAttribute("agentAction","login");
		 AgentValidator agentValidator = new AgentValidator();
	 
		   	 
		agentValidator.validate(agentForm, errors);
		
		if (errors.hasErrors()) {
			 return null;
		}
		ModelAndView mode=null;
		
		Integer agentId = agentValidator.getAgentId();
	 
		String agentName=null;
		
		LOG.info("Login authorized agentId="+agentId);
		/**
		 *  If username and password are authorised 
		 */
		String agentLoginFor = (String) request.getSession().getAttribute(Constant.AGENT_LOGIN_FOR);		
		
		
		if (agentId!=null) {
			/**
			 *  Check Agent Login For (1) just login, (2) agent review (3) agent enter form
			 */
		    if (agentLoginFor==null) {
		    	 request.getSession().setAttribute("agentAction","login");
		    	 mode =new ModelAndView(Constant.AGENT_LOGIN_SUCCESS);
		    }
			if (Constant.AGENT_REVIEW_QUOTE.equalsIgnoreCase(agentLoginFor)) {
			 		// fetch list from Database
				List<AgentReviewQuoteVo> list = agentReviewService.getAgentQuoteInquire(agentId);
				AgentReviewQuoteVo currentVo  = list.get(0);
				agentName = currentVo.getAfirst_name()+" "+ currentVo.getAlast_name();  
				//mode.addObject("QuoteList", list);
				request.getSession().setAttribute("QuoteList", list);
			 
				for (AgentReviewQuoteVo vo:list) vo=null;
				request.getSession().setAttribute(Constant.LOGIN_AGENT_ID, new Integer(agentId).toString());
				request.getSession().setAttribute("AgentName", agentName);
			//	request.getSession().setAttribute(Constant.AGENT_LOGIN_FOR,null);
				mode =new ModelAndView(agentLoginFor);
			} 
			if (Constant.AGENT_ENTER_RATE_SHEET.equalsIgnoreCase(agentLoginFor)) {
				request.getSession().setAttribute(Constant.LOGIN_AGENT_ID,agentId.toString());
			//	request.getSession().setAttribute(Constant.AGENT_LOGIN_FOR,null);
				mode =new ModelAndView(agentLoginFor);
			}

		   
		    
		}  
	 	 
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
		LOG.info("modelAgents begin");
		Agents agent = new Agents();
		try {
	     agent.setFirstName(ServletRequestUtils.getStringParameter(request,"firstName"));			
	     agent.setLastName(	ServletRequestUtils.getStringParameter(request,"lastName" ));			
	     agent.setEmailAddress(	ServletRequestUtils.getStringParameter(request,"emailAddress"));	
	     String digestPassword = ServletRequestUtils.getStringParameter(request,"password");
	     if (!Utility.isEmpty(digestPassword)) {
	    	 digestPassword = KeyPairManager.getInstance().decrypt(digestPassword);
	    	 digestPassword = ui.convertToSHA256(digestPassword);
	    	 agent.setPassword(	digestPassword);	
	     }
 	     agent.setCompanyName(	ServletRequestUtils.getStringParameter(request,"companyName"));			
	     agent.setWorkPhone(	ServletRequestUtils.getStringParameter(request,"workPhone"));			
	     agent.setCellPhone(	ServletRequestUtils.getStringParameter(request,"cellPhone"));	
	     agent.setDreNo(	ServletRequestUtils.getStringParameter(request,"dreNo"));	
	     agent.setNmlsNo(	ServletRequestUtils.getStringParameter(request,"nmlsNo"));		
	     agent.setCellPhone(	ServletRequestUtils.getStringParameter(request,"cellPhone"));	
	     agent.setAddress(ServletRequestUtils.getStringParameter(request,"address"));				
	     agent.setCity(		ServletRequestUtils.getStringParameter(request,"city"));		
	     agent.setState(	ServletRequestUtils.getStringParameter(request,"state"));		
	     agent.setZipCode(	ServletRequestUtils.getStringParameter(request,"zipCode"));		
	     agent.setDescription(ServletRequestUtils.getStringParameter(request,"description"));			
	     agent.setLicenseNo(ServletRequestUtils.getStringParameter(request,"licenseNo"));			
	     String licenseEligibleState[]=ServletRequestUtils.getStringParameters(request,"licenseEligibleState");
	     
	     
	     
	     
	     StringBuffer buf = new StringBuffer();
	     String str = null;
			if (licenseEligibleState!=null) {
				int len = licenseEligibleState.length;
				if (len>1) {
					for (int i=0; i <licenseEligibleState.length;i++) {
						buf.append(licenseEligibleState[i]+", ");
					}
					str = buf.toString();
					str = str.substring(0, str.length()-2);
				} else {
					buf.append(licenseEligibleState[0]);
					str = buf.toString();
				}
			}
	     agent.setLicenseEligibleState(	str);
	     
	     agent.setModifiedDate(Utility.getCurrentTimeStamp());
	     
	     MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	     
	     MultipartFile multipartFile = multipartRequest.getFile(Constant.HTML_CONTENT_PICTURE);   
	     /**
	      *  if not upload file , won't write database 
	      */
	     if (null!=multipartFile) {
	    	// LOG.info("AgentController.modelAgents() now Check File Type="+multipartFile.getContentType()+",fileName="+multipartFile.getOriginalFilename());
	     }		 
	     if (null!=multipartFile && !Utility.isEmpty(multipartFile.getOriginalFilename()) && !Utility.isEmpty(multipartFile.getContentType() )) {
	    	// LOG.info("AgentController.modelAgents() now save File Type="+multipartFile.getContentType()+",fileName="+multipartFile.getOriginalFilename());
	    	 agent.setPictureType((multipartFile.getContentType()));	
	    	 agent.setPictureContent(null);
			 agent.setPictureFilename(multipartFile.getOriginalFilename());
	    } else {
	    	 agent.setPictureType(null);
	    	 agent.setPictureContent(null);
	    	 agent.setPictureFilename(null);
	    //	 LOG.info("AgentController.modelAgents() now NOT save FileType type and Filename!");;
	    		
	    }
	     MultipartFile appMultipartFile = multipartRequest.getFile(Constant.HTML_CONTENT_APP_FORM);   
	     /**
	      *  if not upload file , won't write database 
	      */
	     if (null!=appMultipartFile) {
	    	// LOG.info("AgentController.modelAgents() now Check File Type="+appMultipartFile.getContentType()+",fileName="+appMultipartFile.getOriginalFilename());
	     }		 
	     if (null!=appMultipartFile && !Utility.isEmpty(appMultipartFile.getOriginalFilename()) && !Utility.isEmpty(appMultipartFile.getContentType() )) {
	    	 LOG.info("AgentController.modelAgents() now save File Type="+appMultipartFile.getContentType()+",fileName="+appMultipartFile.getOriginalFilename());
	    	 agent.setAppFormType(appMultipartFile.getContentType());	
	    	 agent.setPictureContent(null);
			 agent.setApplicationFormFilename(appMultipartFile.getOriginalFilename());
	    } else {
	    	 agent.setAppFormType(null);
	    	 agent.setPictureContent(null);
	    	 agent.setApplicationFormFilename(null);
	    	// LOG.info("AgentController.modelAgents() now NOT save FileType type and Filename!");;
	    		
	    }
	} catch (Exception e) {
			LOG.info(e.getMessage());
	}
		 
	return agent;
}
	
	public ModelAndView agentLogoutHandler (HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		request.getSession().setAttribute(Constant.LOGIN_AGENT_ID, null);
		request.getSession().setAttribute(Constant.LOGIN_AGENT_NAME, null);
		ModelAndView mode = new ModelAndView(Constant.AGENT_LOGIN_REDIRECT);
		AgentForm agentForm = new AgentForm();
		mode.addObject("AgentForm", agentForm);
		return mode;
	}
}
