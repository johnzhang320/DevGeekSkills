<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

   
<%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	   String agentImage =request.getContextPath()+"/images/agents/denniecha1.png";
	   String leftCapture =imagePath+ (String)request.getParameter("left_capture");
 %>	
 
 <div class="leftArea"> 
        
          <div class="module-title">Welcome to loans-agent.com</div>
                 <div id="errorBox" class="errorblock"></div>
            	 
				  <fieldset >  
				   
				    <logic:equal name="agentAction" value="signup" >
       			     <legend><span class="AccountCreateTableHeader">Congratulation, you successfully sign up </span> </legend>
        			   <br>
        			    <table border="1" cellpadding="1" cellspacing="0" >   
                        <tr><td>Email Address</td><td>${agentForm.emailAddress}</td></tr>
                        <tr><td>First Name</td><td>${agentForm.firstName}</td></tr>
                        <tr><td>Last Name</td><td>${agentForm.lastName}</td></tr>
                         <tr><td>Company Name</td><td>${agentForm.companyName}</td></tr>
                        <tr><td>Experience</td><td>${agentForm.description}</td></tr>
                        <tr><td>Work Phone</td><td>${agentForm.workPhone}</td></tr>
                        <tr><td>Cell Phone</td><td>${agentForm.cellPhone}</td></tr>
                        <tr><td>Picture File</td><td>${agentForm.pictureFilename}</td></tr>
                        <tr><td>Application Form File</td><td>${agentForm.applicationFormFilename}</td></tr>
                        <tr><td>State RE Sales Lic No.</td><td>${agentForm.dreNo}</td></tr>
                        <tr><td>NMSL No.</td><td>${agentForm.nmlsNo}</td></tr>                     
                        <tr><td>Address</td><td>${agentForm.address}</td></tr>
                        <tr><td>City</td><td>${agentForm.city}</td></tr>
                        <tr><td>State</td><td>${agentForm.state}</td></tr>
                        <tr><td>Zip Code</td><td>${agentForm.zipCode}</td></tr>                        
					</table>	
				  </logic:equal>
				     <logic:equal name="agentAction" value="login" >
       			     	<legend><span class="AccountCreateTableHeader">Login Successful, welcome to Loans-Agent.com</span> </legend>
       			     	<h1>Login Successful....</h1>
       			   </logic:equal>
       				
		 		 </fieldset>
       		 
         </div>