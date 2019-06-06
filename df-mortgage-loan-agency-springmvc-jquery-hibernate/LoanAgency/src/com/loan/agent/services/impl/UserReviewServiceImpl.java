package com.loan.agent.services.impl;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import com.loan.agent.calculators.vo.AgentReviewQuoteVo;
import com.loan.agent.calculators.vo.CompareLoanVo;
import com.loan.agent.calculators.vo.UserReviewQuoteVo;
import com.loan.agent.dao.Agents;
 
import com.loan.agent.mvc.utils.SpringFramework;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.UserReviewService;

public class UserReviewServiceImpl implements UserReviewService {
	private Integer onDutyAgentId=0;
	@Autowired
	@Resource(name="dataSource2")
	private DriverManagerDataSource	dataSource;
	
	static final Logger LOG = Logger.getLogger(UserReviewServiceImpl.class);
	
	private String getStr(ResultSet rs,String field) throws SQLException {
		 return rs.getString(field);
	}
	private CompareLoanVo getCompareLoanObj(ResultSet rs,String field) throws SQLException {
		   Blob blob = rs.getBlob(field);
		   return  Utility.getCompareLoanVOFromBlob(blob);
	}
	private String getTerm(ResultSet rs,String field) throws SQLException {
		String retval=null;
		 if ("360".equals(field)) retval="30 Years Fixed";
		 if ("240".equals(field)) retval="20 Years Fixed";
		 if ("180".equals(field)) retval="15 Years Fixed";
		 if ("120".equals(field)) retval="10/1 ARM";
		 if ("84".equals(field)) retval="7/1 ARM";
		 if ("60".equals(field)) retval="5/1 ARM";
		 if ("36".equals(field)) retval="3/1 ARM";
		 return retval;
	}
	
	private String getDate(ResultSet rs,String field) throws SQLException {
		 return Utility.renderDate(rs.getDate(field));
	}
	private String getDollar(ResultSet rs,String field) throws SQLException {
		 return Utility.renderDollar(rs.getDouble(field));
	}
	
	private String getRate(ResultSet rs,String field) throws SQLException {
		 return Utility.renderRate(rs.getFloat(field));
	}
	public class AgentTurn {
		private Integer agentId;
		private String turn;
		public Integer getAgentId() {
			return agentId;
		}
		public void setAgentId(Integer agentId) {
			this.agentId = agentId;
		}
		public String getTurn() {
			return turn;
		}
		public void setTurn(String turn) {
			this.turn = turn;
		}
		
	}
	
	
	
 
	 
