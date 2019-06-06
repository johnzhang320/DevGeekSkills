<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="com.loan.agent.mvc.formbean.QuoteForm" %>

  

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
  .fieldsetClass {
     margin: 20px 20px 20px 20px;border:solid 2px lightblue;
    }
     
  
  .tableTitleClass {
       background-color: rgb(102, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;
  }
  .tdLeftClasss {
       background-color: rgb(225, 255, 255); padding-left:10px;color:black;
  }
  .spanClass {
  	   font-size:12px;padding-left:10px;
  }
</style> 
  
  
<div class="fullscreen" >     
            
                  
              
                 <logic:present name="quoteForm" scope="session" >  
                  <bean:define id="qf" name="quoteForm"/>
                   
     	  			 <fieldset style="margin: 20px 20px 20px 20px;border:solid 2px lightblue;">  
     	  			 
     	  			    <legend><span class="AccountCreateTableHeader">Refinance Loan Quoted To <bean:write name="agentObject" property="firstName"/>&nbsp;<bean:write name="agentObject" property="lastName"/></span> </legend>
       			 
       			 
       			  <table>
       			     <tr><td>   			     
       			      <span style="font-weight:bold;"> 
       			       Dear <bean:write name="agentObject" property="firstName"/>&nbsp; 
       			     </span>
       			     </td> 
       			      </tr>
       			      <tr>
       			      <td>
       			           Borrower <bean:write name="qf" property="firstName"/>&nbsp;  <bean:write name="qf" property="lastName"/> 
       			           sent you a refinance loan quote, please copy paste this borrower's email: <strong><bean:write name="qf" property="emailAddress"/></strong>
       			           into 'TO' text box to response this email.<br><br>
       			           
       			           Thanks <br><br>
       			           
       			           Loans-agent.com
       			           
       			           
       			    </td>
       			    </tr>
       			   </table>
       			  <br>
       			   
       			      <table style="width: 96%;  text-align: left;" border="1" cellpadding="0" cellspacing="0"> 
       			
					<tr><td>Quote Note  </td><td>
					 					 
					<bean:write name="qf" property="note"/>  </td></tr>
       			    <tr><td>Loan Type</td><td>Refinance</td></tr>
					<tr><td>Loan Amount</td><td> <bean:write name="qf" property="loanAmount"/></td></tr>
					<tr><td>Loan Amount out 1</td><td><bean:write name="qf" property="loanAmount"/></td></tr>
			 			
					 
					<tr><td>EstimateHomeValue</td><td> <bean:write name="qf" property="estimateHomeValue"/></td></tr>
					<tr><td>Refinance CashOut</td><td> <bean:write name="qf" property="refinanceCashOut"/></td></tr>
					 
					<tr><td>Property State</td><td> <bean:write name="qf" property="propertyState"/></td></tr>
					<tr><td>Property County</td><td> <bean:write name="qf" property="propertyCounty"/></td></tr>
					<tr><td>Property ZipCode</td><td> <bean:write name="qf" property="propertyZipCode"/></td></tr>				
					<tr><td>Property Type</td><td> <bean:write name="qf" property="propertyType"/></td></tr>
					<tr><td>Occupancy Status</td><td> <bean:write name="qf" property="occupancyStatus"/></td></tr>
					 
					 
					 
					<tr><td>Current Initial Loan Amount</td><td> <bean:write name="qf" property="firstLoanBalance"/></td></tr>
					<tr><td>Current Loan Rate</td><td> <bean:write name="qf" property="firstLoanRate"/></td></tr>
					<tr><td>Current Loan Term</td><td> <bean:write name="qf" property="firstLoanTerm"/></td></tr>
					 
				
				   
				
					 </table>
				</fieldset>	
					 <fieldset style="margin: 20px 20px 20px 20px;border:solid 2px lightblue;">  
       			     <legend><span class="AccountCreateTableHeader">The Borrower Information:</span> </legend>
       				    <table style="width: 96%;  text-align: left;" border="1" cellpadding="1" cellspacing="0"> 
       		
					<tr><td>First Name</td><td> <bean:write name="qf" property="firstName"/></td></tr>
					<tr><td>Last Name</td><td> <bean:write name="qf" property="lastName"/></td></tr>
					<tr><td>Email Address</td><td> <bean:write name="qf" property="emailAddress"/></td></tr>    
					<tr><td>Living State</td><td> <bean:write name="qf" property="state"/></td></tr>
					<tr><td>Phone Nnumber</td><td> <bean:write name="qf" property="phoneNo"/></td></tr>
					<tr><td>None Rental Income</td><td> <bean:write name="qf" property="noneRentalIncome"/></td></tr>
					<tr><td>Rental Income</td><td> <bean:write name="qf" property="rentalIncome"/></td></tr>
					<tr><td>Credit Score</td><td> <bean:write name="qf" property="creditScore"/></td></tr>
				</table>
		 			 </fieldset>
		 			  
       		 </logic:present>
         </div>