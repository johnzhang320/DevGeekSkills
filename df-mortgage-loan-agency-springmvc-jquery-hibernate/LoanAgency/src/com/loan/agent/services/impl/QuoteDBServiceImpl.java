package com.loan.agent.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.loan.agent.calculators.vo.CompareLoanVo;
import com.loan.agent.calculators.vo.ReplyVoHelp;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.CompareDAO;
import com.loan.agent.dao.CountyDAO;
 
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AppCheckListDAO;
import com.loan.agent.dao.CreditCardDAO;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.dao.QuoteDAO;
import com.loan.agent.dao.RateSheetDAO;
import com.loan.agent.dao.Reply;
import com.loan.agent.dao.ReplyDAO;
import com.loan.agent.dao.ReplyId;
import com.loan.agent.dao.SessionManager;
import com.loan.agent.dao.SessionManagerDAO;
import com.loan.agent.dao.StateDAO;
import com.loan.agent.dao.Users;
import com.loan.agent.dao.UsersDAO;
import com.loan.agent.dao.ZipcodesDAO;
import com.loan.agent.mvc.utils.ConnectionManager;
import com.loan.agent.services.QuoteDBService;

public class QuoteDBServiceImpl implements QuoteDBService {
	 static final Logger LOG = Logger.getLogger(AgentReviewServiceImpl.class);
	  
	 private AppCheckListDAO appCheckListDAO;
	 private NichesDAO nichesDAO;
	 private QuoteDAO quoteDAO;
	 private AgentsDAO agentsDAO;
	 private CompareDAO compareDAO;
	 private CountyDAO countyDAO;
	 private CreditCardDAO creditCardDAO;
	 private ReplyDAO replyDAO;	 	  
	 private UsersDAO usersDAO;
	 private StateDAO stateDAO;
	 private ZipcodesDAO zipcodesDAO;
	 private RateSheetDAO rateSheetDAO;
	 private SessionManagerDAO sessionManagerDAO;	 
	 
	 private static final String STATE_SYMBOL = "stateSymbol";
	 private static final String ZIP_CODE = "zip";
	 private static final String EMAIL_ADDRESS = "emailAddress";
	 private static final String PASSWORD = "password";
	 private static final String QUOTE_ID = "quoteId";
	 @Autowired
		@Resource(name="dataSource2")
		private DriverManagerDataSource	dataSource;
	 
	 
	 
	 
	 
	 public void SaveReply(Integer quoteId,Integer loanId,CompareLoanVo v,
			 Double loanAmt,String adviceNote,Integer agentId,Integer userId)
			 
			 {
			
		    ReplyId id =  new ReplyId();		    
		    id.setLoanId(loanId);
		    id.setQuoteId(quoteId);
		    Reply persistOne = replyDAO.findById(id);
		    if (persistOne!=null) {
		    	persistOne = ReplyVoHelp.setCurrentLoan(persistOne, v, loanAmt, adviceNote, agentId, userId);
		    	replyDAO.merge(persistOne);
		    } else {
		    	Reply newReply = new Reply();
		    	newReply.setId(id);
		    	persistOne =  ReplyVoHelp.setCurrentLoan(newReply, v, loanAmt, adviceNote, agentId, userId);
		    	replyDAO.save(persistOne);
		    }
	}
	 
	 /**
	  *  Save Reply
	  * @param quoteId
	  * @param loanId
	  * @param 
	  */
	 public void SaveReply(Integer quoteId,Integer loanId,CompareLoanVo v) {
			
		    ReplyId id =  new ReplyId();		    
		    id.setLoanId(loanId);
		    id.setQuoteId(quoteId);
		    Reply persistOne = replyDAO.findById(id);
		    if (persistOne!=null) {
		    	persistOne = ReplyVoHelp.setReplyByCompareLoanVo(persistOne, v);
		    	replyDAO.merge(persistOne);
		    } else {
		    	Reply newReply = new Reply();
		    	newReply.setId(id);
		    	persistOne =  ReplyVoHelp.setReplyByCompareLoanVo(newReply, v);
		    	replyDAO.save(persistOne);
		    }
	}
	 public Reply findReplyByPrimaryKey(Integer quoteId,Integer loanId) {
			
		 
		    ReplyId id =  new ReplyId();		    
		    id.setLoanId(loanId);
		    id.setQuoteId(quoteId);
		    Reply persistOne = replyDAO.findById(id);
		    return persistOne;
	} 
	 
