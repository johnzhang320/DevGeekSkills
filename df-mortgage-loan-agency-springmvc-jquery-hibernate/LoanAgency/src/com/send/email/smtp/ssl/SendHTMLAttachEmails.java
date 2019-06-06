package com.send.email.smtp.ssl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Authenticator;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.*;
import org.jfree.util.Log;
 

import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.Constant;
import com.loan.mvc.validator.ValidateHelp;
import com.sun.mail.smtp.SMTPSSLTransport;

public class SendHTMLAttachEmails {
	public static Logger LOG=Logger.getLogger(SendHTMLAttachEmails.class);
	private Properties props;
	private SMTPServerVo vo;
	private EmailEnvelopVo eVo;
	private Session session;
	private String emailContent;
	
	public SendHTMLAttachEmails() {		
		
	}
	
	public SendHTMLAttachEmails(SMTPServerVo vo,EmailEnvelopVo eVo) {		
		this.vo = vo;
		this.eVo = eVo;	
		getSMTPProperty();
		
		
	}
	
	public SendHTMLAttachEmails(String gmailEmail,String gmailPassword) {		
		SMTPServerVo vo = getGmailSTMPServer(gmailEmail, gmailPassword);
		this.vo = vo;
		getSMTPProperty();		 
		
	}
	
	public SMTPServerVo getVo() {
		return vo;
	}

	public void setVo(SMTPServerVo vo) {
		this.vo = vo;
	}

	public EmailEnvelopVo geteVo() {
		return eVo;
	}

	public void seteVo(EmailEnvelopVo eVo) {
		this.eVo = eVo;
	}
	
	public SMTPServerVo getGmailSTMPServer(String gmailEmail,String gmailPassword) {
		SMTPServerVo vo = new SMTPServerVo();	   
	   LOG.info("gmailEmail="+gmailEmail+",gmailPassword="+gmailPassword);
	   vo.setAuthEmailAddress(gmailEmail);
	   vo.setAuthEmailPassword(gmailPassword);
	   return vo;
	   
	}
	
	public String getFinalEmailContent(String recipientName) {
		return eVo.greetingWord+" "+ recipientName+",<br>"+eVo.emailContent+"<br><br>Thanks and best regards<br><br>"+eVo.getSenderName()+"<br><br>"+
	                    eVo.getSenderCorp()+"<br>"+eVo.getSenderPhone()+"<br>"+eVo.getSenderAddress();
		
	}
	public String getFinalEmailContent(String recipientName, boolean footerSection) {
		String content =  eVo.greetingWord+" "+ recipientName+",<br>"+eVo.emailContent;
		
		if (footerSection) {
	    			
			content +=	"<br><br>Thanks and best regards<br><br>"+eVo.getSenderName()+"<br><br>"+
	                    eVo.getSenderCorp()+"<br>"+eVo.getSenderPhone()+"<br>"+eVo.getSenderAddress();
		}
		return content;
		
	}
	public Properties getSMTPProperty() {
		    props = new Properties();
	        props.put("mail.smtp.auth", "true");
	         
	       	if (this.vo==null) {       		            		
	        	LOG.info("SMTPServerVo vo not initialized!");
	        	return null;
	       	}	
	       	
	     	if (null==this.vo.getAuthEmailAddress() || null==this.vo.getAuthEmailAddress()) {       		            		
	        	LOG.info("Authenticated User and Password have not been initialized!");
	        	return null;
	       	}	
	       	
			props.put("mail.smtp.submitter", this.vo.getAuthEmailAddress());
			props.put("mail.smtp.user", this.vo.getAuthEmailAddress());
			props.put("mail.smtp.host", this.vo.getSmtpHostName());
			props.put("mail.debug", this.vo.getDebugMode());
			props.put("mail.smtp.port", this.vo.getSmtpPort());
			props.put("mail.smtp.ssl.socketFactory.port", this.vo.getSmtpPort());
			props.put("mail.smtp.ssl.socketFactory.class", this.vo.getSslFactory());
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.starttls.enable", "true");
	        
	        return props;

	}
	
