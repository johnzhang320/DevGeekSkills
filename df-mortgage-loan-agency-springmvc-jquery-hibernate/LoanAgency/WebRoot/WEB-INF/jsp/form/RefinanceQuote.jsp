<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import ="com.loan.agent.mvc.formbean.NicheForm" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
 <%@ page import="com.loan.agent.services.Constant" %> 
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/tablescroll.css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/tablescroll.js"> </script> 

    
 <%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	   String agentImage =request.getContextPath()+"/images/agents/denniecha1.png";
	   String leftCapture =imagePath+ (String)request.getParameter("left_capture");
	   request.getSession().setAttribute("QuoteMode", "Refinance");
	   int line=0; 
	   String  certPicCode =(String)  request.getSession().getAttribute(Constant.CERT_PIC_CODE_STRING);	
	    
 %>	
 <script  type="text/javascript">
/*<![CDATA[*/

jQuery(document).ready(function($)
{
	$('#thetable').tableScroll({height:400});
  
});

/*]]>*/ 
</script>
 <style>
  .noteClass {
  	width:420px;
  	height:100px;
  } 
</style>
 <div class="leftArea"> 
         <div class="module-title">
          Refinance Loan Quote
          <input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFullScreen('/websiteHelp.html?helpTab=tabs-14','750','600');">
       			 
       </div>              
        <div id="errorBox" class="errorblock"></div>
        <form:form  method="POST" name="loan_quote" commandName="QuoteForm">
          		   <input type="hidden" name="loanType" value="refinance" /> 
     	  		    <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Basic Information</span>
       			 	  </legend>
       				    
       		
                    	 <label id="estimateHomeValueLabel"><font color="red">*</font>Estimate Home Value:</label> 
                       	  <form:input path="estimateHomeValue" />
                      <label id="estimateHomeValueMsg" class="errorMsg"></label>   	  
                      <br> 
                      	  <label id="loanAmountLabel"><font color="red">*</font>Current Loan Amount:</label> 
                       			  <form:input path="loanAmount"  maxlength="80" /> 
                    		  <label id="loanAmountMsg" class="errorMsg"></label> 
                   			  <br>   	  
                  			<!--  <label id="firstLoanBalanceLabel"><font color="red">*</font>Initial Loan Amount:</label>
                      		     <form:input path="firstLoanBalance" />
                      		 <label id="firstLoanBalanceMsg" class="errorMsg"></label> 
                       	 	 <br/> -->
                 		 
                    		<label id="firstLoanRateLabel"><font color="red">*</font> Current Interest Rate:</label>
           						 <form:input path="firstLoanRate"  maxlength="100"  cssClass="small" />
           					 <label id="firstLoanRateMsg" class="errorMsg"></label> 
           					 <br/>
                        
                  			<label><font color="red">*</font>Current Term:</label>
                				<form:select path="firstLoanTerm"  cssClass="large">
                      			  <form:option value=""></form:option>
                     			  <form:options items="${loanTermList}"></form:options>
                  			 </form:select>
                  			 <br/>
                  			 
                     <label id="borrowerCreditScoreLabel"><font color="red">*</font>Borrower Credit Score:</label>
                       	<form:input id="borrowerCreditScore" path="borrowerCreditScore"  maxlength="80" />
                     <label id="borrowerCreditScoreMsg" class="errorMsg"></label> 
                      <br> 
                      <label id="coBorrowerCreditScoreLabel">CoBorrower Credit Score:</label>
                       	<form:input path="coBorrowerCreditScore"  maxlength="80" />
                     <label id="coBorrowerCreditScoreMsg" class="errorMsg"></label> 
                      <br> 
                        <label id="annualIncomeLabel"><font color="red">*</font>Total Annual Income:</label>
                       	<form:input id="annualIncome" path="annualIncome"  maxlength="80" />
                     <label id="annualIncomeMsg" class="errorMsg"></label> 
                      <br> 
                      <label id="propertyTypeLabel"><font color="red">*</font>Property Type:</label> 
                        <form:select path="propertyType" cssClass="small">
                 		<form:option value="" />
                 		<form:option value="Single Family House" />
              	    	<form:option value="Multi Family House" />
              	    	<form:option value="Town House" />
              	    	<form:option value="Condo" />
                 	 </form:select> 
                 	 <label id="propertyTypeMsg" class="errorMsg"></label>              
                     
                     
                      <label id="occupancyStatusLabel"><font color="red">*</font>Occupied Status:</label> 
                        <form:select path="occupancyStatus"  cssClass="small">
                 		<form:option value="" />
                 		<form:option value="Owner Lives Inside" />
              	    	<form:option value="Rental Property" />
              	    	<form:option value="Owner's Second Home" />
                 	 </form:select> 
                 	 <label id="occupancyStatusMsg" class="errorMsg"></label> 
                 	 <br>
                
                         <label id="propertyZipCodeLabel">Property Zip:</label>                    
                         <form:input path="propertyZipCode" />                 		 
                  	 <label id="propertyZipCodeMsg" class="errorMsg"></label> 
                     <br> 
                     <label id="propertyStateLabel">Property State:</label> 
                        <form:select path="propertyState"  cssClass="middle">
               	  		 <form:options items="${stateMap}" />
                  		</form:select> 
                  		 <label id="propertyStateMsg" class="errorMsg"></label> 
                         <br>
                         <label><span style="font-size:13px;">Quoted Loan Term:</span></label><br>
                           <div style="padding-left:40px;">
                   	        <form:checkbox path="loanTerms" id="30yr" value="30 years fixed" cssClass="myCheckbox"/><label style="width:80px;text-align:left" >30 years fixed</label> 
	        			  	<form:checkbox path="loanTerms" id="20yr" value="20 years fixed" cssClass="myCheckbox"/><label style="width:80px;text-align:left">20 years fixed</label> 
	    		  		 	<form:checkbox path="loanTerms" id="15yr" value="15 years fixed" cssClass="myCheckbox"/><label style="width:80px;text-align:left">15 years fixed</label> <br>
	    				 	<form:checkbox path="loanTerms" id="10yr" value="10/1 ARM" cssClass="myCheckbox"/><label style="width:80px;text-align:left">10/1 ARM</label>  
	    				 	<form:checkbox path="loanTerms" id="7yr" value="7/1 ARM" cssClass="myCheckbox"/><label style="width:80px;text-align:left">7/1 ARM</label>   
	    					<form:checkbox path="loanTerms" id="5yr" value="5/1 ARM" cssClass="myCheckbox"/><label style="width:80px;text-align:left">5/1 ARM</label> <br>  
	    				 	<form:checkbox path="loanTerms" id="3yr" value="3/1 ARM" cssClass="myCheckbox"/><label style="width:80px;text-align:left">3/1 ARM</label>   
	               	      </div>  
                      <br><br>
         	 <span class="AccountCreateTableHeader">Questions and your Loan Scenario</span>           			 
      	 	  <form:textarea path="note"   cssClass="noteClass"/><br/> 				
            
             <logic:present name="NichesList" scope="session">
 
              <span class="AccountCreateTableHeader">Select  <bean:write name="loginAgentName" /> Distinctive Niche Loan Program   </span> 
   
                <logic:present name="NichesList" scope="session" >             
                   <table  id="thetable" width="97%" border="1" cellpadding="0" cellspacing="0" >
 	            	 <tbody>
  	 	              <logic:iterate id="review" name="NichesList" type="NicheForm" scope="session" indexId="rowIndex"> 
   		                <%if (line%2==0) {%>
      		           	<tr class="rowwhite">    
        	             <%} else { %>
        	              <tr class="rowshade"> 
        	              <%}
        	              line++;        	    
        	              %>
    	            <td width="95%" >
    	       	    <div style="margin-bottom:10px;">  
    	      	        <input type="checkbox" id='<bean:write name="review" property="nicheId"/>'  onClick="javascript:saveCheckedNiche('<bean:write name="review" property="nicheTitle"/>','<bean:write name="review" property="nicheId"/>');">
    		    	</div>    
    	    		<font style="font-size:13px;color:RGB(50,50,50);font-weight:bold;">  
    	       		    <bean:write name="review" property="nicheTitle"/> 
    	     		</font> 
    	     		<font style="font-size:12px;color:RGB(50,50,50);">  
    	   			     <bean:write name="review" property="nicheNote"/> 
    	   	 		</font>
     	   	      </td>                   
   			   	</tr>
          	      </logic:iterate>
          	     </tbody>
 		       </table>
 	          </logic:present>
            </logic:present>    
                 	 
                  	 </fieldset>
                      
     	     <div id="refinanceDisplay" > 
               		 		 
               
              <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Your Contact Information</span> </legend>
      				 <label id="firstNameLabel"><font color="red">*</font>First Name:</label>                   
                         <form:input path="firstName" size="30" maxlength="80"/> 
		            <label id="firstNameMsg" class="errorMsg"></label> 
		      		 <br>    
		      		   
	                <label id="lastNameLabel">Last Name:</label>		                              
                 	     <form:input path="lastName" size="30" maxlength="80"/>
             	    
		      		 <br>
      				 <label  id="emailAddressLabel"><font color="red">*</font>Email Address:</label>
                      	 
                      	 <form:input path="emailAddress" size="35"  maxlength="160" /> 
		      		 <label id="emailAddressMsg" class="errorMsg"></label> 
		      		 <br>
	      	 		   
         		  
                    <label id="phoneNoLabel">Phone No:</label>
                         <form:input path="phoneNo"   maxlength="80" /> 
		            <label id="phoneNoMsg" class="errorMsg"></label> 
		      		 <br>	       
             
            </fieldset>
            	
             </div>    
             
               <input type="hidden"  name="loanType" value="refinance" />
         
        		  
                      <input type="hidden" id="form_action" name="" value="" />
                       
                <fieldset>  
       		     <legend>
       			    <span class="AccountCreateTableHeader">Enter the Text code and then Submit Form </span>  
       			  </legend>  
       			     <input type="hidden" name="realCertPicCode" id="realCertPicCode" value="<%=certPicCode%>"/>
            
       		     <table cellspacing="0" cellpadding="0">
       		       <!-- Line 1 Graph-->
       			     <tr>
       			      <td>
       			     	      <span style="margin-bottom:0px;" >4 Digits Text Code:</span>    
       		         </td>
       			     <td>
       			     	      <img style="margin-left:0px;" id="certImageCode" src="<%=request.getContextPath()%>/generateCertPicCode.html?certPicCodeDigits=4"><br>      
       		          </td>
       		          <td>      		                
       		                 <a href="javascript:changeCertPicCode('4');">Change the code</a>
       		           </td>       		         
       		           </tr>
       		           
       		       <!-- Line 2  Enter code-->     
       		           
       		           <tr>
       		           <td>
         		               <span style="margin-bottom:70px;" id="verifycodeLabel"></span> 
       		                   <span id="enteredCertPicCodeLabel">Enter Text Code:</span>    
       		             
       		          </td>
       		           <td> 
       		                   <input type="text" id="enteredCertPicCode" name="enteredCertPicCode" size="30" />                 		 
                  	   </td>
                  	   <td>	 
                  		       &nbsp; 
                  	           <span id="enteredCertPicCodeMsg" class="errorMsg"></span> 
                  	    </td>
                  	  </tr>   
                  	  
                      <!-- Line 3 Submit code -->        	  
       		          <tr>  
       		          <td>&nbsp;</td>     		 
       		          <td>       		          
					          <table style="border:0px;" >
  								<tr><td>
  					    			 <input type="button" name="sendQuote" value="Submit" class="buttonImage" onClick="javascript: submitform();">
  								</td><td>
               		 	  			 <input type="button" name="cancel" value="Cancel" class="buttonLeft" onClick="javascript: cancelform();">
  								</td></tr>
  							</table>			 
                 	  </td><td>
               				&nbsp; <label id="errorBox" class="errorMsg"></label>  	 
  			  
  			     	  </td><td>
		     		    
					  </tr>
				</table>  	       
            </fieldset>       
               		 
               	    
    			   
				 
					 
             </form:form>
         </div>
         
      <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
 
     <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/zipcodeService.js"> </script>  
         
     <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/refinanceQuote.js"> </script>    
     
      <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/quoteService.js"> </script>  
      