	 public List findReplyByQuoteId(Integer quoteId) {
			 
			return replyDAO.findByProperty(QUOTE_ID, quoteId);
		    
	} 
    /**
     * Simplified method
     */
	 public List findCountyByStateSymbol(String stateSymbol) {
		 List<String> list=null;
			try {
				String queryString = "select model.countyName from County as model where model."
						+ STATE_SYMBOL + "= ?";
				list = countyDAO.getHibernateTemplate().find(queryString, stateSymbol);
			} catch (RuntimeException re) {
				LOG.error("find by property name failed", re);
				 
			}
			return list; 
		 
	 }
	 
	 public Map findStateAll() {
		 return LookupDataInitialServiceImpl.getStateMap(); 
	 }
	 /* 
	  * 
	  * (non-Javadoc)
	  * @see com.loan.agent.services.QuoteDBService#findByZip(java.lang.String)
	  */
	public List findStateCountyByZip(String zip) {
		try {
			String queryString = "select model.state,model.stateName,model.county from Zipcodes as model where model."
					+ ZIP_CODE + "= ?";
			return zipcodesDAO.getHibernateTemplate().find(queryString, zip);
		} catch (RuntimeException re) {
			LOG.error("find by property name failed", re);
			 
		}
		return null;
		 
	 }
	
	public boolean isUserEmailExists(String emailAddress) { 
		String queryString = "select model.emailAddress from Users as model where model."
				+ EMAIL_ADDRESS + "= ?";
		try {
			List list= usersDAO.getHibernateTemplate().find(queryString, emailAddress);
			return (list.size()>0);
		} catch (RuntimeException re) {
				LOG.error("find by property name failed", re);
		 
		}
		return false;
	}
	
	
	public boolean isAgentEmailExists(String emailAddress) {
		try {
			String queryString = "select model.emailAddress from Agents as model where model."
				+ EMAIL_ADDRESS + "= ?";
			List list= agentsDAO.getHibernateTemplate().find(queryString, emailAddress);
			return (list.size()>0);
		} catch (RuntimeException re) {
			LOG.error("find by property name failed", re);
		 
		}
		return false;
	}
	// Email is unique field in data, no need return List
	
	 public Agents findAgentByEmail(String emailAddress) {
		 if (emailAddress=="" || emailAddress.length()==0) return null;
		 String queryString = "from Agents as model where model."
					+ EMAIL_ADDRESS + "='"+emailAddress+"'";
		 
			try {
				List agentList = agentsDAO.getHibernateTemplate().find(queryString);
				if (agentList.size()==0) {
					LOG.info("email address "+emailAddress+" not exists!");
					return null;
				 
				}
				Agents agent = (Agents) agentList.get(0); 
				return agent;  
				 
			} catch (RuntimeException re) {
				LOG.error("find by property name failed", re);
				return null;
			}
			 
	 }
	 
	
	 
	 public Users findUserByEmail(String emailAddress) {
		 
		 if (emailAddress=="" || emailAddress.length()==0) return null;
		 
		 String queryString = "from Users as model where model."
					+ EMAIL_ADDRESS + "='"+emailAddress+"'";
		 try {
			
					 
			 List userList = usersDAO.getHibernateTemplate().find(queryString);
			 if (userList.size()==0) {
					LOG.info("email address "+emailAddress+" not exists!");
					return null;
					 
			 }
			 Users user = (Users) userList.get(0);
				
			 LOG.info("user="+user);
			 
			 return user;
			 
			} catch (RuntimeException re) {
					LOG.error("find by property name failed", re);
					return null;
			}
			 
	 }
	
