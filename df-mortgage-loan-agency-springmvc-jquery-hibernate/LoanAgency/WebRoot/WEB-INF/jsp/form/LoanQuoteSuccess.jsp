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
           <!--  <img alt="Get 4 mortgage rate quotes and loan comparsions" src="<%=leftCapture%>">-->
               
                 <div id="errorBox" class="errorblock"></div>
                 <logic:present name="quoteForm" scope="session" >  
                  <bean:define id="qf" name="quoteForm"/>
                   
     	  		  <fieldset >  
       			    <legend><span class="AccountCreateTableHeader">The Loan information entered as follows</span> </legend>
       			    <table style="width: 96%;  text-align:left;" border="1" cellpadding="0" cellspacing="0"> 
       			    <tr><td>No.</td><td> <bean:write name="qf" property="quoteId"/></td></tr>
       			    <tr><td>Loan Type</td><td> <bean:write name="qf" property="loanType"/></td></tr>
					<tr><td>Loan Amount</td><td> <bean:write name="qf" property="loanAmount"/></td></tr>
					<tr><td>Loan Amount out 1</td><td><bean:write name="qf" property="loanAmount"/></td></tr>
				  	<tr><td>Purchase Price</td><td> <bean:write name="qf" property="purchasePrice"/></td></tr>
				 
				 	<tr><td>Purchase Price</td><td> <bean:write name="qf" property="purchasePrice"/></td></tr>
				 
					<tr><td>Property State</td><td> <bean:write name="qf" property="propertyState"/></td></tr>
					<tr><td>Property County</td><td> <bean:write name="qf" property="propertyCounty"/></td></tr>
					<tr><td>Property ZipCode</td><td> <bean:write name="qf" property="propertyZipCode"/></td></tr>				
					<tr><td>Property Type</td><td> <bean:write name="qf" property="propertyType"/></td></tr>
					<tr><td>Occupancy Status</td><td> <bean:write name="qf" property="occupancyStatus"/></td></tr>
					<tr><td>Purchase Stage</td><td> <bean:write name="qf" property="purchaseStage"/></td></tr>
					<tr><td>Purchase Date</td><td> <bean:write name="qf" property="purchaseDate"/></td></tr>
					<tr><td>Note</td><td> <bean:write name="qf" property="note"/></td></tr>
					
				 </table>
					
					
				</fieldset>	
				  <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Personal information entered as follows</span> </legend>
       			    <table style="width: 96%;  text-align: left;" border="1" cellpadding="0" cellspacing="0"> 	
					<tr><td>First Name</td><td> <bean:write name="qf" property="firstName"/></td></tr>
					<tr><td>Last Name</td><td> <bean:write name="qf" property="lastName"/></td></tr>
					<tr><td>Email Address</td><td> <bean:write name="qf" property="emailAddress"/></td></tr>   
					<tr><td>Phone Nnumber</td><td> <bean:write name="qf" property="phoneNo"/></td></tr> 
				<!-- 	<tr><td>Living State</td><td> <bean:write name="qf" property="state"/></td></tr> 					
					<tr><td>None Rental Income</td><td> <bean:write name="qf" property="noneRentalIncome"/></td></tr>
					<tr><td>Rental Income</td><td> <bean:write name="qf" property="rentalIncome"/></td></tr>
					<tr><td>Credit Score</td><td> <bean:write name="qf" property="creditScore"/></td></tr>-->
				
		 			</table>
		 			 </fieldset>
		 			  
       		 </logic:present>
         </div>