&quot;<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page language="java" import ="com.loan.agent.mvc.formbean.AppCheckListForm" %> 
<%@ page import="com.loan.agent.services.Constant" %>	
 <style>
<!--
  .appCheckListTitle {
  	width:510px;
  	height:70px;
  	border : #000000 solid 1px; 
  	overflow:scroll-y;
  }
   .appCheckListNote {
  	width:510px;
  	height:140px;
  	border : #000000 solid 1px; 
  	overflow:scroll-y;
  }
-->
</style>
  <script  type="text/javascript">
  function pullQuote(quoteId) {
      document.getElementById("cProgressSamll").style.display="black";
  	  document.forms[0].actionType.value="actionQuotePull";
  	  document.forms[0].submit(); 
  	  
  }
 
  function deleteQuote(quoteId) {
      document.getElementById("cProgressSamll").style.display="black";
  	  document.forms[0].actionType.value="actionQuoteDelete";
  	//  document.getElementById("pullQuoteId").disabled = true; 
  	//  document.getElementById("deleteQuoteId").disabled = true; 
  	  document.forms[0].submit(); 
    	 
  }
      
   function refreshParent() {      
       var LastAction =   document.forms[0].actionType.value;
      
       if (LastAction=='actionQuotePull') {
       		window.opener.opener.location.reload(false);
      		window.opener.close();
      	}
        if (LastAction='actionQuoteDelete') {
       		window.opener.location.reload(false);      		 
      	}	
       self.close();   	    
       
   }
       
      
  </script>    
	<%
	     
	     String imagePath =request.getContextPath()+"/images/layoutImages/";
	     String actionType =(String) request.getSession().getAttribute(Constant.ACTION_TYPE);   
	     if (null==actionType) {
	         actionType="firstTime";
	     } 
	     Integer quoteId =(Integer) request.getSession().getAttribute(Constant.QUOTE_ID);   
	      
	%>          
    
          <div id="errorBox" class="errorblock"></div> 
           <div id="cProgressSamll" style="display:none;" style="algin:left;" class="progress_dialog">      
			 <img style="margin-left:30%;margin-top:20%;" src="<%=imagePath%>animateCircle.gif" />
			</div> 
			       
             <logic:present name="successMessage" >
        	   <div id="saveSuccess" class="saveSuccess">
              	  <span style="font-weight:bold; color:GREEN; font-size:15px;"><bean:write name="successMessage"/></span>
               </div> 
             </logic:present>
       	   <form:form  id="quoteDataForm" method="POST" name="agentForm" commandName="agentForm" action="quotePullChildForm.html">
       	        <!-- Initial data -->
       	   	     <input type="hidden" name="actionType" value="<%=actionType%>"/>       
                 <input type="hidden" name="quoteId" value="<%=quoteId%>"/>           
                
                 <fieldset > 
                   <legend><span class="AccountCreateTableHeader">
                    
                      Reply Data to Reply Quote or Delete this Quote (No.<span style="color:rgb(157,6,60)"><%=quoteId%></span>)               
    		      			 
                    
                     </span> </legend>
                       
                     	<table style="border:0px;" >    			  
		      			   
  						 		 <tr>  		
  						 		  <%if (!"actionQuoteDelete".equalsIgnoreCase(actionType) || !"actionQuotePull".equalsIgnoreCase(actionType))  {%>				 
  						 		  <td>
  						 		     <input type="button" id="pullQuoteId" value="Pull Quote" class="buttonImage" onClick="javascript:pullQuote('<bean:write name="quoteForm" property="quoteId"/>');">
   								 </td>
  								  <td>
  						 		     <input type="button" id="deleteQuoteId" value="Delete Quote" class="buttonLeft" onClick="javascript:deleteQuote('<bean:write name="quoteForm" property="quoteId"/>');">
   								 </td>  
   								<%} %>
  								   <td>
               		 				 <input type="button"  value="Close" class="buttonLeft" onClick="javascript: refreshParent();">
  								 </td>
  								 </tr>
  							</table>
  					  		
                     <!-- Include LoanQuoteSuccess.jsp -->
                     <logic:present name="quoteForm" scope="session">
                        <logic:equal name="quoteForm" property="loanType" value="Purchase" >
      		         	<jsp:include page="PurchaseQuoteSuccess.jsp" flush="true" />
      		         	</logic:equal>
      		         	<logic:equal name="quoteForm" property="loanType" value="Refinance" >
      		         	<jsp:include page="RefinanceQuoteSuccess.jsp" flush="true" />
      		         	</logic:equal>
		      		 </logic:present>	
    			    	 
    			    			<table style="border:0px;" >    			  
		      			   
  						 		 <tr>  		
  						 		  <%if (!"actionQuoteDelete".equalsIgnoreCase(actionType))  {%>				 
  						 		  <td>
  						 		     <input type="button" id="pullQuoteId" value="Pull Quote" class="buttonImage" onClick="javascript:pullQuote('<bean:write name="quoteForm" property="quoteId"/>');">
   								 </td>
  								  <td>
  						 		     <input type="button" id="deleteQuoteId" value="Delete Quote" class="buttonLeft" onClick="javascript:deleteQuote('<bean:write name="quoteForm" property="quoteId"/>');">
   								 </td>  
   								<%} %>
  								   <td>
               		 				 <input type="button"  value="Close" class="buttonLeft" onClick="javascript: refreshParent();">
  								 </td>
  								 </tr>
  							</table>
		      			 
		      			   
    			 </fieldset>  	
    			  </form:form>   
 		          
           	 	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
			 
  