	 public Users findUserByEmailPassword(String emailAddress,String password) {
		 
		 if (emailAddress=="" || emailAddress.length()==0) return null;
		 
		 String queryString = "from Users as model where model."
					+ EMAIL_ADDRESS + "='"+emailAddress+"' and model."+PASSWORD+"="+password;
		 try {
			
					 
			 List userList = usersDAO.getHibernateTemplate().find(queryString);
			 if (userList.size()==0) {
					LOG.info("email address "+emailAddress+" not exists!");
					return null;
					 
			 }
			 Users user = (Users) userList.get(0);
				
			 LOG.info("user="+user);
			 
			 return user;
			 
			} catch (RuntimeException re) {
					LOG.error("find by property name failed", re);
					return null;
			}
			 
	 }
	 /**
	  * find agentId
	  * @param email
	  * @param password (already encoded password)
	  * @return
	  */
		public Integer findAgentIdByEmailPassword(String email, String password) {
			Integer agentId=null;
			StringBuffer queryString = new StringBuffer()
			.append("select ")  
			.append("agent_id ")
			.append("from agents ")
			.append("where upper(email_address)=? and upper(password)=? ");
			Connection conn = null;
			PreparedStatement stmt =null;
			ResultSet rs = null;
			try {
				conn = ConnectionManager.getConnection();
				stmt = conn.prepareStatement(queryString.toString());
			   
		        stmt.setString(1,email.toUpperCase());
		        stmt.setString(2,password.toUpperCase());
		        
				 rs = stmt.executeQuery();
				 
				if (rs.next()) {
					 agentId = rs.getInt("agent_id");
				}
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
				
			} finally {
				if (rs!=null) {
					try {
					rs.close();
					stmt.close();
					conn.close();
					} catch (Exception e) {}
				}
			}
				
			return agentId;
		}
		/**
		 * updatePasswordByOldPassword
		 * @param email
		 * @param oldPassword
		 * @param newPassword
		 * @return
		 */
		public boolean updatePassword(String email,String oldPassword,String newPassword) {
			Integer agentId = findAgentIdByEmailPassword(email, oldPassword);
			if (agentId==null) {
				return false;
			}
			StringBuffer queryString = new StringBuffer()
			.append("update agents set password=? ")  
			.append("where agent_id=? ");
			Connection conn = null;
			PreparedStatement stmt =null;
			
			try {
				conn = ConnectionManager.getConnection();
				stmt = conn.prepareStatement(queryString.toString());
			   
		        stmt.setString(1,newPassword);
		        stmt.setInt(2,agentId);
		        
				stmt.executeUpdate();
				 
			     
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
				return false;
			} finally {
				if (stmt!=null) {
					try {
				
					stmt.close();
					conn.close();
					} catch (Exception e) {}
				}
			}
			return true;
		}
		public boolean updatePassword(String email,String newPassword) {
			Integer agentId = findAgentIdByEmail(email);
			if (agentId==null) {
				return false;
			}
			StringBuffer queryString = new StringBuffer()
			.append("update agents set password=? ")  
			.append("where agent_id=? ");
			Connection conn = null;
			PreparedStatement stmt =null;
			
			try {
				conn = ConnectionManager.getConnection();
				stmt = conn.prepareStatement(queryString.toString());
			   
		        stmt.setString(1,newPassword);
		        stmt.setInt(2,agentId);
		        
				stmt.executeUpdate();
				 
			     
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
				return false;
			} finally {
				if (stmt!=null) {
					try {
				
					stmt.close();
					conn.close();
					} catch (Exception e) {}
				}
			}
			return true;
		}
		/**
		 * 
		 * @param email
		 * @return
		 */
		public Integer findAgentIdByEmail(String email) {
			LOG.info("findbyemail, email="+email);
			Integer agentId=null;
			StringBuffer queryString = new StringBuffer()
			.append("select ")  
			.append("agent_id ")
			.append("from agents ")
			.append("where upper(email_address)=? ");
			Connection conn = null;
			PreparedStatement stmt =null;
			ResultSet rs = null;
			try {
				conn = ConnectionManager.getConnection();
				stmt = conn.prepareStatement(queryString.toString());
			   
		        stmt.setString(1,email.toUpperCase());
		       
		        
				 rs = stmt.executeQuery();
				 
				if (rs.next()) {
					 agentId = rs.getInt("agent_id");
				}
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
				
			} finally {
				if (rs!=null) {
					try {
					rs.close();
					stmt.close();
					conn.close();
					} catch (Exception e) {}
				}
			}
				
			return agentId;
		}
		/**
		 * 
		 * @param nicheIntro
		 * @return
		 */
		public boolean updateNicheIntro(String nicheIntro, Integer agentId) {
			 
			if (agentId==null) {
				return false;
			}
			StringBuffer queryString = new StringBuffer()
			.append("update agents set niche_intro=? ")  
			.append("where agent_id=? ");
			Connection conn = null;
			PreparedStatement stmt =null;
			
			try {
				conn = ConnectionManager.getConnection();
				stmt = conn.prepareStatement(queryString.toString());
			   
		        stmt.setString(1,nicheIntro);
		        stmt.setInt(2,agentId);
		        
				stmt.executeUpdate();				 
			     
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
				return false;
			} finally {
				if (stmt!=null) {
					try {
				
					stmt.close();
					conn.close();
					} catch (Exception e) {}
				}
			}
			return true;
		}
		/**
		 * Find agent niche_intro by agentId
		 * @param agentId
		 * @return niche_intro
		 */
		public String findNicheIntroByAgentId(Integer agentId) {
			String niche_intro=null;
			StringBuffer queryString = new StringBuffer()
			.append("select ")  
			.append("niche_intro ")
			.append("from agents ")
			.append("where agent_id=? ");
			Connection conn = null;
			PreparedStatement stmt =null;
			ResultSet rs = null;
			try {
				conn = ConnectionManager.getConnection();
				stmt = conn.prepareStatement(queryString.toString());
			   
		        stmt.setInt(1,agentId);
		       
		        
				 rs = stmt.executeQuery();
				 
				if (rs.next()) {
					niche_intro = rs.getString("niche_intro");
				}
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
				
			} finally {
				if (rs!=null) {
					try {
					rs.close();
					stmt.close();
					conn.close();
					} catch (Exception e) {}
				}
			}
				
			return niche_intro;
		}
		
		
		
