<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page language="java" import ="com.loan.agent.calculators.vo.AgentReviewQuoteVo" %>

   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/tablescroll.css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/tablescroll.js"> </script> 
 <script  type="text/javascript">
/*<![CDATA[*/

jQuery(document).ready(function($)
{
	$('#thetable').tableScroll({height:350});
});

/*]]>*/

 function reviewQuote(index) {
    var ind = parseInt(index);
     
 	var FrameId = document.getElementById("quoteFrame");
 	var baseUrl = "agentProcessQuote.html?chooseIndex="+index;
 	
 	FrameId.setAttribute('src', baseUrl);
 	
 	$("#basicInfo").css('display','block');  
 }
 function quoteReplyDelete(actionType,quoteIdStr) {     
	    
     javascript:popUpFixedName('/quoteReviewForm.html?actionType='+actionType+'&paramQuoteId='+quoteIdStr,'600','520');
 }
       
 </script>
 
   <%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	   int line=0;
	   String imageForm =request.getContextPath()+"/images/formImages/"; 
	   String leftCapture =imagePath+ "LeftArea_Compare_Refinance_Loans.png";
	  
 %>
 
   
   <div class="tableScrollbox">
     
   <label  class="captain">Borrower Inquired Quotes for <bean:write name="AgentName" /> </label>
        

    <logic:present name="QuoteList" scope="session" >  
         
      <table class="tableHeight" id="thetable" width="98%" border="1" cellpadding="0" cellspacing="0" >
     		<thead>
  			<tr>
    	
   		 	 	 
   		 	<td><font class="tstitle">Borrower(Name)(Email)(Phone)(inquire date)(LoanType)(Loan Amount)(Home Price)</font></td>
    	    <td><label class="title"> <font class="tstitle">Reply</font></label>	</td>     	
   			<td><label class="title"> <font class="tstitle">Delete</font></label></td>     
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
  			   <input type="button" name="reply" value="Reply" class="tinybutton" onclick="javascript:quoteReplyDelete('appCheckListEdit','<bean:write name="review" property="quoteId"/>');">
 			</td><td> 
 			    <input type="button" name="delete" value="Delete" class="tinybutton" onclick="javascript:quoteReplyDelete('appCheckListDelete','<bean:write name="review" property="quoteId"/>');">
 			</td>  
      
  		 
          </logic:iterate>
        </tbody>
 </table>
  
 </logic:present>
 
 
 </div>
   
	<div id = "basicInfo" class="tableboxNone" >
	
	<IFRAME id="quoteFrame" style="background-color:white;" WIDTH=820 HEIGHT=800 frameborder="no" scrolling="yes">
	 
	</IFRAME>
</div>
    
 




 