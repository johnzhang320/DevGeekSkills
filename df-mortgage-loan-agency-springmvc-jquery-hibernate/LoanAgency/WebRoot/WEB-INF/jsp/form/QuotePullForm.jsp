<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" import ="com.loan.agent.services.Constant" %>
<%@ page language="java" import ="com.loan.agent.calculators.vo.AgentReviewQuoteVo" %>

<%@ page language="java" import ="java.util.List" %>

   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/tablescroll.css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/tablescroll.js"> </script> 
 <%
   int line=0;
    String imagePath =request.getContextPath()+"/images/layoutImages/";	  
   
  %> 
  
 <script  type="text/javascript">
/*<![CDATA[*/

jQuery(document).ready(function($)
{
	$('#thetable6').tableScroll({height:500});
  
});

/*]]>*/

 
 
      
   function submitAgentData(selectedId) {
      document.forms[0].actionType.value="actionCurrentCheck";
      var checked = document.getElementById(selectedId).checked;
      
      if (checked) {
      	  document.forms[0].currentCheckStatus.value="checked";
      	  
      } else {
      	 document.forms[0].currentCheckStatus.value="unchecked";
      }
      document.forms[0].currentCheckStatusId.value=selectedId;
      document.forms[0].submit();
   }
       
    function SelectAllAgentData() {
       document.forms[0].actionType.value="actionSelectAllCheck";
       
      var selectAll = document.getElementById("selectAll").checked;
      
      var selectAllStatus = document.getElementById("selectAllStatus").checked
      
      if (selectAll) {
      	  document.forms[0].selectAllStatus.value="checked";
      	  
      } else {
      	 document.forms[0].selectAllStatus.value="unchecked";
      }
     
      document.forms[0].submit();
   }  
   function pullSelectedData() {
       document.forms[0].actionType.value="actionPullSelectedData";
       document.getElementById("cProgressSamll").style.display="block";
       document.forms[0].submit();
   }
   
  function refreshParentCloseSelf() {
  	  window.opener.location.reload(false);
  	  self.close();
  }
  </script>
 
  <div id="errorBox" class="errorblock"></div> 
           <div id="cProgressSamll" style="display:none;" style="algin:center;" class="progress_dialog">      
			 <img style="margin-left:30%;margin-top:20%;" src="<%=imagePath%>animateCircle.gif" />
			</div> 
			       
             <logic:present name="successMessage" >
        	   <div id="saveSuccess" class="saveSuccess">
              	  <span style="font-weight:bold; color:GREEN; font-size:15px;"><bean:write name="successMessage"/></span>
               </div> 
             </logic:present> 
 

   <div class="tableScrollbox" style="width:730px;">   
   
   	  	
   	  
    <form:form  id="quotePullform" method="POST" name="QuotePullForm" commandName="agentForm" action="quotePullForm.html">
   
    <input type="hidden" name="currentCheckStatus"  >
    <input type="hidden" name="currentCheckStatusId"  >
    <input type="hidden" name="selectAllStatus" >
    <input type="hidden" name="actionType">
    
     <label  class="captain">Select the Quote that Borrower Inquired for <bean:write name="loginAgentName" /> </label>
  <table style="border:0px;">
    <tr><td>
 	 <input type="button" name="close" value="Close" class="buttonImage" onclick="javascript:refreshParentCloseSelf();">
     </td></tr>
     </table>       

    <logic:present name="QuoteList" scope="session" >      

       <table id="thetable6" width="700" border="0" cellpadding="30"  >
     		<thead>
  			<tr>
    	
         	<td> 
   		 	    No.
   			</td>    	  		 
   		 	<td ><font class="tstitle">(Borrower Name)(Email)(Phone)(Quote Date)(LoanType)(Loan Amount)(Home Value)</font>
   		 	</td>
   		 	<td><font class="tstitle">Open</font></td>
   		 
       	</tr>   
 	   </thead>
 	   	<tbody>
 	   	
 	  	<logic:iterate id="review" name="QuoteList" type="AgentReviewQuoteVo" scope="session" indexId="rowIndex"> 
      
 	      <%if (line%2==0) {%>
      			<tr class="rowwhite">    
        	 <%} else { %>
        	    <tr class="rowshade"> 
        	   <%}
        	    line++;        	    
        	    %>
     
 			<td><bean:write name="review" property="quoteId"/></td>
    	    <td>
    	     <logic:equal name="review" property="process_status" value="pending">
    	      <font style="font-size:12px;color:RGB(50,50,50);font-weight:bold;">
    	    </logic:equal>
    	     <logic:equal name="review" property="process_status" value="replied">
    	      <font style="font-size:12px;color:grey;">
    	    </logic:equal>
    	      (<bean:write name="review" property="first_name"/>&nbsp<bean:write name="review" property="last_name"/>)
    	      (<bean:write name="review" property="email_address"/>)    	    
    	      (<bean:write name="review" property="phone_no"/>)
    	      (<bean:write name="review" property="modified_date"/>)
    	      (<bean:write name="review" property="loan_type"/>)
    	      (<bean:write name="review" property="loan_amount"/>)
    	      (
    	     	 <logic:equal name="review" property="loan_type" value="Purchase">
        			<bean:write name="review" property="purchase_price"/> 
        		</logic:equal>  
        		<logic:equal name="review" property="loan_type" value="Refinance">
        			<bean:write name="review" property="estimate_home_value"/> 
        		</logic:equal> 
    	      )
      	    </td>  
      	    <td>
      	    
 	 			   
 			   <input type="button" class="tinybutton" value="Open" onClick="javascript:popUpBase('/quotePullChildForm.html?quoteId='+'<bean:write name="review" property="quoteId"/>','670','700');">
    	    </td> 
    	                      
   			</tr>
    		 
          </logic:iterate>
         
        </tbody>
 </table>
    
 </logic:present>
 </form:form> 
    <table style="border:0px;">
    <tr><td>
 	 <input type="button" name="close" value="Close" class="buttonImage" onclick="javascript:refreshParentCloseSelf();">
     </td></tr>
     </table>
  </div>
    
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
         




 