    private class UserAuthenticator extends Authenticator {
        private PasswordAuthentication authentication;
     
        public UserAuthenticator(String username,String password)
        {
          
            authentication = new PasswordAuthentication(username, password);
        }
     
        protected PasswordAuthentication getPasswordAuthentication() {
           return authentication;
        }
     }

    private static  MimeBodyPart getAttachment(String fileNameToAttach) throws MessagingException
    {
		File file = new File(fileNameToAttach);
		if (!file.exists()) {
			return null;
		}
		
        MimeBodyPart mimeBodyPart = new MimeBodyPart();        
    	FileDataSource fileDataSource = new FileDataSource(fileNameToAttach) {		 
			public String getContentType() {
				return "application/octet-stream";
			}
		};
        
        mimeBodyPart.setDataHandler(new DataHandler(fileDataSource));
        
        
        mimeBodyPart.setFileName(file.getName());
 
        return mimeBodyPart;
    }
	
    public void SendBulkEmails()   {
    	LOG.info("SendBulkEmails() begin");
    	try {
    		List<RecipientVo> toList = eVo.getRecipients();
    		if (null==toList || toList.size()==0)  {
    			LOG.info("RecipientVo toList is empty! ");
    			throw new MessagingException("Not set up recipient email address yet !");
    		}
    		
    		InternetAddress[] addressTo = new InternetAddress[1];
		 
    		for (RecipientVo rVo: toList) {
    			addressTo[0]=new InternetAddress(rVo.getRecipientEmailAddress());
    			String recipientFirstName=" ";
    			 
    			recipientFirstName = rVo.getRecipientFirstName();
    			 
    			LOG.info("Send to  "+ rVo.getRecipientFullName()+", email address: "+rVo.getRecipientEmailAddress());
    			
    			emailContent = getFinalEmailContent(recipientFirstName);
    			Message msg =setMessage(addressTo);
    			if (msg==null) {
    				LOG.info("Email Message has not initialized!" );
    			} else {
    				sendEmail(msg);     			 
    			    LOG.info("Successfully sent to recipient email address: "+ rVo.getRecipientEmailAddress());
    			}
    		} 
    			
    	} catch (MessagingException e) {
    		e.getStackTrace();
    		System.out.println(e.getMessage());
    	}
		
    }
    /**
     * Send email without status
     * @param footerSection
     */
    public void SendBulkEmails(boolean footerSection)   {
    	
    	LOG.info("SendBulkEmails() begin");
    	try {
    		List<RecipientVo> toList = eVo.getRecipients();
    		if (null==toList || toList.size()==0)  {
    			LOG.info("RecipientVo toList is empty! ");
    			throw new MessagingException("Not set up recipient email address yet !");
    		}
    		
    		InternetAddress[] addressTo = new InternetAddress[1];
		 
    		for (RecipientVo rVo: toList) {
    			addressTo[0]=new InternetAddress(rVo.getRecipientEmailAddress());
    			String recipientFirstName=" ";
    			 
    			recipientFirstName = rVo.getRecipientFirstName();
    			 
    			LOG.info("Send to  "+ rVo.getRecipientFullName()+", email address: "+rVo.getRecipientEmailAddress());
    			
    			emailContent = getFinalEmailContent(recipientFirstName,footerSection);
    			 
    			Message msg =setMessage(addressTo);
    			if (msg==null) {
    				LOG.info("Email Message has not initialized!" );
    			} else {
    				sendEmail(msg);     			 
    			    LOG.info("Successfully sent to recipient email address: "+ rVo.getRecipientEmailAddress());
    			}
    			 
    		} 
    		LOG.info("Successfully send to All recipients per Email List , total "+ toList.size()+" emails have been sent! ");	
    	} catch (MessagingException e) {
    		e.getStackTrace();
    		System.out.println(e.getMessage());
    	}
		
    }
    
