<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
	 
<%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	  
		String	 leftCapture =imagePath+ "Agent_Login.png";
	 	  
	   
 %>
	 
		  <form:form  action="agentSignupProcess.html" method="POST" name="agentForm" commandName="agentForm" enctype="multipart/form-data">
              
        
			<div id="errorBox" class="errorblock"></div>
				 <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Please enter your email </span> </legend>
      		
                     <span>We will send an email for you to reset password !</span><br>
                	 
        			 <label  id="emailAddressLabel"><font color="red">*</font>Your Email:</label>
                 	  <form:input path="emailAddress" size="80"  maxlength="250" /> 
		      			 <label id="emailAddressMsg" class="errorMsg"></label> 
		      			 <form:errors path="emailAddress" cssClass="errorMsg" />
		      		 <br><br>
		      		   <div style="padding-left:80px;margin-bottom:20px">
               		    
    			    	<a  href="javascript:forgetSubmitForm()"><img style="border:0px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_sendemail.png" 
    			    		title="Next Pag" width="140" height="30" />
    			    	</a>  			    	      	   			     
					   </div>   
				   </fieldset>
				   </form:form>
				   	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
			  	     <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/agentLogin.js"> </script>  
				   
	 
