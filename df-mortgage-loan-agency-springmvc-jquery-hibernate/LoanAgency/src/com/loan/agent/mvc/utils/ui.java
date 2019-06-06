package com.loan.agent.mvc.utils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;
 

import com.loan.agent.calculators.vo.SelectedStateVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.mvc.formbean.EmailForm;
import com.loan.agent.mvc.formbean.EmailServerForm;
import com.loan.agent.services.Constant;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;
import com.send.email.smtp.ssl.EmailEnvelopVo;
import com.send.email.smtp.ssl.RecipientVo;
import com.send.email.smtp.ssl.SMTPServerVo;
import com.send.email.smtp.ssl.SendHTMLAttachEmails;
import com.send.email.smtp.ssl.eUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.util.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.*;
 
public class ui {
	static Logger LOG = Logger.getLogger(ui.class);
	private static HttpServletRequest request;
	
	private static Pattern pattern;
	private static Matcher matcher;
 
	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
	public static HttpServletRequest getRequest() {
		return request;
	}

	public static void setRequest(HttpServletRequest request) {
		ui.request = request;
		
	}
	
	public static void setSessionAttribute(String name, String value) {
		request.getSession().setAttribute(name, value);
	}
	public static String getSessionAttribute(String name) {
		if (null==request.getSession().getAttribute(name)) return null;
		String retVal = (String) request.getSession().getAttribute(name);
		return retVal;
	}
	 
	public static Object getSessionAttributeObject(String name) {
		 
		return  request.getSession().getAttribute(name);
	 
	}
	public static void setSessionAttributeObject(String name,Object value) {
		 
		 request.getSession().setAttribute(name,value);
	 
	}
	  
	public static Integer getSessionAttributeInt(String name) {
		
		if (null==request.getSession().getAttribute(name)) return null;
		Integer retVal = (Integer) request.getSession().getAttribute(name);
		return retVal;
	}
	
	public static boolean isNotEmpty(String str) {
		if (str==null || str.trim().length()<5 || str=="") {
			return false;
		}
		return true;
	}
	
	public static boolean convertEmailStringToList(String emailListString ) {
	
		if (!isNotEmpty(emailListString)) {
			LOG.info("emailListString is empty , failed to convert");
			return false;
		}
		List<RecipientVo> rList = eUtil.generateRecipientVoList(emailListString);
	
		//LOG.info("List<RecipientVo> list ="+rList);
		if (rList==null || rList.isEmpty() || rList.size()==0) {
			LOG.info("List<RecipientVo> list is empty , failed to convert");
			return false;
		}
		LOG.info("Converted email List size()="+rList.size());
		request.getSession().setAttribute(Constant.RECIPIENT_EMAIL_LIST,rList);
		return true;
		
	}
	