    /**
     * Send email with status
     * @param footerSection
     */
    public void SendBulkEmails(boolean footerSection,HttpServletRequest request)   {
    	
    	//LOG.info("SendBulkEmails() begin");
    	try {
    		List<RecipientVo> toList = eVo.getRecipients();
    		if (null==toList || toList.size()==0)  {
    			LOG.info("RecipientVo toList is empty! ");
    			throw new MessagingException("Not set up recipient email address yet !");
    		}
    		
    		InternetAddress[] addressTo = new InternetAddress[1];
		    ui.setRequest(request);
		    String status = null;
		    Integer count=0;
    		for (RecipientVo rVo: toList) {  // loop to send emails based email list
    			addressTo[0]=new InternetAddress(rVo.getRecipientEmailAddress());
    			String recipientFirstName=" ";
    			String emailAddress = rVo.getRecipientEmailAddress();
    			if (Utility.isEmpty(emailAddress) || !ValidateHelp.isValidEmail(emailAddress)) {
    				continue;
    			}
    			recipientFirstName = rVo.getRecipientFirstName();
    			status= "Sending to  "+ rVo.getRecipientFirstName()+" by email address: "+rVo.getRecipientEmailAddress();
    			//LOG.info(status);
    			//ui.setSessionAttribute(Constant.EMAIL_SEND_STATUS, status);
    			
    			emailContent = getFinalEmailContent(recipientFirstName,footerSection);
    			Message msg =setMessage(addressTo);
    			if (msg==null) {
    				LOG.info("Email Message has not initialized!" );
    			} else {
    				sendEmail(msg);     			 
    				count++;    			 
    				status=count.toString()+", Sent to "+ rVo.getRecipientFirstName()+" by email address: "+rVo.getRecipientEmailAddress();
    				LOG.info(status);
    				ui.setSessionAttribute(Constant.EMAIL_SEND_STATUS, status);
    			}
    		} 
    		status= "Successfully send to All recipients, total "+ count.toString()+" recipient's emails have been sent! ";
    		LOG.info(status);	    		
    		ui.setSessionAttribute(Constant.EMAIL_SEND_STATUS, status);
    	} catch (MessagingException e) {
    		e.getStackTrace();
    		String status= "Error Occured, Terminate the sending! ";
    		LOG.info(status);	    		
    		ui.setSessionAttribute(Constant.EMAIL_SEND_STATUS, status);
    		System.out.println(e.getMessage());
    	}
		
    }
    public String getEmailServerConnectionStatus() {
    	try {
    		LOG.info("getEmailServerConnectionStatus()  begin"); 
    		LOG.info("vo.getAuthEmailAddress()="+vo.getAuthEmailAddress()+",vo.getAuthEmailPassword()="+vo.getAuthEmailPassword());
    		UserAuthenticator userAuth= new UserAuthenticator(vo.getAuthEmailAddress(),vo.getAuthEmailPassword());
    	 
  			session = Session.getDefaultInstance(props,userAuth);	
  		 
  			if (session==null) {
  				LOG.info("Connection Failed");
  				return "Create Session Failed";
  			}
  			
 			SMTPSSLTransport transport = (SMTPSSLTransport) session.getTransport("smtps");
 		 
 			
 	 		transport.connect(vo.getSmtpHostName(), new Integer(vo.getSmtpPort()).intValue(), vo.getAuthEmailAddress(), vo.getAuthEmailPassword());
 	 	 
 	 		
    	     LOG.info("transport.isConnected()="+transport.isConnected());		 
 			 if (transport.isConnected()) {
 				LOG.info("Connection OK !");
 				 return "OK";
 			 }  
    	} catch (Exception e){
    		 
    		LOG.info("getEmailServerConnectionStatus()  Exception ! message="+e.getMessage()); 
    		e.printStackTrace();
    		return "Wrong gmail username or password";
    	}
    	 
    	Log.info("Connection Failed End !");
    	return "Connection Failed";
    }
    
