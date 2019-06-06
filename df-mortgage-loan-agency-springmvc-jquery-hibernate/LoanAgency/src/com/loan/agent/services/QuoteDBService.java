package com.loan.agent.services;

import java.util.List;
import java.util.Map;

import com.loan.agent.calculators.vo.CompareLoanVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.AppCheckListDAO;
import com.loan.agent.dao.CompareDAO;
import com.loan.agent.dao.CountyDAO;
import com.loan.agent.dao.CreditCardDAO;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.dao.Quote;
import com.loan.agent.dao.QuoteDAO;
import com.loan.agent.dao.RateSheetDAO;
import com.loan.agent.dao.Reply;
import com.loan.agent.dao.ReplyDAO;
import com.loan.agent.dao.SessionManager;
import com.loan.agent.dao.SessionManagerDAO;
import com.loan.agent.dao.StateDAO;
import com.loan.agent.dao.Users;
import com.loan.agent.dao.UsersDAO;

public interface QuoteDBService {
	 public QuoteDAO getQuoteDAO();
	 public AgentsDAO getAgentsDAO();
	 public CompareDAO getCompareDAO();
	 public CountyDAO getCountyDAO();
	 public CreditCardDAO getCreditCardDAO();
	 public ReplyDAO getReplyDAO();
	 public NichesDAO getNichesDAO();
	 public UsersDAO getUsersDAO();
	 public StateDAO getStateDAO();
	 public RateSheetDAO getRateSheetDAO();
	 public SessionManagerDAO getSessionManagerDAO();
	 public AppCheckListDAO getAppCheckListDAO(); 
	 
	 public void SaveReply(Integer quoteId,Integer loanId,CompareLoanVo v,
			 Double loanAmt,String adviceNote,Integer agentId,Integer userId);
	 public void SaveReply(Integer quoteId,Integer loanId,CompareLoanVo v);
	 
	 public Reply findReplyByPrimaryKey(Integer quoteId,Integer loanId);
	 
	 public List findReplyByQuoteId(Integer quoteId);
	 
	 public List findCountyByStateSymbol(String stateSymbol);
	 public List findStateCountyByZip(String zip);
	 public Map findStateAll();
	 public boolean isAgentEmailExists(String emailAddress);
	 public boolean isUserEmailExists(String emailAddress);
	 public Agents findAgentByEmail(String emailAddress);
	 public Users findUserByEmail(String emailAddress);
	 public Users findUserByEmailPassword(String emailAddress,String password);
     public boolean updateNicheIntro(String nicheIntro, Integer agentId);
	 public String findNicheIntroByAgentId(Integer agentId);
	 public boolean deleteQuote(Integer quoteId);	
	 public SessionManager findSessionManagerByClientMachineName(String localMachineName);
	 public void updateSaveSessionManager(SessionManager sMgr,Integer managerId);

}
