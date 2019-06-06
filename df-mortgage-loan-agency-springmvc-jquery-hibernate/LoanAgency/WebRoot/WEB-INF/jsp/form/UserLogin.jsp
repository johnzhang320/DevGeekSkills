<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  
<%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	   String agentImage =request.getContextPath()+"/images/agents/denniecha1.png";
	   String leftCapture =imagePath+ "LeftArea_Welcome.png";
	   String userAction = request.getParameter("userAction");
	   request.getSession().setAttribute("userAction", userAction);
	      if (userAction!=null) {
	   		if (userAction.equalsIgnoreCase("login")) {
		   		leftCapture =imagePath+ "Borrower_Login.png";
	  		 }
	  	
	   		if (userAction.equalsIgnoreCase("signup")) {
		       leftCapture =imagePath+ "Borrower_Signup.png";
	        }
	       if (userAction.equalsIgnoreCase("forget")) {
		      leftCapture =imagePath+ "Borrower_Login.png";
	        } 
	    }
	 
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  	    <!-- User Login Action keyword relay to controller onSubmit method -->
        <c:set var="userAction" value="${param.userAction}" scope="session" /> 
      <!--   <c:out value="${param.userAction}" /> -->
         <div class="leftArea"> 
           <img alt="Get 4 mortgage rate quotes and loan comparsions" src="<%=leftCapture%>">
           <div id="errorBox" class="errorblock"></div>
             
             <form:form  id="loginForm" method="POST" name="loginForm" commandName="loginForm">
            	 
           		<c:if test="${param.userAction=='login'}"> 
           		 
                 <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Borrower Login </span> </legend>      		
                  
                	 <span>Borrower User Name is your email address</span>  <br><br>
                	 <table>
        				<tr>
        				<td style="text-align:left;width:100px;"> 
        				    <span  id="emailAddressLabel"><font color="red">*</font>Borrower Name:</span>
        				</td><td style="text-align:left;">
                      	 <form:input path="emailAddress" size="57"  maxlength="160" /> 
		      		      <label id="emailAddressMsg" class="errorMsg"></label>
		      			    
		      			    <br><form:errors path="emailAddress" cssClass="errorMsg" /> 
		      		     </td>
		      		     </tr>
		      		     <tr>
		      		     <td>
		      		     
		      			     <span  id="passwordLabel"><font color="red">*</font>Password:</span>
		      			 </td><td>
                  			<form:password path="password" size="57"  maxlength="160" /> 
		            	 <label id="passwordMsg" class="errorMsg"></label> 
		      			 <br><form:errors path="password" cssClass="errorMsg" /> 
		      			 </td>
		      			 </tr>
		      			</table> 	
        			 
		      			 	
		      				<a  href="javascript:loginSubmitForm()"><img style="border:0px;padding-left:160px;padding-top:20px;padding-bottom:20px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_login.png" 
    			    		title="User Login Submit" width="80" height="30" /></span>
    			    		<br>
    			    	  </a>   
    			        <br>
    			 </fieldset>  	 
           	 
              	</c:if>  
              	
              	<c:if test="${param.userAction=='signup'}">
	    	 
	    	    <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Borrower Sign Up</span> </legend>
      				
      				 <label  id="emailAddressLabel"><font color="red">*</font>Email Address:</label>
                      	 <form:input path="emailAddress"  maxlength="150" /> 
		      		 <label id="emailAddressMsg" class="errorMsg"></label> 
		      		 <br>
		      		 
		      		 <label  id="passwordLabel"><font color="red">*</font>Password:</label>
                  		<form:password path="password"  maxlength="150" /> 
		             <label id="passwordMsg" class="errorMsg"></label> 
		      		 <br>	 
		      		 
		      		 <label  id="confirmPasswordLabel"><font color="red">*</font>Confirm Password:</label>
                  		<form:password path="confirmPassword"  maxlength="150" /> 
		             <label id="confirmPasswordMsg" class="errorMsg"></label> 
		      		 <br>	  
		      		 
     	 		    <label id="firstNameLabel"><font color="red">*</font>First Name:</label>                   
                         <form:input path="firstName"  cssClass="firstName"/> 
		            <label id="firstNameMsg" class="errorMsg"></label> 
		      		 <br>    
		      		   
	                <label id="lastNameLabel">Last Name:</label>		                              
                 	     <form:input path="lastName"  maxlength="50"/>
             	    
		      		 <br>
         		  
                    <label id="phoneNoLabel"><font color="red">*</font>Phone No:</label>
                         <form:input path="phoneNo"   maxlength="80" /> 
		            <label id="phoneNoMsg" class="errorMsg"></label> 
		      		 <br>	       
                  
                    <label>Living State:</label>
               
                      <form:select path="state" cssClass="width:300px;">
                 	   <form:option value="" />
              	       <form:options items="${stateMap}" />
                      </form:select> 
		         
            
                   <label id="noneRentalIncomeLabel"><font color="red">*</font>Annual Non Rental Income:</label>
                      <form:input path="noneRentalIncome"  maxlength="250" />  
                   <label id="noneRentalIncomeMsg" class="errorMsg"></label> 
		      	   <br>
		      	  
		          <label>Annual Rental Income:</label>
                      <form:input path="rentalIncome"  maxlength="250" /> 
                      <label id="rentalIncomeMsg" class="errorMsg"></label>  
		      		 <br>
                  <label>Credit Score:</label>
                     <form:input path="creditScore" maxlength="150" cssClass="width:300px;" /> 
                      <label id="creditScoreMsg" class="errorMsg"></label>  
                  <br>
            </fieldset>
          
              		  <div style="padding-left:80px;margin-bottom:20px">
               		    
    			    	<a  href="javascript:signUpSubmitForm()"><img style="border:0px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_signup.png" 
    			    		title="Next Pag" width="80" height="30" />
    			    	</a>   			    	      	   			     
					</div>   
					
					
			</c:if>		
			<c:if test="${param.userAction=='forget'}">	
			 
				 <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Please enter your email </span> </legend>
      		
                     <span>We will send an email for you to reset password !</span>
                	 
        			 <label  id="emailAddressLabel"><font color="red">*</font>Your Email:</label>
                 	  <form:input path="emailAddress"  maxlength="150" /> 
		      			 <label id="emailAddressMsg" class="errorMsg"></label> 
		      		 <br><br>
		      		   <div style="padding-left:80px;margin-bottom:20px">
               		    
    			    	<a  href="javascript:forgetSubmitForm()"><img style="border:0px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_sendemail.png" 
    			    		title="Next Pag" width="140" height="30" />
    			    	</a>
    			    	 
    			    	      	   			     
					</div>   
					</fieldset>
			</c:if>	
					
             </form:form>
         </div>
    
  
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
        
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/userLogin.js"> </script>  
 