	/**
	 * Send email to recipients by email list
	 * @return
	 */
	public static boolean sendEmailForAgentWrap (
			   HttpServletRequest request,
			   Agents agents,
			   EmailServerForm form,
			   List<RecipientVo> rList,
			   boolean footerSection
			) {
		LOG.info("sendEmailForAgentWrap:begin");
		boolean retVal=false;
		if (request==null) {
			LOG.info("Request is not initialized yet!");
			return false;
		}
		if (agents==null) {
			LOG.info("agents list is empty , failed to send email");
			return false;
		}
		/**
		 *  fetch session content: auth user / password , Email List and attachment list
		 */
		ui.setRequest(request);
		/**
		 *  Remove gmail as email sender and set support.staff@loans-agent.com / nus6547299 as temp auth
		 *  
		 *  
		 
		*/
		//String authEmail="support@loans-agent.com";  // dailyrazor mail server is not 100% deliver mail , some missed , not reliable
		//String authPassword="nus658742478";
		
		//String authEmail="support.staff@loans-agent.com";  // Use gmail deliver mail for user but can not post sender email to FROM HEADER always put auth emailto From Header
		//String authPassword="nus6547299";                  // User should not reply to support.staff@loans-agent.com
		 
		String authEmail=(String) request.getSession().getAttribute(Constant.AUTH_EMAIL_ADDRESS);   // keep using user gmail account as AUTH and FROM HEADER
		String authPassword=(String) request.getSession().getAttribute(Constant.AUTH_PASSWORD);		
					
		List<String> aList =(List<String>) ui.getSessionAttributeObject(Constant.ATTACHMENT_LIST);		
		 
		
		LOG.info("List<RecipientVo> list ="+rList);
		if (rList==null) {
			LOG.info("List<RecipientVo> list is empty , failed to send");
			return false;
		}
		
		
		if (authEmail==null || null== authPassword) {
			LOG.info("auth Email  ="+authEmail+",authPassword="+authPassword+" failed to send");
			return false;
		}
		
		LOG.info("Recipient List size=" + rList.size());	
	    
	    
	    List<String> attachPathList=new ArrayList<String>();
	    
	    String destPath = SysPath.getInstance().getAgentEmailAttachmentPath();
	   
		
		
	    if (aList!=null) {
	    	for (String filename: aList) {
	    		
	    		attachPathList.add(destPath+filename);
	    	}

	    }
	    for (String attachPath: attachPathList) {
	    	 LOG.info("Attachment  List file="+attachPath);
    	}
	   
	    /**
	     *  Email EnvelopVo Setting
	     */
	    EmailEnvelopVo eVo = new EmailEnvelopVo();
	    
   	 	eVo.setAttachmentPathList(attachPathList);
   	 	
   		eVo.setRecipients(rList);
   		
   		eVo.setEmailContent(form.getEmailContent());
   		
   	 	Long time = System.currentTimeMillis()/100;
   	 	
   	 	eVo.setMailSubject(form.getSubject()+" (No. "+time+" )");  // timestamp to prevent collapsed email in gmail or yahoo.com
   		eVo.setMailSubject(form.getSubject());  // timestamp to prevent collapsed email in gmail or yahoo.com
   	 	/**
   	 	 *  If using mail.loans-agent.com , the from email address will be shown up at From Header of email
   	 	 *  
   	 	 */
   	 	eVo.setFromEmailAddress(form.getEmailAddress());
   	 	eVo.setSenderName(agents.getFirstName()+" " +agents.getLastName());
   	 	eVo.setSenderCorp(agents.getCompanyName());
   		eVo.setSenderPhone(agents.getCellPhone());
   		eVo.setSenderAddress(agents.getAddress());
   		
        SMTPServerVo vo = new SMTPServerVo();			 
		
     
        
	    vo.setAuthEmailAddress(authEmail);
	    vo.setAuthEmailPassword(authPassword);
	    
	    /**
	     *   SMTPServerVo fromEmailAddress is critical , we must set it
	     */
	    vo.setFromEmailAddress(form.getFromEmailAddress());
	    
	  
   	 
		/**
		 * During promotion period (3-6 months), we use webhost smtp server sending email, 200 emails per hours
		 * If agents number increased , we plan to set bulk mail SMTP server 
		 */
	    
	    vo.setSmtpHostName("smtp.gmail.com");           // this is not easily operation but reliable
	    
	   // vo.setSmtpHostName("mail.loans-agent.com");   // this is not reliable to deliver email, not 100% deliver email, some missed
	    
	    
   	    SendHTMLAttachEmails handler = new SendHTMLAttachEmails();
   	    handler.seteVo(eVo);
   	    handler.setVo(vo);
   	    if (null== handler.getSMTPProperty()) {
   	    	LOG.info("Initialize Property  was failed !");
			return false;
   	    }   
   	    
   	  
   	 
   	    handler.SendBulkEmails(footerSection,request);
   	    retVal=true;	    
	    
		return retVal;
	}
	
