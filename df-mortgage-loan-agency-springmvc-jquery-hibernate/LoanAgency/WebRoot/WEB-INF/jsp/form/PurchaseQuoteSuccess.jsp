<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="com.loan.agent.mvc.formbean.QuoteForm" %>

  

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
   <%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";	 
	   String leftCapture =imagePath+ "LeftArea_Welcome.png";
	    
	 
 %>	
 	 
  
 <div class="leftArea"> 
                
                 <div id="errorBox" class="errorblock"></div>
                 <logic:present name="quoteForm" scope="session" >  
                  <bean:define id="qf" name="quoteForm"/>
                   
     	  		  <fieldset >  
       			    <legend>
       			      <span class="AccountCreateTableHeader">Purchase Loan Inquired on <bean:write name="qf" property="modifiedDate"/>
       			       
       			     </span>        			     
       			     </legend>
       			     
       			   <table  style="width: 96%;  text-align: left;" border="1" cellpadding="1" cellspacing="0">
       			     <tr>
       			       <td>Questions and Loan Scenario</td>
       			      </tr>
       			      <tr>
       			     <td>
       			        <bean:write name="qf" property="note"/>  
       			 
       		          </td>
       			     </tr>	     
        			        			    
       			   </table>
       			 
       			    
                    <table style="width: 96%;  text-align: left;" border="1" cellpadding="1" cellspacing="0"> 
                    
       			  	<!-- <tr><td>Note</td><td> <bean:write name="qf" property="note"/></td></tr> -->
       			    <tr><td>Loan Type</td><td> Purchase</td></tr>
       			    <tr><td>Inquired Date and Time</td><td><bean:write name="qf" property="modifiedDate"/></td></tr>
					<tr><td>Loan Amount</td><td> <bean:write name="qf" property="loanAmount"/></td></tr>
					<!-- <tr><td>Loan Amount out 1</td><td><bean:write name="qf" property="loanAmount"/></td></tr>-->
				  	<tr><td>Purchase Price</td><td> <bean:write name="qf" property="purchasePrice"/></td></tr>
				   	
		 		    <tr><td>Borrower Credit Score</td><td> <bean:write name="qf" property="borrowerCreditScore"/></td></tr>
				    <tr><td>CoBorrower Credit Score</td><td> <bean:write name="qf" property="coBorrowerCreditScore"/></td></tr>
					<tr><td>Total Annual Income</td><td> <bean:write name="qf" property="annualIncome"/></td></tr>
			        <tr><td>Total Monthly Income</td><td> <bean:write name="qf" property="monthlyIncome"/></td></tr>
					<tr><td>Loan To Value</td><td> <bean:write name="qf" property="loanToValue"/></td></tr>
					 
					<tr><td>Property Type</td><td> <bean:write name="qf" property="propertyType"/></td></tr>
					<tr><td>Occupancy Status</td><td> <bean:write name="qf" property="occupancyStatus"/></td></tr>
		
				 
					<tr><td>Property State</td><td> <bean:write name="qf" property="propertyState"/></td></tr>					
					<tr><td>Property ZipCode</td><td> <bean:write name="qf" property="propertyZipCode"/></td></tr>		
					<!-- <tr><td>Property County</td><td> <bean:write name="qf" property="propertyCounty"/></td></tr>	-->	
					<!-- ><tr><td>Purchase Stage</td><td> <bean:write name="qf" property="purchaseStage"/></td></tr>
					<tr><td>Purchase Date</td><td> <bean:write name="qf" property="purchaseDate"/></td></tr>
				
					-->
				 </table>
					
					
				</fieldset>	
				  <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">The Borrower Information:</span> </legend>
       			    <table style="width: 96%;  text-align: left;" border="1" cellpadding="1" cellspacing="0"> 	
					<tr><td>First Name</td><td> <bean:write name="qf" property="firstName"/></td></tr>
					<tr><td>Last Name</td><td> <bean:write name="qf" property="lastName"/></td></tr>
					<tr><td>Email Address</td><td> <bean:write name="qf" property="emailAddress"/></td></tr>    				 
					<tr><td>Phone Nnumber</td><td> <bean:write name="qf" property="phoneNo"/></td></tr>
				 
		 			</table>
		 			 </fieldset>
		 			  
       		 </logic:present>
         </div>