	public Message setMessage(InternetAddress[] addressTo) throws MessagingException {
		 
		    if (this.vo==null) {		    	
		    	LOG.info("SMTPServerVo vo has not been initialized!");
		    	return null;
		    }
		    
		    
		    
		    UserAuthenticator userAuth= new UserAuthenticator(vo.getAuthEmailAddress(),vo.getAuthEmailPassword());
			session = Session.getDefaultInstance(props,userAuth);			 

			session.setDebug(Boolean.parseBoolean(vo.getDebugMode()));

			Message msg = new MimeMessage(session);
			LOG.info("From Email Address="+vo.getFromEmailAddress());
			InternetAddress addressFrom = new InternetAddress(vo.getFromEmailAddress());
			msg.setFrom(addressFrom);		
			
			
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setSubject(eVo.getMailSubject());
			
			/**
			 *   MimeMultipart is a pass way to contain multiple contents
			 *   where allow user add MimeBodyPart object, then setContent to message object
			 *   
			 */
			
			MimeMultipart multipart = new MimeMultipart();
			
			List<String> attachedPathList = eVo.getAttachmentPathList();
			 
			if (null==attachedPathList || 0==attachedPathList.size()) {
				msg.setContent(emailContent, "text/html");
			} else {
				/**
				 *  Add email content to Multipart object first
				 */
				MimeBodyPart mimeBodyPart = new MimeBodyPart();
				mimeBodyPart.setContent(emailContent, "text/html");
				multipart.addBodyPart(mimeBodyPart);
				/**
				 *  Add multiple attachments to Multipart object
				 */
				for (String aPath:attachedPathList) {
					mimeBodyPart =  getAttachment(aPath);
					if (mimeBodyPart!=null) {
						multipart.addBodyPart(mimeBodyPart);
					}
				}
				/**
				 *  Add current Multipart object Message object
				 */
				msg.setContent(multipart, "text/html");
			}
			 
			return msg;
			
	 }
	 
     public void sendEmail(Message msg) throws MessagingException {
    //	LOG.info("call sendEmail begin ");
    	SMTPSSLTransport transport = (SMTPSSLTransport) session.getTransport("smtps");
 		transport.connect(vo.getSmtpHostName(), new Integer(vo.getSmtpPort()).intValue(), vo.getAuthEmailAddress(), vo.getAuthEmailPassword());
 		msg.saveChanges();
 		
 		transport.sendMessage(msg, msg.getAllRecipients());
 		transport.close();
 	//	LOG.info("call sendEmail end ");
	 
     }
     
     public boolean sendSingleEmail(
    		  		String gmailUsername,
    		  		String gmailPassword,
    		  		String fromEmailAddress,
    		  		String subject,
    		  		String recipientEmailAddress,
    		  		String recipientGreetingName,
    		  		List<String> attachPathList,
    		  		String emailContent,
    		  		String senderName,
    		  		String senderCorp,
    		  		String senderPhone,
    		  		String senderAddress    		  		
    		    ) {
    	   SMTPServerVo vo = new SMTPServerVo();
      	   vo.setAuthEmailAddress(gmailUsername);
      	   vo.setAuthEmailPassword(gmailPassword);      	 
      	   vo.setFromEmailAddress(fromEmailAddress);
      	   
      	   List<RecipientVo> rList = new ArrayList<RecipientVo>();
      	   RecipientVo rVo = new RecipientVo();    	
      	   rVo.setRecipientEmailAddress(recipientEmailAddress);
      	   rVo.setRecipientFirstName(recipientGreetingName);
           rList.add(rVo);     
      	 
      	 
      	   EmailEnvelopVo eVo = new EmailEnvelopVo();
	       if (attachPathList!=null && attachPathList.size()>0) {
	    		 eVo.setAttachmentPathList(attachPathList);
      	   }      
      	   eVo.setRecipients(rList);
      	   Long time = System.currentTimeMillis()/100;
      	  // eVo.setMailSubject(subject+" (No. "+time+" )");
      	   eVo.setMailSubject(subject);
      	   eVo.setFromEmailAddress(fromEmailAddress);
      	   if (senderName!=null)
      	   eVo.setSenderName(senderName);
      	   if (senderCorp!=null)
      	   eVo.setSenderCorp(senderCorp);
      	   if (senderPhone!=null)
      	   eVo.setSenderPhone(senderPhone);
      	   if (senderAddress!=null)
      	   eVo.setSenderAddress(senderAddress);
      	   
      	   eVo.setEmailContent(ui.deCodeHTML(emailContent));
      	 
      	 
      	   SendHTMLAttachEmails handler = new SendHTMLAttachEmails();
      	    handler.seteVo(eVo);
      	    handler.setVo(vo);
      	    if (null== handler.getSMTPProperty()) {
      	    	LOG.info("Initialize Property  was failed !");
   			    return false;
      	    }   
      	   handler.SendBulkEmails();
      	   return true;
      	 
     }
     