		public Integer findUserIdByEmailPassword(String email, String password) {
			Integer agentId=null;
			StringBuffer queryString = new StringBuffer()
			.append("select ")  
			.append("user_id ")
			.append("from users ")
			.append("where upper(email_address)=? and upper(password)=? ");
			Connection conn = null;
			PreparedStatement stmt =null;
			ResultSet rs = null;
			try {
				conn = ConnectionManager.getConnection();
				stmt = conn.prepareStatement(queryString.toString());
			   
		        stmt.setString(1,email.toUpperCase());
		        stmt.setString(2,password.toUpperCase());
		        
				 rs = stmt.executeQuery();
				 
				if (rs.next()) {
					 agentId = rs.getInt("user_id");
				}
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
				
			} finally {
				if (rs!=null) {
					try {
					rs.close();
					stmt.close();
					conn.close();
					} catch (Exception e) {}
				}
			}
				
			return agentId;
		}
		
		public Integer findUserIdByEmail(String email) {
			LOG.info("findbyemail, email="+email);
			Integer agentId=null;
			StringBuffer queryString = new StringBuffer()
			.append("select ")  
			.append("user_id ")
			.append("from users ")
			.append("where upper(email_address)=? ");
			Connection conn = null;
			PreparedStatement stmt =null;
			ResultSet rs = null;
			try {
				conn = ConnectionManager.getConnection();
				stmt = conn.prepareStatement(queryString.toString());
			   
		        stmt.setString(1,email.toUpperCase());
		       
		        
				 rs = stmt.executeQuery();
				 
				if (rs.next()) {
					 agentId = rs.getInt("user_id");
				}
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
				
			} finally {
				if (rs!=null) {
					try {
					rs.close();
					stmt.close();
					conn.close();
					} catch (Exception e) {}
				}
			}
				
			return agentId;
		}	
		

		
		public boolean deleteQuote(Integer quoteId) {
			 
			Integer agentId=null;
			StringBuffer queryString = new StringBuffer()
			.append("delete ")  
			.append("from quote ")
			.append("where quote_id=? ");
			Connection conn = null;
			PreparedStatement stmt =null;
		
			try {
				conn = ConnectionManager.getConnection();
				 
				stmt = conn.prepareStatement(queryString.toString());
			   
		        stmt.setInt(1,quoteId);
		       
		        
				 stmt.executeUpdate();
			      
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
				return false;
				
			} finally {
				if (conn!=null) {
					try {
					
					stmt.close();
					conn.close();
					} catch (Exception e) {}
				}
			}
				
			return true;
		} 

		
		
