<br><%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  
<%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	   String agentImage =request.getContextPath()+"/images/agents/denniecha1.png";
	   String leftCapture =imagePath+ (String)request.getParameter("left_capture");

 %>
 <script>
  $("#password").blur(function () {
 		 var pass = $("#password").val();	 
 		 alert(pass);
 		 if (pass==null || pass.substring(0,1)==" ")
 			 return;
 		 var password= document.forms['loan_quote'].password.value;
 		 
	   	 //  var encrypted_password = convertToSHA256(password);
	   	 //  document.forms['loan_quote'].password.value=encrypted_password;
 	 });	
 </script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  	
    
     <div class="externalcontent">
         <div class="leftArea"> 
           <img alt="Get 4 mortgage rate quotes and loan comparsions" src="<%=leftCapture%>">
           <div id="errorBox" class="errorblock"></div>
            <!--    <fieldset ><span class="AccountCreateTableHeader">You Contact Information only for On Duty Agent </span></fieldset>
               -->
             <form:form  method="POST" name="loan_quote" commandName="QuoteForm">
     	  	    <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Simple Personal Information</span> </legend>
      				
      				 <label  id="emailAddressLabel"><font color="red">*</font>Email Address:</label>
                      	 <form:input path="emailAddress"  maxlength="150" cssClass="width:300px;"/> 
		      		 <label id="emailAddressMsg" class="errorMsg"></label> 
		      		 <br>
		      		 
		      		 <label  id="passwordLabel"><font color="red">*</font>Password:</label>
                  		<form:password path="password" id="password" maxlength="150" /> 
		             <label id="passwordMsg" class="errorMsg"></label> 
		      		 <br>	 
		      		 
     	 		    <label id="firstNameLabel"><font color="red">*</font>First Name:</label>                   
                         <form:input path="firstName"  cssClass="firstName"/> 
		            <label id="firstNameMsg" class="errorMsg"></label> 
		      		 <br>    
		      		   
	                <label id="lastNameLabel">Last Name:</label>		                              
                 	     <form:input path="lastName"  maxlength="50"/>
             	    
		      		 <br>
         		  
                    <label id="phoneNoLabel">Phone No:</label>
                         <form:input path="phoneNo"   maxlength="80" /> 
		            <label id="phoneNoMsg" class="errorMsg"></label> 
		      		 <br>	       
                  
                    <label>Living State:</label>
               
                      <form:select path="state" cssClass="width:300px;">
                 	   <form:option value="" />
              	       <form:options items="${stateMap}" />
                      </form:select> 
		         
            
                   <label id="noneRentalIncomeLabel"><font color="red">*</font>Monthly Non Rental Income:</label>
                      <form:input path="noneRentalIncome"  maxlength="250" />  
                   <label id="noneRentalIncomeMsg" class="errorMsg"></label> 
		      	   <br>
		      	  
		          <label>Monthly Rental Income:</label>
                      <form:input path="rentalIncome"  maxlength="250" /> 
                      <label id="rentalIncomeMsg" class="errorMsg"></label>  
		      		 <br>
		      	   <label><font color="red">*</font>Monthly Debt:</label>
                      <form:input path="monthlyDebt"  maxlength="250" /> 
                      <label id="monthlyDebtMsg" class="errorMsg"></label>  
		      		 <br>	 
                  <label>Credit Score:</label>
                     <form:input path="creditScore" maxlength="150" cssClass="width:300px;" /> 
                      <label id="creditScoreMsg" class="errorMsg"></label>  
                  <br>
            </fieldset>
            
                <!--Hidden Input's name could be _target1 and value="Previous" or _target2 and value="Target2" 
                     set by the javascript:previousForm_previous() or javascript:submitForm() 
                -->		    
           		 <input type="hidden" id="form_action" name="" value="" />
              		 
           		  <div style="padding-left:80px;margin-bottom:20px">
               		    <a  href="javascript: previousForm()"><img style="border:0px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_previous.png" 
    			    		title="Next Pag" width="80" height="28" />
    			    	</a>
    			    	<a  href="javascript: submitForm()"><img style="border:0px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_submit.png" 
    			    		title="Next Pag" width="80" height="28" />
    			    	</a>
    			    	 
    			    	<a  href="javascript: cancelform()"><img style="border:0px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_cancel.png" 
    			    		title="cancel this page" width="80" height="28" />
    			    	</a>
       	   			     
					</div>        
				
             </form:form>
         </div>
          
  
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
        
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/userPersonal.js"> </script>  
 