     public static void main(String[] args) throws Exception {
    	 
    	 
    	LOG.info("Test Begin");
    	 
        SMTPServerVo vo = new SMTPServerVo();
    	 vo.setAuthEmailAddress("loansagentfix@gmail.com");
    	 vo.setAuthEmailPassword("abc12345!");
    	// SendHTMLAttachEmails handler = new  SendHTMLAttachEmails(vo.getAuthEmailAddress(),vo.getAuthEmailPassword());
    	 
    	// LOG.info("ConnectionStatus="+handler.getEmailServerConnectionStatus());
    	 
    	 vo.setFromEmailAddress("jzhang@sonicwall.com");
    	 List<RecipientVo> rList = new ArrayList<RecipientVo>();
    	 RecipientVo rVo = new RecipientVo();    	
    	 rVo.setRecipientEmailAddress("johnz148@yahoo.com");
    	 rVo.setRecipientFirstName("John");
    	 rList.add(rVo);
    	/* rVo = new RecipientVo();
    	 rVo.setRecipientEmailAddress("denniecha@yahoo.com");
    	 rVo.setRecipientFirstName("Dennie");
    	 rList.add(rVo);
    	 rVo = new RecipientVo();    	 
    	 rVo.setRecipientEmailAddress("john.zhang320@gmail.com");
    	 rVo.setRecipientFirstName("John");
    	 rList.add(rVo);
    	 rVo = new RecipientVo();
    	 rVo.setRecipientEmailAddress("dennie.cha@baycalfinancial.com");
    	 rVo.setRecipientFirstName("Dennie");*/
    	// rList.add(rVo);
    	// rVo = new RecipientVo();
    	// rVo.setRecipientEmailAddress("jzhang@sonicwall.com");
    	// rVo.setRecipientFirstName("jzhang");
    	// rList.add(rVo);
    	 rVo = new RecipientVo();
    	 rVo.setRecipientEmailAddress("dennie.cha689@gmail.com");
    	 rVo.setRecipientFirstName("Dennie");
    	 rList.add(rVo);
    	 
    	 List<String> aList=new ArrayList<String>();
    	 aList.add("C:/agent_pictures/bob.png");
    	 aList.add("C:/agent_pictures/dennie_cpv_ad.png");
    	 aList.add("C:/agent_pictures/joiceHe.png");
    	 EmailEnvelopVo eVo = new EmailEnvelopVo();
    	 eVo.setAttachmentPathList(aList);
    	 eVo.setRecipients(rList);
    	 Long time = System.currentTimeMillis()/100;
    	// eVo.setMailSubject("Loans_Agent.com (No. "+time+" )");
    	 eVo.setMailSubject("Loans_Agent.com ");
    	 eVo.setFromEmailAddress("jzhang@sonicwall.com");
    	 eVo.setSenderName("John Zhang");
    	 eVo.setSenderCorp("Dell Sonicwall");
    	 eVo.setSenderPhone("408-643-2310");
    	 eVo.setSenderAddress("1364 Carlsen Way, San Jose, CA 95118");
    	 eVo.setEmailContent("<br>This email is HTML format , attached three pictures and send to four recipients<br>");
    	 
    	 SendHTMLAttachEmails handler = new SendHTMLAttachEmails(vo,eVo);
    	 
    	 handler.SendBulkEmails();
    	 
     }
	
}
