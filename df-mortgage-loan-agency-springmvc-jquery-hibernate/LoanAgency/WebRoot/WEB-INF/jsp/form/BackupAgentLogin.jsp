<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	
 
<%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	
	   String leftCapture =imagePath+ "LeftArea_Welcome.png";
	   String agentAction =(String) request.getSession().getAttribute("agentAction");
	 
 %> 
	     <!-- Agent Login Action keyword relay to controller onSubmit method -->
         <c:set var="agentAction" value="${param.agentAction}" scope="request" />
         
         <div class="leftArea" > 
          <div id="errorBox" class="errorblock"></div>
        
           <img alt="Get 4 mortgage rate quotes and loan comparsions" src="<%=leftCapture%>">
          
             
           
          	   <form  action="agentLoginProcess.html" method="POST" id="agentLoginForm" name="agentLoginForm">
              
               
                 <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Agent's User Login </span> </legend>
      		
                  
                	 <span>User Name is your email address</span>  <br><br>
        				 <label  id="emailAddressLabel"><font color="red">*</font>Email Address:</label>
                      	 <input id="emailAddress"  name="emailAddress" value="" maxlength="150" /> 
		      		     <label id="emailAddressMsg" class="errorMsg"></label>
		      			  
		      			 <label  id="passwordLabel"><font color="red">*</font>Password:</label>
                  			<input  id="password" type="password" value=""  maxlength="150" /> 
		            	 <label id="passwordMsg" class="errorMsg"></label> 
		      			 <br>	
		      				<a  href="javascript:loginSubmitForm()"><img style="border:0px;padding-left:160px;padding-top:20px;padding-bottom:20px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_login.png" 
    			    		title="Next Pag" width="80" height="30" />
    			    		<br>
    			    	  </a>   
    			    	 <a href="<%=request.getContextPath()%>/agentPasswordForget.html"><span>Forget My Password</span></a> <br><br>
    			    	 
    			    	 <a  href="<%=request.getContextPath()%>/agentSignup.html"><span>Sign Up</span></a> 
    			    	 <br>
    			 </fieldset>  	
    			  </form> 
           	 	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
			  	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/agentLogin.js"> </script>  
           
         </div>
    
  
         
   