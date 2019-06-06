<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page language="java" import ="com.loan.agent.calculators.vo.UserReviewQuoteVo" %>

   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/tablescroll.css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/tablescroll.js"> </script> 
 <script  type="text/javascript">
/*<![CDATA[*/

jQuery(document).ready(function($)
{
	$('#thetable').tableScroll({height:250});
   //$('#thetable2').tableScroll();
	// $("county").css('text-transform','capitalize');
});

/*]]>*/

 function reviewQuote(index) {
    var ind = parseInt(index);
    
 
 
 	var FrameId = document.getElementById("quoteFrame");
 	var baseUrl = "userProcessQuote.html?chooseIndex="+index;
 	
 	FrameId.setAttribute('src', baseUrl);
 	
 	$("#basicInfo").css('display','block');  
 }

      $(function() {
        $("table tr:nth-child(even)").addClass("striped");
      });
      
     
      
    </script>
 
    <style type="text/css">
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
 

   
  
   <%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	   int line=0;
	   String imageForm =request.getContextPath()+"/images/formImages/"; 
	   String leftCapture =imagePath+ "LeftArea_Compare_Refinance_Loans.png";
	 //  UserReviewQuoteVo currentReview= new UserReviewQuoteVo();
	  // request.setAttribute("currentReview", currentReview);
 %>
 <br>
   <div class="tablebox">
     <div class="tablescollCaption">Quote List for <bean:write name="UserName" /> </div>
   
    <logic:present name="QuoteList" scope="session" >  
         
      <table id="thetable"  border="1" cellpadding="0" cellspacing="0" bgcolor=white>
     		<thead>
  			<tr>
    	
   		 	<td ><label style="width:50px">Status</label></td>
   		 	<td><label  style="width:90px">Apply Date</label></td>  
   		 	<td><label class="title">Agent Name</label></td>
    		<td><label class="title">Loan Type</label></td>
     	 	<td><label class="title">Loan Amount</label></td>     	 	
     		<!-- <td><label class="title">Home Price</label></td>    -->
     		<td><label class="title">Agent Email</label></td>
     		<td><label class="title">Work Phone</label></td>
      	</tr>   
 	   </thead>
 	   	<tbody>
 	  
 	 	<logic:iterate id="review" name="QuoteList" type="UserReviewQuoteVo" scope="session" indexId="rowIndex"> 
   	 	   
   	 	    
   	 	     <%if (line%2==0) {%>
      			<tr class="rowwhite">    
        	 <%} else { %>
        	    <tr class="rowshade"> 
        	   <%}
        	    line++;
        	    
        	    %>
    	 	<td > 
    	 	 <logic:equal name="review" property="process_status" value="pending">
    	 	    <bean:define id="currentReview" name="review"  />
       	 	    <a href="javascript:reviewQuote('<%=rowIndex.toString()%>')"> <img id="editQuote" src="<%=imageForm%>b_edit.png" title="Pending Quote and Click icon to Reply Quote"> </a>
    	     </logic:equal>
    	     <logic:equal name="review" property="process_status" value="replied">
    	  		 <a href="javascript:reviewQuote('<%=rowIndex.toString()%>')"> <img id="QuoteQuote" src="<%=imageForm%>b_chosen.png" title="Replied Quote and Click icon to Review"></a>
     	 		<!--  <a> <img id="deleteQuote" src="<%=imageForm%>b_drop.png" title="You can delete this quote because it was processed"></a> -->
    		</logic:equal>    		
    	 	</td>
    	 	<td style="width:100px"><font size=-1><bean:write name="review" property="modified_date"/></font></td>  
    	 	
    	    <td><font size=-1><bean:write name="review" property="afirst_name"/>&nbsp<bean:write name="review" property="alast_name"/></font></td>                   
      
  			<td><font size=-1><bean:write name="review" property="loan_type"/></font></td>                   
  			<td><font size=-1><bean:write name="review" property="loan_amount"/></font></td>   
  			<logic:equal name="review" property="loan_type" value="Purchase">
        	<!--	<td><font size=-1><bean:write name="review" property="purchase_price"/></font></td>  -->
        	</logic:equal>  
        	<logic:equal name="review" property="loan_type" value="Refinance">
        		<!-- <td><font size=-1><bean:write name="review" property="estimate_home_value"/></font></td> -->
        	</logic:equal>              
      			<td style="width:140px;"><font size=-1><bean:write name="review" property="aemail_address"/></font></td>                   
  			 <td style="width:100px;"><font size=-1><bean:write name="review" property="awork_phone"/></font></td>
   			</tr>                
          </logic:iterate>
        </tbody>
 </table>
  
 </logic:present>
 
 </div>
 
	<div id = "basicInfo" class="tableboxNone" >
	
	<IFRAME id="quoteFrame" style="background-color:white;" WIDTH=780 HEIGHT=1170 frameborder="no" scrolling="yes">
	 
	</IFRAME>
</div>
    





 