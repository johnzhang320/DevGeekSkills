<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
 <%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	   String agentImage =request.getContextPath()+"/images/agents/denniecha1.png";
	 
	   
	   int line=0;
  	   
 %> 

 <% String contextPath = request.getContextPath()+"/"; %> 
 
     <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/regularRightSideBar.js"> </script>  
 	<script type="text/javascript">
   
      $(function() {
        $("table tr:nth-child(even)").addClass("striped");
      });
             
    
   
 	$(document).ready(function() {  
 	
  	   	 getAgentOnDutyInfo("getAgentInfo.html");
    	    
  	}); 
    </script>
 
<!--     <style type="text/css">
      body,td {
        font-size: 10pt;
      }
      table {
        background-color: black;
        border: 1px black solid;
        border-collapse: collapse;
      }
      th {
        border: 1px outset silver;
        background-color: maroon;
        color: white;
      }
      tr {
        background-color: white;
        margin: 1px;
      }
      tr.striped {
        background-color: lightblue;
      }
      td {
        padding: 1px 8px;
      }
      
  
    </style>
    -->
 <div class="rightArea"> 
      <div class="module-title">
         Security
      </div>
  	   <div id="description" class="regularsidebar">
  	       This form uses Secure SSL Encryption to protect the your information. Please find https:// on your browser address box.
  	    </div>
  	    
  	   <div class="module-title">
         Submitting Quote Form to  
      </div>
       <div id="description" class="regularsidebar">
  	  	 <strong><bean:write name="agentProfile" property="firstName"/>&nbsp;<bean:write name="agentProfile" property="lastName"/> </strong> 
         who will review your quote and reply you as soon as possible by sending you an SSL encrypted email. <br>
       </div>     
  
  	    <div class="module-title">
         Kindly Notice  
      </div>
       <div id="description" class="regularsidebar">
  	  	 
         You are kindly noticed that <bean:write name="agentProfile" property="firstName"/>&nbsp;<bean:write name="agentProfile" property="lastName"/>'s license is eligible for:<Strong>
         <bean:write name="agentProfile" property="licenseEligibleState"/></Strong> 
       </div>    
  	          <logic:present name="dutyAgentOnly"> 
  	            <div style="border:1px #ACD6E0 solid;">
     	    	    <img alt="Your Quote will be sent to this agent" src="<%=imagePath%>RightArea_Caption_OnDuty.png">
                </div>
                
                  <div  class="sidebar">
     	    	      <img style="margin-left:50px; width:170px;" src="<%=request.getContextPath()%>/getAgentPicture.html">
            	 </div>
    			   
            	 <div id="agentInfo" style="border:1px #ACD6E0 solid;margin-top:10px;">
                 </div>
               </logic:present>
            
          	      <div class="module-title">
       				  Borrower Menu
                  </div>
                    <div id="description" class="regularsidebar">
       	    	   <ul>
       	    	     <li>  <logic:present name="QuoteMode" >
       	    	          <logic:equal name="QuoteMode" value="Purchase" >
       	    	           <a style="color:blue;" href="<%=contextPath%>refinanceQuote.html" title="4 Quotes and 4 Loan Comparision either for Purchase loan ">Go to Refinance Quote</a><br> <br>
         	    	          </logic:equal>
       	    	           <logic:equal name="QuoteMode" value="Refinance" >
        	    	            <a href="<%=contextPath%>purchaseQuote.html" title="4 Quotes and 4 Loan Comparision either for Purchase loan ">Go to Purchase Quote</a><br><br>
		    	          
       	    	          </logic:equal>
       	    	       </logic:present>
       	    	     </li>
       	    	     <li>   
       	    	        <a href="javascript:popUpFixedName('/affordableDialog.html','600','750');">What home price can I afford?</a><br><br> 
       	    		 </li>
       	    		 <li>  
       	    			<a href="javascript:popUpFixedName('/calculateMonthlyExpenseDialog.html','600','680');">Monthly mortgage Expense</a><br> <br>
       	    		</li>
       	    		 
       	    
       	    		<li>
       	    		    <a href="javascript:popUpFixedName('https://www.annualcreditreport.com/cra/index?move=yes','860','750');">Get My Credit Score</a><br><br>
       	    		</li>
				 </ul>
  			 </div>
  			<!--  <logic:present name="agentProfile" property="description" >
  			    <div class="module-title">Agent Experience</div>
             	<div id="description" class="regularsidebar">
             	 <strong><bean:write name="agentProfile" property="firstName"/>&nbsp;<bean:write name="agentProfile" property="lastName"/> Experience:</strong> <br>
             	  <bean:write name="agentProfile" property="description"/>
             	</div> 
             </logic:present> -->
  			 <logic:present name="agentProfile" property="nicheIntro" >
  			   <div class="module-title">Agent Niche Loan Program</div>
             	<div id="nicheIntro" class="regularsidebar">
            	 <strong><bean:write name="agentProfile" property="firstName"/>&nbsp;<bean:write name="agentProfile" property="lastName"/> Niche Loan Program:</strong> <br>
            
             	  <bean:write name="agentProfile" property="nicheIntro"/>
             	</div> 
             </logic:present>
          
   </div>  	 

 