	/**
	 * Send email to recipients by email list
	 * @return
	 */
	public static boolean sendEmailForAgentWrap (
			   HttpServletRequest request,			 
			   EmailServerForm form,
			   List<RecipientVo> rList,
			   boolean footerSection
			) {
		LOG.info("sendEmailForAgentWrap:begin");
		boolean retVal=false;
		if (request==null) {
			LOG.info("Request is not initialized yet!");
			return false;
		}
		
		/**
		 *  fetch session content: auth user / password , Email List and attachment list
		 */
		ui.setRequest(request);
		/**
		 *  Remove gmail as email sender and set support.staff@loans-agent.com / nus6547299 as temp auth
		 *  
		 *  
		 
		*/
		//String authEmail="support@loans-agent.com";  // dailyrazor mail server is not 100% deliver mail , some missed , not reliable
		//String authPassword="nus658742478";
		
		//String authEmail="support.staff@loans-agent.com";  // Use gmail deliver mail for user but can not post sender email to FROM HEADER always put auth emailto From Header
		//String authPassword="nus6547299";                  // User should not reply to support.staff@loans-agent.com
		 
		String authEmail=(String) request.getSession().getAttribute(Constant.AUTH_EMAIL_ADDRESS);   // keep using user gmail account as AUTH and FROM HEADER
		String authPassword=(String) request.getSession().getAttribute(Constant.AUTH_PASSWORD);		
					
		List<String> aList =(List<String>) ui.getSessionAttributeObject(Constant.ATTACHMENT_LIST);		
		 
		
		LOG.info("List<RecipientVo> list ="+rList);
		if (rList==null) {
			LOG.info("List<RecipientVo> list is empty , failed to send");
			return false;
		}
		
		
		if (authEmail==null || null== authPassword) {
			LOG.info("auth Email  ="+authEmail+",authPassword="+authPassword+" failed to send");
			return false;
		}
		
		LOG.info("Recipient List size=" + rList.size());	
	    
	    
	    List<String> attachPathList=new ArrayList<String>();
	    
	    String destPath = SysPath.getInstance().getAgentEmailAttachmentPath();
	   
		
		
	    if (aList!=null) {
	    	for (String filename: aList) {
	    		
	    		attachPathList.add(destPath+filename);
	    	}

	    }
	    for (String attachPath: attachPathList) {
	    	 LOG.info("Attachment  List file="+attachPath);
    	}
	   
	    /**
	     *  Email EnvelopVo Setting
	     */
	    EmailEnvelopVo eVo = new EmailEnvelopVo();
	    
   	 	eVo.setAttachmentPathList(attachPathList);
   	 	
   		eVo.setRecipients(rList);
   		
   		eVo.setEmailContent(form.getEmailContent());
   		
   	 	Long time = System.currentTimeMillis()/100;
   	 	
   	 //	eVo.setMailSubject(form.getSubject()+" (No. "+time+" )");  // timestamp to prevent collapsed email in gmail or yahoo.com
   		eVo.setMailSubject(form.getSubject());  // timestamp to prevent collapsed email in gmail or yahoo.com
   	 	/**
   	 	 *  If using mail.loans-agent.com , the from email address will be shown up at From Header of email
   	 	 *  
   	 	 */
   	 	eVo.setFromEmailAddress(form.getEmailAddress());
   	 
   		
        SMTPServerVo vo = new SMTPServerVo();			 
		
     
        
	    vo.setAuthEmailAddress(authEmail);
	    vo.setAuthEmailPassword(authPassword);
	    
	    /**
	     *   SMTPServerVo fromEmailAddress is critical , we must set it
	     */
	    vo.setFromEmailAddress(form.getFromEmailAddress());
	    
	  
   	 
		/**
		 * During promotion period (3-6 months), we use webhost smtp server sending email, 200 emails per hours
		 * If agents number increased , we plan to set bulk mail SMTP server 
		 */
	    
	    vo.setSmtpHostName("smtp.gmail.com");           // this is not easily operation but reliable
	    
	   // vo.setSmtpHostName("mail.loans-agent.com");   // this is not reliable to deliver email, not 100% deliver email, some missed
	    
	    
   	    SendHTMLAttachEmails handler = new SendHTMLAttachEmails();
   	    handler.seteVo(eVo);
   	    handler.setVo(vo);
   	    if (null== handler.getSMTPProperty()) {
   	    	LOG.info("Initialize Property  was failed !");
			return false;
   	    }   
   	    
   	  
   	 
   	    handler.SendBulkEmails(footerSection,request);
   	    retVal=true;	    
	    
		return retVal;
	}
	
 
	/**
	 * Send email to recipients by email list
	 * @return
	 */
	public static boolean sendSingleEmailWrap (
			   HttpServletRequest request,			 
			   EmailServerForm form,
			   String smtpServer,
			   String authEmail,
			   String authPassword,
			   List<RecipientVo> rList
			) {
		LOG.info("sendSingleEmailWrap:begin");
		boolean retVal=false;
		if (request==null) {
			LOG.info("Request is not initialized yet!");
			return false;
		}
		
		/**
		 *  fetch session content: auth user / password , Email List and attachment list
		 */
		ui.setRequest(request);
		/**
		 *  Remove gmail as email sender and set support.staff@loans-agent.com / nus6547299 as temp auth
		 *  
		 *  
		 
		*/
					
	 	 
		 
		LOG.info("List<RecipientVo> list ="+rList);
		if (rList==null) {
			LOG.info("List<RecipientVo> list is empty , failed to send");
			return false;
		}
		
		
		if (authEmail==null || null== authPassword) {
			LOG.info("auth Email  ="+authEmail+",authPassword="+authPassword+" failed to send");
			return false;
		}
		
		LOG.info("Recipient List size=" + rList.size());	
	    
	    
	   
	   
	    /**
	     *  Email EnvelopVo Setting
	     */
	    EmailEnvelopVo eVo = new EmailEnvelopVo();	    
    
   	 	
   		eVo.setRecipients(rList);
   		
   		eVo.setEmailContent(form.getEmailContent());
   		
   	 	Long time = System.currentTimeMillis()/100;
   	 	
   	 	eVo.setMailSubject(form.getSubject());  // timestamp to prevent collapsed email in gmail or yahoo.com
   	 	/**
   	 	 *  If using mail.loans-agent.com , the from email address will be shown up at From Header of email
   	 	 *  
   	 	 */
   	 	eVo.setFromEmailAddress(form.getEmailAddress());
   	 
   		
        SMTPServerVo vo = new SMTPServerVo();			 
		
     
        
	    vo.setAuthEmailAddress(authEmail);
	    vo.setAuthEmailPassword(authPassword);
	    
	    /**
	     *   SMTPServerVo fromEmailAddress is critical , we must set it
	     */
	    vo.setFromEmailAddress(form.getFromEmailAddress());
	    
	  
   	 
		/**
		 * During promotion period (3-6 months), we use webhost smtp server sending email, 200 emails per hours
		 * If agents number increased , we plan to set bulk mail SMTP server 
		 */
	    
	    vo.setSmtpHostName(smtpServer);           // this is not easily operation but reliable
	    
	   // vo.setSmtpHostName("mail.loans-agent.com");   // this is not reliable to deliver email, not 100% deliver email, some missed
	    
	    
   	    SendHTMLAttachEmails handler = new SendHTMLAttachEmails();
   	    handler.seteVo(eVo);
   	    handler.setVo(vo);
   	    if (null== handler.getSMTPProperty()) {
   	    	LOG.info("Initialize Property  was failed !");
			return false;
   	    }   
   	    
   	  
   	 
   	    handler.SendBulkEmails(false,request);
   	    retVal=true;	    
	    
		return retVal;
	}
	
	
	
