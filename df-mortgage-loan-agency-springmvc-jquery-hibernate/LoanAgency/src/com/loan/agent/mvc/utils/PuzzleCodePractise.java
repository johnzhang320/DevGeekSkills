package com.loan.agent.mvc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;

import com.google.gson.JsonElement;
import com.loan.agent.calculators.vo.AgentReviewQuoteVo;
import com.loan.agent.calculators.vo.CompareLoanVo;
import com.loan.agent.calculators.vo.QuoteReplyVo;
import com.loan.agent.calculators.vo.TodayInterestRateVo;
import com.loan.agent.calculators.vo.UserReviewQuoteVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.Reply;
import com.loan.agent.dao.ReplyDAO;
import com.loan.agent.services.AgentReviewService;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.jfree.util.Log;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PuzzleCodePractise {
	static Logger LOG = Logger.getLogger( PuzzleCodePractise.class);
	 private static String RESOURCE_URL = "http://www.corralesluxuryandreo.com/webservice/mortgageRates.asmx/getMortgageRates";
	  
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	/**  DriverManagerDataSource	dataSource = (DriverManagerDataSource) SpringFramework.getBean("dataSource2");
	  
	  
	  
	  QuoteReplyVo qr = new QuoteReplyVo();
	  
	  qr.setLoanAmount("417,000.00");
	  qr.setClosingFee("0.0");
	  qr.setPoint("0.0");
	  qr.setTerm("360");
	  qr.setTotalPayment("671,000.00");
	  
	  ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream out = new ObjectOutputStream(bos);
	 
	  out.writeObject(qr);
	  Log.info("Wrote to ByteArrayOutputStream already!");
	  out.flush();
	  out.close();
	  
	  byte[] data = bos.toByteArray();
	  
	  Connection conn = dataSource.getConnection();
	  Log.info("Connection="+conn);
	  String sql="Insert into reply (agent_id,user_id,reply_loan1) values (?,?,?)";
	  
	  PreparedStatement stmt = conn.prepareStatement(sql); 
	  
	  stmt.setInt(1,10001);
	  
	  stmt.setInt(2, 1);
	  
	  stmt.setObject(3, data);
	  
	  stmt.executeUpdate();
	  
	  stmt.close();
	  
	  bos.close();
	  Log.info("Wrote to MySQL already!");
	  //==============Read object from mysql ==================
	   
	/*  sql = "select reply_loan1 from reply where reply_id=1";
	  ByteArrayInputStream bis;
	  
	  ObjectInputStream in = new ObjectInputStream(bis);*/
	  
	  
	  
/**	 ReplyDAO dao = (ReplyDAO)	SpringFramework.getBean("ReplyDAO");
	 QuoteReplyVo qr = new QuoteReplyVo();
	  
  /**qr.setLoanAmount("417,000.00");
	  qr.setClosingFee("0.0");
	  qr.setPoint("0.0");
	  qr.setTerm("360");
	  qr.setTotalPayment("671,000.00");
	  
	  ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream out = new ObjectOutputStream(bos);
	 
	  out.writeObject(qr);
	  Log.info("Wrote to ByteArrayOutputStream already!");
	  out.flush();
	  out.close();
	  byte[] data = bos.toByteArray();
	  
	  Blob blob = Hibernate.createBlob(data);
	  
	  Reply r = new Reply();
	  
	  r.setAgentId(10001);
	  
	  r.setUserId(1);
	  
	  r.setReplyLoan1(blob);
	  
	  dao.save(r);
	  LOG.info("Wrote to MySQL already!");
	  
	 
	  Reply re = dao.findById(100001);
	  
	  Blob b= re.getReplyLoan1();
	  
	  ObjectInputStream ins = new ObjectInputStream(b.getBinaryStream()); 
	  
	  QuoteReplyVo read =(QuoteReplyVo) ins.readObject();
	 
	  
	  LOG.info("read loan Amount= "+read.getLoanAmount()+",read total Payment="+read.getTotalPayment());
	  
	  
		
		AgentsDAO aDao = (AgentsDAO) SpringFramework.getBean("AgentsDAO");
		Integer value = 10001;
		
		StringBuffer queryString = new StringBuffer();
		
		queryString.append("Select a.firstName as firstName,") //0
		.append("a.lastName as lastName,")    // 1
		.append("a.emailAddress as emailAddress,")  //2
		.append("a.Quote.loanAmount as loanAmount,")  //3
		.append("a.Quote.loanType as loanType,") //4
		.append("a.Quote.Users.firstName as uFirstName") //5
		.append(" from Agents as a where a.agentId=10001")
		.append(" left join fetch a.Quote")
		.append(" left join fetch a.Quote.Users" )		
		.append(" order by a.modifiedDate desc");
		
		LOG.info(queryString.toString());
		
		/*String queryString ="from Agents ag where ag.agentId ="+ value +
					"left join fetch ag.Quote "+
				    "left join fetch ag.Quote.Users ";
         
		
		HibernateTemplate temp = aDao.getHibernateTemplate();
		
		List<Object[]> list = (List<Object[]> )temp.find(queryString.toString(), value);
		
		for (Object[] g: list) {
			 
			LOG.info("agent name="+g[0]+" "+g[1]+",agent email="+g[2]+",quote loanAmount="+g[3]+",loanType="+g[4]+",user firstName="+g[5]);
		} 
		
		DriverManagerDataSource	dataSource = (DriverManagerDataSource) SpringFramework.getBean("dataSource2");
		Connection conn = dataSource.getConnection();
		Log.info("Connection="+conn);
		 
Select 
q.quote_id,
q.loan_type,
q.loan_amount,
q.purchase_price,
q.property_county,
q.purchase_stage,
q.purchase_date,
q.property_address,
q.property_city,
q.property_zip_code,
q.property_type,
q.occupancy_status,
q.note,
q.modified_date,
q.estimate_home_value,
q.refinance_cash_out,
q.first_loan_balance,
q.first_loan_rate,
q.first_loan_term,
q.second_loan_balance,
q.second_loan_rate,
q.second_loan_term,   
u.first_name,
u.last_name,
u.email_address,
u.state,
u.none_rental_income,
u.rental_income,
u.credt_score,
u.phone_no,
a.first_name afirst_name,
a.last_name alast_name,
a.email_address aemail_address,
r.term1,
r.term2,
r.term3,
r.term4
from quote q 
left join users u
on q.user_id = u.user_id
left join reply r
on q.quote_id = r.quote_id 
join agents a
on q.agent_id = a.agent_id
where a.agent_id=10001;

quote_id
loan_type
loan_amount
purchase_price
property_county
purchase_stage
purchase_date
property_address
property_city
property_zip_code
property_type
occupancy_status
note
modified_date
estimate_home_value
refinance_cash_out
first_loan_balance
first_loan_rate
first_loan_term
second_loan_balance
second_loan_rate
second_loan_term   
first_name
last_name
email_address
state
none_rental_income
monthly_debt
rental_income
credt_score
phone_no
first_name afirst_name
last_name alast_name
email_address aemail_address
term1
term2
term3
term4

		 
		Integer value=10001;
		StringBuffer queryString = new StringBuffer()
		.append("select ")  
		.append("q.quote_id,")
		.append("q.loan_type,")
		.append("q.loan_amount,")
		.append("q.purchase_price,")
		.append("q.property_county,")
		.append("q.purchase_stage,")
		.append("q.purchase_date,")
		.append("q.property_address,")
		.append("q.property_city,")
		.append("q.property_zip_code,")
		.append("q.property_type,")
		.append("q.occupancy_status,")
		.append("q.note,")
		.append("q.modified_date,")
		.append("q.estimate_home_value,")
		.append("q.refinance_cash_out,")
		.append("q.first_loan_balance,")
		.append("q.first_loan_rate,")
		.append("q.first_loan_term,")
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
		.append("u.phone_no, ")
		.append("a.first_name afirst_name,")
		.append("a.last_name alast_name,")
		.append("a.email_address aemail_address,")
		.append("r.term1,")
		.append("r.term2,")
		.append("r.term3,")
		.append("r.term4 ")
		.append("from quote q 	")
		.append("left join users u ")
		.append("on q.user_id = .u.user_id  ")
		.append("left join reply r ")
		.append("on q.quote_id = r.quote_id  ")
		.append("join agents a	 ")
		.append("on q.agent_id = a.agent_id ")
		.append("where a.agent_id=?");
		
		PreparedStatement stmt = conn.prepareStatement(queryString.toString());
		stmt.setInt(1,value);
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			LOG.info("agent name="+
		    rs.getString("afirst_name") +" "+rs.getString("alast_name")+
		    ",agent email="+rs.getString("aemail_address")+
		    ",quote loanAmount="+rs.getString("loan_amount")+
		    ",loanType="+rs.getString("loan_type")+
		    ",user firstName="+rs.getString("first_name")+
		    ",reply term1="+rs.getString("term1")
					);

		}
		rs.close();
		stmt.close();
		conn.close(); 
		AgentReviewService	agentReviewService = (AgentReviewService) SpringFramework.getBean("AgentRender");
		List<AgentReviewQuoteVo> list = agentReviewService.getAgentQuoteInquire(10001);
		for (AgentReviewQuoteVo v: list) {
			LOG.info("agent name="+
				    v.getAfirst_name() +" "+v.getAlast_name()+
				    ",agent email="+v.getAemail_address()+
				    ",quote loanAmount="+v.getLoan_amount()+
				    ",loanType="+v.getLoan_type()+
				    ",user firstName="+v.getFirst_name()+
				    ",reply term1="+v.getTerm1()
							);
		}*/
		 getXml("doGetAsXml1");    
	        System.out.println("Got XML. from doGetAsXml1...");
		 
	}
	  private static void getXml(String path) {
	        HttpClient client = new HttpClient();
	        
	       // GetMethod get = new GetMethod(RESOURCE_URL+"/"+path);
	        GetMethod get = new GetMethod(RESOURCE_URL);
	        get.setRequestHeader("Accept", "text/xml");
	        System.out.println("get="+get);
	        try {
	            int httpStatus = client.executeMethod(get);
	            System.out.println("httpStatus="+httpStatus);
	            if (HttpStatus.SC_OK == httpStatus) {
	                String xmlResponse = get.getResponseBodyAsString();
	                System.out.println("Xml Response: " + 
	                        xmlResponse);
	                 
	              DocumentBuilderFactory dbf =   DocumentBuilderFactory.newInstance();
	              DocumentBuilder db = dbf.newDocumentBuilder();
	              Document doc = db.parse(new ByteArrayInputStream(xmlResponse.getBytes()));
	              Set<String> setMap = new HashSet<String>();
	              List<TodayInterestRateVo> list = new ArrayList<TodayInterestRateVo>();
	              NodeList nRate = doc.getElementsByTagName("latestRate");
		            NodeList nTerm = doc.getElementsByTagName("symbol");
		            NodeList nPostedTime = doc.getElementsByTagName("postedDate");
		            for (int i=0;i<nRate.getLength();i++) {
		            	  Node n_postedTime = nPostedTime.item(i);
		            	  Node n_term = nTerm.item(i);
		            	  Node n_rate = nRate.item(i);
		            	  System.out.println(n_rate.getFirstChild().getNodeValue());
		            	  String postedTime = n_postedTime.getFirstChild().getNodeValue();
		            	  String term = n_term.getFirstChild().getNodeValue();
		            	  String rate = n_rate.getFirstChild().getNodeValue();
		            	  
		            	  String renderRate =Utility.renderRate(Double.parseDouble(rate)*100.0);
		            	  String renderPostedTime = postedTime.substring(0, 19).replace('T', ' ');
		            	  //System.out.println(term+":"+renderRate+",postedTime:"+renderPostedTime);
		            	  if (!setMap.contains(term)) {
		            		  TodayInterestRateVo vo = new TodayInterestRateVo();
		            		  vo.setIntRate(renderRate);
		            		  vo.setPostedTime(renderPostedTime);
		            		  vo.setTermName(term);
		            		  list.add(vo);
		            	  }
		            	  setMap.add(term);
		            }
		            setMap.clear();
	               for ( TodayInterestRateVo v : list) {
	            	   System.out.println("Term"+Utility.DescriptionToTerm(v.getTermName())+v.getTermName()+":"+v.getIntRate()+",postedTime:"+v.getPostedTime());
	 		          
	               }
	            }
	            
	            
	        } catch (HttpException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            //clean up
	            get.releaseConnection();
	        }
	    }
}
