package com.loan.agent.services.impl;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
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
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.StateDAO;
 
import com.loan.agent.mvc.utils.SpringFramework;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.AgentReviewService;

public class AgentReviewServiceImpl implements AgentReviewService {
	private Integer onDutyAgentId=0;
	@Autowired
	@Resource(name="dataSource2")
	private DriverManagerDataSource	dataSource;
	
	static final Logger LOG = Logger.getLogger(AgentReviewServiceImpl.class);
	
	private String getStr(ResultSet rs,String field) throws SQLException {
		 return rs.getString(field);
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
	
	
	
	public Integer getAgentOnDuty () {
		LOG.info("getAgentOnDuty begin");
		DriverManagerDataSource	dataSource2 = (DriverManagerDataSource)  SpringFramework.getBean("dataSource2");
		
		StringBuffer queryString = new StringBuffer()
		.append("select agent_id, gendar ") 
		.append("from agents");
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		List<AgentTurn> list =new ArrayList<AgentTurn>();
		try {
			
			
			conn = dataSource2.getConnection();
			LOG.info("DataSource2 connection conn="+conn);
			conn.setAutoCommit(true);
			
			stmt = conn.prepareStatement(queryString.toString());		
			 rs = stmt.executeQuery();
			while (rs.next()) {
				AgentTurn v = new AgentTurn();
				v.setAgentId(rs.getInt("agent_id"));
				v.setTurn(rs.getString("gendar"));
				list.add(v);
		 
			}
			 
			Integer index=0;
			for (int i = 0 ; i<list.size();i++) {
				String turn = list.get(i).getTurn();
				
				if ("turn".equals(turn)) {
					LOG.info("Current turn i="+i );
					if (i==list.size()-1) index=0; else index=i+1;
					break;
				}
			}    	
			
			onDutyAgentId = list.get(index).getAgentId();		
			LOG.info("Next turn index="+index+",onDutyAgentId ="+onDutyAgentId );
			
			stmt = conn.prepareStatement("update agents set gendar=null");			 
			stmt.executeUpdate();		    
			
			stmt = conn.prepareStatement("update agents set gendar='turn' where agent_id=?");
			stmt.setInt(1, onDutyAgentId);
			stmt.executeUpdate();
			
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
		
		
		
		LOG.info("Agent Turn Data Ready!");
		LOG.info("getAgentOnDuty end");	
		return onDutyAgentId;
		
		 
	}
	public Integer findAgentIdByEmailPassword(String email, String password) {		
		
		Integer agentId=null;
		StringBuffer queryString = new StringBuffer()
		.append("select ")  
		.append("agent_id ")
		.append("from agents ")
		.append("where upper(email_address)=? and password=?");
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(queryString.toString());
		   
	        stmt.setString(1,email.toUpperCase());
	        stmt.setString(2,password);
	        
			 rs = stmt.executeQuery();
			 boolean found = rs.next();
		
			if (found) {
				 Log.info("found email ="+email+",password="+password);
				 agentId = rs.getInt("agent_id");
				 return agentId;
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
	
	public Integer findAgentIdByEmail(String email) {
	 
		Integer agentId=null;
		StringBuffer queryString = new StringBuffer()
		.append("select ")  
		.append("agent_id ")
		.append("from agents ")
		.append("where email_address=? ");
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(queryString.toString());
		   
	        stmt.setString(1,email);
	       
	        
			 rs = stmt.executeQuery();
			 
			if (rs.next()) {
				 LOG.info("found Email="+email);
				 agentId = rs.getInt("agent_id");
				 return agentId;
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

	public List<AgentReviewQuoteVo> getAgentQuoteInquire(Integer agentId) {
	 
		List<AgentReviewQuoteVo> list = new ArrayList<AgentReviewQuoteVo>();
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
		.append("a.email_address aemail_address ")
		.append("from quote q 	")
		.append("left join users u ")
		.append("on q.user_id = .u.user_id  ")
		.append("join agents a	 ")
		.append("on q.agent_id = a.agent_id ")
		.append("where a.agent_id=? ")
		.append("order by process_status asc, quote_id desc "); 
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
				AgentReviewQuoteVo v = new AgentReviewQuoteVo();
				
				
				Double purchasePrice = rs.getDouble("purchase_price");
				Double estimate_home_value = rs.getDouble("estimate_home_value");
				v.setQuote_id(rs.getInt("quote_id"));
				v.setAgent_id(rs.getInt("agent_id"));
				v.setUser_id(rs.getInt("user_id"));
				v.setLoan_type(getStr(rs,"loan_type"));
				v.setAemail_address(getStr(rs,"aemail_address"));
				v.setAfirst_name(getStr(rs,"afirst_name"));
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
				
				
				
				String adviceNote ="Dear "+ v.getFirst_name()+" "+v.getLast_name()+":\n\n\n"+"Thanks for your inquiring a quote\n"+
				                   "\n"+v.getAfirst_name()+" "+v.getAlast_name()+"\n"+v.getAemail_address();
					   
				v.setAdvice_note(adviceNote);				
				
				
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
		if (list!=null) {
			LOG.info("AgentReviewData Ready list.size()="+list.size());
		} else {
			LOG.info("AgentReviewData Ready list=null");
		}
		return list;
	}
	
	public void updateAgents (Agents agents) {
		 
		StringBuffer queryString = new StringBuffer()
        .append("update agents set  ") 
        .append("agent_id  = ?,")                   // 1
        .append("first_name = ?,")	                // 2
        .append("last_name = ?,")	                // 3
        .append("email_address= ?,")	            // 4
        .append("password = ?,")	  	            // 5
        .append("company_name = ?,")	  	        // 6
        .append("work_phone= ?,")	  	            // 7
        .append("cell_phone = ?,")	  	            // 8
        .append("picture_content= ?,")  	        // 9
        .append("picture_filename = ?,")	        // 10
        .append("picture_type= ?,")	  	            // 11
        .append("gendar= ?,")		  	            // 12
        .append("address = ?,")	  	      			// 13
        .append("state= ?,")		  	      		// 14
        .append("zip_code = ?,")		     		// 15
        .append("description= ?,")		      		// 16
        .append("license_no = ?,")		      		// 17
        .append("license_eligible_state = ?,")      // 18
        .append("modified_date= ?,")		      	// 19
        .append("city	= ? ")			      		//20
        .append("from agents ")  		      
        .append("where agent_id=?");		     		 // 21
   					    

		//DriverManagerDataSource	dataSource = (DriverManagerDataSource) SpringFramework.getBean("dataSource2");
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(queryString.toString());
		   
	        stmt.setInt(1,agents.hashCode());
	        
	         
	        stmt.setString(2,agents.getFirstName() );	                // 2
	        stmt.setString(3,agents.getLastName());	                // 3
	        stmt.setString(4,agents.getEmailAddress());	            // 4
	        stmt.setString(5,agents.getPassword());	  	            // 5
	        stmt.setString(6,agents.getCompanyName());	  	        // 6
	        stmt.setString(7,agents.getWorkPhone());	  	            // 7
	        stmt.setString(8,agents.getCellPhone());	  	            // 8
	        stmt.setBlob(9,agents.getPictureContent());  	        // 9
	        stmt.setString(10,agents.getPictureFilename());	        // 10
	        stmt.setString(11,agents.getPictureType());	  	            // 11
	        stmt.setString(12,agents.getGendar());		  	            // 12
	        stmt.setString(13,agents.getAddress());	  	      			// 13
	        stmt.setString(14,agents.getState());		  	      		// 14
	        stmt.setString(15,agents.getZipCode());		     		// 15
	        stmt.setString(16,agents.getDescription());		      		// 16
	        stmt.setString(17,agents.getLicenseNo());		      		// 17
	        stmt.setString(18,agents.getLicenseEligibleState());      // 18
	        stmt.setTimestamp(19,agents.getModifiedDate());		      	// 19
	        stmt.setString(20,agents.getCity());		      		// 20	        	      
	        stmt.setInt(21,agents.getAgentId());		     		 // 21
	        
		    
			stmt.executeUpdate();
			LOG.info("Update Agents by JDBC.....");
			conn.commit();
			 
			 
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
	}
	public static void main(String[] args)  {
		AgentReviewServiceImpl a = new AgentReviewServiceImpl();
		List<AgentReviewQuoteVo> list = a.getAgentQuoteInquire(10001);
		for (AgentReviewQuoteVo v: list) {
			LOG.info("agent name="+
				    v.getAfirst_name() +" "+v.getAlast_name()+
				    ",agent email="+v.getAemail_address()+
				    ",quote loanAmount="+v.getLoan_amount()+
				    ",loanType="+v.getLoan_type()+
				    ",user firstName="+v.getFirst_name()+
				    ",reply term1="+v.getTerm1()
							);
		}
		
		//Integer agentId= a.findAgentIdByEmailPassword("denniecha@yahoo.com", "14f8f4bb8c0e79a02670a5fea5682da717a5b3d3dc7b1706f7a4bab9afae18c2");
		//System.out.println("Agentid = "+agentId);
	
		
	}
	public DriverManagerDataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}
	public Integer getOnDutyAgentId() {
		return onDutyAgentId;
	}
	public void setOnDutyAgentId(Integer onDutyAgentId) {
		this.onDutyAgentId = onDutyAgentId;
	}
    
}