   public static String getEncodedString(String str) {
	   if (str==null || str.trim().length()==0) {
		   return null;
	   }
	   String encoded =null;
	   try {
		   byte[] bytes = str.getBytes("UTF-8");
			  
	       encoded = Base64.encode(bytes); 	   
	   } catch (Exception e) {
		   LOG.info("Failed to encode this String "+str);
		   return null;
	   }
	   return encoded;
   }
	
   public static String getDecodedString(String str) {
	   if (str==null || str.trim().length()==0) {
		   return null;
	   }
	   byte[] decoded =null;
	   try {
		   
			  
	       decoded = Base64.decode(str);  
	   } catch (Exception e) {
		   LOG.info("Failed to encode this String "+str);
		   return null;
	   }
	 
	   
	   return new String(decoded);
   }
	
   public static String convertToSHA256(String password) {
	   StringBuffer hexString =null;
	   try { 
		   MessageDigest md = MessageDigest.getInstance("SHA-256");
		   md.update(password.getBytes());

		   byte byteData[] = md.digest();

		   //convert the byte to hex format method 1
		   StringBuffer sb = new StringBuffer();
		   for (int i = 0; i < byteData.length; i++) {
			   sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		   }

       

		   //convert the byte to hex format method 2
		    hexString = new StringBuffer();
   			for (int i=0;i<byteData.length;i++) {
   			String hex=Integer.toHexString(0xff & byteData[i]);
  	     	if(hex.length()==1) hexString.append('0');
  	     	hexString.append(hex);
  	     	
   			}
   			return hexString.toString();
	  } catch (Exception e) {
		 // LOG.error("Message Digest (SH256) Failed");
		  
	  }
	   return null;
   }
   
   public static String getEmailContentFromURL(String urlString) {
	   LOG.info("getEmailContentFromURL begin");
	   StringBuffer buf = new StringBuffer();
	   BufferedReader in =null;
	   try {
		 
		   URL url = new URL(urlString);
		  
		   
		   in = new BufferedReader( new InputStreamReader(url.openStream()));
		    
		 
		   String inputLine;
		   while ((inputLine = in.readLine()) != null) {
			  
			   buf.append(inputLine);
			//   LOG.info(inputLine);
		   }
		  
		  
	   	} catch (Exception e) {
		   LOG.info("Read URL" + urlString +" failed!");
		   return null;
	   } finally {
		   try {
			   if (in!=null) {
				   in.close();
			   }  
		   } catch (Exception e) {
			  
		   }
	   }
	   String retStr = buf.toString();
	   retStr = deCodeHTML(retStr);
	   return retStr;
   }
     
