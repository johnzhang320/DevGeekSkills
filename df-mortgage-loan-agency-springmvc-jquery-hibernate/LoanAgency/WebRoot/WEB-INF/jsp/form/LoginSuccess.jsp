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
        
            <img alt="Get 4 mortgage rate quotes and loan comparsions" src="<%=leftCapture%>">
                 <div id="errorBox" class="errorblock"></div>
            	 
				  <fieldset >  
				   
				    <logic:equal name="userAction" value="signup" >
       			     <legend><span class="AccountCreateTableHeader">Your Signup information entered as follows</span> </legend>
       				   
						<label>First Name</label>${LoginForm.firstName}<br>
						<label>Last Name</label>${LoginForm.lastName}<br>
						<label>Email Address</label>${LoginForm.emailAddress}<br>    
						<label>Living State</label>${LoginForm.state}<br>
						<label>Phone Nnumber</label>${LoginForm.phoneNo}<br>
						<label>None Rental Income</label>${LoginForm.noneRentalIncome}<br>
						<label>Rental Income</label>${LoginForm.rentalIncome}<br>
						<label>Credit Score</label>${LoginForm.creditScore}<br>
				  </logic:equal>
				     <logic:equal name="userAction" value="login" >
       			     	<legend><span class="AccountCreateTableHeader">Login Successful, welcome to MortgageLoanAgency.com</span> </legend>
       			     	<h1>Login Successful....</h1>
       			   </logic:equal>
       				
		 		 </fieldset>
       		 
         </div>