	public Integer findUserIdByEmailPassword(String email, String password) {
		Integer agentId=null;
		StringBuffer queryString = new StringBuffer()
		.append("select ")  
		.append("user_id ")
		.append("from users ")
		.append("where upper(email_address)=? and password=? ");
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(queryString.toString());
		   
	        stmt.setString(1,email.toUpperCase());
	        stmt.setString(2,password);
	        
			 rs = stmt.executeQuery();
			 
			if (rs.next()) {
				 agentId = rs.getInt("user_id");
			}
		} catch (SQLException e) {
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
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(queryString.toString());
		   
	        stmt.setString(1,email.toUpperCase());
	       
	        
			 rs = stmt.executeQuery();
			 
			if (rs.next()) {
				 agentId = rs.getInt("user_id");
			}
		} catch (SQLException e) {
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
	
	
	public List<UserReviewQuoteVo> getUserQuoteInquire(Integer agentId) {
	 
		List<UserReviewQuoteVo> list = new ArrayList<UserReviewQuoteVo>();
		StringBuffer queryString = new StringBuffer()
		.append("select ")  
		.append("q.quote_id,")
		.append("q.user_id,")
		.append("q.agent_id,")
		.append("q.loan_type,")
		.append("q.loan_amount,")
		.append("q.purchase_price,")
		.append("q.property_county,")
		.append("q.purchase_stage,")
		.append("q.purchase_date,")
		.append("q.process_status,")
		.append("q.property_address,")
		.append("q.property_city,")
		.append("q.property_state,")
		.append("q.property_zip_code,")
		.append("q.property_type,")
		.append("q.occupancy_status,")
		.append("q.note,")
		.append("q.modified_date,")
		.append("q.created_date,")
		.append("q.estimate_home_value,")
		.append("q.refinance_cash_out,")
		.append("q.first_loan_balance,")
		.append("q.first_loan_rate,")
		.append("q.first_loan_term,")
		.append("q.first_date,")
		.append("q.second_loan_balance,")
		.append("q.second_loan_rate,")
		.append("q.second_loan_term,")   
		.append("u.first_name,")
		.append("u.last_name,")
		.append("u.email_address,")
		.append("u.state,")
		.append("u.none_rental_income,")
		.append("u.rental_income,")
		.append("u.credt_score,")
		.append("u.monthly_debt,")
		.append("u.phone_no, ")
		.append("a.first_name afirst_name,")
		.append("a.last_name alast_name,")
		.append("a.email_address aemail_address,")
		.append("a.work_phone ")
		.append("from quote q ")
		.append("join users u ")
		.append("on q.user_id = u.user_id  ")
		.append("join agents a	 ")
		.append("on q.agent_id = a.agent_id ")
		.append("where u.user_id=? ")
		.append("order by process_status, modified_date desc "); 
		//DriverManagerDataSource	dataSource = (DriverManagerDataSource) SpringFramework.getBean("dataSource2");
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(queryString.toString());
		   
	        stmt.setInt(1,agentId);
		
			 rs = stmt.executeQuery();
			while (rs.next()) {
				UserReviewQuoteVo v = new UserReviewQuoteVo();
				
				Double purchasePrice = rs.getDouble("purchase_price");
				Double estimate_home_value = rs.getDouble("estimate_home_value");
				v.setQuote_id(rs.getInt("quote_id"));
				v.setAgent_id(rs.getInt("agent_id"));
				v.setUser_id(rs.getInt("user_id"));
				v.setLoan_type(getStr(rs,"loan_type"));
				v.setAemail_address(getStr(rs,"aemail_address"));
				v.setAfirst_name(getStr(rs,"afirst_name"));
				v.setAwork_phone(getStr(rs,"work_phone"));
				v.setAlast_name(getStr(rs,"alast_name"));
				v.setCredt_score(getStr(rs,"credt_score"));;
				v.setEmail_address(getStr(rs,"email_address"));		
				v.setModified_date(getDate(rs,"modified_date"));
				v.setCreated_date(getDate(rs,"created_date"));
				
				v.setEstimate_home_value(getDollar(rs,"estimate_home_value"));
				v.setFirst_loan_balance(getDollar(rs,"first_loan_balance"));
				v.setFirst_loan_rate(getRate(rs,"first_loan_rate"));
				v.setFirst_loan_term(getStr(rs,"first_loan_term"));
				v.setFirst_date(getDate(rs,"first_date"));
				v.setFirst_name(getStr(rs,"first_name"));
				v.setLast_name(getStr(rs,"last_name"));
				v.setLoan_amount(getDollar(rs,"loan_amount"));
				v.setProcess_status(getStr(rs,"process_status"));
				v.setNone_rental_income(getDollar(rs,"none_rental_income"));
				v.setNote(getStr(rs,"note"));
				v.setOccupancy_status(getStr(rs,"occupancy_status"));
				v.setPhone_no(getStr(rs,"phone_no"));
				v.setProperty_address(getStr(rs,"property_address"));
				v.setProperty_city(getStr(rs,"property_city"));
				v.setProperty_state(getStr(rs,"property_state"));		
				String county = WordUtils.capitalize(getStr(rs,"property_county"));
			//	LOG.info("County="+county.toLowerCase());
				v.setProperty_county(county);
				
				v.setProperty_type(getStr(rs,"property_type"));
				v.setPurchase_date(getStr(rs,"purchase_date"));
				v.setPurchase_price(getDollar(rs,"purchase_price"));
				v.setPurchase_stage(getStr(rs,"purchase_stage"));
				v.setQuote_id(rs.getInt("quote_id"));
				v.setRefinance_cash_out(getStr(rs,"refinance_cash_out"));
				v.setRental_income(getDollar(rs,"rental_income"));
				v.setSecond_loan_balance(getDollar(rs,"second_loan_balance"));
				v.setSecond_loan_rate(getRate(rs,"second_loan_rate"));
				v.setSecond_loan_term(getStr(rs,"second_loan_term"));
				v.setMonthly_debt(getDollar(rs,"monthly_debt"));
				v.setState(getStr(rs,"state"));
				
				
				   
					
				list.add(v);
			}
		} catch (SQLException e) {
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
		LOG.info("AgentReviewData Ready!");
		return list;
	}
	public static void main(String[] args)  {
		UserReviewServiceImpl a = new UserReviewServiceImpl();
		List<UserReviewQuoteVo> list = a.getUserQuoteInquire(10001);
		for (UserReviewQuoteVo v: list) {
			LOG.info("agent name="+
				    v.getAfirst_name() +" "+v.getAlast_name()+
				    ",agent email="+v.getAemail_address()+
				    ",quote loanAmount="+v.getLoan_amount()+
				    ",loanType="+v.getLoan_type()+
				    ",user firstName="+v.getFirst_name()+
				    ",reply term1="+v.getReply_loan1()
							);
		}
	}
	public DriverManagerDataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}
 	 
    
}