	public static String excutePostGetResponse(String targetURL, String urlParameters)
	  {
	    URL url;
	    HttpURLConnection connection = null;  
	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/x-www-form-urlencoded");
				
	      connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
	      connection.setRequestProperty("Content-Language", "en-US");  
				
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      wr.writeBytes (urlParameters);
	      wr.flush ();
	      wr.close ();

	      //Get Response	
	     InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      rd.close();
	      return response.toString();

	    } catch (Exception e) {

	      e.printStackTrace();
	      return null;

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	  }
	
	/**
	 * Save states like California, Massachusetts, Delaware and pass the string to selectedStates
	 * @param selectedStates
	 * @return
	 */
	
	public static List<SelectedStateVo> mergeSelectedState(String selectedStates) {
		
		List<String> stateNames = LookupDataInitialServiceImpl.getStateNames();
		List<SelectedStateVo> list= new ArrayList<SelectedStateVo>();
		if (selectedStates==null) return null;
		String selectedArgs[] = selectedStates.split(", ");
		int len = selectedArgs.length;
		 
		for(String state:stateNames) {
			 
			String selected="false";
			for ( int i=0;i<len;i++) {
				if (state.equalsIgnoreCase(selectedArgs[i])) {
					selected="true";
				}
			}
			SelectedStateVo vo = new SelectedStateVo();
			vo.setSelected(selected);
			vo.setStateKey(state);
			vo.setStateName(state);
			list.add(vo);
			
		}
		
		return list;
	}
	public static String delInvalidChar(String str) {
		if (Utility.isEmpty(str)) return null;
		return str.replace(",","").replace(";", "").replace("\"", "").replace("\t", "").replace("'","");
	}
	public static String deCodeHTML(String htmlString) {
		 
		if (htmlString==null || htmlString.trim().length()==0) return null;
		return htmlString.replace("&Acirc;&#160;", "");
	}
	public static String delLastChar(String str) {
	    	if (str==null || str.length()==0) {
	    		return null;
	    	}
	    	int len = str.length();
	    	return str.substring(0,len-1);
	}
   
	public static void main(String args[]) throws Exception {
		/* Integer agent_id = 10008;
		 String destPath = SysPath.getInstance().getAgentEmailAttachmentPath()+"att-"+agent_id.toString()+"/";
		 File file = new File(destPath);
		 if (file.mkdir()) {
			 LOG.info("Create folder succeed!");
		 } else {
			 LOG.info("Create folder failed!");
		 }
		 
		  Integer agentId = 10007;
		 
		  LOG.info("before encripted string ="+agentId.toString());
		  
		  byte[] bytes = agentId.toString().getBytes("UTF-8");
		  
		
		  
		  String encoded = Base64.encode(bytes); 
		  
		  LOG.info("encripted string ="+encoded);
		  
		  byte[] decoded = Base64.decode(encoded);
		  
		  
		  
		 
		  
		  LOG.info("decripted string ="+new String(decoded));
		 
		
		
		 //getEmailContentFromURL("http://localhost:8080/generateAgentEmail.html");
		  LookupDataInitialServiceImpl handler = new LookupDataInitialServiceImpl();
		  
		  handler.initialize();
		  String selectedStates = "California, Massachusetts, Delaware";
		  List<SelectedStateVo> list = mergeSelectedState( selectedStates);
		  for (SelectedStateVo vo:list ) {
			  System.out.println(vo.getStateName()+","+vo.getSelected());
		  }
		  System.out.println(convertToSHA256("abc12345"));
		  
		  String test = "<tr><td style=\"background-color:rgb(225, 255, 255)\">&Acirc;&#160;</td>\n" +
		  		"<tr><td style=\"background-color:rgb(225, 255, 255)\">&Acirc;&#160;</td>"	 ;		
	 
		  System.out.println(deCodeHTML(test) );*/
		//String urlString ="http://localhost:8080/generateAgentEmail.html?paramAgentId=MTAwMDE=&pullALLAgentData=yes&paramMyEmail=denniecha@yahoo.com";
		
		
		//urlString = "http://localhost:8080/generateAgentEmail.html?paramAgentId=MTAwMDE=";
				 
	//	getEmailContentFromURL(urlString);
		  
        String paraAppCheckListIds="1|2|3|4|5|9";
        String arr[] = paraAppCheckListIds.split("\\|");
        System.out.println("arr.length="+arr.length);
	}
	 
}
