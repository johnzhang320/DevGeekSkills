<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ page import="com.ckeditor.CKEditorConfig" %> 
<%@ page import="com.html.email.editor.ConfigurationHelper" %> 
<%@ page import="com.loan.agent.services.Constant" %>  
<%
	 String imagePath =request.getContextPath()+"/images/layoutImages/";	  
	 Integer  loginAgentId = (Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
	 String authUserName =(String) request.getSession().getAttribute(Constant.AUTH_EMAIL_ADDRESS);
	 String authPassword =(String) request.getSession().getAttribute(Constant.AUTH_PASSWORD);
	 String esConnectionStatus =(String) request.getSession().getAttribute(Constant.EMAIL_SERVER_CONNECTION_STATUS);
 	 String emailListStatus=(String) request.getSession().getAttribute(Constant.EMAIL_LIST_STATUS);
	 String emailSendStatus=(String) request.getSession().getAttribute(Constant.EMAIL_SEND_STATUS);
	 
	 String legendStr="NOTSET";
	// if (null!=authUserName && null!=authPassword && null!=esConnectionStatus && "OK".equalsIgnoreCase(esConnectionStatus)) {
	if (loginAgentId!=null) {
	 	 request.getSession().setAttribute("emailServer","OK");	  
	       legendStr="(1) Upload Email List. (2) Pull Agent Profile Data.";
	 	 
	 } else {
	 	   request.getSession().setAttribute("emailServer","NOTSET");
	 	   legendStr="Upload Email List and Set Connection to your Gmail Account for Non-Registed Agents";
	 }
	 
 %>
 
 <script type="text/javascript">				 
   window.onload=cancelActivityRefresh();
   var timer;
   var IDE_TIME=120*60;    // 2 hours to consider send emails 
   

 	function cancelActivityRefresh() {
 	    clearInterval(timer);
 	}
 	 function goToLogin() {
   	     if (window.opener != null && !window.opener.closed) {
        	  window.opener.location.href = '/agentLogout.html';
         }  else {
              window.open('/agentLogout.html', 'login');
         }
   	      window.open('','_parent','');
          window.close();
       //window.self.close();
      }
  
     attachEvent(window,'load',function(){
	  var idleSeconds = IDE_TIME;   // if no activities for 5 minutes, then close window  
	  var idleTimer;
	  function resetTimer(){
	    clearTimeout(idleTimer);
	    idleTimer = setTimeout(goToLogin,idleSeconds*1000);
	  }
	 
	  attachEvent(document.body,'mousemove',resetTimer);
	  attachEvent(document.body,'keydown',resetTimer);
	  attachEvent(document.body,'click',resetTimer);

	  resetTimer(); // Start the timer when the page loads
	});
 
	function attachEvent(obj,evt,fnc,useCapture){
	  if (obj.addEventListener){
	    obj.addEventListener(evt,fnc,!!useCapture);
	    return true;
	  } else if (obj.attachEvent){
	    return obj.attachEvent("on"+evt,fnc);
	  }
	} 

	
   </script> 
  
   
  <style type="text/css">
      .radioClass {
          margin-bottom:7px;
      }
  </style>
  <br>
  <div class="module-title">
  	 	Agent Email Marketing for 
    	 	<logic:present name="loginAgentId" > 
    	 	     :<bean:write name="loginAgentName" />      	     	     	 
   	        </logic:present>	
   	         <input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFullScreen('/helpAgentEmailMarket.html','800','600');">
   </div>  
     <div id="cProgressSamll" class="progress_dialog">      
		 <img style="margin-left:30%;margin-top:15%;" src="<%=imagePath%>animateCircle.gif" />
		  <div id="emailSendStatus" ></div>	    
	</div>  
    
	
	<div id="errorBox" class="errorblock"></div>
	
	<div id="configureMailServer">
	   	   
	    <fieldset >  
	     
       	     <legend><span ><%=legendStr%></span></legend> 
       	       
       	       <logic:equal name="emailServer" value="NOTSET">
  	         
 		      </logic:equal>
  	           
  	          	 <input type="button"  value="Upload Email List" class="buttonImage" onclick="javascript:popUpFixedName('/uploadDocFile.html','860','650');">
  	        
  	      		  <logic:equal name="emailServer" value="NOTSET">
  	        	     <input type="button" value="Connect Your Gmail" class="buttonLeft"  onClick="javascript:popUpFixedName('/configureEmailServer.html','600','350');">
    	      	  </logic:equal>         	 
    	      	 <logic:notEqual name="emailServer" value="NOTSET">    	      
             		 <input  type="button"  value="Pull Agent Profile Data" class="buttonLeft" onclick="javascript:popUpFixedName('/agentDataForm.html','700','500');">
       	 
        		 </logic:notEqual>
  	         
  	      <br><br>
  		   <logic:equal name="emailServer" value="NOTSET">
  	         <span style="font-weight:bold;">If you do not have Gmail Account , click <a href="javascript:popUpFixedName('https://accounts.google.com/SignUp?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&ltmpl=default','650','500');">here</a>  to create one, If you need to verify your Gmail Account ,Click  
  	         <a href="javascript:popUpFixedName('https://accounts.google.com/ServiceLogin?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&service=mail&ltmpl=default','650','500');">here</a> </span><br>
           </logic:equal>
          </fieldset>
	</div>
	
	 
	 
	<div id="editSendEmail" > 
      <form:form id="emailForm"  method="POST" name="emailForm" commandName="emailServerForm" enctype="multipart/form-data">
                
              <form:hidden path="actionType" id="actionType"/>
              <input type="hidden" name="esConnectionStatus" id="esConnectionStatus" value="<%=esConnectionStatus%>"/>
              <input type="hidden" name="emailListStatus" id="emailListStatus" value="<%=emailListStatus%>"/>
              <input type="hidden" name="authUserName" id="authUserName" value="<%=authUserName%>"/>
              <input type="hidden" name="authPassword" id="authPassword" value="<%=authPassword%>"/>
              <form:hidden id="sendEmailList" path="sendEmailTo"  />
              <form:hidden path="fromEmailAddress" id="fromEmailAddress"  /> 
              <logic:present name="emailSendStatus" >
        		<div>
                     <span style="font-weight:bold; color:rgb(28,142,62);margin-left:30px; font-size:15px;"><bean:write name="emailSendStatus"/></span>
                 </div>
                 <br>         	   
                </logic:present>
              <form:hidden path="emailList" id="emailList"/>
        
	    	  
	    	    <fieldset >  
       			     <legend><span >(3) Fill Subject. (4) Review/Edit Email Content. (5) If need, Save/Upload Email or Load Attachments. (6) Send Email.</span> </legend>
 		             
		              <logic:present name="LoginAgentId"> 	
                	      	 <label id="pictureContentLabel"><font color="red">*</font>Attaching Profile Photo:</label>
                	   	    <img width="133" height="180" alt="" id="agentImage" src="<%=request.getContextPath()%>/getAgentPicture.html" />    
                	   	     <br>            	   	 
		              </logic:present>
		          
		      	    
		      		 
     	 		    <label id="subjectLabel" style="font-weight:bold;"><font color="red">*</font>Subject:</label>                   
                         <form:input path="subject" id="subject" size="80" maxlength="250" />              
		            <br> 
		      		   
	               	  
 		      		  <logic:present name="attachmentList" >
 		      		     <table>
 		      		       <tr><td>
 		      		       <label id="subjectLabel" style="font-weight:bold;">Current Attachements:</label>
 		      		       </td>
 		      		       <td align="left"> 
 		      		         <logic:iterate id="attachment" name="attachmentList" type="java.lang.String">	      	
 		      		             <a  href="javascript:popUpFixedName('/uploadAttachment.html','700','500');">	         		       
 		      		               <font > <bean:write name="attachment"/></font>	  
 		      		            </a> ,
 		      		         </logic:iterate>	
 		      		       </td></tr>
 		      		      	      
 		      		       
 		      		     </table>    
 	    			 	   	      
 		      		  </logic:present>  
 		      		  
 		      	  
 		      		   <label id="subjectLabel" style="font-weight:bold;">Email Functions:</label>  
       			 		 <input  type="button" name="saveEmai" value="Save Email" class="buttonImage" onclick="javascript:downloadEmailContent();">
      			 		 <input  type="button" name="attachment" value="Attachment" class="buttonLeft" onclick="javascript:popUpFixedName('/uploadAttachment.html','700','500');">
      			 	  <!--    <input  type="button" name="Encrypt" value="Encrypt" class="buttonLeft" onclick="javascript:EncryptEmail();">-->
      			 	
      			 		 <input  type="button" name="sendEmail" value="Send Email" class="buttonLeft" onclick="javascript:sendBatchEmail();">
     		    	     <input  type="button" name="cleanUpEmail" value="Clean Up Email" class="buttonLeft" onclick="javascript:cleanEmailAll();">

 		      		   
 		      	 
 		      	    <br><br>
 		      	    <label id="expertise" style="font-weight:bold;">Upload Email File:</label> 
 		      	         <input id="fileContent" type="file" name="fileContent"  style="width:420px;" onChange="javascript:uploadEmailContent();" /> 
           			      <br>
           			         <label id="expertise" style="font-weight:bold; font-size:15px;">Email Content:</label> 
           			       <br>
           			   <form:textarea path="emailContent" id="emailContent" />		
           		
		      		<br> 
		      		 <input  type="button" name="sendEmail" value="Send Email" class="buttonLeft" onclick="javascript:sendBatchEmail();">
	   			     <input  type="button" name="cleanUpEmail" value="Clean Up Email" class="buttonLeft" onclick="javascript:cleanEmailAll();">
		      		
 		      		 
                   <br><br>
                
            </fieldset>   
              		  
    			     
    			    	 	 
    			    	      	   			     
					 
			 </form:form>	
	</div>		
	 
	     <ckeditor:replace replace="emailContent" basePath="ckeditor/"  
	         config="<%= ConfigurationHelper.createConfig() %>" /> 
   		  
	    
	   			   
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/form/emailMarketing.js"> </script>  