		/**
		 * 
		 * @param localMachineName
		 * @return
		 */
		//	.append("manager_id,client_machine_name,login_agent_session_id,login_agent_id,param_agent_id,login_agent_name,param_agent_name ")
		
		public SessionManager findSessionManagerByClientMachineName(String localMachineName) {
		//	LOG.info("findByClientMachineName, localMachineName="+localMachineName);
			
			SessionManager sMgr=null;
			StringBuffer queryString = new StringBuffer()
			.append("select ")  
			.append("manager_id,client_machine_name,login_agent_session_id,login_agent_id,param_agent_id,login_agent_name,param_agent_name ")
			.append("from session_manager ")
			.append("where client_machine_name=? ");
			Connection conn = null;
			PreparedStatement stmt =null;
			ResultSet rs = null;
			try {
				conn = ConnectionManager.getConnection();
				stmt = conn.prepareStatement(queryString.toString());
			   
		        stmt.setString(1,localMachineName);		       
		        
				 rs = stmt.executeQuery();
				 
				if (rs.next()) {
					sMgr = new SessionManager();
					sMgr.setManagerId(rs.getInt("manager_id"));
					sMgr.setClientMachineName(rs.getString("client_machine_name"));
					sMgr.setLoginAgentSessionId(rs.getString("login_agent_session_id"));
					sMgr.setLoginAgentId(rs.getInt("login_agent_id"));
					sMgr.setParamAgentId(rs.getInt("param_agent_id"));
					sMgr.setLoginAgentName(rs.getString("login_agent_name"));
					sMgr.setLoginAgentName(rs.getString("login_agent_name"));
				}
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
				
			} finally {
				if (rs!=null) {
					try {
					rs.close();
					stmt.close();
					conn.close();
					} catch (Exception e) {}
				}
			}
				
			return sMgr;
		}	
		
