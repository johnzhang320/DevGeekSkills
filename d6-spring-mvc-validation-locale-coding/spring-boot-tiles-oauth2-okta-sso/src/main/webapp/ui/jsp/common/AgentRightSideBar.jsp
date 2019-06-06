
<%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	   String agentImage =request.getContextPath()+"/images/agents/denniecha1.png";
	 
	   
	   int line=0;
  	   
 %> 

 <% String contextPath = request.getContextPath()+"/"; %> 
 
     <script type="text/javascript" src="<%=request.getContextPath()%>/ui/static/js/form/regularRightSideBar.js"> </script>  
 	<script type="text/javascript">
   
      $(function() {
        $("table tr:nth-child(even)").addClass("striped");
      });
             
    
   
 	$(document).ready(function() {  
 	
  	   	 getAgentOnDutyInfo("getAgentInfo.html");
    	    
  	}); 
    </script>
 
 
 <div class="rightArea"> 
      <div class="module-title">
         Security
      </div>
  	   <div id="description" class="regularsidebar">
  	       
  	      (1) Loans-agent.com uses Secure SSL Encryption to protect your account information. Please ensure https:// in your browser address box.
  	      <br>(2) Also use Front End Encryption to protect your password, After you type password and leaves the 'Password' text box, encrypt it immediately.(become longer) 
  	      <br>We try to prevent password from local hacked (Troy virus) and internet hacked. 
  	      <br>(3) We store your password with non - reversed algorithm (SHA256), we don't have access to your password.
  	      <br>(4) If you change your gmail password, please use 'Forget Password' to reset password for your agent account.
  	      <br>(5) In order to avoid enter password again when you conduct emailAddress marketing and replying quote, we store non-reversed password. 
  	        
  	    </div>
  	    
  	    
          
   </div>  	 

 