		public void updateSaveSessionManager(SessionManager sMgr,Integer managerId) {
			
			StringBuffer queryString = new StringBuffer();
			//  LOG.info("managerId= "+managerId);
			Connection conn = null;
			PreparedStatement stmt =null;
			try {
				conn = ConnectionManager.getConnection();
					 
				if (null==managerId || 0==managerId) {
					queryString.append("insert session_manager (client_machine_name,login_agent_session_id,login_agent_id," +
							                                    "param_agent_id,login_agent_name,param_agent_name) values ").
					append(" (?,?,?,?,?,?)");
					//LOG.info("Insert Session Manager!");
				        
				} else { 
	 				queryString.append("update session_manager set client_machine_name=?,login_agent_session_id=?,login_agent_id=?,param_agent_id=?,login_agent_name=?,param_agent_name=?")  
					.append(" where manager_id=? ");
				  
				   //  LOG.info("Update Session Manager! ");
	 			}
				  
				 stmt = conn.prepareStatement(queryString.toString());
				 stmt.setString(1,sMgr.getClientMachineName());
				 stmt.setString(2,sMgr.getLoginAgentSessionId());
			     stmt.setInt(3,(null==sMgr.getLoginAgentId() ? 0:sMgr.getLoginAgentId()));
			     stmt.setInt(4,(null==sMgr.getParamAgentId() ? 0:sMgr.getParamAgentId()));
			     stmt.setString(5,sMgr.getLoginAgentName());
			     stmt.setString(6,sMgr.getParamAgentName());	
			     
			     if (null!=managerId ){
			    	   stmt.setInt(7,managerId); 
			     }
				stmt.executeUpdate();				 
			     
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
			
			} finally {
				if (stmt!=null) {
					try {
				
					stmt.close();
					conn.close();
					} catch (Exception e) {}
				}
			}
			
		}
		
		public AppCheckListDAO getAppCheckListDAO() {
			return appCheckListDAO;
		}

		public void setAppCheckListDAO(AppCheckListDAO appCheckListDAO) {
			this.appCheckListDAO = appCheckListDAO;
		}

		public NichesDAO getNichesDAO() {
			return nichesDAO;
		}

		public void setNichesDAO(NichesDAO nichesDAO) {
			this.nichesDAO = nichesDAO;
		}

	public ZipcodesDAO getZipcodesDAO() {
		return zipcodesDAO;
	}
	public void setZipcodesDAO(ZipcodesDAO zipcodesDAO) {
		this.zipcodesDAO = zipcodesDAO;
	}
	public QuoteDAO getQuoteDAO() {
		return quoteDAO;
	}
	public void setQuoteDAO(QuoteDAO quoteDAO) {
		this.quoteDAO = quoteDAO;
	}
	public AgentsDAO getAgentsDAO() {
		return agentsDAO;
	}
	public void setAgentsDAO(AgentsDAO agentsDAO) {
		this.agentsDAO = agentsDAO;
	}
	public CompareDAO getCompareDAO() {
		return compareDAO;
	}
	public void setCompareDAO(CompareDAO compareDAO) {
		this.compareDAO = compareDAO;
	}
	public CountyDAO getCountyDAO() {
		return countyDAO;
	}
	public void setCountyDAO(CountyDAO countyDAO) {
		this.countyDAO = countyDAO;
	}
	public CreditCardDAO getCreditCardDAO() {
		return creditCardDAO;
	}
	public void setCreditCardDAO(CreditCardDAO creditCardDAO) {
		this.creditCardDAO = creditCardDAO;
	}
	public ReplyDAO getReplyDAO() {
		return replyDAO;
	}
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	public UsersDAO getUsersDAO() {
		return usersDAO;
	}
	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}
	public StateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(StateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public RateSheetDAO getRateSheetDAO() {
		return rateSheetDAO;
	}

	public void setRateSheetDAO(RateSheetDAO rateSheetDAO) {
		this.rateSheetDAO = rateSheetDAO;
	}

	public SessionManagerDAO getSessionManagerDAO() {
		return sessionManagerDAO;
	}

	public void setSessionManagerDAO(SessionManagerDAO sessionManagerDAO) {
		this.sessionManagerDAO = sessionManagerDAO;
	}
   
	public static void main(String []args) {
		QuoteDBServiceImpl handler = new QuoteDBServiceImpl();
		SessionManager sMgr = handler.findSessionManagerByClientMachineName("johnzhang-HP");
		sMgr.setManagerId(null);
		sMgr.setLoginAgentId(null);
		handler.updateSaveSessionManager(sMgr,sMgr.getManagerId());
	}
}
